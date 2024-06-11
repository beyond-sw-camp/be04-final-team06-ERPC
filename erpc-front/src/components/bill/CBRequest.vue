<template>
    <div class="cb-search-content33">
        <div class="cb-search33">
            <h1 class="cbtext">세금계산서 발행 요청</h1>
        </div>
        <div class="cb-list-box1">
            <table class="cb-table1">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>거래처 코드</th>
                        <th>법인 여부</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="order-test333">
                            <div class="order-number-div33">
                                <input v-model="projectCode" type="text" id="order-number-box33" class="order-number-box33" @input="projectCode = projectCode.replace(/\s/g, '')">
                                <button @click="fetchOrderData" class="order-number-btn33">확인</button>
                            </div>
                        </td>
                        <td>{{ orderData.account.accountCode }}</td>
                        <td>{{ orderData.account.corporationStatus }}</td>
                        <td>{{ orderData.orderTotalPrice ? orderData.orderTotalPrice.toLocaleString() : '' }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table2">
                <thead>
                    <tr>
                        <th>사업자 번호</th>
                        <th>사업자명</th>
                        <th>대표자명</th>
                        <th>업종</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.account.corporationNum }}</td>
                        <td>{{ orderData.account.accountName }}</td>
                        <td>{{ orderData.account.accountRepresentative }}</td>
                        <td>{{ orderData.account.accountType }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table3">
                <thead>
                    <tr>
                        <th>소재지</th>
                        <th>전화 번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ orderData.account.accountLocation }}</td>
                        <td>{{ orderData.account.accountContact }}</td>
                        <td>{{ orderData.account.accountEmail }}</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table4">
                <thead>
                    <tr>
                        <th>입금 코드</th>
                        <th>입금 일자</th>
                        <th>입금 금액</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="order-test333">
                            <div class="deposit-code-div33">
                                <input v-model="depositCode" type="text" id="deposit-code-box33" class="deposit-code-box33" @input="depositCode = depositCode.replace(/\s/g, '')">
                                <button class="deposit-code-btn33" @click="fetchCollectionData">확인</button>
                            </div>
                        </td>
                        <td>{{ collectionData.depositDate }}</td>
                        <td>{{ collectionData.depositPrice ? collectionData.depositPrice.toLocaleString() : '' }}</td>
                        <td><input v-model="taxInvoiceNote" type="text" id="remark-box2" class="remark-box2" placeholder="비고란 입력"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="billing-attachment23">
            <h2 class="billing-file">첨부파일</h2>
            <div v-for="(file, index) in files" :key="index" class="file-list23">
                <span class="file-icon">📄</span>
                <span class="file-name">{{ file.name }}</span>
            </div>
            <input type="file" @change="handleFileUpload" multiple class="file-upload-btn" id="file-upload"/>
            <label for="file-upload" class="file-upload-label">파일 선택</label>
        </div>
        <div class="billing-regist-btn-div">
            <button class="billing-regist-btn" @click="registerRequest">발행 요청하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

const projectCode = ref('');
const depositCode = ref('');
const taxInvoiceNote = ref('');
const orderData = ref({
    transaction: { transactionCode: '' },
    account: {
        accountCode: '',
        corporationStatus: '',
        corporationNum: '',
        accountName: '',
        accountRepresentative: '',
        accountType: '',
        accountLocation: '',
        accountContact: '',
        accountEmail: ''
    },
    orderTotalPrice: 0
});

const collectionData = ref({
    depositDate: '',
    depositPrice: 0,
    depositCategory: { depositCategory: '' }
});

const files = ref([]);

const fetchOrderData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order', {
            params: {
                contractCode: projectCode.value
            }
        });
        if (response.data && response.data.length > 0) {
            const matchedOrder = response.data.find(order => order.transaction.transactionCode === projectCode.value);
            if (matchedOrder) {
                orderData.value = matchedOrder;
            } else {
                alert('프로젝트 코드를 찾을 수 없습니다.');
            }
        } else {
            alert('프로젝트 코드를 찾을 수 없습니다.');
        }
    } catch (error) {
        console.error('Error fetching order data:', error);
        alert('데이터를 가져오는 중 오류가 발생했습니다.');
    }
};

const fetchCollectionData = async () => {
    try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/collection', {
            params: {
                depositCode: depositCode.value
            }
        });
        if (response.data && response.data.length > 0) {
            const result = response.data.find(item => item.depositCode === depositCode.value);
            if (result) {
                collectionData.value = result;
            } else {
                alert('입금 코드를 찾을 수 없습니다.');
            }
        } else {
            alert('입금 코드를 찾을 수 없습니다.');
        }
    } catch (error) {
        console.error('Error fetching collection data:', error);
        alert('데이터를 가져오는 중 오류가 발생했습니다.');
    }
};

const handleFileUpload = (event) => {
    const uploadedFiles = Array.from(event.target.files);
    files.value = uploadedFiles; // 기존 파일 목록을 초기화하고 새 파일로 교체
};


const removeFile = (index) => {
    files.value.splice(index, 1);
};

const registerRequest = async () => {
    // 모든 필수 필드가 채워졌는지 확인
    const isOrderValid = orderData.value.transaction.transactionCode;
    const isAccountValid = orderData.value.account.accountCode && orderData.value.account.corporationStatus && orderData.value.account.corporationNum && orderData.value.account.accountName && orderData.value.account.accountRepresentative && orderData.value.account.accountType && orderData.value.account.accountLocation && orderData.value.account.accountContact && orderData.value.account.accountEmail;
    const isCollectionValid = collectionData.value.depositDate && collectionData.value.depositPrice;

    if (!isOrderValid || !isAccountValid || !isCollectionValid) {
        alert('모든 필수 입력란을 채워주세요.');
        return;
    }

    // 첨부파일 확인
    if (files.value.length === 0) {
        alert('첨부파일을 등록해주세요.');
        return;
    }

    const taxInvoiceRequest = {
        taxInvoiceNote: taxInvoiceNote.value,
        order: {
            orderRegistrationId: orderData.value.orderRegistrationId
        },
        account: {
            accountId: orderData.value.account.accountId
        },
        employee: {
            employeeId: orderData.value.employee.employeeId
        },
        collection: {
            collectionId: collectionData.value.collectionId
        }
    };

    const formData = new FormData();
    formData.append('taxInvoiceRequest', new Blob([JSON.stringify(taxInvoiceRequest)], { type: 'application/json' }));
    files.value.forEach(file => {
        formData.append('files', file);
    });

    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/tax_invoice/regist', formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
        alert('발행 요청이 성공적으로 완료되었습니다.');
        router.push({ path: `/order` });
    } catch (error) {
        console.error('발행 요청 중 오류가 발생했습니다.', error);
        alert('발행 요청 중 오류가 발생했습니다.');
    }
};
</script>

<style>
    @import url('@/assets/css/bill/CBRequest.css');
</style>
