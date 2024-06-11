<template>
    <div class="delete-content7" v-if="deleteAccountData">
        <div class="order-search">
                <h1 class="maintext">거래처 삭제 내역</h1>
                <div class="estimate-approval-note1" >
                    <h3 class="delete-approval-note2">삭제 사유</h3>
                    <div class="delete-approval-note3">{{ deleteAccountData.accountDeleteRequestReason }}</div>
                    <button @click="processContractDeleteRequest(deleteAccountData.accountDeleteRequestId)"class="delete-button">거래처 삭제</button>
                </div>
            </div>
            <div class="estimate-list-box">
                <table class="estimate2-table1">
                    <thead>
                        <tr>
                            <th>거래처 코드</th>
                            <th>사업자 번호</th>
                            <th>사업자명</th>
                            <th>대표자명</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ deleteAccountData.account.accountCode }}</td>
                            <td>{{ deleteAccountData.account.corporationNum }}</td>
                            <td>{{ deleteAccountData.account.accountName }}</td>
                            <td>{{ deleteAccountData.account.accountRepresentative }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="estimate2-table2">
                    <thead>
                        <tr>
                            <th>기업 상태</th>
                            <th>법인 여부</th>
                            <th>소재지</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ deleteAccountData.account.accountStatus?.accountStatus }}</td>
                            <td>{{ deleteAccountData.account.corporationStatus }}</td>
                            <td>{{ deleteAccountData.account.accountLocation }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="estimate2-table3">
                    <thead>
                        <tr>
                            <th>업종</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ deleteAccountData.account.accountType }}</td>
                            <td>{{ deleteAccountData.account.accountContact }}</td>
                            <td>{{ deleteAccountData.account.accountEmail }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="estimate2-table4">
                    <thead>
                        <tr>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ deleteAccountData.account.accountNote }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'; 
import axios from 'axios';
import DeleteService from '@/components/delete/DeleteService';


const route = useRoute();
const router = useRouter();
const deleteAccountData = ref(null);

const fetchDeleteAccountData = async () => {
    const accountDeleteRequestId = route.params.accountDeleteRequestId;
    try {
        const response = await DeleteService.findAccountDeleteRequestById(accountDeleteRequestId);
        deleteAccountData.value = response.data;
    } catch (error) {
        console.error("Error fetching deleted account data:", error);
    }
};

const processContractDeleteRequest = async (accountDeleteRequestId) => {
    try {
        const requestData = { 
            accountDeleteRequestId: accountDeleteRequestId,
        };
        console.log('Request Data:', requestData); 
        await DeleteService.processAccountDeleteRequest(requestData);
        alert('거래처 삭제 요청이 성공적으로 처리되었습니다.');
        fetchDeleteAccountData(); 
        router.push('/delete');
    } catch (error) {
        console.error('거래처 삭제 요청 처리 중 오류가 발생했습니다:', error);
    }
};
onMounted(fetchDeleteAccountData);
</script>

<style>
/* 거래처 삭제 내역 스타일 */
.delete-content {
    padding: 20px;
}

.delete-search {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
}

.maintext {
    font-size: 24px;
    font-weight: bold;
}

.delete-approval-note {
    margin-top: 20px;
}

.delete-approval-note2 {
    font-size: 18px;
    font-weight: bold;
}

.delete-approval-note3 {
    margin-top: 10px;
    padding: 10px;
    border: 1px solid #ccc;
    background-color: #f9f9f9;
}

.delete-list-box {
    margin-top: 20px;
}

.delete-table1, .delete-table2, .delete-table3, .delete-table4 {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.delete-table1 th, .delete-table2 th, .delete-table3 th, .delete-table4 th,
.delete-table1 td, .delete-table2 td, .delete-table3 td, .delete-table4 td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

.delete-table1 th, .delete-table2 th, .delete-table3 th, .delete-table4 th {
    background-color: #f2f2f2;
}

.delete-process-box {
    margin-top: 20px;
}

.delete-process-text {
    font-size: 20px;
    font-weight: bold;
    margin-bottom: 10px;
}

.delete-process-box-detail {
    border: 1px solid #ddd;
    padding: 10px;
    margin-bottom: 10px;
    background-color: #f9f9f9;
}

.delete-process-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.delete-process-writer {
    font-weight: bold;
}

.delete-process-date {
    font-size: 14px;
    color: #888;
}

.delete-process-detail {
    margin-top: 10px;
}
</style>