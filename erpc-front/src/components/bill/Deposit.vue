<template>
    <div class="deposit-search-content">
        <div class="deposit-search">
            <h1 class="deposit-search-text">수금 조회</h1>
        </div>
        <div class="deposit-box">
            <input type="date" class="search-date-box" v-model="startDate">
            <span class="search-date-separator">-</span>
            <input type="date" class="search-date-box" v-model="endDate">
            <input type="text" class="search-depositor-box" v-model="depositorName" placeholder="입금자명으로 검색">
            <button class="deposit-search-btn" @click="applyFilter">조회하기</button>
        </div>
        <div class="deposit-list-box">
            <table class="deposit-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>입금 코드</th>
                        <th>입금자명</th>
                        <th>입금거래처</th>
                        <th>입금 금액</th>
                        <th>입금 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(deposit, index) in paginatedDeposits" :key="index">
                        <td>{{ totalDeposits - ((currentPage - 1) * pageSize + index) }}</td>
                        <td>{{ deposit.depositCode }}</td>
                        <td>{{ deposit.depositPic }}</td>
                        <td>{{ deposit.depositAccount }}</td>
                        <td>{{ deposit.depositPrice.toLocaleString() }}</td>
                        <td>{{ deposit.depositDate }}</td>
                    </tr>
                    <tr v-if="paginatedDeposits.length === 0">
                        <td colspan="6" class="no-result">검색 결과가 없습니다.</td>
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

<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';

const deposits = ref([]);
const startDate = ref('');
const endDate = ref('');
const depositorName = ref('');
const filteredDeposits = ref([]);
const currentPage = ref(1);
const pageSize = ref(10);

// 데이터 가져오기
onMounted(async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/collection');
        deposits.value = response.data.sort((a, b) => b.collectionId - a.collectionId);
        filteredDeposits.value = deposits.value;
    } catch (error) {
        console.error('Error fetching deposits:', error);
    }
});

function applyFilter() {
    filteredDeposits.value = deposits.value.filter(deposit => {
        const matchesDateRange = (!startDate.value || deposit.depositDate >= startDate.value) && (!endDate.value || deposit.depositDate <= endDate.value);
        const matchesDepositor = !depositorName.value || deposit.depositPic.includes(depositorName.value);
        return matchesDateRange && matchesDepositor;
    });
    currentPage.value = 1; // 검색할 때 페이지를 1페이지로 초기화
}

const paginatedDeposits = computed(() => {
    const start = (currentPage.value - 1) * pageSize.value;
    const end = start + pageSize.value;
    return filteredDeposits.value.slice(start, end);
});

const totalPages = computed(() => {
    return Math.ceil(filteredDeposits.value.length / pageSize.value);
});

const totalDeposits = computed(() => {
    return filteredDeposits.value.length;
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}
</script>

<style scoped>
    @import url('@/assets/css/bill/Deposit.css');
</style>
