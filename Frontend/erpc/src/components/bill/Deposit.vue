<template>
    <div class="deposit-search-content">
        <div class="deposit-search">
            <h1 class="deposit-search-text">수금 조회</h1>
        </div>
        <div class="deposit-box">
            <div class="search-date">
                <p class="search-start-date-text">조회 시작 일자</p>
                <input type="date" class="search-start-date-box" id="search-start-date-box" v-model="startDate">
                <p class="search-end-date-text">조회 종료 일자</p>
                <input type="date" class="search-end-date-box" id="search-end-date-box" v-model="endDate">
            </div>
            <div class="deposit-search-btn-div">
                <button class="deposit-search-btn" @click="applyFilter">조회하기</button>
            </div>
        </div>
        <div class="deposit-search2">
            <h1 class="deposit-search2-text">입금 내역</h1>
        </div>
        <div class="deposit-list-box">
            <table class="deposit-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>입금 코드</th>
                        <th>입금자명</th>
                        <th>입금 계좌</th>
                        <th>입금 금액</th>
                        <th>입금 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(deposit, index) in filteredDeposits" :key="index">
                        <td>{{ index + 1 }}</td>
                        <td>{{ deposit.code }}</td>
                        <td>{{ deposit.depositor }}</td>
                        <td>{{ deposit.account }}</td>
                        <td>{{ deposit.amount }}</td>
                        <td>{{ deposit.date }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const deposits = ref([
    { code: 'DP-20240430001', depositor: '홍길동', account: '0000-00-0000000', amount: '600,000', date: '2024-04-01' },
    { code: 'DP-20240430002', depositor: '이순신', account: '0000-00-0000000', amount: '500,000', date: '2024-04-01' },
    { code: 'DP-20240430003', depositor: '장보고', account: '0000-00-0000000', amount: '7,000,000', date: '2024-03-31' }
]);
const startDate = ref('');
const endDate = ref('');
const filteredDeposits = ref(deposits.value);  

function applyFilter() {
    if (startDate.value && endDate.value) {
        filteredDeposits.value = deposits.value.filter(deposit => {
            return deposit.date >= startDate.value && deposit.date <= endDate.value;
        });
    } else {
        filteredDeposits.value = deposits.value;
    }
}
</script>

<style>
    @import url('@/assets/css/bill/Deposit.css');
</style>