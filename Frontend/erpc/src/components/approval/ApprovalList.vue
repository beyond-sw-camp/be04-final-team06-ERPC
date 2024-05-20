<template>
    <div class="approval-content">
        <div class="approval-search">
            <h1 class="approval-search-text">결재 승인 요청 목록</h1>
        </div>
        <div class="approval-box">
            <div class="search-date">
                <p class="search-start-date-text">조회 시작 일자</p>
                <input type="date" class="search-start-date-box" id="search-start-date-box" v-model="startDate">
                <p class="search-end-date-text">조회 종료 일자</p>
                <input type="date" class="search-end-date-box" id="search-end-date-box" v-model="endDate">
            </div>
            <div class="approval-search-btn-div">
                <button class="approval-search-btn" @click="applyFilter">조회하기</button>
            </div>
        </div>
        <div class="approval-list-box">
            <table class="approval-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>요청 코드</th>
                        <th>거래처명</th>
                        <th>수주 예정 금액</th>
                        <th>요청 일자</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(approval, index) in filteredapprovals" :key="index" @click="goToapprovalContents(approval.code)">
                        <td>{{ index + 1 }}</td>
                        <td>{{ approval.code }}</td>
                        <td>{{ approval.approvalor }}</td>
                        <td>{{ approval.amount }}</td>
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
    { code: 'PJ-20240401001', approvalor: 'A-회사', amount: '600,000', date: '2024-04-01', status: '진행중' },
    { code: 'PJ-20240401002', approvalor: 'B-회사', amount: '500,000', date: '2024-04-01', status: '승인' },
    { code: 'PJ-20240401003', approvalor: 'C-회사', amount: '7,000,000', date: '2024-03-31', status: '반려' }
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

function goToapprovalContents(approvalCode) {
    router.push({ path: '/order/contents', query: { code: approvalCode } });
}
</script>

<style>
    @import url('@/assets/css/approval/ApprovalList.css');
</style>
