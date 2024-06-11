<template>
  <div class="contract-list-content333">
    <div class="contract-list">
      <h1>영업기회 목록</h1>
    </div>

    <!-- 검색 및 필터링 섹션 -->
    <div class="contract-list-search">
      <!-- 검색 기준 선택 드롭다운 -->
      <div class="contract-dropdown">
        <button class="contract-dropdown-btn">{{ searchBy }} ▼</button>
        <div class="contract-dropdown-content">
          <a href="#" @click.prevent="setSearchBy('거래처명')">거래처명</a>
          <a href="#" @click.prevent="setSearchBy('거래처 담당자')">거래처 담당자</a>
        </div>
      </div>
      
      <!-- 검색어 입력 -->
      <input v-model="searchKeyword" type="text" class="contract-search-input" placeholder="검색어를 입력하세요">
      
      <!-- 상태 필터링 드롭다운 -->
      <div class="contract-dropdown">
        <button class="contract-dropdown-btn">{{ statusFilter }} ▼</button>
        <div class="contract-dropdown-content">
          <a href="#" @click.prevent="setStatusFilter('전체')">전체</a>
          <a href="#" @click.prevent="setStatusFilter('등록')">등록</a>
          <a href="#" @click.prevent="setStatusFilter('진행중')">진행중</a>
          <a href="#" @click.prevent="setStatusFilter('불발')">불발</a>
          <a href="#" @click.prevent="setStatusFilter('성사')">성사</a>
        </div>
      </div>

      <!-- 검색 버튼 -->
      <button class="contract-search-btn" @click="filterApprovals">검색</button>
    </div>

    <!-- 영업기회 목록 테이블 -->
    <div class="contract-list-box">
      <table class="contract-table88">
        <thead>
          <tr class="header1">
            <th>번호</th>
            <th class="wide-column">{{ searchBy }}</th>
            <th class="wide-column">영업기회 작성일</th>
            <th>상태</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(approval, index) in paginatedApprovals" :key="approval.salesOppId" @click="goToSalesOppContents(approval.salesOppId)">
            <td>{{ totalApprovals - ((currentPage - 1) * pageSize + index) }}</td>
            <td class="wide-column">{{ searchBy === '거래처명' ? approval.oppAccountName : approval.oppAccountPic }}</td>
            <td class="wide-column">{{ approval.oppDate }}</td>
            <td>{{ approval.salesOppStatus.salesOppStatus }}</td>
          </tr>
          <tr v-if="filteredApprovals.length === 0">
            <td colspan="4" class="no-result">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 페이징 -->
    <div class="pagination">
      <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
      <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
      <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();

const approvals = ref([]);
const filteredApprovals = ref([]);
const searchKeyword = ref('');
const searchBy = ref('거래처명');
const statusFilter = ref('전체');
const currentPage = ref(1);
const pageSize = ref(10);

// 검색 기준 설정 함수
function setSearchBy(type) {
  searchBy.value = type;
}

// 상태 필터 설정 함수
function setStatusFilter(status) {
  statusFilter.value = status;
}

// 검색 및 상태 필터링 함수
function filterApprovals() {
  const query = searchKeyword.value.trim().toLowerCase();
  const filterStatus = statusFilter.value;

  // 상태 비교를 위한 상태 값 매핑
  const statusMap = {
    '전체': '전체',
    '등록': '등록',
    '진행중': '진행중',
    '불발': '불발',
    '성사': '성사'
  };

  filteredApprovals.value = approvals.value.filter(approval => {
    // 검색어 필터링
    const target = searchBy.value === '거래처명' ? approval.oppAccountName.toLowerCase() : approval.oppAccountPic.toLowerCase();
    const matchesQuery = target.includes(query);

    // 상태 필터링
    const matchesStatus = filterStatus === '전체' || approval.salesOppStatus.salesOppStatus === statusMap[filterStatus];

    return matchesQuery && matchesStatus;
  });
  currentPage.value = 1; // 검색할 때 페이지를 1페이지로 초기화
}

const paginatedApprovals = computed(() => {
  const start = (currentPage.value - 1) * pageSize.value;
  const end = start + pageSize.value;
  return filteredApprovals.value.slice(start, end);
});

const totalPages = computed(() => {
  return Math.ceil(filteredApprovals.value.length / pageSize.value);
});

const totalApprovals = computed(() => {
  return filteredApprovals.value.length;
});

function changePage(page) {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
}

// 상세 보기로 이동하는 함수
function goToSalesOppContents(salesOppId) {
  router.push({ path: `/salesopp/${salesOppId}` });
}

// 페이지 로드 시 영업기회 데이터 가져오기
onMounted(async () => {
  try {
    const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales_opportunity');
    approvals.value = response.data;
    filteredApprovals.value = approvals.value;
  } catch (error) {
    console.error('영업기회 목록을 불러오는 중 오류가 발생했습니다:', error);
  }
});
</script>

<style scoped>
.contract-list-content333 {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.contract-list {
  text-align: center;
}

.contract-list-search {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 10px;
  margin-bottom: 20px;
}

.contract-dropdown {
  position: relative;
  display: inline-block;
}

.contract-dropdown-btn {
  width: 150px;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 8px 12px;
  font-size: 14px;
  cursor: pointer;
  outline: none;
  color: black;
}

.contract-dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  border: 1px solid #ccc;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 5px;
  width: 100%;
  font-size: 14px;
}

.contract-dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  border-bottom: 1px solid #ccc;
}

.contract-dropdown-content a:hover {
  background-color: #d5e6ff;
}

.contract-dropdown:hover .contract-dropdown-content {
  display: block;
}

.contract-search-input {
  height: 40px;
  padding: 10px;
  border: 2px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
  font-size: 14px;
  background-color: #e5f0ff;
  color: #0c2092;
  outline: none;
  width: 250px;
}

.contract-search-btn {
  height: 40px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  background-color: #0c2092;
  color: white;
  font-size: 14px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.contract-search-btn:hover {
  background-color: #007bff;
}

.contract-list-box {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 15px;
  border-radius: 10px;
  box-sizing: border-box;
  background-color: white;
  max-width: 1400px;
  overflow-x: auto;
}

.contract-table88 {
  width: 100%;
  border-collapse: collapse;
  margin: 20px 0;
  font-size: 16px;
}

.contract-table88 th,
.contract-table88 td {
  text-align: center;
  width: 160px; /* 너비 조절 */
  border: 1px solid #ccc;
  padding: 8px;
  font-family: GmarketSansMedium;
}

.contract-table88 th.wide-column,
.contract-table88 td.wide-column {
  width: 250px; /* 넓은 열 너비 설정 */
}

.contract-table88 th {
  background-color: #0c2092;
  color: white;
  font-size: 18px;
  padding: 10px;
}

.contract-table88 tr:hover {
  background-color: #d5e6ff;
  cursor: pointer;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
}

.pagination button {
  padding: 8px 12px;
  margin: 0 5px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 12px;
}

.pagination button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.pagination .active {
  background-color: #0C2092 !important;
  color: white;
}
</style>
