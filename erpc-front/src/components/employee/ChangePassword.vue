<template>
    <div class="change-password">
        <h1>비밀번호 변경</h1>
        <label for="employee-id">사번</label>
        <input type="text" v-model="employee.employeeId" id="employee-id" name="employee-id">
        <label for="new-password">새 비밀번호</label>
        <input type="password" v-model="employee.newPassword" id="new-password" name="new-password">
        <button @click="changePassword">비밀번호 변경</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const employee = ref({
    employeeId: '',
    newPassword: ''
});

const changePassword = async () => {
    try {
        const response = await axios.patch('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/modify_password', {
            employeeId: employee.value.employeeId,
            employeePassword: employee.value.newPassword,
        }, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true
        });

        if (response.status === 200) {
            alert('비밀번호 변경 성공!');
        } else {
            alert('비밀번호 변경 실패: 서버 오류');
        }
    } catch (error) {
        console.error('비밀번호 변경 오류:', error);
        alert('비밀번호 변경 실패: 서버 오류');
    }
};
</script>
<style scoped>
.change-password {
    max-width: 400px;
    margin: 0 auto;
    padding: 1em;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #f9f9f9;
}
.change-password h1 {
    text-align: center;
    margin-bottom: 1em;
}
.change-password label {
    display: block;
    margin-bottom: 0.5em;
    font-weight: bold;
}
.change-password input {
    width: calc(100% - 22px);
    padding: 10px;
    margin-bottom: 1em;
    border: 1px solid #ccc;
    border-radius: 4px;
}
.change-password button {
    width: 100%;
    padding: 10px;
    background-color: #28a745;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}
.change-password button:hover {
    background-color: #218838;
}
</style>