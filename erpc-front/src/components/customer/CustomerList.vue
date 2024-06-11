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
                    <a href="#" @click="setSearchBy('사업자 번호')">사업자 번호</a>
                </div>
            </div>
            <input type="text" class="customer-search-input" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="customer-search-btn" @click="applyFilter">조회하기</button>
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
                    <tr v-for="(customer, index) in paginatedCustomers" :key="customer.accountId" @click="goToCustomerContents(customer.accountId)">
                        <td>{{ filteredCustomers.length - ((currentPage - 1) * itemsPerPage + index) }}</td> <!-- Reverse numbering -->
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
        <div class="pagination">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
            <button v-for="page in totalPages" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
        </div>
    </div>
</template>




<script setup>
import { ref, computed, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();
const customers = ref([]);
const searchQuery = ref('');
const searchBy = ref('거래처명');
const filteredCustomers = ref([]);
const currentPage = ref(1);
const itemsPerPage = ref(10);

onMounted(async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/list');
        customers.value = response.data.sort((a, b) => b.accountId - a.accountId); // Sort by accountId
        filteredCustomers.value = customers.value;
        applyFilter(); // Apply initial filter
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
        const query = searchQuery.value.toUpperCase().replace(/\D/g, ''); // 숫자만 남기기
        filteredCustomers.value = customers.value.filter(customer => {
            const accountName = customer.accountName.toUpperCase();
            const accountCode = customer.accountCode.toUpperCase();
            const corporationNumDigits = customer.corporationNum.replace(/\D/g, ''); // 사업자 번호의 숫자만 추출

            if (searchBy.value === '거래처명') {
                return accountName.includes(searchQuery.value.toUpperCase()); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '거래처 코드') {
                return accountCode.includes(searchQuery.value.toUpperCase()); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '사업자 번호') {
                return corporationNumDigits.includes(query); // 숫자만 비교
            }
        });
    }
    currentPage.value = 1; // 필터 적용 시 첫 페이지로 이동
}


const paginatedCustomers = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return filteredCustomers.value.slice(start, end);
});

const totalPages = computed(() => {
    return Math.ceil(filteredCustomers.value.length / itemsPerPage.value);
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}

function goToCustomerContents(accountId) {
    router.push({ path: `/customer/${accountId}` });
}
</script>





<style>
    @import url('@/assets/css/customer/CustomerList.css');
</style>
