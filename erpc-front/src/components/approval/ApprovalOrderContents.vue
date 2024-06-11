<template>
    <div class="regist-content7" v-if="approveShipmentData">
        <div class="order-search">
            <h1 class="maintext">수주 결재 요청 내역</h1>

            <div class="shipment-btn" v-if="approveShipmentData.approvalStatus.approvalStatusId === 1">
                <button class="shipment-approve" @click="openApprovalPopup">결재 승인</button>
                <button class="shipment-reject" @click="openRejectPopup">결재 반려</button>
            </div>

            <div class="order-approval-note1" v-if="approveShipmentData.approvalContent">
                <h3 class="order-approval-note2">결재 비고란</h3>
                <div class="order-approval-note3">{{ approveShipmentData.approvalContent }}</div>
            </div>

            <div class="order-approval-attachment">
                <div v-if="approveShipmentData.order.orderFile && approveShipmentData.order.orderFile.length > 0">
                    <div v-for="file in approveShipmentData.order.orderFile" :key="file.fileId" class="file-download">
                        <button class="order-approval-attachment1" @click="downloadFile(file.accessUrl)">
                            {{ file.originName }} 다운로드
                        </button>
                    </div>
                </div>
                <div v-else class="file-download no-file">
                    첨부파일 없음
                </div>
            </div>
        </div>
        <div class="shipment-list-box">
            <table class="shipment2-table1">
                <thead>
                    <tr>
                        <th>수주서 코드</th>
                        <th>수주 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveShipmentData.order.transaction.transactionCode }}</td>
                        <td>{{ approveShipmentData.order.orderTotalPrice.toLocaleString() }}</td>
                        <td>{{ approveShipmentData.order.orderDate }}</td>
                        <td>{{ approveShipmentData.order.orderDeleteDate }}</td>
                        <td>{{ approveShipmentData.order.orderDueDate }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="shipment2-table2">
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
                    <tr v-for="product in approveShipmentData.order.orderProduct" :key="product.orderProductId">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.orderProductCount }}</td>
                        <td>{{ product.product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.orderSupplyPrice.toLocaleString() }}</td>
                        <td>{{ product.orderProductionNote }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="shipment2-table3">
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
                        <td>{{ approveShipmentData.order.warehouse.warehouseCode }}</td>
                        <td>{{ approveShipmentData.order.warehouse.warehouseName }}</td>
                        <td>{{ approveShipmentData.order.warehouse.warehouseType }}</td>
                        <td>{{ approveShipmentData.order.warehouse.warehouseLocation }}</td>
                        <td>{{ approveShipmentData.order.warehouse.warehouseUsage }}</td>
                        <td>{{ approveShipmentData.order.warehouse.productionLineName }}</td>
                        <td>{{ approveShipmentData.order.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="shipment2-table4">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처명</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ approveShipmentData.order.employee.employeeName }}</td>
                        <td>{{ approveShipmentData.order.account.accountName }}</td>
                        <td>{{ approveShipmentData.order.orderNote }}</td>
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
const approveShipmentData = ref(null);
const showApprovalPopup = ref(false);
const showRejectPopup = ref(false);
const approvalContent = ref('');
const rejectContent = ref('');
const actionCompleted = ref(false);

const fetchApproveShipmentData = async () => {
    const orderRegistrationId = route.params.orderRegistrationId;
    if (!orderRegistrationId) {
        console.error("Order Registration ID is undefined");
        return;
    }
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment/${orderRegistrationId}`);
        approveShipmentData.value = response.data;
    } catch (error) {
        console.error("Error fetching approval shipment data:", error);
    }
};

onMounted(fetchApproveShipmentData);

const downloadFile = (url) => {
    window.open(url, '_blank');
};

const openApprovalPopup = () => {
    approvalContent.value = '수주 승인';
    showApprovalPopup.value = true;
};

const closeApprovalPopup = () => {
    showApprovalPopup.value = false;
};

const submitApproval = async () => {
    const payload = {
        shipmentApprovalId: approveShipmentData.value.shipmentApprovalId,
        approvalContent: approvalContent.value,
        approvalStatus: {
            approvalStatusId: 2
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment/process`, payload);
        actionCompleted.value = true;
        showApprovalPopup.value = false;
        fetchApproveShipmentData();
    } catch (error) {
        console.error("Error submitting approval:", error);
    }
};

const openRejectPopup = () => {
    rejectContent.value = '수주 반려';
    showRejectPopup.value = true;
};

const closeRejectPopup = () => {
    showRejectPopup.value = false;
};

const submitRejection = async () => {
    const payload = {
        shipmentApprovalId: approveShipmentData.value.shipmentApprovalId,
        approvalContent: rejectContent.value,
        approvalStatus: {
            approvalStatusId: 3
        }
    };
    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment/process`, payload);
        actionCompleted.value = true;
        showRejectPopup.value = false;
        fetchApproveShipmentData();
    } catch (error) {
        console.error("Error submitting rejection:", error);
    }
};
</script>

<style>
    @import url('@/assets/css/approval/ApprovalOrderContents.css');
</style>
