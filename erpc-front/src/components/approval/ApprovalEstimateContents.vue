<template>
    <div class="regist-content7" v-if="approveQuotationData">
        <div class="order-search">
            <h1 class="maintext">견적서 결재 요청 내역</h1>

            <div class="estimate-btn" v-if="approveQuotationData.approvalStatus.approvalStatusId === 1">
                <button class="estimate-approve" @click="openApprovalPopup">결재 승인</button>
                <button class="estimate-reject" @click="openRejectPopup">결재 반려</button>
            </div>

            <div class="estimate-approval-note1" v-if="approveQuotationData.approvalContent">
                <h3 class="estimate-approval-note2">결재 비고란</h3>
                <div class="estimate-approval-note3">{{ approveQuotationData.approvalContent }}</div>
            </div>

            <div class="estimate-approval-attachment">
                <div v-if="approveQuotationData.quotation.quotationFile.length > 0">
                    <div v-for="file in approveQuotationData.quotation.quotationFile" :key="file.fileId" class="file-download">
                        <button class="estimate-approval-attachment1" @click="downloadFile(file.accessUrl)">
                            {{ file.originName }} 다운로드
                        </button>
                    </div>
                </div>
                <div v-else class="file-download no-file">
                    첨부파일 없음
                </div>
            </div>
        </div>
        <div class="estimate-list-box">
            <table class="estimate2-table1">
                <thead>
                    <tr>
                        <th>견적서 코드</th>
                        <th>견적 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveQuotationData.quotation.quotationCode }}</td>
                        <td>{{ approveQuotationData.quotation.quotationTotalCost.toLocaleString() }}</td>
                        <td>{{ approveQuotationData.quotation.quotationDate }}</td>
                        <td>{{ approveQuotationData.quotation.quotationDeleteDate }}</td>
                        <td>{{ approveQuotationData.quotation.quotationDueDate }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate2-table2">
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
                    <tr v-for="product in approveQuotationData.quotation.quotationProduct" :key="product.quotationProductId">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.quotationProductCount }}</td>
                        <td>{{ product.product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.quotationSupplyPrice.toLocaleString() }}</td>
                        <td>{{ product.quotationProductionNote }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate2-table3">
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
                        <td>{{ approveQuotationData.quotation.warehouse.warehouseCode }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.warehouseName }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.warehouseType }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.warehouseLocation }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.warehouseUsage }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.productionLineName }}</td>
                        <td>{{ approveQuotationData.quotation.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate2-table4">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처명</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveQuotationData.quotation.employee.employeeName }}</td>
                        <td>{{ approveQuotationData.quotation.account.accountName }}</td>
                        <td>{{ approveQuotationData.quotation.quotationNote }}</td>
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
const approveQuotationData = ref(null);
const showApprovalPopup = ref(false);
const showRejectPopup = ref(false);
const approvalContent = ref('');
const rejectContent = ref('');
const actionCompleted = ref(false);

const fetchApproveQuotationData = async () => {
    const quotationId = route.params.quotationId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation/${quotationId}`);
        approveQuotationData.value = response.data;
    } catch (error) {
        console.error("Error fetching approval quotation data:", error);
    }
};

onMounted(fetchApproveQuotationData);

const downloadFile = (url) => {
    window.open(url, '_blank');
};

const openApprovalPopup = () => {
    approvalContent.value = '견적 승인';
    showApprovalPopup.value = true;
};

const closeApprovalPopup = () => {
    showApprovalPopup.value = false;
};

const submitApproval = async () => {
    const payload = {
        quotationApprovalId: approveQuotationData.value.quotationApprovalId,
        approvalContent: approvalContent.value,
        approvalStatus: {
            approvalStatusId: 2
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation/process`, payload);
        actionCompleted.value = true;
        showApprovalPopup.value = false;
        fetchApproveQuotationData();
    } catch (error) {
        console.error("Error submitting approval:", error);
    }
};

const openRejectPopup = () => {
    rejectContent.value = '견적 반려';
    showRejectPopup.value = true;
};

const closeRejectPopup = () => {
    showRejectPopup.value = false;
};

const submitRejection = async () => {
    const payload = {
        quotationApprovalId: approveQuotationData.value.quotationApprovalId,
        approvalContent: rejectContent.value,
        approvalStatus: {
            approvalStatusId: 3
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation/process`, payload);
        actionCompleted.value = true;
        showRejectPopup.value = false;
        fetchApproveQuotationData();
    } catch (error) {
        console.error("Error submitting rejection:", error);
    }
};
</script>

<style>
    @import url('@/assets/css/approval/ApprovalEstimateContents.css');
</style>
