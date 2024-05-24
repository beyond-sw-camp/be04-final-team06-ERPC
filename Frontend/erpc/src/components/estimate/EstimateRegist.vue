<template>
    <div class="regist-content">
        <div class="estimate-regist">
            <h1>견적서 등록</h1>
        </div>
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
                    <tr>
                        <td>
                            <div class="item-code-div2">
                                <input type="text" v-model="itemCode" class="item-code-box2" placeholder="품목 코드를 입력해주세요.">
                                <button @click="fetchProductData" class="item-code-btn2">확인</button>
                            </div>
                        </td>
                        <td>{{ productName }}</td>
                        <td class="narrow-column"><input type="number" v-model.number="quantity" class="estimate-test2"></td>
                        <td>{{ productPrice }}</td>
                        <td>{{ supplyValue }}</td>
                        <td><input type="text" class="estimate-test3"></td>
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
                            <div class="storage-code-div2">
                                <input type="text" v-model="warehouseCode" class="storage-code-box2" placeholder="창고 코드를 입력해주세요.">
                                <button @click="fetchWarehouseData" class="storage-code-btn2">확인</button>
                            </div>
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
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처명</th>
                        <th>마감일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td><input type="text" class="estimate-test5"></td>
                        <td><input type="text" class="estimate-test6"></td>
                        <td><input type="text" class="estimate-test7"></td>
                        <td><input type="text" class="estimate-test8"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-attachment">
            <div class="estimate-attachment-header">
                <h2 class="estimate-file">첨부파일</h2>
                <img src="@/assets/img/pdf.png" class="estimate-pdfimage">
            </div>
            <div class="estimate-attachment-content">
                <div class="file-list">
                    <span class="file-icon">📄</span>
                    <span class="file-name">제품 카탈로그.pdf</span>
                </div>
            </div>
        </div>
        <div class="estimate-regist-btn-div">
            <button class="estimate-regist-btn">견적 등록하기</button>
        </div>
    </div>
</template>



<script setup>
import { ref, watch } from 'vue';
import axios from 'axios';

const itemCode = ref('');
const productName = ref('');
const productPrice = ref(0);
const quantity = ref(0);
const supplyValue = ref(0);

const warehouseCode = ref('');
const warehouseName = ref('');
const warehouseType = ref('');
const warehouseLocation = ref('');
const warehouseUsage = ref('');
const productionLineName = ref('');
const outsourceName = ref('');

const fetchProductData = async () => {
    try {
        const response = await axios.get('http://localhost:7775/product');
        const products = response.data;
        const product = products.find(p => p.productCode === itemCode.value);
        if (product) {
            productName.value = product.productName;
            productPrice.value = product.productPrice;
            updateSupplyValue(); // 수량과 단가로 공급가액 계산
        } else {
            clearProductData();
            alert('해당 품목 코드를 찾을 수 없습니다.');
        }
    } catch (error) {
        console.error('Error fetching product data:', error);
        clearProductData();
        alert('제품 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const fetchWarehouseData = async () => {
    try {
        const response = await axios.get('http://localhost:7775/warehouse');
        const warehouses = response.data;
        const warehouse = warehouses.find(w => w.warehouseCode === warehouseCode.value);
        if (warehouse) {
            warehouseName.value = warehouse.warehouseName;
            warehouseType.value = warehouse.warehouseType;
            warehouseLocation.value = warehouse.warehouseLocation;
            warehouseUsage.value = warehouse.warehouseUsage;
            productionLineName.value = warehouse.productionLineName;
            outsourceName.value = warehouse.outsourceName;
        } else {
            clearWarehouseData();
            alert('해당 창고 코드를 찾을 수 없습니다.');
        }
    } catch (error) {
        console.error('Error fetching warehouse data:', error);
        clearWarehouseData();
        alert('창고 정보를 조회하는 중 오류가 발생했습니다.');
    }
};

const updateSupplyValue = () => {
    supplyValue.value = productPrice.value * Math.max(quantity.value, 0);
};

// 수량이 변경될 때 공급가액을 자동으로 업데이트
watch(quantity, (newQuantity) => {
    if (newQuantity < 0) {
        quantity.value = 0;
    }
    updateSupplyValue();
});

// 제품 데이터를 초기화하는 함수
const clearProductData = () => {
    productName.value = '';
    productPrice.value = 0;
    quantity.value = 0;
    supplyValue.value = 0;
};

// 창고 데이터를 초기화하는 함수
const clearWarehouseData = () => {
    warehouseName.value = '';
    warehouseType.value = '';
    warehouseLocation.value = '';
    warehouseUsage.value = '';
    productionLineName.value = '';
    outsourceName.value = '';
};
</script>




<style>
    @import url('@/assets/css/estimate/EstimateRegist.css');
</style>