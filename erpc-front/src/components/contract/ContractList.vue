<template>
    <div class="contract-list-content9">
        <div class="contract-list">
            <h1>계약서 목록</h1>
        </div>
        <div class="contract-list-search22">
            <div class="contract-dropdown22">
                <button class="contract-dropdown-btn22">{{ searchBy }} ▼</button>
                <div class="contract-dropdown-content22">
                    <a href="#" @click="setSearchBy('계약서 코드')">계약서 코드</a>
                    <a href="#" @click="setSearchBy('담당자')">담당자</a>
                </div>
            </div>
            <input type="text" class="contract-search-input22" v-model="searchQuery" placeholder="검색어를 입력하세요">
            <button class="contract-search-btn22" @click="applyFilter">조회하기</button>
        </div>
        <div class="contract-list-box9">
            <table class="contract-table9">
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
                    <tr v-for="(contract, index) in paginatedContracts" :key="contract.contractId" @click="goToContractContents(contract.contractId)">
                        <td>{{ filteredContracts.length - ((currentPage - 1) * itemsPerPage + index) }}</td> <!-- Reverse numbering -->
                        <td>{{ contract.contractCode }}</td>
                        <td>{{ contract.contractTotalPrice.toLocaleString() }}</td>
                        <td>{{ contract.contractDate }}</td>
                        <td>{{ contract.contractDueDate }}</td>
                        <td>{{ contract.approvalStatus || '' }}</td> <!-- 결재상태 표시 -->
                        <td>{{ contract.employee.employeeName }}</td>
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

const contracts = ref([]);
const filteredContracts = ref([]);
const searchQuery = ref('');
const searchBy = ref('계약서 코드');
const currentPage = ref(1);
const itemsPerPage = ref(10);

onMounted(async () => {
    await fetchContracts();
    await fetchApprovalStatuses();
    applyFilter();
});

const fetchContracts = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract');
        contracts.value = response.data.map(contract => ({
            contractId: contract.contractId,
            contractCode: contract.contractCode,
            contractTotalPrice: contract.contractTotalPrice,
            contractDate: contract.contractDate,
            contractDueDate: contract.contractDueDate,
            employee: {
                employeeName: contract.employee.employeeName
            }
        })).sort((a, b) => b.contractId - a.contractId); // Sort by contractId in descending order
        filteredContracts.value = contracts.value;
    } catch (error) {
        console.error('계약서 정보를 조회하는 중 오류가 발생했습니다.', error);
    }
};

const fetchApprovalStatuses = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract');
        const approvals = response.data;
        approvals.forEach(approval => {
            const contract = contracts.value.find(con => con.contractId === approval.contract.contractId);
            if (contract) {
                contract.approvalStatus = approval.approvalStatus.approvalStatus;
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
        filteredContracts.value = contracts.value;
    } else {
        const query = searchQuery.value.toUpperCase(); // 검색어를 대문자로 변환
        filteredContracts.value = contracts.value.filter(contract => {
            if (searchBy.value === '계약서 코드') {
                return contract.contractCode.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            } else if (searchBy.value === '담당자') {
                return contract.employee.employeeName.toUpperCase().includes(query); // 대상 문자열을 대문자로 변환 후 비교
            }
        });
    }
    currentPage.value = 1; // 필터 적용 시 첫 페이지로 이동
}

const paginatedContracts = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return filteredContracts.value.slice(start, end);
});

const totalPages = computed(() => {
    return Math.ceil(filteredContracts.value.length / itemsPerPage.value);
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}

function goToContractContents(contractId) {
    router.push({ path: `/contract/${contractId}` });
}
</script>



<style>
    @import url('@/assets/css/contract/ContractList.css');
</style>
