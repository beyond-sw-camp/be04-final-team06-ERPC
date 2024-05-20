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
.approval-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.approval-search {
    text-align: center;
    margin-top: 3%;
}

.approval-search-text {
    margin-top: 5%;
    margin-bottom: 30px;
}

.approval-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 50px;
    border-radius: 10px;
    border: 2px solid #ccc;
    box-sizing: border-box;
    background-color: whitesmoke;
    height: auto;
    max-width: 300px;
}

.search-date {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.search-start-date-text,
.search-end-date-text {
    min-width: 50px;
    margin-right: 10px;
    margin-bottom: 2px;
}

.search-start-date-box,
.search-end-date-box {
    flex-grow: 1;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 220px;
}

.approval-search-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
    margin-top: 10px;
}

.approval-search-btn {
    padding: 10px 20px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 5px;
    margin-bottom: 5px;
    max-width: 320px;
}

.approval-list-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    box-sizing: border-box;
    background-color: white;
    height: auto;
    max-width: 1400px;
    margin: 20px auto;
    margin-bottom: 7%;
    gap: 1px;
}

.approval-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.approval-table th,
.approval-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.approval-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.approval-table tr:hover {
    background-color: #d5e6ff;
    cursor: pointer;
}
</style>
