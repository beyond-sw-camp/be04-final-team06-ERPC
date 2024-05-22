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
    @import url('@/assets/css/estimate/EstimateList.css');
</style>
