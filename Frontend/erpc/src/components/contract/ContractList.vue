<template>
    <div class="contract-content">
        <div class="contract-list">
            <h1>계약서 목록</h1>
        </div>
        <div class="contract-list-search">
            <div class="contract-dropdown">
                <button class="contract-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="contract-dropdown-content">
                    <a href="#" @click="setSearchBy('계약서 코드')">계약서 코드</a>
                    <a href="#" @click="setSearchBy('담당자')">담당자</a>
                </div>
            </div>
            <input type="text" class="contract-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="contract-search-btn7" @click="applyFilter">검색</button>
        </div>
        <div class="contract-list-box7">
            <table class="contract-table7">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>계약서 코드</th>
                        <th>계약금액</th>
                        <th>작성일자</th>
                        <th>납기일자</th>
                        <th>결재상태</th>
                        <th>담당자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="contract in filteredContracts" :key="contract.id"  @click="goToContractContents(contract.code)">
                        <td>{{ contract.id }}</td>
                        <td>{{ contract.code }}</td>
                        <td>{{ contract.amount }}</td>
                        <td>{{ contract.creationDate }}</td>
                        <td>{{ contract.dueDate }}</td>
                        <td>{{ contract.status }}</td>
                        <td>{{ contract.inCharge }}</td>
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

const contracts = ref([
    { id: 1, code: 'CO-20240427001', amount: '600,000', creationDate: '2024-04-01', dueDate: '2025-04-01', status: '진행중', inCharge: '유관순' },
    { id: 2, code: 'CO-20240427002', amount: '500,000', creationDate: '2024-04-01', dueDate: '2025-04-01', status: '진행중', inCharge: '이순신' }
]);
const searchQuery = ref('');
const searchBy = ref('계약서 코드');
const filteredContracts = ref(contracts.value);

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredContracts.value = contracts.value;
    } else {
        filteredContracts.value = contracts.value.filter(contract => {
            if (searchBy.value === '계약서 코드') {
                return contract.code.includes(searchQuery.value);
            } else if (searchBy.value === '담당자') {
                return contract.inCharge.includes(searchQuery.value);
            }
        });
    }
}

function goToContractContents(contractCode) {
    router.push({ path: '/contract/contents', query: { code: contractCode } });
}
</script>

<style>
    @import url('@/assets/css/contract/ContractList.css');
</style>
