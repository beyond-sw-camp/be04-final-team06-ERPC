<template>
    <div class="order-content">
        <div class="order-list">
            <h1>수주 목록</h1>
        </div>
        <div class="order-list-search">
            <div class="order-dropdown">
                <button class="order-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="order-dropdown-content">
                    <a href="#" @click="setSearchBy('프로젝트 코드')">프로젝트 코드</a>
                    <a href="#" @click="setSearchBy('담당자')">담당자</a>
                </div>
            </div>
            <input type="text" class="order-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="order-search-btn" @click="applyFilter">검색</button>
        </div>
        <div class="order-list-box">
            <table class="order-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>프로젝트 코드</th>
                        <th>수주금액</th>
                        <th>작성일자</th>
                        <th>납기일자</th>
                        <th>결재상태</th>
                        <th>담당자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="order in filteredOrders" :key="order.id">
                        <td>{{ order.id }}</td>
                        <td>{{ order.projectCode }}</td>
                        <td>{{ order.amount }}</td>
                        <td>{{ order.creationDate }}</td>
                        <td>{{ order.dueDate }}</td>
                        <td>{{ order.status }}</td>
                        <td>{{ order.inCharge }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const orders = ref([
    { id: 1, projectCode: 'PJ-20240508001', amount: '600,000', creationDate: '2024-04-01', dueDate: '2025-04-04', status: '진행중', inCharge: '유관순' },
    { id: 2, projectCode: 'PJ-20240508002', amount: '500,000', creationDate: '2024-04-01', dueDate: '2025-04-04', status: '진행중', inCharge: '이순신' }
]);
const searchQuery = ref('');
const searchBy = ref('프로젝트 코드');
const filteredOrders = ref(orders.value);

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredOrders.value = orders.value;
    } else {
        filteredOrders.value = orders.value.filter(order => {
            if (searchBy.value === '프로젝트 코드') {
                return order.projectCode.includes(searchQuery.value);
            } else if (searchBy.value === '담당자') {
                return order.inCharge.includes(searchQuery.value);
            }
        });
    }
}
</script>

<style>
.order-list-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.order-list {
    text-align: center;
    margin-top: 8%;
}

.order-list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.order-dropdown {
    position: relative;
    display: inline-block;
}

.order-dropdown-btn {
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

.order-dropdown-content {
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

.order-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.order-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.order-dropdown:hover .order-dropdown-content {
    display: block;
}

.order-search-input {
    max-width: 300px;
    padding: 10px;
    border: 2px solid #0C2092;
    border-radius: 10px;
    outline: none;
    font-size: 16px;
    background-color: #E5F0FF;
    color: #0C2092;
}

.order-search-btn {
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

.order-list-box {
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

.order-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.order-table th,
.order-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.order-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.order-table tr:hover {
    background-color: #d5e6ff;
}
</style>