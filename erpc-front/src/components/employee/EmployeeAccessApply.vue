<template>
  <!-- 권한 관리 섹션 -->
  <div class="section access-management">
    <!-- 사원 권한 조회 및 추가 권한 등록 섹션 -->
    <div class="access-panel">
      <h2>권한 관리</h2>
      <form @submit.prevent="submitAddAccess">
        <div class="form-group">
          <label for="employeeCode">사원 코드:</label>
          <input 
            v-model="addAccess.employee.employeeCode" 
            id="employeeCode" 
            placeholder="사원 코드를 입력하세요" 
            @input="updateEmployeeCode"
            @keydown.enter.prevent="handleEnterKey"
            required>
        </div>
        <div class="form-group">
          <button @click="getEmployeesAccess" type="button">조회</button>
        </div>
        <div class="form-group access-rights">
          <label for="accessRights">권한 목록:</label>
          <div v-for="(name, id) in accessRightsMap" :key="id" class="access-checkbox">
            <!-- 사원의 현재 권한을 확인하여 체크박스를 체크 상태로 설정 -->
            <input type="checkbox" :value="id" v-model="selectedAccessRights">
            {{ name }}
          </div>
        </div>
        <button type="submit">등록</button>
      </form>
    </div>

    <!-- 모든 권한 신청 조회 섹션 -->
    <div class="section all-requests">
      <h2>모든 권한 신청 조회</h2>
      <table v-if="showAllAccessRequests" class="request-table">
        <thead>
          <tr>
            <th>이름</th>
            <th>사번</th>
            <th>부서</th>
            <th>직급</th>
            <th>권한</th>
            <th>처리</th> <!-- 삭제 버튼 열 추가 -->
          </tr>
        </thead>
        <tbody>
          <tr v-for="(request, index) in paginatedRequests" :key="index">
            <td>{{ request.employee.employeeName }}</td>
            <td>{{ request.employee.employeeCode }}</td>
            <td>{{ request.employee.teamCode.teamCode }}</td>
            <td>{{ request.employee.employeeRank.employeeRank }}</td>
            <td>{{ request.accessRight.accessRight }}</td>
            <td>
              <button @click="deleteRequest(request.accessRequestId)">삭제</button> 
            </td>
          </tr>
        </tbody>
      </table>
      <div class="pagination">
        <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
        <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
        <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';

// 상태 변수
const employeeCode = ref('');
const employeeAccess = ref([]);
const employeeCheckedAccessRights = ref([]);
const selectedAccessRights = ref([]);
const allAccessRights = ref([]);
const allAccessRequests = ref([]);
const showAllAccessRequests = ref(false);
const currentPage = ref(1);
const itemsPerPage = ref(10);

const addAccess = ref({
  employee: {
    employeeCode: ''
  },
  accessRight: []
});

const accessRightsMap = {
  1: "자유게시판 읽기",
  2: "자유게시판 쓰기",
  3: "영업기회 읽기",
  4: "영업기회 쓰기",
  5: "영업기회 참고사항 쓰기",
  6: "거래처 읽기",
  7: "거래처 쓰기",
  8: "거래처 참고사항 쓰기",
  9: "견적서 읽기",
  10: "견적서 쓰기",
  11: "견적서 참고사항 쓰기",
  12: "계약서 읽기",
  13: "계약서 쓰기",
  14: "수주 읽기",
  15: "수주 쓰기",
  16: "수주 참고사항 쓰기",
  17: "수금 읽기",
  18: "수금 쓰기",
  19: "결재 읽기",
  20: "결재 처리",
  21: "품목 읽기",
  22: "관리자"
};

// 사원의 보유 권한 조회 함수
const getEmployeesAccess = async () => {
  try {
    console.log('getEmployeesAccess 호출됨'); // 디버깅용 로그
    const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/access/find_access?employeeCode=${addAccess.value.employee.employeeCode}`);
    employeeAccess.value = response.data;
    // 현재 사원이 보유한 권한 ID를 배열로 저장
    employeeCheckedAccessRights.value = employeeAccess.value.map(access => access.accessRight.accessId);

    // 현재 보유한 권한을 선택된 권한으로 설정
    selectedAccessRights.value = [...employeeCheckedAccessRights.value];
  } catch (error) {
    console.error('보유 권한 조회 중 에러 발생:', error);
  }
};

// 엔터 키 이벤트 핸들러 함수
const handleEnterKey = () => {
  getEmployeesAccess(); // 조회 기능 호출
};

// 추가 권한 등록 함수
const submitAddAccess = async () => {
  try {
    addAccess.value.accessRight = selectedAccessRights.value.map(accessId => ({ accessId }));

    await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/access/add_access', addAccess.value);
    alert('추가 권한이 성공적으로 등록되었습니다.');

    // 초기화
    addAccess.value.employee.employeeCode = '';
    selectedAccessRights.value = [];
    employeeAccess.value = [];
    employeeCheckedAccessRights.value = [];
  } catch (error) {
    console.error('추가 권한 등록 중 에러 발생:', error);
  }
};

// 페이지 로드 시 모든 권한 신청 조회
const getAllAccessRequests = async () => {
  try {
    const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/access/list');
    allAccessRequests.value = response.data;
    showAllAccessRequests.value = true;
  } catch (error) {
    console.error('모든 권한 신청 조회 중 에러 발생:', error);
  }
};

// 권한 삭제 요청 함수
const deleteRequest = async (accessRequestId) => {
  try {
    await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/access/process?accessRequestId=${accessRequestId}`);
    alert('권한 요청이 삭제되었습니다.');
    await getAllAccessRequests();
  } catch (error) {
    console.error('권한 삭제 중 에러 발생:', error);
  }
};

// 페이징 처리를 위한 함수
const paginatedRequests = computed(() => {
  const start = (currentPage.value - 1) * itemsPerPage.value;
  const end = start + itemsPerPage.value;
  return allAccessRequests.value.slice(start, end);
});

const totalPages = computed(() => {
  return Math.ceil(allAccessRequests.value.length / itemsPerPage.value);
});

function changePage(page) {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
}

// 사원 코드 업데이트 함수
const updateEmployeeCode = (event) => {
  addAccess.value.employee.employeeCode = event.target.value.toUpperCase();
  employeeCode.value = addAccess.value.employee.employeeCode;
};

onMounted(() => {
  getAllAccessRequests();
});
</script>

<style>
.access-request-container {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  margin-top: 60px;
}

.section {
  flex: 1 1 300px;
  margin-bottom: 20px;
  padding: 20px;
  border-radius: 10px;
  background-color: #e0f0ff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

@media (min-width: 1200px) {
  .section {
    flex: 1;
  }
}

h2 {
  color: #007bff;
  margin-bottom: 15px;
}

.form-group {
  margin-bottom: 15px;
}

label {
  font-weight: bold;
}

button[type="submit"] {
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 8px 16px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}

button[type="button"] {
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 8px 16px;
  cursor: pointer;
  margin-bottom: 10px;
}

button[type="button"]:hover {
  background-color: #218838;
}

.access-checkbox input[type="checkbox"] {
  margin-right: 5px;
}

.request-table {
  width: 100%;
  border-collapse: collapse;
}

.request-table th,
.request-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.request-table th {
  background-color: #f2f2f2;
  color: #333;
}

.request-table tr:nth-child(even) {
  background-color: #f2f2f2;
}
.request-table button {
  background-color: #5170de;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}

.request-table button:hover {
  background-color: #5170de;
}
</style>