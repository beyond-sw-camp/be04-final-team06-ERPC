<template>
    <div class="cb-content">
        <div class="cb-search">
            <h1 class="cb-search-text">세금계산서 요청 목록</h1>
        </div>
        <div class="cb-box">
            <div class="search-date">
                <p class="search-start-date-text">조회 시작 일자</p>
                <input type="date" class="search-start-date-box" id="search-start-date-box" v-model="startDate">
                <p class="search-end-date-text">조회 종료 일자</p>
                <input type="date" class="search-end-date-box" id="search-end-date-box" v-model="endDate">
            </div>
            <div class="cb-search-btn-div">
                <button class="cb-search-btn" @click="applyFilter">조회하기</button>
            </div>
        </div>
        <div class="cb-list-box">
            <table class="cb-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>프로젝트 코드</th>
                        <th>거래처명</th>
                        <th>세금계산서 발행 금액</th>
                        <th>세금계산서 발행일</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(cb, index) in filteredcbs" :key="index" @click="goToCBContents(cb.code)">
                        <td>{{ index + 1 }}</td>
                        <td>{{ cb.code }}</td>
                        <td>{{ cb.cbor }}</td>
                        <td>{{ cb.amount }}</td>
                        <td>{{ cb.date }}</td>
                        <td>{{ cb.status }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const cbs = ref([
    { code: 'PJ-20240401001', cbor: 'A-회사', amount: '600,000', date: '2024-04-01', status: '진행중' },
    { code: 'PJ-20240401002', cbor: 'B-회사', amount: '500,000', date: '2024-04-01', status: '승인' },
    { code: 'PJ-20240401003', cbor: 'C-회사', amount: '7,000,000', date: '2024-03-31', status: '반려' }
]);
const startDate = ref('');
const endDate = ref('');
const filteredcbs = ref(cbs.value);

function applyFilter() {
    if (startDate.value && endDate.value) {
        filteredcbs.value = cbs.value.filter(cb => {
            return cb.date >= startDate.value && cb.date <= endDate.value;
        });
    } else {
        filteredcbs.value = cbs.value;
    }
}

function goToCBContents(cbCode) {
    router.push({ path: '/bill/contents', query: { code: cbCode } });
}
</script>

<style>
    @import url('@/assets/css/bill/CBList.css');
</style>
