<template>
    <div class="delete-content7" v-if="deleteRequestData">
        <div class="order-search">
        <h1 class="maintext">계약서 삭제 내역</h1>
        <div class="estimate-approval-note1" >
                <h3 class="estimate-approval-note2">삭제 사유</h3>
                <div class="estimate-approval-note3">{{ deleteRequestData.contractDeleteRequestReason }}</div>
        <button @click="processContractDeleteRequest(deleteRequestData.contractDeleteRequestId)"class="delete-button">계약서 삭제</button>
</div>
</div>
        <div class="estimate-list-box">
            <table class="estimate2-table1">
                <thead>
                    <tr>
                        <th>계약서 코드</th>
                        <th>계약 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 요청 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ deleteRequestData.contract.contractCode }}</td>
                        <td>{{ deleteRequestData.contract.contractTotalPrice.toLocaleString() }}</td>
                        <td>{{ deleteRequestData.contract.contractDate }}</td>
                        <td>{{ deleteRequestData.requestDate }}</td>
                        <td>{{ deleteRequestData.contract.contractDueDate }}</td>
                    </tr>
                </tbody>
            </table>

            <table class="estimate2-table2">
                <thead>
                    <tr>
                        <th>담당자</th>
                        <th>거래처명</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{{ deleteRequestData.contract.employee.employeeName }}</td>
                        <td>{{ deleteRequestData.contract.account.accountName }}</td>
                    </tr>
                </tbody>
            </table>
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
import DeleteService from '@/components/delete/DeleteService';


const route = useRoute();
const router = useRouter();
const deleteRequestData = ref(null);

const fetchDeleteRequestData = async () => {
    const contractDeleteRequestId = route.params.contractDeleteRequestId;
    try {
        const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/delete/contract/${contractDeleteRequestId}`);
        deleteRequestData.value = response.data;
    } catch (error) {
        console.error("Error fetching delete request data:", error);
    }
};

const processContractDeleteRequest = async (contractDeleteRequestId) => {
    try {
        const requestData = { 
            contractDeleteRequestId: contractDeleteRequestId,
        };
        console.log('Request Data:', requestData); 
        await DeleteService.updateContractDeleteRequestStatus(requestData);
        alert('계약서 삭제 요청이 성공적으로 처리되었습니다.');
        fetchDeleteRequestData(); 
        router.push('/delete');
    } catch (error) {
        console.error('계약서 삭제 요청 처리 중 오류가 발생했습니다:', error);
    }
};

onMounted(fetchDeleteRequestData);


</script>

<style scoped>
.delete-request-content {
    padding: 20px;
}

.maintext {
    font-size: 24px;
    margin-bottom: 20px;
}

.delete-btns {
    display: flex;
    gap: 10px;
    margin-bottom: 20px;
}

.approve-btn, .reject-btn {
    padding: 10px 20px;
    border: none;
    cursor: pointer;
}

.approve-btn {
    background-color: green;
    color: white;
}

.reject-btn {
    background-color: red;
    color: white;
}

.contract-details {
    margin-top: 20px;
}

.details-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.details-table th, .details-table td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

.popup-overlay {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
}

.popup-content {
    background: white;
    padding: 20px;
    border-radius: 5px;
    width: 300px;
}

.confirm-btn, .cancel-btn {
    padding: 10px;
    margin-top: 10px;
    width: 100%;
    cursor: pointer;
}

.confirm-btn {
    background-color: blue;
    color: white;
    border: none;
}

.cancel-btn {
    background-color: gray;
    color: white;
    border: none;
    margin-top: 5px;
}
</style>