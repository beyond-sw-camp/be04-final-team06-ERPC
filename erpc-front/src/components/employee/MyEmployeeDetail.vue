<template>
  <div>
    <h1>사원 상세 정보</h1>
    <p>사원 코드: {{ employee.employeeCode }}</p>
    <p>이름: {{ employee.employeeName }}</p>
    <p>이메일: {{ employee.employeeEmail }}</p>
    <p>전화번호: {{ employee.employeeHp }}</p>
    <p>사원 번호: {{ employee.employeeNumber }}</p>
    <p>입사일: {{ employee.employmentDate }}</p>
    <p>직급: {{ mapEmployeeRank(employee.employeeRank.employeeRankId) }}</p>
    <p>팀: {{ mapTeamCode(employee.teamCode.teamCodeId) }}</p>

    <div class="button-container">
      <router-link :to="{ path: '/change-password', query: { employeeId: employee.employeeId }}" class="edit-button">비밀번호 변경</router-link>
      <router-link :to="{ path: '/employees/app'}" class="edit-button">권한 신청</router-link>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees', 
});

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
  created() {
    this.fetchEmployeeDetails(); // 데이터 가져오기만 처리
  },
  methods: {
    async fetchEmployeeDetails() {
      try {
        const employeeId = this.$route.params.employeeId; // 변경
        if (!employeeId) {
          throw new Error('Employee ID not found in route parameters');
        }
        const response = await axiosInstance.get(`/${employeeId}`);
        this.employee = response.data;
      } catch (error) {
        console.error('Error fetching employee details:', error);
      }
    },
    mapEmployeeRank(employeeRankId) {
      switch (parseInt(employeeRankId)) {
        case 1:
          return '사원';
        case 2:
          return '대리';
        case 3:
          return '과장';
        case 4:
          return '팀장';
        case 5:
          return '관리자';
        default:
          return '직급 미정';
      }
    },
    mapTeamCode(teamCodeId) {
      switch (parseInt(teamCodeId)) {
        case 1:
          return '영업 1팀';
        case 2:
          return '영업 2팀';
        case 3:
          return '영업 3팀';
        case 4:
          return '영업 4팀';
        case 5:
          return '영업 5팀';
        case 6:
          return '관리자';
        default:
          return '팀 미정';
      }
    }
  }
};
</script>
<style scoped>
div {
  font-family: Arial, sans-serif;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0 auto;
}

h1 {
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}

p {
  color: #555;
  margin: 10px 0;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.edit-button, .register-button {
  background-color: #007bff;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  text-decoration: none;
  text-align: center;
  transition: background-color 0.3s ease;
  margin: 0 10px;
}

.edit-button:hover, .register-button:hover {
  background-color: #0056b3;
}

.edit-button:active, .register-button:active {
  background-color: #004080;
}
</style>