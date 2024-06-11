<template>
    <div class="regist-content21" v-if="orderData">
        <div class="order-regist21">
            <h1>수주 수정</h1>
        </div>
        <div class="order-list-box1">
            <table class="order-table1">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.transaction.transactionCode }}</td>
                        <td>{{ orderData.employee.employeeName }}</td>
                        <td>{{ orderData.account.accountCode }}</td>
                        <td>{{ orderData.account.accountName }}</td>
                        <td>{{ orderData.orderTotalPrice }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table2">
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
                        <td>{{ product.product.productPrice }}</td>
                        <td>{{ product.orderSupplyPrice }}</td>
                        <td>{{ product.orderProductionNote }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table3">
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
            <div v-if="orderData.contractCategory.contractCategoryId === 1">
                <table class="order2-table4">
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
            </div>
            <div v-else>
                <table class="order2-table5">
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
                            <td>{{ orderData.downPayment.toLocaleString() }}</td>
                            <td>{{ orderData.progressPayment.toLocaleString() }}</td>
                            <td>{{ orderData.balance.toLocaleString() }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="order-attachment77">
            <div class="order-attachment-header">
                <h2 class="order-file">첨부파일</h2>
            </div>
            <div class="order-attachment-content">
                <div v-if="files.length > 0">
                    <div v-for="(file, index) in files" :key="index" class="file-list77">
                        <span class="file-icon">📄</span>
                        <span class="file-name77">{{ file.name }}</span>
                    </div>
                </div>
                <div v-else>
                    <div v-for="(file, index) in orderData.orderFile" :key="file.fileId" class="file-list77">
                        <span class="file-icon">📄</span>
                        <span class="file-name77">{{ file.originName }}</span>
                    </div>
                </div>
            </div>
            <label class="file-upload-label">
                파일 선택
                <input type="file" @change="handleFileUpload" multiple class="file-upload-btn" />
            </label>
        </div>
        <div class="order-regist-btn-div1">
            <button class="order-regist-btn1" @click="registerOrder">수주 수정하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const orderRegistrationId = route.params.orderRegistrationId;
const orderData = ref(null);
const files = ref([]);
const removedFiles = ref([]);

const fetchOrderData = async () => {
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order/${orderRegistrationId}`, {
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            }
        });
        orderData.value = response.data;
    } catch (error) {
        console.error('Error fetching order data:', error);
    }
};

const handleFileUpload = (event) => {
    const uploadedFiles = Array.from(event.target.files);
    files.value.push(...uploadedFiles);
    orderData.value.orderFile = []; // 파일 선택 시 기존 파일 목록 초기화
};

const removeFile = (index) => {
    files.value.splice(index, 1);
};

const removeExistingFile = (fileId, index) => {
    removedFiles.value.push(fileId);
    orderData.value.orderFile.splice(index, 1);
};

const registerOrder = async () => {
    if (files.value.length === 0 && orderData.value.orderFile.length === 0) {
        alert('첨부파일을 등록해주세요.');
        return;
    }

    const orderUpdateData = {
        orderRegistrationId: orderData.value.orderRegistrationId,
        contactDate: orderData.value.contactDate,
        orderTotalPrice: orderData.value.orderTotalPrice,
        orderDueDate: orderData.value.orderDueDate,
        totalBalance: orderData.value.totalBalance,
        downPayment: orderData.value.downPayment,
        progressPayment: orderData.value.progressPayment,
        balance: orderData.value.balance,
        orderNote: orderData.value.orderNote,
        employee: { employeeId: orderData.value.employee.employeeId },
        account: { accountId: orderData.value.account.accountId },
        warehouse: { warehouseId: orderData.value.warehouse.warehouseId },
        contractCategory: { contractCategoryId: orderData.value.contractCategory.contractCategoryId },
        transaction: { transactionId: orderData.value.transaction.transactionId },
        orderProduct: orderData.value.orderProduct.map(product => ({
            orderProductCount: product.orderProductCount,
            orderSupplyPrice: product.orderSupplyPrice,
            orderProductionNote: product.orderProductionNote,
            product: { productId: product.product.productId }
        }))
    };

    const formData = new FormData();
    formData.append('order', JSON.stringify(orderUpdateData));
    files.value.forEach(file => {
        formData.append('files', file);
    });
    formData.append('removedFiles', JSON.stringify(removedFiles.value));

    try {
        const response = await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order/modify`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data',
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`
            },
            withCredentials: true
        });
        alert('수주 수정이 성공적으로 완료되었습니다.');
        router.push({ path: `/order/${route.params.orderRegistrationId}` });
    } catch (error) {
        console.error('수주 수정 중 오류가 발생했습니다.', error);
        console.log('Response data:', error.response?.data); // Additional logging
        console.log('Request config:', error.config); // Additional logging
        alert('수주 수정 중 오류가 발생했습니다.');
    }
};

onMounted(() => {
    fetchOrderData();
});
</script>




<style>
    @import url('@/assets/css/order/OrderEdit.css');
</style>
