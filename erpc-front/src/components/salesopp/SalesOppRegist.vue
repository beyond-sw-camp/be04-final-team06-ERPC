<template>
    <div class="regist-content">
        <div class="order-regist">
            <h1>영업기회 등록</h1>
        </div>
        <div class="order-list-box1">
            <table class="order-table1">
                <thead>
                    <tr>
                        <th>거래처 명</th>
                        <th>거래처 담당자</th>
                        <th>거래처 주소</th>
                        <th>연락처</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input v-model="newOpp.oppAccountName"></td>
                        <td><input v-model="newOpp.oppAccountPic"></td>
                        <td><input v-model="newOpp.oppAccountLocation"></td>
                        <td><input v-model="newOpp.oppAccountContact" @input="validateContact" placeholder="02-123-1234 or 010-1234-1234"></td>
                        <td><input v-model="newOpp.oppAccountEmail"  placeholder="example@erpc.com"></td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table2">
                <thead>
                    <tr>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input v-model="newOpp.oppAccountNote"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-regist-btn-div">
            <button class="estimate-regist-btn" @click="registSalesOpp">영업기회 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();
const newOpp = ref({
    oppAccountName: '',
    oppAccountPic: '',
    oppAccountLocation: '',
    oppAccountContact: '',
    oppAccountNote: '',
    oppAccountEmail:'',
    salesOppStatus: { salesOppStatusId: 1 }, // 기본 상태 ID 설정
    employee: {
        employeeId: 5
    },
});

const validateContact = () => {
    const phonePattern = /^[0-9]{2,3}-\d{3,4}-\d{4}$/;
    validPhoneNumber.value = phonePattern.test(accountContact.value);
}
const validateEmailFormat = (email) => {
    // 이메일에 '@'가 있는지 확인
    return email.includes('@');
};

const registSalesOpp = async () => {
    if (newOpp.value.oppAccountEmail && !validateEmailFormat(newOpp.value.oppAccountEmail)) {
        alert('이메일 형식이 맞지 않습니다.');
        return;
    }
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales_opportunity/regist', newOpp.value);
        alert('영업기회가 성공적으로 등록되었습니다.');
        router.push('/salesOpp/list');
        console.log('등록된 영업기회:', response.data);
    } catch (error) {
        console.error('영업기회 등록 중 오류가 발생했습니다:', error);
        alert('영업기회 등록 중 오류가 발생했습니다.');
    }
};
</script>

<style scoped>
::placeholder {
    font-size: 10.5px;
}

.order-regist h1 {
    margin: 0;
    font-size: 24px;
    color: #333;
}

.order-list-box1 {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    box-sizing: border-box;
    background-color: white;
    height: auto;
    max-width: 1200px;
    margin: 20px auto;
    gap: 1px;
}

.order-table1,
.order-table2 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.order-table1 th,
.order-table1 td,
.order-table2 th,
.order-table2 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.order-table1 th,
.order-table2 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.order-table1 td,
.order-table2 td {
    height: 40px;
}

.salesOpp-test1,
.salesOpp-test2,
.salesOpp-test3,
.salesOpp-test4,
.salesOpp-test5,
.salesOpp-test6 {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
}

.estimate-regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.estimate-regist-btn {
    width: 320px;
    padding: 10px 20px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    font-size: 18px;
    margin-top: 30px;
    margin-bottom: 100px;
}

.estimate-regist-btn:hover {
    background-color: #007bff;
}   
</style>