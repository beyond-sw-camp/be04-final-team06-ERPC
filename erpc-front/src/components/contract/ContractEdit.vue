<template>
    <div class="contract-edit-content11" v-if="contractData">
        <h1>계약서 수정</h1>
        <div class="contract-list-box1">
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
                        <td><input type="text" v-model="contractNote" class="contract-test5"></td>
                    </tr>
                </tbody>
            </table>
            <table class="contract-table4">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th v-if="searchBy === '일시납부'">일시납부 금액</th>
                        <th v-if="searchBy === '분할납부'">계약금</th>
                        <th v-if="searchBy === '분할납부'">중도금</th>
                        <th v-if="searchBy === '분할납부'">잔금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test1">
                            <div class="contract-dropdown1">
                                <button class="contract-dropdown-btn1">{{ searchBy }} ▼</button>
                                <div class="contract-dropdown-content1">
                                    <a href="#" @click.prevent="setSearchBy('일시납부')">일시납부</a>
                                    <a href="#" @click.prevent="setSearchBy('분할납부')">분할납부</a>
                                </div>
                            </div>
                        </td>
                        <td v-if="searchBy === '일시납부'">
                            {{ contractData.contractTotalPrice.toLocaleString() }}
                        </td>
                        <td v-if="searchBy === '분할납부'">
                            <input type="text" v-model="formattedDeposit" class="contract-test6" @input="updateDeposit">
                        </td>
                        <td v-if="searchBy === '분할납부'">
                            <input type="text" v-model="formattedIntermediatePayment" class="contract-test7" @input="updateIntermediatePayment">
                        </td>
                        <td v-if="searchBy === '분할납부'">
                            <input type="text" v-model="formattedFinalPayment" class="contract-test8" @input="updateFinalPayment">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="contract-attachment55">
                <h2 class="contract-file">첨부파일</h2>
                <div v-if="files.length > 0">
                    <div v-for="(file, index) in files" :key="index" class="file-list55">
                        <span class="file-icon">📄</span>
                        <span class="file-name55">{{ file.name }}</span>
                    </div>
                </div>
                <div v-else>
                    <div v-for="(file, index) in contractData.contractFile" :key="file.fileId" class="file-list55">
                        <span class="file-icon">📄</span>
                        <span class="file-name55">{{ file.originName }}</span>
                    </div>
                </div>
                <input type="file" @change="handleFileUpload" multiple class="file-upload-btn" id="file-upload" />
                <label for="file-upload" class="file-upload-label">파일 선택</label>
            </div>
            <div class="contract-edit-btn-div33">
                <button @click="updateContract" class="contract-edit-btn33">계약 수정하기</button>
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

const route = useRoute();
const router = useRouter();
const contractData = ref(null);
const files = ref([]);

const contractNote = ref('');
const downPayment = ref(0);
const progressPayment = ref(0);
const balance = ref(0);
const searchBy = ref('분할납부');

const formattedDeposit = ref('');
const formattedIntermediatePayment = ref('');
const formattedFinalPayment = ref('');
const formattedTotalCost = ref('');

onMounted(async () => {
    const contractId = route.params.contractId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract/${contractId}`, { withCredentials: true });
        contractData.value = response.data;
        populateFields(contractData.value);
    } catch (error) {
        console.error('Error fetching contract data:', error);
    }
});

const populateFields = (data) => {
    contractNote.value = data.contractNote;
    downPayment.value = data.downPayment;
    progressPayment.value = data.progressPayment;
    balance.value = data.balance;
    searchBy.value = data.contractCategory.contractCategoryId === 1 ? '일시납부' : '분할납부';
    updateFormattedPayments();
};

const handleFileUpload = (event) => {
    files.value = Array.from(event.target.files);
    contractData.value.contractFile = []; // 파일 선택 시 기존 파일 목록 초기화
};

const removeNewFile = (index) => {
    files.value.splice(index, 1);
};

const removeExistingFile = (index) => {
    contractData.value.contractFile.splice(index, 1);
};

const setSearchBy = (criteria) => {
    searchBy.value = criteria;
    calculatePayments();
};

const calculatePayments = () => {
    const totalCost = contractData.value.contractTotalPrice || 0;
    if (searchBy.value === '일시납부') {
        downPayment.value = 0;
        progressPayment.value = 0;
        balance.value = 0;
        formattedTotalCost.value = totalCost.toLocaleString();
    } else {
        downPayment.value = Math.round(totalCost * 0.1);
        progressPayment.value = Math.round(totalCost * 0.6);
        balance.value = Math.round(totalCost * 0.3);
    }
    updateFormattedPayments();
};

const updateFormattedPayments = () => {
    formattedDeposit.value = downPayment.value.toLocaleString();
    formattedIntermediatePayment.value = progressPayment.value.toLocaleString();
    formattedFinalPayment.value = balance.value.toLocaleString();
    formattedTotalCost.value = (downPayment.value + progressPayment.value + balance.value).toLocaleString();
};

const updateDeposit = (event) => {
    downPayment.value = parseInt(event.target.value.replace(/,/g, ''), 10) || 0;
    updateFormattedPayments();
};

const updateIntermediatePayment = (event) => {
    progressPayment.value = parseInt(event.target.value.replace(/,/g, ''), 10) || 0;
    updateFormattedPayments();
};

const updateFinalPayment = (event) => {
    balance.value = parseInt(event.target.value.replace(/,/g, ''), 10) || 0;
    updateFormattedPayments();
};

const updateContract = async () => {
    const contractId = route.params.contractId;

    const contractCategoryId = searchBy.value === '일시납부' ? 1 : 2;

    const contract = {
        contractId: contractId,
        contractNote: contractNote.value,
        contractTotalPrice: contractData.value.contractTotalPrice,
        contractDueDate: contractData.value.contractDueDate,
        downPayment: searchBy.value === '일시납부' ? 0 : downPayment.value,
        progressPayment: searchBy.value === '일시납부' ? 0 : progressPayment.value,
        balance: searchBy.value === '일시납부' ? 0 : balance.value,
        employee: contractData.value.employee,
        account: contractData.value.account,
        warehouse: contractData.value.warehouse,
        contractCategory: {
            contractCategoryId: contractCategoryId
        },
        contractProduct: contractData.value.contractProduct.map(product => ({
            contractProductCount: product.contractProductCount,
            contractSupplyPrice: product.contractSupplyPrice,
            contractProductionNote: product.contractProductionNote,
            product: product.product
        }))
    };

    const formData = new FormData();
    formData.append('contract', JSON.stringify(contract));
    files.value.forEach(file => {
        formData.append('files', file);
    });

    try {
        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/contract/modify/${contractId}`, formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
            withCredentials: true
        });
        alert('계약서가 성공적으로 수정되었습니다.');
        router.push({ path: `/contract/${contractId}` });
    } catch (error) {
        console.error('계약서를 수정하는 중 오류가 발생했습니다.', error);
        alert('계약서를 수정하는 중 오류가 발생했습니다.');
    }
};
</script>

<style>
    @import url('@/assets/css/contract/ContractEdit.css');
</style>
