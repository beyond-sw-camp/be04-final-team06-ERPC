<template>
    <div class="estimate-regist-content11">
        <h1>견적서 등록</h1>
        <div class="estimate-list-box">
            <table class="estimate-table1">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th class="narrow-column">수량</th>
                        <th>단가</th>
                        <th>공급가액</th>
                        <th>기타</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(product, index) in products" :key="index">
                        <td>
                            <div class="item-code-div2">
                                <input type="text" v-model="product.itemCode"
                                    class="item-code-box2" @input="product.itemCode = product.itemCode.replace(/\s/g, '')"/>
                                <div v-if="index === products.length - 1" class="button-group">
                                    <button @click="fetchProductData(index)" class="item-code-btn2">확인</button>
                                    <button @click="addProductRow" class="item-add-btn2">추가</button>
                                    <button @click="removeProductRow(index)" :disabled="products.length === 1"
                                        class="item-delete-btn2">삭제</button>
                                </div>
                            </div>
                        </td>
                        <td>{{ product.productName }}</td>
                        <td class="narrow-column">
                            <input type="number" v-model.number="product.quantity" class="estimate-test2"
                                @input="updateSupplyValue(index)" />
                        </td>
                        <td>{{ product.productPrice.toLocaleString() }}</td>
                        <td>{{ product.supplyValue.toLocaleString() }}</td>
                        <td><input type="text" v-model="product.otherInfo" class="estimate-test3" /></td>
                    </tr>
                </tbody>
            </table>

            <table class="estimate-table2">
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
                        <td>
                            <select v-model="selectedWarehouseCode" @change="updateWarehouseData" class="warehousedrop">
                                <option v-for="warehouse in warehouses" :key="warehouse.warehouseId"
                                    :value="warehouse.warehouseCode">
                                    {{ warehouse.warehouseCode }}
                                </option>
                            </select>
                        </td>
                        <td>{{ warehouseName }}</td>
                        <td>{{ warehouseType }}</td>
                        <td>{{ warehouseLocation }}</td>
                        <td>{{ warehouseUsage }}</td>
                        <td>{{ productionLineName }}</td>
                        <td>{{ outsourceName }}</td>
                    </tr>
                </tbody>
            </table>

            <table class="estimate-table3">
                <thead>
                    <tr>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>담당자</th>
                        <th>마감일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <div class="customer-code-div34">
                                <input type="text" v-model="customerCode"
                                    @input="customerCode = customerCode.replace(/\s/g, '').toUpperCase()"
                                    class="customer-code-box34" />
                                <button @click="fetchCustomerData" class="customer-code-btn34">확인</button>
                            </div>
                        </td>
                        <td>{{ customerName }}</td>
                        <td>{{ employeeName }}</td>
                        <td><input type="date" v-model="dueDate" class="due-date-box" id="due-date-box" /></td>
                        <td><input type="text" v-model="accountNote" class="customer-test9" /></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-attachment3">
            <h2 class="estimate-file">첨부파일</h2>
            <div v-for="(file, index) in files" :key="index" class="file-list">
                <span class="file-icon">📄</span>
                <span class="file-name">{{ file.name }}</span>
            </div>
            <input type="file" @change="handleFileUpload" multiple class="file-upload-btn" id="file-upload" />
            <label for="file-upload" class="file-upload-label">파일 선택</label>
        </div>
        <div class="estimate-regist-btn-div33">
            <button @click="registerQuotation" class="estimate-regist-btn33">견적 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref, watch, onMounted } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

// 상품 정보
const products = ref([createNewProduct()]);

// 창고 정보
const warehouses = ref([]); // 모든 창고 정보를 저장
const selectedWarehouseCode = ref(''); // 선택된 창고 코드
const warehouseId = ref(null); // Warehouse ID를 저장하기 위한 ref
const warehouseName = ref('');
const warehouseType = ref('');
const warehouseLocation = ref('');
const warehouseUsage = ref('');
const productionLineName = ref('');
const outsourceName = ref('');

// 거래처 정보
const customerCode = ref('');
const accountId = ref(null); // Account ID를 저장하기 위한 ref
const customerName = ref('');
const responsiblePerson = ref('');
const dueDate = ref('');
const accountNote = ref('');
const employeeName = ref(''); // Employee Name을 저장하기 위한 ref
const employeeId = ref(null); // Employee ID를 저장하기 위한 ref

// 파일 첨부
const files = ref([]);

function createNewProduct() {
    return {
        itemCode: '',
        productId: null,
        productName: '',
        productPrice: 0,
        quantity: 0,
        supplyValue: 0,
        otherInfo: '' // 기타
    };
}

const fetchProductData = async (index) => {
    const product = products.value[index];
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/product', { withCredentials: true });
        const productsData = response.data;
        const productData = productsData.find(p => p.productCode === product.itemCode);
        if (productData) {
            product.productId = productData.productId; // Product ID 저장
            product.productName = productData.productName;
            product.productPrice = productData.productPrice;
            updateSupplyValue(index);
        } else {
            alert('해당 품목 코드를 찾을 수 없습니다.');
            clearProductData(index);
        }
    } catch (error) {
        console.error('제품 정보를 조회하는 중 오류가 발생했습니다.', error);
        alert('제품 정보를 조회하는 중 오류가 발생했습니다.');
        clearProductData(index);
    }
};

const fetchWarehouses = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/warehouse', { withCredentials: true });
        warehouses.value = response.data;
    } catch (error) {
        console.error('창고 정보를 조회하는 중 오류가 발생했습니다.', error);
        alert('창고 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const updateWarehouseData = () => {
    const warehouse = warehouses.value.find(w => w.warehouseCode === selectedWarehouseCode.value);
    if (warehouse) {
        warehouseId.value = warehouse.warehouseId; // Warehouse ID 저장
        warehouseName.value = warehouse.warehouseName;
        warehouseType.value = warehouse.warehouseType;
        warehouseLocation.value = warehouse.warehouseLocation;
        warehouseUsage.value = warehouse.warehouseUsage;
        productionLineName.value = warehouse.productionLineName;
        outsourceName.value = warehouse.outsourceName;
    } else {
        clearWarehouseData();
    }
};

const fetchCustomerData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/list', { withCredentials: true });
        const customers = response.data;
        const customer = customers.find(c => c.accountCode === customerCode.value);
        if (customer) {
            accountId.value = customer.accountId; // Account ID 저장
            customerName.value = customer.accountName;
        } else {
            alert('해당 거래처 코드를 찾을 수 없습니다.');
            clearCustomerData();
        }
    } catch (error) {
        console.error('거래처 정보를 조회하는 중 오류가 발생했습니다.', error);
        alert('거래처 정보를 조회하는 중 오류가 발생했습니다.');
        clearCustomerData();
    }
};

const fetchEmployeeData = async () => {
    const userId = localStorage.getItem('userId');
    if (userId) {
        try {
            const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`, { withCredentials: true });
            const employeeData = response.data;
            employeeId.value = employeeData.employeeId;
            employeeName.value = employeeData.employeeName;
        } catch (error) {
            console.error('직원 정보를 조회하는 중 오류가 발생했습니다.', error);
            alert('직원 정보를 조회하는 중 오류가 발생했습니다.');
        }
    }
};

onMounted(() => {
    fetchEmployeeData(); // 컴포넌트가 마운트될 때 employeeId와 employeeName을 가져옴
    fetchWarehouses(); // 컴포넌트가 마운트될 때 창고 정보를 가져옴
});

const updateSupplyValue = (index) => {
    const product = products.value[index];
    product.supplyValue = product.productPrice * product.quantity;
};

const handleFileUpload = (event) => {
    files.value = Array.from(event.target.files);
    console.log('Files selected:', files.value);
};

const addProductRow = () => {
    products.value.push(createNewProduct());
};

const removeProductRow = (index) => {
    if (products.value.length > 1) {
        products.value.splice(index, 1);
    }
};

const registerQuotation = async () => {
    // 모든 필수 필드가 채워졌는지 확인
    const areProductsValid = products.value.every(product =>
        product.itemCode && product.productId && product.productName && product.productPrice && product.quantity
    );
    const isWarehouseValid = selectedWarehouseCode.value && warehouseId.value && warehouseName.value && warehouseType.value && warehouseLocation.value && warehouseUsage.value;
    const isCustomerValid = customerCode.value && customerName.value;
    const isEmployeeValid = employeeId.value && employeeName.value;
    const isDueDateValid = dueDate.value;

    if (!areProductsValid || !isWarehouseValid || !isCustomerValid || !isEmployeeValid || !isDueDateValid) {
        alert('모든 필수 입력란을 채워주세요.');
        return;
    }

    const quotation = {
        quotationNote: accountNote.value,
        quotationTotalCost: products.value.reduce((total, product) => total + product.supplyValue, 0),
        quotationDueDate: dueDate.value,
        employee: {
            employeeId: employeeId.value,
            employeeName: employeeName.value
        },
        account: { accountId: accountId.value },
        warehouse: { warehouseId: warehouseId.value },
        quotationProduct: products.value.map(product => ({
            quotationProductCount: product.quantity,
            quotationSupplyPrice: product.supplyValue,
            quotationProductionNote: product.otherInfo,
            product: { productId: product.productId }
        }))
    };

    const formData = new FormData();
    formData.append('quotation', JSON.stringify(quotation));

    // 첨부 파일이 있는 경우에만 파일 추가
    if (files.value.length > 0) {
        files.value.forEach(file => {
            formData.append('files', file);
        });
    } else {
        // 첨부 파일이 없는 경우 빈 배열로 초기화
        formData.append('files', new Blob([]));
    }

    // 콘솔 로그에 전달하는 데이터 출력
    console.log("Quotation Data to be sent:", quotation);
    console.log("Files to be sent:", files.value);

    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/quotation/regist', formData, {
            headers: { 'Content-Type': 'multipart/form-data' },
            withCredentials: true
        });
        alert('견적서가 성공적으로 등록되었습니다.');
        router.push({ path: `/estimate` });
    } catch (error) {
        console.error('견적서를 등록하는 중 오류가 발생했습니다.', error);
        console.error('에러 응답 데이터:', error.response ? error.response.data : '응답 없음');
        console.error('에러 응답 상태:', error.response ? error.response.status : '응답 없음');
        console.error('에러 요청 설정:', error.config);
        alert('견적서를 등록하는 중 오류가 발생했습니다.');
    }
};

const clearProductData = (index) => {
    const product = products.value[index];
    product.productId = null;
    product.productName = '';
    product.productPrice = 0;
    product.quantity = 0;
    product.supplyValue = 0;
};

const clearWarehouseData = () => {
    warehouseId.value = null;
    warehouseName.value = '';
    warehouseType.value = '';
    warehouseLocation.value = '';
    warehouseUsage.value = '';
    productionLineName.value = '';
    outsourceName.value = '';
};

const clearCustomerData = () => {
    accountId.value = null;
    customerName.value = '';
    employeeName.value = '';
};

// 수량이 변경될 때 공급가액을 자동으로 업데이트
watch(products, (newProducts) => {
    newProducts.forEach((product, index) => {
        if (product.quantity < 0) {
            product.quantity = 0;
        }
        updateSupplyValue(index);
    });
}, { deep: true });
</script>

<style>
    @import url('@/assets/css/estimate/EstimateRegist.css');
</style>
