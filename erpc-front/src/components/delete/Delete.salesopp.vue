<template>
    <div class="delete-content7">
        <div v-if="!deleteSalesOppData">로딩 중...</div>
        <div v-else>
            <div class="order-search">
                <h1 class="maintext">영업기회 삭제 내역</h1>
                <div class="estimate-approval-note1">
                    <h3 class="estimate-approval-note2">삭제 사유</h3>
                    <div class="estimate-approval-note3">{{ deleteSalesOppData.salesOppDeleteRequestReason }}</div>
                    <button @click="processSalesOppDeleteRequest(deleteSalesOppData.salesOppDeleteRequestId)" class="delete-button">영업기회 삭제</button>
                </div>
            </div>
            <div class="estimate-list-box">
                <table class="estimate-table1">
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
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountName }}</td>
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountPic }}</td>
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountLocation }}</td>
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountContact }}</td>
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountEmail }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="estimate-table2">
                    <thead>
                        <tr>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ deleteSalesOppData.salesOpp.oppAccountNote }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router'; 
import DeleteService from '@/components/delete/DeleteService';

const route = useRoute();
const router = useRouter();
const deleteSalesOppData = ref(null);

const fetchDeleteSalesOppData = async () => {
    const salesOppDeleteRequestId = route.params.salesOppDeleteRequestId;
    try {
        const response = await DeleteService.findSalesOppDeleteRequestById(salesOppDeleteRequestId);
        deleteSalesOppData.value = response.data;
    } catch (error) {
        console.error("Error fetching deleted sales opportunity data:", error);
    }
};
const processSalesOppDeleteRequest = async (salesOppDeleteRequestId) => {
    try {
        const requestData = { 
            salesOppDeleteRequestId: salesOppDeleteRequestId,
        };
        console.log('Request Data:', requestData); 
        await DeleteService.updateSalesOppDeleteRequestProcess(requestData);
        alert('영업기회 삭제 요청이 성공적으로 처리되었습니다.');
        fetchDeleteSalesOppData(); 
        router.push('/delete');
    } catch (error) {
        console.error('영업기회 삭제 요청 처리 중 오류가 발생했습니다:', error);
    }
};

onMounted(fetchDeleteSalesOppData);
</script>

<style>
.delete-button {
  background-color: #7581cb; /* 버튼 배경색 */
  color: white;             /* 버튼 글자색 */
  padding: 10px 20px;       /* 버튼 패딩 */
  border: none;             /* 테두리 없음 */
  border-radius: 5px;       /* 둥근 모서리 */
  cursor: pointer;          /* 포인터 커서 */
  font-size: 14px;          /* 글자 크기 */
  font-weight: bold;        /* 글자 두께 */
  transition: background-color 0.3s ease; /* 배경색 변화 애니메이션 */
}

/* 호버 시 스타일 */
.delete-button:hover {
  background-color: #7581cb; /* 호버 시 배경색 */
}

/* 비활성화 시 스타일 */
.delete-button:disabled {
  background-color: #ccc; /* 비활성화 시 배경색 */
  cursor: not-allowed;    /* 비활성화 시 커서 */
}
</style>