<template>
  <div class="access-request-container">
    <h1>권한 신청</h1>
    <form @submit.prevent="submitAccessRequest">
      <div class="checkbox-group">
        <label v-for="(access, index) in accessRights" :key="index" class="checkbox-label">
          <input type="checkbox" v-model="selectedAccess" :value="access.id">
          {{ access.name }}
        </label>
      </div>
      <button type="submit" class="submit-button">신청</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const accessRights = [
  { id: 1, name: '자유게시판 읽기' },
  { id: 2, name: '자유게시판 쓰기' },
  { id: 3, name: '영업기회 읽기' },
  { id: 4, name: '영업기회 쓰기' },
  { id: 5, name: '영업기회 참고사항 쓰기' },
  { id: 6, name: '거래처 읽기' },
  { id: 7, name: '거래처 쓰기' },
  { id: 8, name: '거래처 참고사항 쓰기' },
  { id: 9, name: '견적서 읽기' },
  { id: 10, name: '견적서 쓰기' },
  { id: 11, name: '견적서 참고사항 쓰기' },
  { id: 12, name: '계약서 읽기' },
  { id: 13, name: '계약서 쓰기' },
  { id: 14, name: '수주 읽기' },
  { id: 15, name: '수주 쓰기' },
  { id: 16, name: '수주 참고사항 쓰기' },
  { id: 17, name: '수금 읽기' },
  { id: 18, name: '수금 쓰기' },
  { id: 19, name: '결재 읽기' },
  { id: 20, name: '결재 처리' },
  { id: 21, name: '품목 읽기' }
];

const selectedAccess = ref([]);

const submitAccessRequest = async () => {
  try {
    const selectedAccessIds = selectedAccess.value.map(access => ({
      accessId: access
    }));
    const requestAccess = {
      accessRight: selectedAccessIds,
      employee: {
        employeeId: localStorage.getItem('userId')
      }
    };
    console.log('Request Data:', requestAccess);
    await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/access/access_request', requestAccess);
    alert('권한 신청이 완료되었습니다.');
    selectedAccess.value = [];
  } catch (error) {
    console.error('권한 신청 중 에러 발생:', error);
  }
};
</script>

<style scoped>

.access-request-container {
  font-family: Arial, sans-serif;
  padding: 40px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 800px;
  margin: 0 auto;
  flex-direction: column;
}

h2 {
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

form {
  display: flex;
  flex-direction: column;
}

.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}

.checkbox-label {
  flex: 0 0 48%;
  margin-bottom: 15px;
  display: flex;
  align-items: center;
}

input[type="checkbox"] {
  margin-right: 10px;
}

.submit-button {
  background-color: #007bff;
  color: #fff;
  padding: 15px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-top: 20px;
  width: 100%;
}

.submit-button:hover {
  background-color: #0056b3;
}

.submit-button:active {
  background-color: #004080;
}
</style>