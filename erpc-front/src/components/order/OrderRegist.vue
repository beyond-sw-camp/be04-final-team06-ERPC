<template>
    <div class="order-regist-content11">
        <div class="order-regist11">
            <h1>수주 등록</h1>
        </div>
        <div class="order-list-box1">
            <table class="order-table1">
                <thead>
                    <tr>
                        <th>계약서 코드</th>
                        <th>담당자</th>
                        <th>프로젝트 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="contract-test333">
                            <div class="contract-number-div33">
                                <input type="text" v-model="contractCode" class="contract-number-box33" @input="contractCode = contractCode.replace(/\s/g, '')">
                                <button @click="fetchContractData" class="contract-number-btn33">확인</button>
                            </div>
                        </td>
                        <td>{{ contractData.employee.employeeName }}</td>
                        <td>{{ contractData.contractCode }}</td>
                        <td>{{ contractData.account.accountName }}</td>
                        <td>{{ contractData.contractTotalPrice }}</td>
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
                    <tr v-for="product in contractData.contractProduct" :key="product.product.productCode">
                        <td>{{ product.product.productCode }}</td>
                        <td>{{ product.product.productName }}</td>
                        <td>{{ product.contractProductCount }}</td>
                        <td>{{ product.product.productPrice }}</td>
                        <td>{{ product.contractSupplyPrice }}</td>
                        <td>{{ product.contractProductionNote }}</td>
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
            <div v-if="contractData.contractCategory.contractCategoryId === 1">
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
                            <td>{{ contractData.contractTotalPrice.toLocaleString() }}</td>
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
                            <td>{{ contractData.downPayment.toLocaleString() }}</td>
                            <td>{{ contractData.progressPayment.toLocaleString() }}</td>
                            <td>{{ contractData.balance.toLocaleString() }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="order-attachment33">
            <h2 class="order-file">첨부파일</h2>
            <div v-if="files.length > 0">
                <div v-for="(file, index) in files" :key="index" class="file-list34">
                    <span class="file-icon">📄</span>
                    <span class="file-name">{{ file.name }}</span>
                </div>
            </div>
            <div v-else>
                <div v-for="(file, index) in contractData.contractFile" :key="file.fileId" class="file-list34">
                    <span class="file-icon">📄</span>
                    <span class="file-name">{{ file.originName }}</span>
                </div>
            </div>
            <input type="file" @change="handleFileUpload" multiple class="file-upload-btn" id="file-upload"/>
            <label for="file-upload" class="file-upload-label">파일 선택</label>
        </div>
        <div class="order-regist-btn-div33">
            <button class="order-regist-btn33" @click="registerOrder">수주 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const contractCode = ref('');
const contractData = ref({
    contractCode: '',
    employee: {
        employeeName: ''
    },
    account: {
        accountCode: '',
        accountName: ''
    },
    contractTotalPrice: '',
    contractProduct: [
        {
            product: {
                productCode: '',
                productName: '',
                productPrice: ''
            },
            contractProductCount: '',
            contractSupplyPrice: '',
            contractProductionNote: ''
        }
    ],
    warehouse: {
        warehouseId: '',
        warehouseCode: '',
        warehouseName: '',
        warehouseType: '',
        warehouseLocation: '',
        warehouseUsage: '',
        productionLineName: '',
        outsourceName: ''
    },
    contractCategory: {
        contractCategoryId:'',
        contractCategory: ''
    },
    downPayment: '',
    progressPayment: '',
    balance: '',
    contractDate: '',
    contractDueDate: '',
    contractNote: '',
    contractFile: []
});

const files = ref([]);

const fetchContractData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract/code', {
            params: {
                contractCode: contractCode.value
            }
        });
        contractData.value = response.data;
    } catch (error) {
        console.error('Error fetching contract data:', error);
    }
};

const handleFileUpload = (event) => {
    const uploadedFiles = Array.from(event.target.files);
    files.value = uploadedFiles;
    contractData.value.contractFile = [];
};

const registerOrder = async () => {
    if (files.value.length === 0) {
        alert('첨부파일을 등록해주세요.');
        return;
    }

    const orderData = {
        contactDate: contractData.value.contractDate,
        orderTotalPrice: contractData.value.contractTotalPrice,
        orderDueDate: contractData.value.contractDueDate,
        orderNote: contractData.value.contractNote,
        employee: {
            employeeId: contractData.value.employee.employeeId
        },
        account: {
            accountId: contractData.value.account.accountId
        },
        warehouse: {
            warehouseId: contractData.value.warehouse.warehouseId
        },
        contractCategory: {
            contractCategoryId: contractData.value.contractCategory.contractCategoryId
        },
        transaction: {
            transactionId: contractData.value.transaction.transactionId
        },
        orderProduct: contractData.value.contractProduct.map(product => ({
            orderProductCount: product.contractProductCount,
            orderSupplyPrice: product.contractSupplyPrice,
            orderProductionNote: product.contractProductionNote,
            product: {
                productId: product.product.productId
            }
        }))
    };

    if (contractData.value.contractCategory.contractCategoryId === 2) {
        orderData.downPayment = contractData.value.downPayment;
        orderData.progressPayment = contractData.value.progressPayment;
        orderData.balance = contractData.value.balance;
    }

    const formData = new FormData();
    formData.append('order', JSON.stringify(orderData));
    files.value.forEach(file => {
        formData.append('files', file);
    });

    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order/regist', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        alert('수주 등록이 성공적으로 완료되었습니다.');
        router.push({ path: `/order` });
    } catch (error) {
        console.error('수주 등록 중 오류가 발생했습니다.', error);
        alert('수주 등록 중 오류가 발생했습니다.');
    }
};
</script>

<style>
    @import url('@/assets/css/order/OrderRegist.css');
</style>
