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
                        <th>업태</th>
                        <th>종목</th>
                        <th>전화번호</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(customer, index) in filteredCustomers" :key="index" @click="goToCustomerContents(customer.code)">
                        <td>{{ index + 1 }}</td>
                        <td>{{ customer.code }}</td>
                        <td>{{ customer.name }}</td>
                        <td>{{ customer.registration }}</td>
                        <td>{{ customer.representative }}</td>
                        <td>{{ customer.businessType }}</td>
                        <td>{{ customer.industry }}</td>
                        <td>{{ customer.phone }}</td>
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

const customers = ref([
    { code: 'AC-20240430001', name: 'A-회사', registration: '123-45-67890', representative: '홍길동', businessType: '소매업', industry: '백화점', phone: '000-0000-0000' },
    { code: 'AC-20240430002', name: 'B-회사', registration: '234-56-78901', representative: '이순신', businessType: '도매업', industry: '가전제품', phone: '111-1111-1111' }
]);
const searchQuery = ref('');
const searchBy = ref('거래처명');
const filteredCustomers = ref(customers.value);

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredCustomers.value = customers.value;
    } else {
        filteredCustomers.value = customers.value.filter(customer => {
            if (searchBy.value === '거래처명') {
                return customer.name.includes(searchQuery.value);
            } else if (searchBy.value === '거래처 코드') {
                return customer.code.includes(searchQuery.value);
            }
        });
    }
}

function goToCustomerContents(customerCode) {
    router.push({ path: '/customer/contents', query: { code: customerCode } });
}
</script>

<style>
@import url('@/assets/css/customer/CustomerList.css');
</style>
