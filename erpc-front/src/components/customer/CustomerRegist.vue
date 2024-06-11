<template>
    <div class="customer-regist-content11">
        <div class="customer-regist">
            <h1>거래처 등록</h1>
        </div>
        <div class="customer-list-box33">
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
                        <td>
                            <div class="business-number-div33">
                                <input type="text" v-model="brNo" @input="formatBusinessNumber" class="business-number-box33"/>
                                <button @click="fetchBusinessData" class="business-number-btn33">확인</button>
                            </div>
                        </td>
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
                        <td>
                            <select v-model="corporationStatus" class="customer-test3">
                                <option value="C">C</option>
                                <option value="P">P</option>
                            </select>
                        </td>
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
                        <td>
                            <input 
                                type="text" 
                                v-model="accountContact" 
                                class="customer-test6" 
                                :class="{ 'error': !validPhoneNumber }" 
                                @input="validatePhoneNumber"
                                placeholder="02-123-1234 or 010-1234-1234">
                        </td>
                        <td>
                            <input 
                                type="text" 
                                v-model="accountEmail" 
                                class="customer-test7" 
                                :class="{ 'error': !validEmail }" 
                                @input="validateEmail"
                                placeholder="example@erpc.com">
                        </td>
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

        <div class="customer-regist-btn-div33">
            <button @click="registerAccount" class="customer-regist-btn33">거래처 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router/mainRouter';

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
const validPhoneNumber = ref(true);
const validEmail = ref(true);

const formatBusinessNumber = () => {
    let value = brNo.value.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거

    if (value.length > 10) {
        value = value.slice(0, 10); // 10자리 초과 입력 방지
    }

    const parts = [];
    if (value.length > 3) {
        parts.push(value.slice(0, 3));
        value = value.slice(3);
    }
    if (value.length > 2) {
        parts.push(value.slice(0, 2));
        value = value.slice(2);
    }
    if (value.length > 0) {
        parts.push(value);
    }
    brNo.value = parts.join('-');
}

const fetchBusinessData = async () => {
    if (!brNo.value) {
        alert("사업자 번호를 입력해주세요.");
        return;
    }

    const cleanBrNo = brNo.value.replace(/-/g, ''); // 하이픈 제거

    try {
        console.log('사업자 번호:', brNo.value);
        cleanBrNo
        const response = await axios.post('https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=IU5nhZBdwX%2FQMWdk0H0JTyf%2BUeqSzFG7Q6JNh%2Fvwuj%2BIt4%2F1wIy2ikm65nd5EisKla2Z3w1InmzW8MMEhu%2BRNA%3D%3D', {
            b_no: [cleanBrNo]
        });
        console.log('API 응답:', response.data);
        if (response.data.data && response.data.data.length > 0) {
            const result = response.data.data[0];
            businessNumber.value = result.b_no;
            taxType.value = result.tax_type;
            console.log('사업자 번호:', businessNumber.value, '사업자 상태:', result.b_stt, '세금 유형:', taxType.value);
            if (taxType.value === "국세청에 등록되지 않은 사업자등록번호입니다.") {
                alert(taxType.value);
                return;
            }
            if (result.b_stt === "계속사업자") {
                businessStatus.value = "영업중";
            } else if (result.b_stt === "휴업자") {
                alert("해당 사업자 번호는 휴업 상태입니다. 다시 확인해주세요.");
                businessStatus.value = "";
                return;
            } else if (result.b_stt === "폐업자") {
                alert("해당 사업자 번호는 폐업 상태입니다. 다시 확인해주세요.");
                businessStatus.value = "";
                return;
            } else {
                alert("국세청에 등록되지 않은 사업자등록번호입니다.");
                businessStatus.value = "";
                return;
            }
        } else {
            alert('조회된 결과가 없습니다.');
            console.warn('조회된 결과가 없습니다.');
            businessStatus.value = "";
        }
    } catch (error) {
        console.error('Error fetching business data:', error);
        alert('사업자 정보를 조회하는 중 오류가 발생했습니다.');
        businessStatus.value = "";
    }
}

const validatePhoneNumber = () => {
    const phonePattern = /^[0-9]{2,3}-\d{3,4}-\d{4}$/;
    validPhoneNumber.value = phonePattern.test(accountContact.value);
}

const validateEmail = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    validEmail.value = emailPattern.test(accountEmail.value);
}

const registerAccount = async () => {
    // 필수 필드가 모두 채워졌는지 확인
    if (!brNo.value || !accountName.value || !accountRepresentative.value || !corporationStatus.value || !accountLocation.value || !accountContact.value || !accountEmail.value || !accountType.value) {
        alert('모든 필수 입력란을 채워주세요.');
        return;
    }

    // 전화번호와 이메일 형식이 유효한지 확인
    validatePhoneNumber();
    validateEmail();
    if (!validPhoneNumber.value) {
        alert('전화번호 형식이 올바르지 않습니다. 예시: 010-1234-5678, 02-1234-5678');
        return;
    }
    if (!validEmail.value) {
        alert('이메일 형식이 올바르지 않습니다. 예시: example@example.com');
        return;
    }

    // LocalStorage에서 employeeId를 가져옴
    const employeeId = localStorage.getItem('userId');
    if (!employeeId) {
        alert('로그인 정보가 없습니다. 다시 로그인해주세요.');
        return;
    }

    const postData = {
        accountName: accountName.value,
        corporationNum: brNo.value,
        accountDeleteDate: null,
        accountRepresentative: accountRepresentative.value,
        corporationStatus: corporationStatus.value,
        accountLocation: accountLocation.value,
        accountContact: accountContact.value,
        accountEmail: accountEmail.value,
        accountNote: accountNote.value,
        accountType: accountType.value,
        employee: {
            employeeId: employeeId  // LocalStorage에서 가져온 employeeId 사용
        },
        accountStatus: {
            accountStatusId: businessStatus.value === "영업중" ? 1 : 4 // Assume 1 is the ID for "영업중"
        }
    };

    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/regist', postData);
        console.log('등록 응답:', response.data);
        alert('거래처 등록이 완료되었습니다.');
        router.push({ path: `/customer` });
    } catch (error) {
        console.error('등록 중 오류 발생:', error);
        alert('거래처 등록 중 오류가 발생했습니다.');
    }
}
</script>

<style>
    @import url('@/assets/css/customer/CustomerRegist.css');
</style>
