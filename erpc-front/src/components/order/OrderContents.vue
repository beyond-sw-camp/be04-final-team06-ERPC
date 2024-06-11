<template>
    <div class="order-content11" v-if="orderData">
        <div class="order-search11">
            <h1>수주 정보 조회 내역</h1>
            <h3 class="maintext2">결재 승인</h3>
            <div class="order-btn">
                <div class="order-btn2" v-if="!['결재요청', '승인', '반려'].includes(approvalStatus)">
                    <button class="order-request" @click="requestApproval" :disabled="deleteRequested">결재요청</button>
                </div>
                <button class="order-edit" @click="goToOrderPage" :disabled="deleteRequested">수정</button>
                <button class="order-delete" v-if="showDeleteButton" @click="deleteOrder">삭제요청</button>
                <button class="order-excel" @click="downloadExcel">엑셀다운</button>
            </div>
            <div class="order-approval-note1" v-if="['승인', '반려'].includes(approvalStatus) && orderData.approvalContent">
                <h3 class="order-approval-note2">결재 비고란</h3>
                <div class="order-approval-note3">{{ orderData.approvalContent }}</div>
            </div>
            <div class="order-pdf77">
                <div v-if="orderData.orderFile.length > 0">
                    <div v-for="file in orderData.orderFile" :key="file.fileId" class="file-download">
                        <button class="order-pdf33" @click="downloadFile(file.accessUrl)">
                            {{ file.originName }} 다운로드
                        </button>
                    </div>
                </div>
                <div v-else class="file-download no-file">
                    첨부파일 없음
                </div>
            </div>
        </div>
        <div class="order-list-box2">
            <table class="order2-table1">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>계약 일자</th>
                        <th>작성 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.transaction.transactionCode }}</td>
                        <td>{{ orderData.contactDate }}</td>
                        <td>{{ orderData.orderDate }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table2">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                        <th>Billing 잔액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.employee.employeeName }}</td>
                        <td>{{ orderData.account.accountCode }}</td>
                        <td>{{ orderData.account.accountName }}</td>
                        <td>{{ orderData.orderTotalPrice.toLocaleString() }}</td>
                        <td>{{ orderData.totalBalance.toLocaleString() }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table3">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>합계</th>
                        <th>기타</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in orderData.orderProduct" :key="product.product.productId">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.orderProductCount }}</td>
                        <td>{{ product.product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.orderSupplyPrice.toLocaleString() }}</td>
                        <td>{{ product.orderProductionNote }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table4">
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
                        <td>{{ orderData.warehouse.warehouseCode }}</td>
                        <td>{{ orderData.warehouse.warehouseName }}</td>
                        <td>{{ orderData.warehouse.warehouseType }}</td>
                        <td>{{ orderData.warehouse.warehouseLocation }}</td>
                        <td>{{ orderData.warehouse.warehouseUsage }}</td>
                        <td>{{ orderData.warehouse.productionLineName }}</td>
                        <td>{{ orderData.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>
            <table v-if="orderData.contractCategory.contractCategoryId === 1" class="order2-table5">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>일시납부</td>
                        <td>{{ orderData.orderTotalPrice.toLocaleString() }}</td>
                    </tr>
                </tbody>
            </table>
            <table v-else class="order2-table6">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th>계약금</th>
                        <th>중도금(선금)</th>
                        <th>잔금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>분할납부</td>
                        <td>{{ orderData.downPayment.toLocaleString() }}</td>
                        <td>{{ orderData.progressPayment.toLocaleString() }}</td>
                        <td>{{ orderData.balance.toLocaleString() }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table7">
                <thead>
                    <tr>
                        <th>입금 코드</th>
                        <th>입금자명</th>
                        <th>입금 금액</th>
                        <th>입금 일자</th>
                        <th>발행 요청 일자</th>
                        <th>비고</th>
                        <th>승인 여부</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="taxInvoice in taxInvoiceRequestData" :key="taxInvoice.taxInvoiceRequestId">
                        <td>{{ taxInvoice.collection.depositCode }}</td>
                        <td>{{ taxInvoice.collection.depositPic }}</td>
                        <td>{{ taxInvoice.collection.depositPrice.toLocaleString() }}</td>
                        <td>{{ taxInvoice.collection.depositDate }}</td>
                        <td>{{ taxInvoice.taxInvoiceRequestDate }}</td>
                        <td>{{ taxInvoice.taxInvoiceNote }}</td>
                        <td>{{ taxInvoice.taxInvoiceRequestStatus?.taxInvoiceRequestStatus }}</td>
                    </tr>
                    <tr v-if="taxInvoiceRequestData.length === 0">
                        <td colspan="7">세금계산서 요청 내역이 없습니다.</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="shipment-search">
            <h1 class="maintext3">출하 정보</h1>
            <h3 class="maintext4">{{ orderData.shipmentStatus.shipmentStatus }}</h3>
        </div>
        <div class="shipment-list-box">
            <table :class="['shipment-table333', shipmentStatusClass]">
                <thead>
                    <tr>
                        <th>출고 예정일</th>
                        <th>실사 출고일</th>
                        <th>수령 예정일</th>
                        <th>수령 확정일</th>
                        <th>수주 완료일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.estimatedDeliveryDate }}</td>
                        <td>{{ orderData.estimatedReleaseDate }}</td>
                        <td>{{ orderData.estimatedArriveDate }}</td>
                        <td>{{ orderData.arriveDate }}</td>
                        <td>{{ orderData.releaseDate }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order22-process-box">
            <h1 class="order22-process-text">Process</h1>
            <div v-for="note in filteredOrderNotes" :key="note.orderNoteId" class="order22-process-box-detail">
                <div class="order22-process-info">
                    <h4 class="order22-process-writer">{{ employeeName }}</h4>
                    <p class="order22-process-date">{{ note.orderNoteDate }}</p>
                </div>
                <div class="order22-process-detail">
                    {{ note.orderNote }}
                </div>
                <div class="order22-process-btn">
                    <button class="order22-process-delete" @click="deleteNote(note.orderNoteId)">삭제하기</button>
                </div>
            </div>
            <div class="order22-process-reply">
                <input type="text" v-model="newNote" id="order22-process-reply-box" class="order22-process-reply-box"
                    placeholder="내용을 입력해주세요.">
                <div class="order22-process-btn2">
                    <button class="order22-process-regist" @click="addNote">등록하기</button>
                </div>
            </div>
        </div>
    </div>
    <div v-else>
        Loading...
    </div>
    <!-- 삭제 요청 팝업 -->
    <div v-if="showPopup" class="popup-overlay">
        <div class="popup-content">
            <h2>삭제 요청 사유 입력</h2>
            <textarea v-model="deleteReason" placeholder="삭제 사유를 입력하세요"></textarea>
            <button @click="confirmDelete" class="confirm-btn">확인</button>
            <button @click="closePopup" class="cancel-btn">취소</button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const orderData = ref(null);
const orderNoteData = ref([]); // 수주 노트 데이터를 저장하는 배열
const showPopup = ref(false);
const deleteReason = ref('');
const newNote = ref('');
const employeeName = ref('');
const employeeId = ref('');
const taxInvoiceRequestData = ref([]); // 세금계산서 요청 데이터를 저장하는 배열
const approvalStatus = ref('Pending'); // Default value as Pending
const showDeleteButton = ref(true); // 삭제 버튼 표시 여부
const deleteRequested = ref(false); // 삭제 요청 상태를 저장

// filteredOrderNotes는 orderDeleteDate가 null인 노트만 반환합니다.
const filteredOrderNotes = computed(() => {
    return orderNoteData.value.filter(note => note.orderDeleteDate === null);
});

const shipmentStatusClass = computed(() => {
    if (orderData.value?.shipmentStatus?.shipmentStatusId === 1) {
        return 'shipment-status-requested'; // 회색
    } else if (orderData.value?.shipmentStatus?.shipmentStatusId === 2) {
        return 'shipment-status-in-progress'; // 노란색
    } else if (orderData.value?.shipmentStatus?.shipmentStatusId === 3) {
        return 'shipment-status-completed'; // 녹색
    } else {
        return 'shipment-status-requested'; // 기본값 회색
    }
});


onMounted(async () => {
    const orderRegistrationId = route.params.orderRegistrationId;
    console.log('Order Registration ID:', orderRegistrationId);  // Debugging line

    if (!orderRegistrationId) {
        console.error('Order Registration ID is not defined');
        return;
    }

    const userId = localStorage.getItem('userId'); // userId를 localStorage에서 가져오기

    try {
        // 수주 데이터를 가져오는 API 호출
        const orderResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order/${orderRegistrationId}`);
        orderData.value = orderResponse.data;

        // 수주 노트 데이터를 가져오는 API 호출
        const noteResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order_note/${orderRegistrationId}`);
        orderNoteData.value = noteResponse.data;

        // userId로 직원 이름을 가져오는 API 호출
        const employeeResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`);
        employeeName.value = employeeResponse.data.employeeName;

        // 세금계산서 요청 데이터를 가져오는 API 호출
        if (orderData.value.taxInvoiceRequest && orderData.value.taxInvoiceRequest.length > 0) {
            for (const taxInvoice of orderData.value.taxInvoiceRequest) {
                const taxInvoiceResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/tax_invoice/${taxInvoice.taxInvoiceRequestId}`);
                taxInvoiceRequestData.value.push(taxInvoiceResponse.data);
            }
        }

        // 전체 승인 데이터를 가져오는 API 호출
        const approvalResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment');
        const approvalData = approvalResponse.data;

        // 현재 수주에 해당하는 결재 상태를 찾기
        const currentApproval = approvalData.find(approval => approval.order.orderRegistrationId === parseInt(orderRegistrationId));
        if (currentApproval) {
            approvalStatus.value = currentApproval.approvalStatus.approvalStatus;
            orderData.value.approvalContent = currentApproval.approvalContent; // 비고란 내용 설정
        }

        // 전체 삭제 요청 데이터를 가져오는 API 호출
        const deleteResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/delete/order/${orderRegistrationId}`);
        const deleteData = deleteResponse.data;

        // 현재 수주에 해당하는 삭제 요청 상태를 찾기
        if (deleteData) {
            showDeleteButton.value = false; // 삭제 요청이 있으면 삭제 버튼 숨기기
            deleteRequested.value = true; // 삭제 요청 상태를 true로 설정
        }

    } catch (error) {
        console.error('Error fetching order data:', error);
    }
});

// 결재 요청 함수
const requestApproval = async () => {
    if (deleteRequested.value) {
        alert('삭제 요청한 수주는 결재 요청할 수 없습니다.');
        return;
    }

    const orderRegistrationId = route.params.orderRegistrationId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/shipment/regist', {
            order: { orderRegistrationId: orderRegistrationId }
        });
        alert('결재 요청이 성공적으로 완료되었습니다.');
        console.log('Approval request sent successfully:', response.data);
        approvalStatus.value = 'Requested'; // 결재 요청 후 상태를 업데이트
        location.reload();
    } catch (error) {
        console.error('Error sending approval request:', error);
        alert('결재 요청 중 오류가 발생했습니다.');
    }
};

// 수주 수정 페이지로 이동하는 함수
const goToOrderPage = () => {
    if (deleteRequested.value) {
        alert('삭제 요청한 수주는 수정할 수 없습니다.');
        return;
    }

    router.push({ path: `/order/modify/${route.params.orderRegistrationId}` });
};

// 파일 다운로드 함수
const downloadFile = (url) => {
    const link = document.createElement('a');
    link.href = url;
    link.download = url.split('/').pop();
    link.target = '_blank';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
};

// 엑셀 다운로드 함수
const downloadExcel = () => {
    const orderRegistrationId = route.params.orderRegistrationId;
    const url = `http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/excel/order/${orderRegistrationId}`;
    const link = document.createElement('a');
    link.href = url;
    link.download = `order_${orderRegistrationId}.xlsx`;
    link.target = '_blank';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
};

// 수주 삭제 요청 함수
const deleteOrder = () => {
    showPopup.value = true;
};

// 팝업 닫기 함수
const closePopup = () => {
    showPopup.value = false;
};

// 수주 삭제 확인 함수
const confirmDelete = async () => {
    const orderRegistrationId = route.params.orderRegistrationId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order/delete', {
            orderDeleteRequestReason: deleteReason.value,
            order: orderData.value
        });
        console.log('Order delete request sent successfully:', response.data);
        alert('삭제 요청이 성공적으로 완료되었습니다.');
        location.reload();
        // router.push('/order');
    } catch (error) {
        console.error('Error sending delete request:', error);
        alert('삭제 요청 중 오류가 발생했습니다.');
    } finally {
        closePopup();
    }
};

// 노트 추가 함수
const addNote = async () => {
    const orderRegistrationId = route.params.orderRegistrationId;
    const userId = localStorage.getItem('userId'); // userId를 localStorage에서 가져오기
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order_note/regist', {
            orderNote: newNote.value,
            order: { orderRegistrationId: orderRegistrationId },
            employee: { employeeId: userId } // employeeId를 userId로 설정
        });
        alert('process 등록되었습니다.');
        console.log('order note added successfully:', response.data);
        orderNoteData.value.push(response.data);
        newNote.value = '';
        location.reload(); // 페이지 새로고침 추가
    } catch (error) {
        console.error('Error adding order note:', error);
        alert('노트 추가 중 오류가 발생했습니다.');
    }
};

// 노트 삭제 함수
const deleteNote = async (orderNoteId) => {
    try {
        const response = await axios.patch('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order_note/delete', null, {
            params: {
                orderNoteId
            }
        });
        const updatedNote = response.data;
        const noteIndex = orderNoteData.value.findIndex(note => note.orderNoteId === orderNoteId);
        alert('process 삭제되었습니다.');
        if (noteIndex !== -1) {
            orderNoteData.value[noteIndex] = updatedNote;
        }
    } catch (error) {
        console.error('Error deleting note:', error);
        alert('노트 삭제 중 오류가 발생했습니다.');
    }
};
</script>


<style>
    @import url('@/assets/css/order/OrderContents.css');
</style>
