<template>
  <div class="employee-registration">
    <h1>사원 등록</h1>
    <form @submit.prevent="registerEmployee">
      <div class="form-group">
        <label for="employeeCode">사원 번호:</label>
        <input type="text" v-model="employee.employeeCode" id="employeeCode" required>
      </div>
      <div class="form-group">
        <label for="employeeName">이름:</label>
        <input type="text" v-model="employee.employeeName" id="employeeName" required>
      </div>
      <div class="form-group">
        <label for="employeePassword">비밀번호:</label>
        <input type="password" v-model="employee.employeePassword" id="employeePassword" required>
      </div>
      <div class="form-group">
        <label for="employeeEmail">이메일:</label>
        <input type="email" v-model="employee.employeeEmail" id="employeeEmail" required placeholder="example@erpc.com">
      </div>
      <div class="form-group">
        <label for="employeeHp">전화번호:</label>
        <input type="text" v-model="employee.employeeHp" id="employeeHp" required placeholder="010-1234-1234">
      </div>
      <div class="form-group">
        <label for="employeeNumber">내선번호:</label>
        <input type="text" v-model="employee.employeeNumber" id="employeeNumber" required>
      </div>
      <div class="form-group">
        <label for="employmentDate">입사일:</label>
        <input type="date" v-model="employee.employmentDate" id="employmentDate" required>
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
      <button type="submit">등록</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      employee: {
        employeeCode: '',
        employeeName: '',
        employeePassword: '',
        employeeEmail: '',
        employeeHp: '',
        employeeNumber: '',
        employmentDate: '',
        employeeRank: { employeeRankId: '' },
        teamCode: { teamCodeId: '' }
      },
    };
  },
  methods: {
    async registerEmployee() {
      try {
        await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/regist', this.employee);
        this.$router.push('/employees');
      } catch (error) {
        console.error('Error registering employee:', error);
      }
    },
  },
};
</script>

<style scoped>
.employee-registration {
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
  width: 100%;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}

button[type="submit"]:active {
  background-color: #004080;
}

@media (max-width: 600px) {
  .form-group {
    flex: 0 0 100%;
  }
}
</style>