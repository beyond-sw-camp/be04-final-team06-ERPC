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
.contract-content {
    margin-top: 51px;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.contract-list {
    text-align: center;
    margin-top: 50px;
}

.contract-list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.contract-dropdown {
    position: relative;
    display: inline-block;
}

.contract-dropdown-btn {
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

.contract-dropdown-content {
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

.contract-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.contract-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.contract-dropdown:hover .contract-dropdown-content {
    display: block;
}

.contract-search-input {
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

.contract-search-btn7 {
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

.contract-list-box7 {
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

.contract-table7 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.contract-table7 th,
.contract-table7 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
    width: 160px; /* 너비 조절 */
}

.contract-table7 th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.contract-table7 tr:hover {
    background-color: #d5e6ff;
    cursor: pointer;
}
</style>
