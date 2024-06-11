<template>
  <div class="employee-edit">
    <h1>사원 정보 수정</h1>
    <form @submit.prevent="modifyEmployee">
      <div class="form-group">
        <label for="employeeCode">사원 번호:</label>
        <input type="text" v-model="employee.employeeCode">
      </div>
      <div class="form-group">
        <label for="employeeName">이름:</label>
        <input type="text" v-model="employee.employeeName">
      </div>
      <div class="form-group">
        <label for="employeePassword">비밀번호:</label>
        <input type="password" v-model="newPassword">
      </div>
      <div class="form-group">
        <label for="employeeEmail">이메일:</label>
        <input type="email" v-model="employee.employeeEmail">
      </div>
      <div class="form-group">
        <label for="employeeHp">전화번호:</label>  
        <input type="text" v-model="employee.employeeHp">
      </div>
      <div class="form-group">
        <label for="employeeNumber">내선번호:</label>
        <input type="text" v-model="employee.employeeNumber">
      </div>
      <div class="form-group">
        <label for="employmentDate">입사일:</label>
        <input type="date" v-model="employee.employmentDate">
      </div>
      <div class="form-group">
        <label for="resignationDate">퇴사일:</label>
        <input type="date" v-model="employee.resignationDate">
      </div>
      <div class="form-group">
        <label for="employeeRank">직급:</label>
        <select v-model="employee.employeeRank.employeeRankId" id="employeeRank" required>
          <option value="1">사원</option>
          <option value="2">대리</option>
          <option value="3">과장</option>
          <option value="4">팀장</option>
          <option value="5">관리자</option>
        </select>
      </div>
      <div class="form-group">
        <label for="teamCode">부서:</label>
        <select v-model="employee.teamCode.teamCodeId" id="teamCode" required>
          <option value="1">영업 1팀</option>
          <option value="2">영업 2팀</option>
          <option value="3">영업 3팀</option>
          <option value="4">영업 4팀</option>
          <option value="5">영업 5팀</option>
          <option value="6">관리자</option>
        </select>
      </div>
      <button type="submit">수정</button>
      <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
    </form>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const employee = ref({
  employeeCode: '',
  employeeName: '',
  employeePassword: '',
  employeeEmail: '',
  employeeHp: '',
  employeeNumber: '',
  employmentDate: '',
  resignationDate: '',
  employeeRank: { employeeRankId: '' },
  teamCode: { teamCodeId: '' }
});

const newPassword = ref('');  // 새로운 비밀번호를 위한 ref
const errorMessage = ref('');

const fetchEmployeeDetails = async () => {
  try {
    const employeeId = route.query.employeeId; // 변경
    if (!employeeId) {
      throw new Error('Employee ID not found in route parameters');
    }
    const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${employeeId}`);
    employee.value = response.data;
    employee.value.employeePassword = ''; // 비밀번호는 공란으로 설정
  } catch (error) {
    console.error('Error fetching employee details:', error);
    errorMessage.value = '사원 정보를 불러오는 중 오류가 발생했습니다.';
  }
};

const modifyEmployee = async () => {
  try {
    const employeeId = route.query.employeeId; // 변경
    if (!employeeId) {
      throw new Error('Employee ID not found in route parameters');
    }

    // 새로운 비밀번호가 입력된 경우 employee 객체에 설정
    if (newPassword.value) {
      employee.value.employeePassword = newPassword.value;
    }

    const requestURL = `http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/modify?employeeId=${employeeId}`;
    console.log('Request URL:', requestURL);
    await axios.patch(requestURL, employee.value);
    router.push('/employees');
  } catch (error) {
    console.error('Error modifying employee:', error);
    errorMessage.value = '사원 정보 수정 중 오류가 발생했습니다.';
  }
};

fetchEmployeeDetails();
</script>
  
  <style scoped>
  .employee-edit {
    font-family: Arial, sans-serif;
    padding: 20px;
    background-color: #f9f9f9;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: 0 auto;
  }
  
  h1 {
    color: #333;
    margin-bottom: 20px;
    text-align: center;
  }
  
  form {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .form-group {
    flex: 0 0 48%;
    margin-bottom: 15px;
  }
  
  label {
    color: #555;
    margin-bottom: 5px;
    display: block;
    font-weight: bold;
  }
  
  input[type="text"],
  input[type="password"],
  input[type="email"],
  input[type="date"],
  select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
  }
  
  input[type="text"]:focus,
  input[type="password"]:focus,
  input[type="email"]:focus,
  input[type="date"]:focus,
  select:focus {
    border-color: #007bff;
    outline: none;
  }
  
  button[type="submit"] {
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 10px;
  }
  
  button[type="submit"]:hover {
    background-color: #0056b3;
  }
  
  button[type="submit"]:active {
    background-color: #004080;
  }
  
  .error-message {
    color: red;
    margin-top: 10px;
  }
  
  @media (max-width: 600px) {
    .form-group {
      flex: 0 0 100%;
    }
  }
  </style>