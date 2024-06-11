<template>
    <div class="order-content33">
        <div class="order-list33">
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
            <button class="order-search-btn" @click="applyFilter">조회하기</button>
        </div>
        <div class="order-list-box">
            <table class="order-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>프로젝트 코드</th>
                        <th>수주금액</th>
                        <th>작성일자</th>
                        <th>결재상태</th>
                        <th>담당자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(order, index) in paginatedOrders" :key="order.orderRegistrationId" @click="goToOrderContents(order.orderRegistrationId)">
                        <td>{{ filteredOrders.length - ((currentPage - 1) * itemsPerPage + index) }}</td>
                        <td>{{ order.transaction.transactionCode }}</td>
                        <td>{{ order.orderTotalPrice.toLocaleString() }}</td>
                        <td>{{ order.orderDate }}</td>
                        <td>{{ order.approvalStatus || '' }}</td>
                        <td>{{ order.employee.employeeName }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="pagination">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
            <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
        </div>
    </div>
</template>


<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();

const orders = ref([]);
const searchQuery = ref('');
const searchBy = ref('프로젝트 코드');
const filteredOrders = ref([]);
const currentPage = ref(1);
const itemsPerPage = ref(10);

onMounted(async () => {
    await fetchOrders();
    await fetchApprovalStatuses();
    applyFilter();
});

const fetchOrders = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order');
        orders.value = response.data.map(order => ({
            orderRegistrationId: order.orderRegistrationId,
            transaction: order.transaction,
            orderTotalPrice: order.orderTotalPrice,
            orderDate: order.orderDate,
            employee: order.employee
        })).sort((a, b) => b.orderRegistrationId - a.orderRegistrationId); // Sort by orderRegistrationId in descending order
        filteredOrders.value = orders.value;
    } catch (error) {
        console.error('Error fetching order data:', error);
    }
};

const fetchApprovalStatuses = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment');
        const approvals = response.data;
        approvals.forEach(approval => {
            const order = orders.value.find(ord => ord.orderRegistrationId === approval.order.orderRegistrationId);
            if (order) {
                order.approvalStatus = approval.approvalStatus.approvalStatus;
            }
        });
    } catch (error) {
        console.error('Error fetching approval statuses:', error);
    }
};

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredOrders.value = orders.value;
    } else {
        const query = searchQuery.value.toUpperCase(); // 검색어를 대문자로 변환
        filteredOrders.value = orders.value.filter(order => {
            if (searchBy.value === '프로젝트 코드') {
                return order.transaction.transactionCode.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '담당자') {
                return order.employee.employeeName.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            }
        });
    }
    currentPage.value = 1; // 필터 적용 시 첫 페이지로 이동
}

const paginatedOrders = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return filteredOrders.value.slice(start, end);
});

const totalPages = computed(() => {
    return Math.ceil(filteredOrders.value.length / itemsPerPage.value);
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}

function goToOrderContents(orderRegistrationId) {
    router.push({ path: `/order/${orderRegistrationId}` });
}
</script>

<style>
    @import url('@/assets/css/order/OrderList.css');
</style>


