<template>
    <div>
      <h1>Register Employee</h1>
      <form @submit.prevent="registerEmployee">
        <div>
          <label for="name">Name:</label>
          <input type="text" v-model="employee.name" required>
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="email" v-model="employee.email" required>
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="password" v-model="employee.password" required>
        </div>
        <!-- 추가 필드 -->
        <button type="submit">Register</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        employee: {
          name: '',
          email: '',
          password: '',
        },
      };
    },
    methods: {
      async registerEmployee() {
        try {
          const response = await axios.post('http://localhost:7775/employees/regist', this.employee);
          this.$router.push('/employees');
        } catch (error) {
          console.error('Error registering employee:', error);
        }
      },
    },
  };
  </script>