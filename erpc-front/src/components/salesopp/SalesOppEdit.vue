<template>
    <div class="regist-content" v-if="salesOppData">
        <h1>영업기회 수정</h1>
        <div class="salesOpp-list-box1">
            <table class="salesOpp2-table1">
                <thead>
                    <tr>
                        <th>거래처명</th>
                        <th>거래처 담당자</th>
                        <th>거래처 주소</th>
                        <th>연락처</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountName" class="salesOpp-test1"></td>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountPic" class="salesOpp-test2"></td>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountLocation" class="salesOpp-test3"></td>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountContact" class="salesOpp-test4"></td>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountEmail" class="salesOpp-test5"></td>
                    </tr>
                </tbody>
            </table>
            <table class="salesOpp2-table2">
                <thead>
                    <tr>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input type="text" v-model="editedSalesOppData.oppAccountNote" class="salesOpp-test6"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="salesOpp-regist-btn-div">
            <button @click="updatesalesOpp" class="salesOpp-regist-btn">영업기회 수정하기</button>
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
const salesOppData = ref(null);
const editedSalesOppData = ref({
    oppAccountName: '',
    oppAccountPic: '',
    oppAccountLocation: '',
    oppAccountContact: '',
    oppAccountEmail: '',
    salesOppNote: ''
});

onMounted(async () => {
    const salesOppId = route.params.salesOppId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales_opportunity/${salesOppId}`, { withCredentials: true });
        salesOppData.value = response.data;
        // 클라이언트에 가져온 데이터를 수정용 데이터에 반영
        Object.assign(editedSalesOppData.value, response.data);
    } catch (error) {
        console.error('Error fetching sales_opportunity data:', error);
    }
});

const updatesalesOpp = async () => {
    const salesOppId = route.params.salesOppId;

    try {
        const requestData = {
            oppAccountName: editedSalesOppData.value.oppAccountName,
            oppAccountPic: editedSalesOppData.value.oppAccountPic,
            oppAccountLocation: editedSalesOppData.value.oppAccountLocation,
            oppAccountContact: editedSalesOppData.value.oppAccountContact,
            oppAccountEmail: editedSalesOppData.value.oppAccountEmail,
            oppAccountNote: editedSalesOppData.value.oppAccountNote
        };

        console.log('Request Data:', requestData); // 데이터를 콘솔에 출력

        await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/sales_opportunity/modify/${salesOppId}`, requestData, {
            withCredentials: true
        });
        
        alert('영업기회가 성공적으로 수정되었습니다.');
        router.push({ path: `/salesopp/${salesOppId}`});
    } catch (error) {
        console.error('영업기회를 수정하는 중 오류가 발생했습니다.', error);
        alert('영업기회를 수정하는 중 오류가 발생했습니다.');
    }
};
</script>

<style>
.regist-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.salesOpp-list-box1 {
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

.salesOpp2-table1,
.salesOpp2-table2 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.salesOpp2-table1 th,
.salesOpp2-table1 td,
.salesOpp2-table2 th,
.salesOpp2-table2 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.salesOpp2-table1 th,
.salesOpp2-table2 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.salesOpp2-table1 td,
.salesOpp2-table2 td {
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

.salesOpp-regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.salesOpp-regist-btn {
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

.salesOpp-regist-btn:hover {
    background-color: #007bff;
}
</style>
