<template>
    <div class="approval-content1">
        <div class="approval-search">
            <h1 class="approval-search-text">결재 승인 요청 목록</h1>
        </div>
        <div class="approval-box">
            <div class="search-date">
                <input type="date" class="search-start-date-box" id="search-start-date-box" v-model="startDate">
                <span>-</span>
                <input type="date" class="search-end-date-box" id="search-end-date-box" v-model="endDate">
                <select v-model="selectedApprovalType" @change="handleDropdownChange" class="approval-type-select">
                    <option value="quotation">견적서 결재</option>
                    <option value="contract">계약서 결재</option>
                    <option value="shipment">수주 결재</option>
                </select>
                <select v-model="selectedApprovalStatus" @change="handleDropdownChange" class="approval-status-select">
                    <option value="">전체</option>
                    <option value="결재요청">결재요청</option>
                    <option value="승인">승인</option>
                    <option value="반려">반려</option>
                </select>
                <input type="text" class="approval-search-input" v-model="searchQuery" :placeholder="placeholderText" @input="toUpperCase">
                <button class="approval-search-btn" @click="applyFilter">조회하기</button>
            </div>
        </div>

        <div class="approval-list-box">
            <template v-if="selectedApprovalType === 'quotation'">
                <h2>견적서 결재</h2>
                <table class="approval-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>견적서 코드</th>
                            <th>거래처명</th>
                            <th>견적금액</th>
                            <th>요청일자</th>
                            <th>결재상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(approval, index) in paginatedApprovals" :key="approval.quotationApprovalId" @click="goToApprovalContents(approval.quotationApprovalId)">
                            <td>{{ sortedFilteredApprovals.length - ((currentPage - 1) * itemsPerPage + index) }}</td>
                            <td>{{ approval.quotation.quotationCode }}</td>
                            <td>{{ approval.quotation.account.accountName }}</td>
                            <td>{{ approval.quotation.quotationTotalCost.toLocaleString() }}</td>
                            <td>{{ approval.approvalRequestDate }}</td>
                            <td>{{ approval.approvalStatus.approvalStatus }}</td>
                        </tr>
                    </tbody>
                </table>
            </template>

            <template v-if="selectedApprovalType === 'contract'">
                <h2>계약서 결재</h2>
                <table class="approval-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>계약서 코드</th>
                            <th>거래처명</th>
                            <th>계약금액</th>
                            <th>요청일자</th>
                            <th>결재상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(approval, index) in paginatedContractApprovals" :key="approval.contractApprovalId" @click="goToContractContents(approval.contractApprovalId)">
                            <td>{{ sortedFilteredContractApprovals.length - ((currentPage - 1) * itemsPerPage + index) }}</td>
                            <td>{{ approval.contract.contractCode }}</td>
                            <td>{{ approval.contract.account.accountName }}</td>
                            <td>{{ approval.contract.contractTotalPrice.toLocaleString() }}</td>
                            <td>{{ approval.approvalRequestDate }}</td>
                            <td>{{ approval.approvalStatus.approvalStatus }}</td>
                        </tr>
                    </tbody>
                </table>
            </template>

            <template v-if="selectedApprovalType === 'shipment'">
                <h2>수주 결재</h2>
                <table class="approval-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>프로젝트 코드</th>
                            <th>거래처명</th>
                            <th>주문금액</th>
                            <th>요청일자</th>
                            <th>결재상태</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(approval, index) in paginatedShipmentApprovals" :key="approval.shipmentApprovalId" @click="goToShipmentContents(approval.shipmentApprovalId)">
                            <td>{{ sortedFilteredShipmentApprovals.length - ((currentPage - 1) * itemsPerPage + index) }}</td>
                            <td>{{ approval.order.transaction.transactionCode }}</td>
                            <td>{{ approval.order.account.accountName }}</td>
                            <td>{{ approval.order.orderTotalPrice.toLocaleString() }}</td>
                            <td>{{ approval.approvalRequestDate }}</td>
                            <td>{{ approval.approvalStatus.approvalStatus }}</td>
                        </tr>
                    </tbody>
                </table>
            </template>
        </div>

        <div class="pagination">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
            <button v-for="page in totalPages" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
        </div>
    </div>
</template>


<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const router = useRouter();

const approvals = ref([]);
const contractApprovals = ref([]);
const shipmentApprovals = ref([]);
const startDate = ref('');
const endDate = ref('');
const searchQuery = ref('');
const selectedApprovalType = ref('quotation');
const selectedApprovalStatus = ref('');
const filteredApprovals = ref([]);
const filteredContractApprovals = ref([]);
const filteredShipmentApprovals = ref([]);
const currentPage = ref(1);
const itemsPerPage = ref(10);

const placeholderText = computed(() => {
    switch (selectedApprovalType.value) {
        case 'quotation':
            return '견적서 코드로 조회';
        case 'contract':
            return '계약서 코드로 조회';
        case 'shipment':
            return '수주 코드로 조회';
        default:
            return '코드로 조회';
    }
});

const toUpperCase = () => {
    searchQuery.value = searchQuery.value.toUpperCase();
};

const handleDropdownChange = () => {
    searchQuery.value = '';  // Clear the search input
    applyFilter();           // Apply filter immediately
};

onMounted(async () => {
    await fetchApprovals();
    await fetchContractApprovals();
    await fetchShipmentApprovals();
    applyFilter();
});

watch([selectedApprovalStatus, selectedApprovalType], () => {
    applyFilter();
});

const fetchApprovals = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation');
        approvals.value = response.data;
        filteredApprovals.value = approvals.value;
    } catch (error) {
        console.error('Error fetching approvals:', error);
    }
};

const fetchContractApprovals = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract');
        contractApprovals.value = response.data;
        filteredContractApprovals.value = contractApprovals.value;
    } catch (error) {
        console.error('Error fetching contract approvals:', error);
    }
};

const fetchShipmentApprovals = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment');
        shipmentApprovals.value = response.data;
        filteredShipmentApprovals.value = shipmentApprovals.value;
    } catch (error) {
        console.error('Error fetching shipment approvals:', error);
    }
};

const resetApprovalStatus = () => {
    selectedApprovalStatus.value = '';
};

const applyFilter = () => {
    filteredApprovals.value = approvals.value;
    filteredContractApprovals.value = contractApprovals.value;
    filteredShipmentApprovals.value = shipmentApprovals.value;

    if (startDate.value && endDate.value) {
        filteredApprovals.value = filteredApprovals.value.filter(approval => {
            return approval.approvalRequestDate >= startDate.value && approval.approvalRequestDate <= endDate.value;
        });
        filteredContractApprovals.value = filteredContractApprovals.value.filter(approval => {
            return approval.approvalRequestDate >= startDate.value && approval.approvalRequestDate <= endDate.value;
        });
        filteredShipmentApprovals.value = filteredShipmentApprovals.value.filter(approval => {
            return approval.approvalRequestDate >= startDate.value && approval.approvalRequestDate <= endDate.value;
        });
    }

    if (searchQuery.value) {
        filteredApprovals.value = filteredApprovals.value.filter(approval =>
            approval.quotation.quotationCode.includes(searchQuery.value)
        );
        filteredContractApprovals.value = filteredContractApprovals.value.filter(approval =>
            approval.contract.contractCode.includes(searchQuery.value)
        );
        filteredShipmentApprovals.value = filteredShipmentApprovals.value.filter(approval =>
            approval.order.transaction.transactionCode.includes(searchQuery.value)
        );
    }

    if (selectedApprovalStatus.value) {
        filteredApprovals.value = filteredApprovals.value.filter(approval =>
            approval.approvalStatus.approvalStatus === selectedApprovalStatus.value
        );
        filteredContractApprovals.value = filteredContractApprovals.value.filter(approval =>
            approval.approvalStatus.approvalStatus === selectedApprovalStatus.value
        );
        filteredShipmentApprovals.value = filteredShipmentApprovals.value.filter(approval =>
            approval.approvalStatus.approvalStatus === selectedApprovalStatus.value
        );
    }

    // Sort filtered results by the respective ApprovalId in descending order
    filteredApprovals.value.sort((a, b) => b.quotationApprovalId - a.quotationApprovalId);
    filteredContractApprovals.value.sort((a, b) => b.contractApprovalId - a.contractApprovalId);
    filteredShipmentApprovals.value.sort((a, b) => b.shipmentApprovalId - a.shipmentApprovalId);

    currentPage.value = 1; // 필터 적용 시 첫 페이지로 이동
};

const sortedFilteredApprovals = computed(() => {
    return filteredApprovals.value.sort((a, b) => b.quotationApprovalId - a.quotationApprovalId);
});

const sortedFilteredContractApprovals = computed(() => {
    return filteredContractApprovals.value.sort((a, b) => b.contractApprovalId - a.contractApprovalId);
});

const sortedFilteredShipmentApprovals = computed(() => {
    return filteredShipmentApprovals.value.sort((a, b) => b.shipmentApprovalId - a.shipmentApprovalId);
});

const paginatedApprovals = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return sortedFilteredApprovals.value.slice(start, end);
});

const paginatedContractApprovals = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return sortedFilteredContractApprovals.value.slice(start, end);
});

const paginatedShipmentApprovals = computed(() => {
    const start = (currentPage.value - 1) * itemsPerPage.value;
    const end = start + itemsPerPage.value;
    return sortedFilteredShipmentApprovals.value.slice(start, end);
});

const totalPages = computed(() => {
    switch (selectedApprovalType.value) {
        case 'quotation':
            return Math.ceil(filteredApprovals.value.length / itemsPerPage.value);
        case 'contract':
            return Math.ceil(filteredContractApprovals.value.length / itemsPerPage.value);
        case 'shipment':
            return Math.ceil(filteredShipmentApprovals.value.length / itemsPerPage.value);
        default:
            return 1;
    }
});

function changePage(page) {
    if (page > 0 && page <= totalPages.value) {
        currentPage.value = page;
    }
}

const goToApprovalContents = (quotationApprovalId) => {
    router.push({ path: `/approval/quotation/${quotationApprovalId}` });
};

const goToContractContents = (contractApprovalId) => {
    router.push({ path: `/approval/contract/${contractApprovalId}` });
};

const goToShipmentContents = (shipmentApprovalId) => {
    router.push({ path: `/approval/order/${shipmentApprovalId}` });
};
</script>



<style>
    @import url('@/assets/css/approval/ApprovalList.css');
</style>
