<template>
    <div class="estimate-content11" v-if="quotationData">
        <div class="order-search">
            <h1>견적서 정보 조회 내역</h1>
            <div class="estimate-btn">
                <div class="estimate-btn2" v-if="!['결재요청', '승인', '반려'].includes(approvalStatus)">
                    <button class="estimate-request" @click="handleApprovalRequest" :disabled="deleteRequested">결재요청</button>
                </div>
                <button class="estimate-edit" @click="handleEditQuotation" :disabled="deleteRequested">수정</button>
                <button class="estimate-delete" v-if="showDeleteButton" @click="deleteQuotation">삭제요청</button>
                <button class="estimate-excel" @click="downloadExcel">엑셀다운</button>
            </div>
            <div class="estimate-approval-note1" v-if="['승인', '반려'].includes(approvalStatus) && quotationData.approvalContent">
                <h3 class="estimate-approval-note2">결재 비고란</h3>
                <div class="estimate-approval-note3">{{ quotationData.approvalContent }}</div>
            </div>
            <div class="estimate-pdf">
                <div v-if="filteredFiles.length > 0">
                    <div v-for="file in filteredFiles" :key="file.fileId" class="file-download">
                        <button class="estimate-pdf1" @click="downloadFile(file.accessUrl)">
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
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ quotationData.quotationCode }}</td>
                        <td>{{ quotationData.quotationTotalCost.toLocaleString() }}</td>
                        <td>{{ quotationData.quotationDate }}</td>
                        <td>{{ quotationData.quotationDueDate }}</td>
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
                    <tr v-for="product in quotationData.quotationProduct" :key="product.quotationProductId">
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
                        <td>{{ quotationData.warehouse.warehouseCode }}</td>
                        <td>{{ quotationData.warehouse.warehouseName }}</td>
                        <td>{{ quotationData.warehouse.warehouseType }}</td>
                        <td>{{ quotationData.warehouse.warehouseLocation }}</td>
                        <td>{{ quotationData.warehouse.warehouseUsage }}</td>
                        <td>{{ quotationData.warehouse.productionLineName }}</td>
                        <td>{{ quotationData.warehouse.outsourceName }}</td>
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
                        <td>{{ quotationData.employee.employeeName }}</td>
                        <td>{{ quotationData.account.accountName }}</td>
                        <td>{{ quotationData.quotationNote }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-process-box">
            <h1 class="estimate-process-text">Process</h1>
            <div v-for="note in filteredQuotationNotes" :key="note.quotationNoteId" class="estimate-process-box-detail">
                <div class="estimate-process-info">
                    <h4 class="estimate-process-writer">{{ employeeName }}</h4>
                    <p class="estimate-process-date">{{ note.quotationNoteDate }}</p>
                </div>
                <div class="estimate-process-detail">
                    {{ note.quotationNote }}
                </div>
                <div class="estimate-process-btn">
                    <button class="estimate-process-delete" @click="deleteNote(note.quotationNoteId)">삭제하기</button>
                </div>
            </div>
            <div class="estimate-process-reply">
                <input type="text" v-model="newNote" id="estimate-process-reply-box" class="estimate-process-reply-box"
                    placeholder="내용을 입력해주세요.">
                <div class="estimate-process-btn2">
                    <button class="estimate-process-regist" @click="addNote">등록하기</button>
                </div>
            </div>
        </div>
    </div>
    <div v-else>
        <p>Loading...</p>
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
const quotationData = ref(null);
const quotationNoteData = ref([]); // 견적서 노트 데이터를 저장하는 배열
const showPopup = ref(false);
const deleteReason = ref('');
const newNote = ref('');
const employeeName = ref('');
const approvalStatus = ref('Pending'); // Default value as Pending
const showDeleteButton = ref(true);
const deleteRequested = ref(false); // 삭제 요청 상태를 저장

// filteredQuotationNotes는 quotationDeleteDate가 null인 노트만 반환합니다.
const filteredQuotationNotes = computed(() => {
    return quotationNoteData.value.filter(note => note.quotationDeleteDate === null);
});

// filteredFiles는 Blob이 빈 배열인 경우를 포함하여 "blob"을 포함하지 않는 파일만 반환합니다.
const filteredFiles = computed(() => {
    return quotationData.value.quotationFile.filter(file => file.originName !== 'blob' && file.originName.length > 0);
});

// 컴포넌트가 마운트될 때 실행되는 코드
onMounted(async () => {
    const quotationId = route.params.quotationId;
    const userId = localStorage.getItem('userId'); // userId를 localStorage에서 가져오기

    try {
        // 견적서 데이터를 가져오는 API 호출
        const quotationResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation/${quotationId}`);
        quotationData.value = quotationResponse.data;

        // 견적서 노트 데이터를 가져오는 API 호출
        const noteResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation_note/${quotationId}`);
        quotationNoteData.value = noteResponse.data;

        // userId로 직원 이름을 가져오는 API 호출
        const employeeResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`);
        employeeName.value = employeeResponse.data.employeeName;

        // 전체 삭제 요청 데이터를 가져오는 API 호출
        const deleteResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/delete/quotation');
        const deleteData = deleteResponse.data;

        // 현재 견적서에 해당하는 삭제 요청 상태를 찾기
        const currentDeleteRequest = deleteData.find(deleteRequest => deleteRequest.quotation.quotationId === parseInt(quotationId));
        if (currentDeleteRequest) {
            showDeleteButton.value = false; // 삭제 요청이 있으면 삭제 버튼 숨기기
            deleteRequested.value = true; // 삭제 요청 상태를 true로 설정
        }

        // 전체 승인 데이터를 가져오는 API 호출
        const approvalResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation');
        const approvalData = approvalResponse.data;

        // 현재 견적서에 해당하는 결재 상태를 찾기
        const currentApproval = approvalData.find(approval => approval.quotation.quotationId === parseInt(quotationId));
        if (currentApproval) {
            approvalStatus.value = currentApproval.approvalStatus.approvalStatus;
            quotationData.value.approvalContent = currentApproval.approvalContent; // 비고란 내용 설정
        }

    } catch (error) {
        console.error('Error fetching quotation data:', error);
    }
});

// 결재 요청 함수
const handleApprovalRequest = () => {
    if (!deleteRequested.value) {
        requestApproval();
    }
};

// 견적서 수정 함수
const handleEditQuotation = () => {
    if (!deleteRequested.value) {
        goToQuotationPage();
    }
};

const requestApproval = async () => {
    const quotationId = route.params.quotationId;
    const currentDate = new Date().toISOString().split('T')[0]; // 현재 날짜를 YYYY-MM-DD 형식으로 가져오기
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/quotation/regist', {
            quotation: { quotationId: quotationId },
            approvalRequestDate: currentDate // 현재 날짜를 추가
        });
        console.log('Approval request sent successfully:', response.data);
        approvalStatus.value = 'Requested'; // 결재 요청 후 상태를 업데이트
        location.reload();
    } catch (error) {
        console.error('Error sending approval request:', error);
        alert('결재 요청 중 오류가 발생했습니다.');
    }
};

// 견적서 수정 페이지로 이동하는 함수
const goToQuotationPage = () => {
    router.push({ path: `/estimate/modify/${route.params.quotationId}` });
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
    const quotationId = route.params.quotationId;
    const url = `http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/excel/quotation/${quotationId}`;
    const link = document.createElement('a');
    link.href = url;
    link.download = `quotation_${quotationId}.xlsx`;
    link.target = '_blank';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
};

// 견적서 삭제 요청 함수
const deleteQuotation = () => {
    showPopup.value = true;
};

// 팝업 닫기 함수
const closePopup = () => {
    showPopup.value = false;
};

// 견적서 삭제 확인 함수
const confirmDelete = async () => {
    const quotationId = route.params.quotationId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation/delete', {
            quotationDeleteRequestReason: deleteReason.value,
            quotation: quotationData.value
        });
        console.log('Quotation delete request sent successfully:', response.data);
        showDeleteButton.value = false;
        deleteRequested.value = true;
        closePopup();
    } catch (error) {
        console.error('Error sending delete request:', error);
        alert('삭제 요청 중 오류가 발생했습니다.');
    }
};

// 노트 추가 함수
const addNote = async () => {
    const quotationId = route.params.quotationId;
    const userId = localStorage.getItem('userId'); // userId를 localStorage에서 가져오기
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation_note/regist', {
            quotationNote: newNote.value,
            quotation: { quotationId: quotationData.value.quotationId },
            employee: { employeeId: userId } // employeeId를 userId로 설정
        });
        console.log('Quotation note added successfully:', response.data);
        quotationNoteData.value.push(response.data);
        newNote.value = '';
        location.reload(); // 페이지 새로고침 추가
    } catch (error) {
        console.error('Error adding quotation note:', error);
        alert('노트 추가 중 오류가 발생했습니다.');
    }
};

// 노트 삭제 함수
const deleteNote = async (quotationNoteId) => {
    try {
        const response = await axios.patch('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation_note/delete', null, {
            params: {
                quotationNoteId
            }
        });
        const updatedNote = response.data;
        const noteIndex = quotationNoteData.value.findIndex(note => note.quotationNoteId === quotationNoteId);
        if (noteIndex !== -1) {
            quotationNoteData.value[noteIndex] = updatedNote;
        }
    } catch (error) {
        console.error('Error deleting note:', error);
        alert('노트 삭제 중 오류가 발생했습니다.');
    }
};
</script>


<style>
    @import url('@/assets/css/estimate/EstimateContents.css');
</style>
