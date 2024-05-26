<template>
    <div class="mainpage">
        <div class="mainbanner">
            <img src="@/assets/img/mainbanner.png" class="mainbannerimage" />
            <div class="mainbannertxt">함께 만드는 건강한 영업 환경</div>
            <p class="mainbannertxt2">모든 구성원들의 건강한 몸과 마음은 최고의 가치입니다.</p>
            <p class="mainbannertxt3">모든 직원분들께서 격려와 배려, 열린 의사소통으로</p>
            <p class="mainbannertxt4">서로 존중하는 사내 문화를 지키는 약속에 함께 동참하여 주시기를 당부 드립니다.</p>
        </div>
        <div class="mainbanner2">
            <div class="inmainbanner">
                <img src="@/assets/img/mainbanner5.jpg" class="mainbannerimage2" />
            </div>
            <div class="inmainbanner2">
                <div class="login-container">
                    <h1>ERPC</h1>
                    <label for="employee-id">Employee ID</label>
                    <input type="text" v-model="employeeCode" id="employee-id" name="employee-id">
                    <label for="password">Password</label>
                    <input type="password" v-model="employeePassword" id="password" name="password">
                    <button @click="login">로그인</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
// import router from '@/router/mainRouter';

const employeeCode = ref('');
const employeePassword = ref('');

const login = async () => {
    try {
        const response = await axios.post('http://localhost:7775/login', {
            employeeCode: employeeCode.value,
            employeePassword: employeePassword.value
        }, {
            headers: {
                'Content-Type': 'application/json'
            },
            withCredentials: true // CORS 관련 설정
        });

        // 콘솔에 응답 전체를 출력해 확인
        console.log(response);

        // 헤더에서 값을 가져옴
        const token = response.headers['token'];
        const userId = response.headers['userid'];

        if (token && userId) {
            localStorage.setItem('token', token);
            localStorage.setItem('userId', userId);
            alert('로그인 성공!');

            // router.push('/notice/list');
        } else {
            alert('로그인 실패: 토큰을 받지 못했습니다.');
        }
    } catch (error) {
        console.error('로그인 오류:', error);
        alert('로그인 실패: 서버 오류');
    }
};
</script>



<style>
    @import url('@/assets/css/main/Main.css');
</style>
