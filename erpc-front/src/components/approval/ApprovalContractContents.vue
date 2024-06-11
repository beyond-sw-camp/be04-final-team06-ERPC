<template>
    <div class="regist-content7" v-if="approveContractData">
        <div class="order-search">
            <h1 class="maintext">계약서 결재 요청 내역</h1>

            <div class="contract-btn" v-if="approveContractData.approvalStatus.approvalStatusId === 1">
                <button class="contract-approve" @click="openApprovalPopup">결재 승인</button>
                <button class="contract-reject" @click="openRejectPopup">결재 반려</button>
            </div>

            <div class="contract-approval-note1" v-if="approveContractData.approvalContent">
                <h3 class="contract-approval-note2">결재 비고란</h3>
                <div class="contract-approval-note3">{{ approveContractData.approvalContent }}</div>
            </div>

            <div class="contract-approval-attachment">
                <div v-if="approveContractData.contract.contractFile && approveContractData.contract.contractFile.length > 0">
                    <div v-for="file in approveContractData.contract.contractFile" :key="file.fileId" class="file-download">
                        <button class="contract-approval-attachment1" @click="downloadFile(file.accessUrl)">
                            {{ file.originName }} 다운로드
                        </button>
                    </div>
                </div>
                <div v-else class="file-download no-file">
                    첨부파일 없음
                </div>
            </div>
        </div>
        <div class="contract-list-box">
            <table class="contract2-table1">
                <thead>
                    <tr>
                        <th>계약서 코드</th>
                        <th>계약 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveContractData.contract.contractCode }}</td>
                        <td>{{ approveContractData.contract.contractTotalPrice.toLocaleString() }}</td>
                        <td>{{ approveContractData.contract.contractDate }}</td>
                        <td>{{ approveContractData.contract.contractDeleteDate }}</td>
                        <td>{{ approveContractData.contract.contractDueDate }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table2">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>공급가액</th>
                        <th>기타</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in approveContractData.contract.contractProduct" :key="product.contractProductId">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.contractProductCount }}</td>
                        <td>{{ product.product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.contractSupplyPrice.toLocaleString() }}</td>
                        <td>{{ product.contractProductionNote }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table3">
                <thead>
                    <tr>
                        <th>창고 코드</th>
                        <th>창고명</th>
                        <th>구분</th>
                        <th>창고 주소</th>
                        <th>사용 여부</th>
                        <th>생산 공정명</th>
                        <th>외주 거래처명</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveContractData.contract.warehouse.warehouseCode }}</td>
                        <td>{{ approveContractData.contract.warehouse.warehouseName }}</td>
                        <td>{{ approveContractData.contract.warehouse.warehouseType }}</td>
                        <td>{{ approveContractData.contract.warehouse.warehouseLocation }}</td>
                        <td>{{ approveContractData.contract.warehouse.warehouseUsage }}</td>
                        <td>{{ approveContractData.contract.warehouse.productionLineName }}</td>
                        <td>{{ approveContractData.contract.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table4">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처명</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveContractData.contract.employee.employeeName }}</td>
                        <td>{{ approveContractData.contract.account.accountName }}</td>
                        <td>{{ approveContractData.contract.contractNote }}</td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Approval Popup -->
        <div v-if="showApprovalPopup" class="popup-overlay">
            <div class="popup-content">
                <h2>결재 승인</h2>
                <textarea v-model="approvalContent" placeholder="승인 내용을 입력하세요"></textarea>
                <button class="confirm-btn" @click="submitApproval">확인</button>
                <button class="cancel-btn" @click="closeApprovalPopup">취소</button>
            </div>
        </div>

        <!-- Reject Popup -->
        <div v-if="showRejectPopup" class="popup-overlay">
            <div class="popup-content">
                <h2>결재 반려</h2>
                <textarea v-model="rejectContent" placeholder="반려 사유를 입력하세요"></textarea>
                <button class="confirm-btn" @click="submitRejection">확인</button>
                <button class="cancel-btn" @click="closeRejectPopup">취소</button>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading...</p>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const approveContractData = ref(null);
const showApprovalPopup = ref(false);
const showRejectPopup = ref(false);
const approvalContent = ref('');
const rejectContent = ref('');
const actionCompleted = ref(false);

const fetchApproveContractData = async () => {
    const contractId = route.params.contractId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract/${contractId}`);
        approveContractData.value = response.data;
    } catch (error) {
        console.error("Error fetching approval contract data:", error);
    }
};

onMounted(fetchApproveContractData);

const downloadFile = (url) => {
    window.open(url, '_blank');
};

const openApprovalPopup = () => {
    approvalContent.value = '계약 승인';
    showApprovalPopup.value = true;
};

const closeApprovalPopup = () => {
    showApprovalPopup.value = false;
};

const submitApproval = async () => {
    const payload = {
        contractApprovalId: approveContractData.value.contractApprovalId,
        approvalContent: approvalContent.value,
        approvalStatus: {
            approvalStatusId: 2
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract/process`, payload);
        actionCompleted.value = true;
        showApprovalPopup.value = false;
        fetchApproveContractData();
    } catch (error) {
        console.error("Error submitting approval:", error);
    }
};

const openRejectPopup = () => {
    rejectContent.value = '계약 반려';
    showRejectPopup.value = true;
};

const closeRejectPopup = () => {
    showRejectPopup.value = false;
};

const submitRejection = async () => {
    const payload = {
        contractApprovalId: approveContractData.value.contractApprovalId,
        approvalContent: rejectContent.value,
        approvalStatus: {
            approvalStatusId: 3
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract/process`, payload);
        actionCompleted.value = true;
        showRejectPopup.value = false;
        fetchApproveContractData();
    } catch (error) {
        console.error("Error submitting rejection:", error);
    }
};
</script>


<style>
    @import url('@/assets/css/approval/ApprovalContractContents.css');
</style>
