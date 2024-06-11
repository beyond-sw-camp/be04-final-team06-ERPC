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
            <button class="estimate-search-btn" @click="applyFilter">조회하기</button>
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
                    <tr v-for="(estimate, index) in paginatedEstimates" :key="estimate.quotationId" @click="goToEstimateContents(estimate.quotationId)">
                        <td>{{ filteredEstimates.length - ((currentPage - 1) * itemsPerPage + index) }}</td> <!-- Reverse numbering -->
                        <td>{{ estimate.quotationCode }}</td>
                        <td>{{ estimate.quotationTotalCost.toLocaleString() }}</td>
                        <td>{{ estimate.quotationDate }}</td>
                        <td>{{ estimate.quotationDueDate }}</td>
                        <td>{{ estimate.approvalStatus || '' }}</td>
                        <td>{{ estimate.employee.employeeName }}</td>
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
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();

const estimates = ref([]);
const filteredEstimates = ref([]);
const searchQuery = ref('');
const searchBy = ref('견적서 코드');
const currentPage = ref(1);
const itemsPerPage = ref(10);

onMounted(async () => {
    await fetchEstimates();
    await fetchApprovalStatuses();
    applyFilter();
});

const fetchEstimates = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation');
        estimates.value = response.data.map(estimate => ({
            quotationId: estimate.quotationId,
            quotationCode: estimate.quotationCode,
            quotationTotalCost: estimate.quotationTotalCost,
            quotationDate: estimate.quotationDate,
            quotationDueDate: estimate.quotationDueDate,
            employee: {
                employeeName: estimate.employee.employeeName
            }
        })).sort((a, b) => b.quotationId - a.quotationId); // Sort by quotationId in descending order
        filteredEstimates.value = estimates.value;
    } catch (error) {
        console.error('견적서 정보를 조회하는 중 오류가 발생했습니다.', error);
    }
};

const fetchApprovalStatuses = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation');
        const approvals = response.data;
        approvals.forEach(approval => {
            const estimate = estimates.value.find(est => est.quotationId === approval.quotation.quotationId);
            if (estimate) {
                estimate.approvalStatus = approval.approvalStatus.approvalStatus;
            }
        });
    } catch (error) {
        console.error('결재 상태를 조회하는 중 오류가 발생했습니다.', error);
    }
};

function setSearchBy(criteria) {
    searchBy.value = criteria;
}

function applyFilter() {
    if (!searchQuery.value) {
        filteredEstimates.value = estimates.value;
    } else {
        const query = searchQuery.value.toUpperCase(); // 검색어를 대문자로 변환
        filteredEstimates.value = estimates.value.filter(estimate => {
            if (searchBy.value === '견적서 코드') {
                return estimate.quotationCode.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '담당자') {
                return estimate.employee.employeeName.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            }
        });
    }
    currentPage.value = 1; // 필터 적용 시 첫 페이지로 이동
}

const paginatedEstimates = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return filteredEstimates.value.slice(start, end);
});

const totalPages = computed(() => {
    return Math.ceil(filteredEstimates.value.length / itemsPerPage.value);
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}

function goToEstimateContents(quotationId) {
    router.push({ path: `/estimate/${quotationId}` });
}
</script>


<style>
    @import url('@/assets/css/estimate/EstimateList.css');
</style>
