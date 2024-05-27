<template>
    <div>
      <h1>Employee Detail</h1>
      <p>ID: {{ employee.employeeId }}</p>
      <p>Name: {{ employee.employeeName }}</p>
      <p>Email: {{ employee.email }}</p>
      <p>Password: {{ employee.password }}</p>
      <!-- 추가 정보 표시 -->
    </div>
  </template>
  
  <script>
   import axios from "axios";

   const axiosInstance = axios.create({
  baseURL: 'http://localhost:7775/employees', // Spring Boot 서버의 URL
});
  
  export default {
    data() {
      return {
        employee: {},
      };
    },
    async created() {
      const employeeId = this.$route.params.employeeId;
      const response = await axios.get(`/${employeeId}`);
      this.employee = response.data;
    },
  };
  </script>