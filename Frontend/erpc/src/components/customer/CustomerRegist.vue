<template>
    <div class="search-content">
        <div class="customer-search">
            <h1>사업자 신용 정보 조회</h1>
        </div>
        <div class="search-box">
            <div class="business-number">
                <p class="business-number-text">사업자 번호</p>
                <input type="text" v-model="brNo" class="business-number-box" placeholder="사업자 번호를 입력해주세요.">
            </div>
            <div class="search-btn-div1">
                <button @click="fetchBusinessData" class="search-btn1">조회하기</button>
            </div>
        </div>
        <div class="regist-content">
            <div class="customer-regist">
                <h1>거래처 등록</h1>
            </div>
            <div class="customer-list-box1">
                <table class="customer-table1">
                    <thead>
                        <tr>
                            <th>사업자 번호</th>
                            <th>사업자명</th>
                            <th>대표자명</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ businessNumber }}</td>
                            <td><input type="text" v-model="accountName" class="customer-test1"></td>
                            <td><input type="text" v-model="accountRepresentative" class="customer-test2"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table2">
                    <thead>
                        <tr>
                            <th>기업 상태</th>
                            <th>법인 여부</th>
                            <th>소재지</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ businessStatus }}</td>
                            <td><input type="text" v-model="corporationStatus" class="customer-test3"></td>
                            <td><input type="text" v-model="accountLocation" class="customer-test4"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table3">
                    <thead>
                        <tr>
                            <th>업종</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" v-model="accountType" class="customer-test5"></td>
                            <td><input type="text" v-model="accountContact" class="customer-test6"></td>
                            <td><input type="text" v-model="accountEmail" class="customer-test7"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table4">
                    <thead>
                        <tr>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" v-model="accountNote" class="customer-test9"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="customer-regist-btn-div">
            <button @click="registerAccount" class="customer-regist-btn">거래처 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const brNo = ref('');
const businessStatus = ref('');
const businessNumber = ref('');
const taxType = ref('');
const accountName = ref('');
const accountRepresentative = ref('');
const corporationStatus = ref('');
const accountLocation = ref('');
const accountContact = ref('');
const accountEmail = ref('');
const accountNote = ref('');
const accountType = ref('');

const fetchBusinessData = async () => {
    try {
        console.log('사업자 번호:', brNo.value);
        const response = await axios.post('https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=IU5nhZBdwX%2FQMWdk0H0JTyf%2BUeqSzFG7Q6JNh%2Fvwuj%2BIt4%2F1wIy2ikm65nd5EisKla2Z3w1InmzW8MMEhu%2BRNA%3D%3D', {
            b_no: [brNo.value]
        });
        console.log('API 응답:', response.data);
        if (response.data.data && response.data.data.length > 0) {
            const result = response.data.data[0];
            businessNumber.value = result.b_no;
            businessStatus.value = result.b_stt;
            taxType.value = result.tax_type;
            console.log('사업자 번호:', businessNumber.value, '사업자 상태:', businessStatus.value, '세금 유형:', taxType.value);
            if (taxType.value === "국세청에 등록되지 않은 사업자등록번호입니다.") {
                alert(taxType.value);
            }
        } else {
            alert('조회된 결과가 없습니다.');
            console.warn('조회된 결과가 없습니다.');
        }
    } catch (error) {
        console.error('Error fetching business data:', error);
        alert('사업자 정보를 조회하는 중 오류가 발생했습니다.');
    }
}

const registerAccount = async () => {
    const postData = {
        accountName: accountName.value,
        corporationNum: businessNumber.value,
        accountDeleteDate: null,
        accountRepresentitive: accountRepresentative.value,
        corporationStatus: corporationStatus.value,
        accountLocation: accountLocation.value,
        accountContact: accountContact.value,
        accountEmail: accountEmail.value,
        accountNote: accountNote.value,
        accountType: accountType.value,
        employee: {
            employeeId: 1
        },
        accountStatus: {
            accountStatusId: 4
        }
    };

    try {
        const response = await axios.post('http://localhost:7775/account/regist', postData);
        console.log('등록 응답:', response.data);
        alert('거래처 등록이 완료되었습니다.');
    } catch (error) {
        console.error('등록 중 오류 발생:', error);
        alert('거래처 등록 중 오류가 발생했습니다.');
    }
}
</script>

<style>
    @import url('@/assets/css/customer/CustomerRegist.css');
</style>
