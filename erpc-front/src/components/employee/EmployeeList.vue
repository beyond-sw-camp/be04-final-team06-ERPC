<template>
  <div>
    <div class="table-container">
      <div class="header-container">
        <router-link :to="{ path: '/employees/regist' }" class="writebutton">직원 등록</router-link>
      </div>
      <table class="table">
        <thead>
          <tr class="header1">
            <th class="num">No</th>
            <th>이름</th>
            <th>팀</th>
            <th>직급</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(employee, index) in paginatedEmployees" :key="employee.employeeId" class="allpost">
            <td>{{ index + 1 }}</td>
            <td>
              <router-link :to="'/employees/' + employee.employeeId" class="employee-item">
                {{ employee.employeeName }}
              </router-link>
            </td>
            <td>{{ mapTeamCode(employee.teamCode.teamCodeId) }}</td>
            <td>{{ mapEmployeeRank(employee.employeeRank.employeeRankId) }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="pagination">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
            <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
        </div>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import axios from 'axios';

export default {
  data() {
    return {
      employees: [],
      currentPage: 1,
      itemsPerPage: 10
    };
  },
  created() {
    axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees')
      .then(response => {
        this.employees = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  },
  computed: {
    paginatedEmployees() {
      const start = (this.currentPage - 1) * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.employees.slice(start, end);
    },
    totalPages() {
      return Math.ceil(this.employees.length / this.itemsPerPage);
    }
  },
  methods: {
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
    },
    changePage(page) {
      if (page > 0 && page <= this.totalPages) {
        this.currentPage = page;
      }
    }
  }
}
</script>

<style>
.table {
  width: 100%;
  min-width: 1000px;
  max-width: 1000px;
  border-collapse: separate;
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-spacing: 0 10px;
 
}

.boardTitle {
  font-size: 32px;
  margin-top: 20px; 
  margin-bottom: 20px;
  text-align: center; 
}

.thing {
  text-align: right;
}

.header1 {
  background-color: #b8c4e4;
  height: 50px;
  font-size: 12px;
  text-align: center;
}

.header1 .num {
  width: 5%;
}

.allpost {
  text-align: center;
  padding: 10px 0;
}

.allpost .num {
  width: 5%;
}

.header-container {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-top: 60px;
}

.writebutton {
  background-color: #6c7aa1;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px; 
  font-weight: bold;
}

.writebutton:hover {
  background-color: #b8c4e4;
}
</style>