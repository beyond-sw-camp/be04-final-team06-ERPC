<template>
    <div class="contract-content">
        <div class="contract-list">
            <h1>영업기회 목록</h1>
        </div>
        <div class="contract-list-search">
            <div class="contract-dropdown">
                <button class="contract-dropdown-btn">거래처 명 </button>
            </div>
            <input type="text" class="contract-search-input" placeholder="검색어를 입력하세요">
            <button class="contract-search-btn">검색</button>
        </div>
        <div class="contract-list-box">
            <table class="contract-table">
                <thead>
                    <tr class="header1">
                        <th>번호</th>
                        <th>거래처 명</th>
                        <th>영업기회 작성일</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(approval, index) in filteredapprovals" :key="index" @click="goTosalesOppContents(approval.code)">
                        <td>{{ index + 1 }}</td>
                        <td>{{ approval.approvalor }}</td>
                        <td>{{ approval.date }}</td>
                        <td>{{ approval.status }}</td>
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

const approvals = ref([
    { code: 'PJ-20240401001', approvalor: 'A-회사',  date: '2024-04-01', status: '진행중' },
    { code: 'PJ-20240401002', approvalor: 'B-회사',  date: '2024-04-01', status: '승인' },
    { code: 'PJ-20240401003', approvalor: 'C-회사',  date: '2024-03-31', status: '반려' }
]);
const startDate = ref('');
const endDate = ref('');
const filteredapprovals = ref(approvals.value);

function applyFilter() {
    if (startDate.value && endDate.value) {
        filteredapprovals.value = approvals.value.filter(approval => {
            return approval.date >= startDate.value && approval.date <= endDate.value;
        });
    } else {
        filteredapprovals.value = approvals.value;
    }
}

function goTosalesOppContents(approvalCode) {
    router.push({ path: '/salesopp/contents', query: { code: approvalCode } });
}
</script>

<style>
    @import url('@/assets/css/contract/ContractList.css');
</style>