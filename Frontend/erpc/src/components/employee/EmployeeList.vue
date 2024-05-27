<template>
    <div>
      <h1>Employee List</h1>
      <ul>
        <li v-for="employee in employees" :key="employee.employeeId">
          <router-link :to="'/employees/' + employee.employeeId">
            {{ employee.name }}
          </router-link>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        employees: [],
      };
    },
    async created() {
      const response = await axios.get('/');
      this.employees = response.data;
    },
  };
  </script>