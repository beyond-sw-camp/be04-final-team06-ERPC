<template>
    <div class="customer-edit-content11">
        <div class="customer-regist">
            <h1>거래처 정보 수정</h1>
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
                        <td>{{ accountData.corporationNum }}</td>
                        <td><input type="text" v-model="accountData.accountName" class="customer-test1" required></td>
                        <td><input type="text" v-model="accountData.accountRepresentative" class="customer-test2" required></td>
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
                        <td>{{ accountData.accountStatus?.accountStatus }}</td>
                        <td>
                            <select v-model="accountData.corporationStatus" class="customer-test3" required>
                                <option value="C">C</option>
                                <option value="P">P</option>
                            </select>
                        </td>
                        <td><input type="text" v-model="accountData.accountLocation" class="customer-test4" required></td>
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
                        <td><input type="text" v-model="accountData.accountType" class="customer-test5" required></td>
                        <td>
                            <input 
                                type="text" 
                                v-model="accountData.accountContact" 
                                class="customer-test6" 
                                :class="{ 'error': !validPhoneNumber }" 
                                @input="validatePhoneNumber"
                                required
                            >
                        </td>
                        <td>
                            <input 
                                type="text" 
                                v-model="accountData.accountEmail" 
                                class="customer-test7" 
                                :class="{ 'error': !validEmail }" 
                                @input="validateEmail"
                                required
                            >
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
                        <td><input type="text" v-model="accountData.accountNote" class="customer-test9"></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="customer-edit-btn-div33">
            <button @click="updateAccount" class="customer-edit-btn33">거래처 수정하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const accountData = ref({});
const validPhoneNumber = ref(true);
const validEmail = ref(true);

const employeeId = ref(null); // Employee ID를 저장하기 위한 ref
const employeeName = ref(''); // Employee Name을 저장하기 위한 ref

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

onMounted(async () => {
    fetchEmployeeData(); // 컴포넌트가 마운트될 때 employeeId와 employeeName을 가져옴

    const accountId = route.params.accountId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/${accountId}`);
        accountData.value = response.data;
    } catch (error) {
        console.error('Error fetching account data:', error);
        alert('거래처 정보를 불러오는 중 오류가 발생했습니다.');
    }
});

const validatePhoneNumber = () => {
    const phonePattern = /^[0-9]{2,3}-\d{3,4}-\d{4}$/;
    validPhoneNumber.value = phonePattern.test(accountData.value.accountContact);
}

const validateEmail = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    validEmail.value = emailPattern.test(accountData.value.accountEmail);
}

const updateAccount = async () => {
    if (
        !accountData.value.accountName ||
        !accountData.value.accountRepresentative ||
        !accountData.value.corporationStatus ||
        !accountData.value.accountLocation ||
        !accountData.value.accountType ||
        !accountData.value.accountContact ||
        !accountData.value.accountEmail
    ) {
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

    if (!employeeId.value) {
        alert('직원 ID를 찾을 수 없습니다.');
        return;
    }
    
    accountData.value.employee = { employeeId: employeeId.value };

    try {
        const response = await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/modify`, accountData.value);
        alert('거래처 정보가 성공적으로 수정되었습니다.');
        router.push({ path: `/customer/${route.params.accountId}` });
    } catch (error) {
        console.error('Error updating account data:', error);
        alert('거래처 정보를 수정하는 중 오류가 발생했습니다.');
    }
};
</script>

<style>
@import url('@/assets/css/customer/CustomerEdit.css');
</style>
