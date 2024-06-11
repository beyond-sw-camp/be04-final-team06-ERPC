<template>
    <div class="delete-content7" v-if="deleteQuotationData">
        <div class="order-search">
            <h1 class="maintext">견적서 삭제 내역</h1>

            <div class="estimate-approval-note1" >
                <h3 class="estimate-approval-note2">삭제 사유</h3>
                <div class="estimate-approval-note3">{{ deleteQuotationData.quotationDeleteRequestReason }}</div>
                <button @click="processContractDeleteRequest(deleteQuotationData.quotationDeleteRequestId)"class="delete-button">견적서 삭제</button>
            </div>

            <div class="estimate-approval-attachment">
                <div v-if="deleteQuotationData.quotation.quotationFile.length > 0">
                    <div v-for="file in deleteQuotationData.quotation.quotationFile" :key="file.fileId" class="file-download">
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
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ deleteQuotationData.quotation.quotationCode }}</td>
                        <td>{{ deleteQuotationData.quotation.quotationTotalCost.toLocaleString() }}</td>
                        <td>{{ deleteQuotationData.quotation.quotationDate }}</td>
                        <td>{{ deleteQuotationData.quotation.quotationDueDate }}</td>
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
                    <tr v-for="product in deleteQuotationData.quotation.quotationProduct" :key="product.quotationProductId">
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
                        <td>{{ deleteQuotationData.quotation.warehouse.warehouseCode }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.warehouseName }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.warehouseType }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.warehouseLocation }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.warehouseUsage }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.productionLineName }}</td>
                        <td>{{ deleteQuotationData.quotation.warehouse.outsourceName }}</td>
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
                        <td>{{ deleteQuotationData.quotation.employee.employeeName }}</td>
                        <td>{{ deleteQuotationData.quotation.account.accountName }}</td>
                        <td>{{ deleteQuotationData.quotation.quotationNote }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div v-else>
        <p>Loading...</p>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'; 
import axios from 'axios';
import DeleteService from '@/components/delete/DeleteService';

const route = useRoute();
const router = useRouter();
const deleteQuotationData = ref(null);

const fetchDeleteQuotationData = async () => {
  const quotationDeleteRequestId = route.params.quotationDeleteRequestId;
  try {
    const response = await DeleteService.findQuotationDeleteRequestById(quotationDeleteRequestId);
    deleteQuotationData.value = response.data;
  } catch (error) {
    console.error("Error fetching deleted quotation data:", error);
  }
};

const processContractDeleteRequest = async (quotationDeleteRequestId) => {
    try {
        const requestData = { 
            quotationDeleteRequestId: quotationDeleteRequestId,
        };
        console.log('Request Data:', requestData); 
        await DeleteService.processQuotationDeleteRequest(requestData);
        alert('견적서 삭제 요청이 성공적으로 처리되었습니다.');
        fetchDeleteQuotationData(); 
        router.push('/delete');
    } catch (error) {
        console.error('견적서 삭제 요청 처리 중 오류가 발생했습니다:', error);
    }
};

onMounted(fetchDeleteQuotationData);

const downloadFile = (url) => {
    window.open(url, '_blank');
};
</script>

<style>
/* 스타일을 위한 CSS 코드가 여기에 올 것입니다 */
</style>