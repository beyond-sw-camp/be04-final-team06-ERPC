<template>
    <div class="contract-content11" v-if="contractData">
        <div class="contract-search">
            <h1>계약서 정보 조회 내역</h1>
            <div class="contract-btn">
                <div class="contract-btn2" v-if="!['결재요청', '승인', '반려'].includes(approvalStatus)">
                    <button class="contract-request" @click="handleApprovalRequest" :disabled="deleteRequested">결재요청</button>
                </div>
                <button class="contract-edit" @click="handleEditContract" :disabled="deleteRequested">수정</button>
                <button class="contract-delete" v-if="!deleteRequested" @click="deleteContract">삭제요청</button>
                <button class="contract-excel" @click="downloadExcel">엑셀다운</button>
            </div>
            <div class="contract-approval-note1" v-if="['승인', '반려'].includes(approvalStatus) && contractData.approvalContent">
                <h3 class="contract-approval-note2">결재 비고란</h3>
                <div class="contract-approval-note3">{{ contractData.approvalContent }}</div>
            </div>
            <div class="contract-pdf">
                <div v-if="contractData.contractFile.length > 0">
                    <div v-for="file in contractData.contractFile" :key="file.fileId" class="file-download">
                        <button class="contract-pdf2" @click="downloadFile(file.accessUrl)">
                            {{ file.originName }} 다운로드
                        </button>
                    </div>
                </div>
                <div v-else class="file-download no-file">
                    첨부파일 없음
                </div>
            </div>
        </div>
        <div class="contract-list-box2">
            <table class="contract2-table1">
                <thead>
                    <tr>
                        <th>계약서 코드</th>
                        <th>계약 금액</th>
                        <th>작성 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ contractData.contractCode }}</td>
                        <td>{{ contractData.contractTotalPrice.toLocaleString() }}</td>
                        <td>{{ contractData.contractDate }}</td>
                        <td>{{ contractData.contractDueDate }}</td>
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
                    <tr v-for="product in contractData.contractProduct" :key="product.contractProductId">
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
                        <td>{{ contractData.warehouse.warehouseCode }}</td>
                        <td>{{ contractData.warehouse.warehouseName }}</td>
                        <td>{{ contractData.warehouse.warehouseType }}</td>
                        <td>{{ contractData.warehouse.warehouseLocation }}</td>
                        <td>{{ contractData.warehouse.warehouseUsage }}</td>
                        <td>{{ contractData.warehouse.productionLineName }}</td>
                        <td>{{ contractData.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table4">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처</th>
                        <th>수주 금액</th>
                        <th>납기 일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ contractData.employee.employeeName }}</td>
                        <td>{{ contractData.account.accountName }}</td>
                        <td>{{ contractData.contractTotalPrice.toLocaleString() }}</td>
                        <td>{{ contractData.contractDueDate }}</td>
                        <td>{{ contractData.contractNote }}</td>
                    </tr>
                </tbody>
            </table>
            <div v-if="contractData.contractCategory.contractCategoryId === 1">
                <table class="contract2-table5">
                    <thead>
                        <tr>
                            <th>납부 형태</th>
                            <th>일시납부 금액</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>일시납부</td>
                            <td>{{ contractData.contractTotalPrice.toLocaleString() }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div v-else>
                <table class="contract2-table6">
                    <thead>
                        <tr>
                            <th>납부 형태</th>
                            <th>계약금</th>
                            <th>중도금</th>
                            <th>잔금</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>분할납부</td>
                            <td>{{ contractData.downPayment.toLocaleString() }}</td>
                            <td>{{ contractData.progressPayment.toLocaleString() }}</td>
                            <td>{{ contractData.balance.toLocaleString() }}</td>
                        </tr>
                    </tbody>
                </table>
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
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const contractData = ref(null);
const showPopup = ref(false);
const deleteReason = ref('');
const employeeName = ref('');
const approvalStatus = ref('Pending'); // Default value as Pending
const deleteRequested = ref(false); // 삭제 요청 상태를 저장

// 컴포넌트가 마운트될 때 실행되는 코드
onMounted(async () => {
    const contractId = route.params.contractId;
    const userId = localStorage.getItem('userId'); // userId를 localStorage에서 가져오기

    try {
        // 계약서 데이터를 가져오는 API 호출
        const contractResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract/${contractId}`);
        contractData.value = contractResponse.data;

        // userId로 직원 이름을 가져오는 API 호출
        const employeeResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`);
        employeeName.value = employeeResponse.data.employeeName;

        // 전체 승인 데이터를 가져오는 API 호출
        const approvalResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract');
        const approvalData = approvalResponse.data;

        if (approvalData && Array.isArray(approvalData)) {
            // 현재 계약서에 해당하는 결재 상태를 찾기
            const currentApproval = approvalData.find(approval => approval.contract && approval.contract.contractId === parseInt(contractId));
            if (currentApproval) {
                approvalStatus.value = currentApproval.approvalStatus.approvalStatus;
                contractData.value.approvalContent = currentApproval.approvalContent; // 비고란 내용 설정
            }
        }

        // 전체 삭제 요청 데이터를 가져오는 API 호출
        const deleteResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/delete/contract');
        const deleteData = deleteResponse.data;

        if (deleteData && Array.isArray(deleteData)) {
            // 현재 계약서에 해당하는 삭제 요청 상태를 찾기
            const currentDeleteRequest = deleteData.find(deleteRequest => deleteRequest.contract && deleteRequest.contract.contractId === parseInt(contractId));
            if (currentDeleteRequest) {
                deleteRequested.value = true; // 삭제 요청 상태를 true로 설정
            }
        }

    } catch (error) {
        console.error('Error fetching contract data:', error);
    }
});

// 결재 요청 함수
const handleApprovalRequest = () => {
    if (deleteRequested.value) {
        alert('삭제 요청한 계약서는 결재 요청할 수 없습니다.');
    } else {
        requestApproval();
    }
};

// 계약서 수정 함수
const handleEditContract = () => {
    if (deleteRequested.value) {
        alert('삭제 요청한 계약서는 수정할 수 없습니다.');
    } else {
        goToEditPage();
    }
};

// 결재 요청 함수
const requestApproval = async () => {
    const contractId = route.params.contractId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/approval/contract/regist', {
            contract: { contractId: contractId }
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

// 계약서 수정 페이지로 이동하는 함수
const goToEditPage = () => {
    router.push({ path: `/contract/modify/${route.params.contractId}` });
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
    const contractId = route.params.contractId;
    const url = `http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/excel/contract/${contractId}`;
    const link = document.createElement('a');
    link.href = url;
    link.download = `contract_${contractId}.xlsx`;
    link.target = '_blank';
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
};

// 계약서 삭제 요청 함수
const deleteContract = () => {
    showPopup.value = true;
};

// 팝업 닫기 함수
const closePopup = () => {
    showPopup.value = false;
};

// 계약서 삭제 확인 함수
const confirmDelete = async () => {
    const contractId = route.params.contractId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract/delete', {
            contractDeleteRequestReason: deleteReason.value,
            contract: contractData.value
        });
        console.log('Contract delete request sent successfully:', response.data);
        alert('삭제 요청이 성공적으로 완료되었습니다.');
        location.reload(); // 삭제 요청 후 페이지 새로고침
    } catch (error) {
        console.error('Error sending delete request:', error);
        alert('삭제 요청 중 오류가 발생했습니다.');
    } finally {
        closePopup();
    }
};
</script>

<style>
    @import url('@/assets/css/contract/ContractContents.css');
</style>
