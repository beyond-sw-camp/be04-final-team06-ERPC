<template>
    <div class="estimate-content">
        <div class="estimate-list">
            <h1>견적서 목록</h1>
        </div>
        <div class="estimate-list-search">
            <div class="estimate-dropdown">
                <button class="estimate-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="estimate-dropdown-content">
                    <a href="#" @click="setSearchBy('견적서 코드')">견적서 코드</a>
                    <a href="#" @click="setSearchBy('담당자')">담당자</a>
                </div>
            </div>
            <input type="text" class="estimate-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="estimate-search-btn" @click="applyFilter">검색</button>
        </div>
        <div class="estimate-list-box">
            <table class="estimate-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>견적서 코드</th>
                        <th>견적금액</th>
                        <th>작성일자</th>
                        <th>마감일자</th>
                        <th>결재상태</th>
                        <th>담당자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="estimate in filteredEstimates" :key="estimate.id"  @click="goToEstimateContents(estimate.code)">
                        <td>{{ estimate.id }}</td>
                        <td>{{ estimate.projectCode }}</td>
                        <td>{{ estimate.amount }}</td>
                        <td>{{ estimate.creationDate }}</td>
                        <td>{{ estimate.dueDate }}</td>
                        <td>{{ estimate.status }}</td>
                        <td>{{ estimate.inCharge }}</td>
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

const estimates = ref([
    { id: 1, projectCode: 'QO-20240427001', amount: '600,000', creationDate: '2024-04-01', dueDate: '2025-04-01', status: '진행중', inCharge: '유관순' },
    { id: 2, projectCode: 'QO-20240427002', amount: '500,000', creationDate: '2024-04-01', dueDate: '2025-04-01', status: '진행중', inCharge: '이순신' }
]);
const searchQuery = ref('');
const searchBy = ref('견적서 코드');
const filteredEstimates = ref(estimates.value);

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredEstimates.value = estimates.value;
    } else {
        filteredEstimates.value = estimates.value.filter(estimate => {
            if (searchBy.value === '견적서 코드') {
                return estimate.projectCode.includes(searchQuery.value);
            } else if (searchBy.value === '담당자') {
                return estimate.inCharge.includes(searchQuery.value);
            }
        });
    }
}

function goToEstimateContents(estimateCode) {
    router.push({ path: '/estimate/contents', query: { code: estimateCode } });
}
</script>

<style>
.estimate-content {
    margin-top: 51px;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.estimate-list {
    text-align: center;
    margin-top: 50px;
}

.estimate-list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.estimate-dropdown {
    position: relative;
    display: inline-block;
}

.estimate-dropdown-btn {
    width: 200px;
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.estimate-dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
    font-size: 15px;
}

.estimate-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.estimate-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.estimate-dropdown:hover .estimate-dropdown-content {
    display: block;
}

.estimate-search-input {
    max-width: 300px;
    height: 12px;
    padding: 10px;
    border: 2px solid #0C2092;
    border-radius: 10px;
    outline: none;
    font-size: 16px;
    background-color: #e5F0FF;
    color: #0C2092;
}

.estimate-search-btn {
    background-color: #0C2092;
    border: none;
    color: white;
    padding: 10px 8px;
    width: 70px;
    margin-top: 16px;
    margin-bottom: 16px;
    font-size: 16px;
    border-radius: 10px;
    cursor: pointer;
    outline: none;
}

.estimate-list-box {
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
    width: 100%;
    max-width: 1400px;
    margin: 20px auto;
    margin-bottom: 7%;
    gap: 1px;
}

.estimate-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.estimate-table th,
.estimate-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
    width: 160px; /* 너비 조절 */
}

.estimate-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.estimate-table tr:hover {
    background-color: #d5e6ff;
    cursor: pointer;
}
</style>
