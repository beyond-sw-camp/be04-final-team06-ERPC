<template>
    <div class="delete-content7" v-if="deleteOrderData">
        <div class="order-search">
            <h1 class="maintext">수주 삭제 내역</h1>
            <div class="estimate-approval-note1" >
                <h3 class="estimate-approval-note2">삭제 사유</h3>
                <div class="estimate-approval-note3">{{ deleteOrderData.orderDeleteRequestReason }}</div>
                <button @click="processContractDeleteRequest(deleteOrderData.orderDeleteRequestId)"class="delete-button">수주 삭제</button>
            </div>


            <div class="estimate-approval-attachment">
                <div v-if="deleteOrderData.order.orderFile.length > 0">
                    <div v-for="file in deleteOrderData.order.orderFile" :key="file.fileId" class="file-download">
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
            <!-- 주문 정보 테이블 -->
            <table class="estimate2-table1">
                <thead>
                    <tr>
                        <th>수주서 코드</th>
                        <th>수주 금액</th>
                        <th>작성 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ deleteOrderData.order.transaction.transactionCode }}</td>
                        <td>{{ deleteOrderData.order.orderTotalPrice.toLocaleString() }}</td>
                        <td>{{ deleteOrderData.order.orderDate }}</td>
                        <td>{{ deleteOrderData.order.orderDueDate }}</td>
                    </tr>
                </tbody>
            </table>

            <!-- 품목 정보 테이블 -->
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
                    <tr v-for="product in deleteOrderData.order.orderProduct" :key="product.orderProductId">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.orderProductCount }}</td>
                        <td>{{ product.product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.orderSupplyPrice.toLocaleString() }}</td>
                        <td>{{ product.orderProductionNote }}</td>
                    </tr>
                </tbody>
            </table>

            <!-- 창고 정보 테이블 -->
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
                        <td>{{ deleteOrderData.order.warehouse.warehouseCode }}</td>
                        <td>{{ deleteOrderData.order.warehouse.warehouseName }}</td>
                        <td>{{ deleteOrderData.order.warehouse.warehouseType }}</td>
                        <td>{{ deleteOrderData.order.warehouse.warehouseLocation }}</td>
                        <td>{{ deleteOrderData.order.warehouse.warehouseUsage }}</td>
                        <td>{{ deleteOrderData.order.warehouse.productionLineName }}</td>
                        <td>{{ deleteOrderData.order.warehouse.outsourceName }}</td>
                    </tr>
                </tbody>
            </table>

            <!-- 담당자 및 거래처 정보 테이블 -->
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
                        <td>{{ deleteOrderData.order.employee.employeeName }}</td>
                        <td>{{ deleteOrderData.order.account.accountName }}</td>
                        <td>{{ deleteOrderData.order.orderNote }}</td>
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
const deleteOrderData = ref(null);

const fetchDeleteOrderData = async () => {
  const orderDeleteRequestsId = route.params.orderDeleteRequestsId;
  try {
    const response = await DeleteService.findOrderDeleteRequestById(orderDeleteRequestsId);
    deleteOrderData.value = response.data;
  } catch (error) {
    console.error("Error fetching deleted order data:", error);
  }
};

const processContractDeleteRequest = async (orderDeleteRequestId) => {
    try {
        const requestData = { 
            orderDeleteRequestId: orderDeleteRequestId,
        };
        console.log('Request Data:', requestData); 
        await DeleteService.processOrderDeleteRequest(requestData);
        alert('견적서 삭제 요청이 성공적으로 처리되었습니다.');
        fetchDeleteOrderData(); 
        router.push('/delete');
    } catch (error) {
        console.error('견적서 삭제 요청 처리 중 오류가 발생했습니다:', error);
    }
};

onMounted(fetchDeleteOrderData);

const downloadFile = (url) => {
    window.open(url, '_blank');
};
</script>

<style>
/* 여기에 스타일링 CSS 코드를 추가하세요 */
</style>