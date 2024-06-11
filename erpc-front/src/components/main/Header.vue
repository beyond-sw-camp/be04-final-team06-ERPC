<template>
    <link rel="icon" href="/favicon.ico" type="image/x-icon">
    <header class="header">
        <div class="logo">
            <img src="@/assets/img/logo2.png" class="logoimage" @click="main">
        </div>
        <div class="nav-buttons" v-if="isLoggedIn">
            <button class="profile-button" @click="viewProfile">내 정보</button>
            <button class="logout-button" @click="logout">로그아웃</button>
        </div>
    </header>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();
const employeeName = ref('');

const isLoggedIn = computed(() => {
    return !!localStorage.getItem('userId');
});

const fetchEmployeeData = async () => {
    const userId = localStorage.getItem('userId');
    if (userId) {
        try {
            const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`, { withCredentials: true });
            const employeeData = response.data;
            employeeName.value = employeeData.employeeName;
        } catch (error) {
            console.error('직원 정보를 조회하는 중 오류가 발생했습니다.', error);
            alert('직원 정보를 조회하는 중 오류가 발생했습니다.');
        }
    }
};

onMounted(() => {
    if (isLoggedIn.value) {
        fetchEmployeeData();
    }
});

function main() {
    router.push("/");
}

function viewProfile() {
    const userId = localStorage.getItem('userId');
    if (userId) {
        router.push(`/employees/my/${userId}`);
    } else {
        alert('로그인 정보가 없습니다. 다시 로그인해주세요.');
    }
}

function logout() {
    alert("로그아웃 되었습니다!")
    localStorage.removeItem('token'); // 토큰 삭제
    localStorage.removeItem('userId'); // 사용자 ID 삭제
    employeeName.value = ''; // 로그인한 사용자 이름 초기화
    router.replace({ path: '/' }).then(() => {
        router.go(0); // 페이지 새로고침 없이 상태 초기화
    });
}
</script>

<style>
    @import url('@/assets/css/main/Header.css');
</style>