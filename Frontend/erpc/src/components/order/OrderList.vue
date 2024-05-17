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
                    <tr v-for="order in filteredOrders" :key="order.id"  @click="goToOrderContents(order.code)">
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
import { useRouter } from 'vue-router';

const router = useRouter();

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

function goToOrderContents(orderCode) {
    router.push({ path: '/order/contents', query: { code: orderCode } });
}
</script>

<style>
    @import url('@/assets/css/order/OrderList.css');
</style>