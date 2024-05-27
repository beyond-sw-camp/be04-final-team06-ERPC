<template>
  <div>
    <h2>직원 목록</h2>
    <ul>
      <li v-for="employee in employees" :key="employee.employeeId">
        <router-link :to="'/employees/' + employee.employeeId">
          {{ employee.employeeName }}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      employees: []
    };
  },
  created() {
    axios.get('http://localhost:7775/employees')
      .then(response => {
        this.employees = response.data;
      })
      .catch(error => {
        console.log(error);
      });
  }
}
</script>