<template>
    <div class="customer-list-content">
        <div class="customer-list">
            <h1>거래처 목록</h1>
        </div>
        <div class="customer-list-search">
            <div class="customer-dropdown">
                <button class="customer-dropdown-btn">{{ searchBy }} ▼</button>
                <div class="customer-dropdown-content">
                    <a href="#" @click="setSearchBy('거래처명')">거래처명</a>
                    <a href="#" @click="setSearchBy('거래처 코드')">거래처 코드</a>
                </div>
            </div>
            <input type="text" class="customer-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="customer-search-btn" @click="applyFilter">검색</button>
        </div>
        <div class="customer-list-box">
            <table class="customer-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>거래처 코드</th>
                        <th>사업자명</th>
                        <th>사업자 번호</th>
                        <th>대표자명</th>
                        <th>업종</th>
                        <th>전화번호</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(customer, index) in filteredCustomers" :key="index" @click="goToCustomerContents(customer.accountId)">
                        <td>{{ index + 1 }}</td>
                        <td>{{ customer.accountCode }}</td>
                        <td>{{ customer.accountName }}</td>
                        <td>{{ customer.corporationNum }}</td>
                        <td>{{ customer.accountRepresentative }}</td>
                        <td>{{ customer.accountType }}</td>
                        <td>{{ customer.accountContact }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
const customers = ref([]);
const searchQuery = ref('');
const searchBy = ref('거래처명');
const filteredCustomers = ref(customers.value);

onMounted(async () => {
    try {
        const response = await axios.get('http://localhost:7775/account/list');
        customers.value = response.data;
        filteredCustomers.value = customers.value;
    } catch (error) {
        console.error('Error fetching customers:', error);
    }
});

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredCustomers.value = customers.value;
    } else {
        const query = searchQuery.value.toUpperCase(); // 검색어를 대문자로 변환
        filteredCustomers.value = customers.value.filter(customer => {
            if (searchBy.value === '거래처명') {
                return customer.accountName.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '거래처 코드') {
                return customer.accountCode.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            }
        });
    }
}

function goToCustomerContents(accountId) {
    router.push({ path: `/customer/contents/${accountId}` });
}
</script>

<style>
@import url('@/assets/css/customer/CustomerList.css');
</style>
