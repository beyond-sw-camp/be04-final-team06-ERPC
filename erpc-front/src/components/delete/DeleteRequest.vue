<template>
  <div class="delete-request-content">
    <div class="delete-request-search">
      <h1 class="delete-request-title">삭제요청관리</h1>
    </div>
    <div class="filter-box">
      <div class="filter-controls">
        <select v-model="selectedDeleteRequestType" @change="applyFilter" class="delete-type-select">
          <option value="salesOpp">영업기회 삭제</option>
          <option value="contract">계약서 삭제</option>
          <option value="quotation">견적서 삭제</option>
          <option value="account">거래처 삭제</option>
          <option value="order">수주 삭제</option>
        </select>
        <input type="text" class="delete-request-search-input" v-model="searchQuery" :placeholder="placeholderText" @input="toUpperCase">
        <button class="delete-request-search-btn" @click="applyFilter">조회하기</button>
      </div>
    </div>

    <div class="delete-request-list">
      <template v-if="selectedDeleteRequestType === 'salesOpp'">
        <div class="delete-request-table-wrapper">
          <h2 class="delete-request-subtitle">영업기회 삭제 요청</h2>
          <table class="delete-request-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>거래처명</th>
                <th>거래처 담당자</th>
                <th>작성 일자</th>
                <th>요청 사유</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(request, index) in filteredsalesOppDeleteRequests" :key="request.salesOppDeleteRequestId" @click="goToSalesOppDetail(request.salesOppDeleteRequestId)">
                <td>{{ filteredsalesOppDeleteRequests.length - index }}</td>
                <td>{{ request.salesOpp.oppAccountName }}</td>
                <td>{{ request.salesOpp.oppAccountPic }}</td>
                <td>{{ request.salesOpp.oppDate }}</td>
                <td>{{ request.salesOppDeleteRequestReason }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </template>

      <template v-if="selectedDeleteRequestType === 'contract'">
        <div class="delete-request-table-wrapper">
          <h2 class="delete-request-subtitle">계약서 삭제 요청</h2>
          <table class="delete-request-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>계약서 코드</th>
                <th>거래처명</th>
                <th>요청일자</th>
                <th>요청 사유</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(request, index) in filteredContractDeleteRequests" :key="request.contractDeleteRequestId" @click="goToContractDetail(request.contractDeleteRequestId)">
                <td>{{ filteredContractDeleteRequests.length - index }}</td>
                <td>{{ request.contract.contractCode }}</td>
                <td>{{ request.contract.account.accountName }}</td>
                <td>{{ request.contract.contractDueDate }}</td>
                <td>{{ request.contractDeleteRequestReason }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </template>

      <!-- 추가된 삭제 요청 유형들 -->
      <template v-if="selectedDeleteRequestType === 'quotation'">
        <div class="delete-request-table-wrapper">
          <h2 class="delete-request-subtitle">견적서 삭제 요청</h2>
          <table class="delete-request-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>견적서 코드</th>
                <th>거래처명</th>
                <th>요청일자</th>
                <th>요청 사유</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(request, index) in filteredQuotationDeleteRequests" :key="request.quotationDeleteRequestId" @click="goToQuotationDetail(request.quotationDeleteRequestId)">
                <td>{{ filteredQuotationDeleteRequests.length - index }}</td>
                <td>{{ request.quotation.quotationCode }}</td>
                <td>{{ request.quotation.account.accountName }}</td>
                <td>{{ request.requestDate }}</td>
                <td>{{ request.quotationDeleteRequestReason }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </template>

      <template v-if="selectedDeleteRequestType === 'account'">
        <div class="delete-request-table-wrapper">
          <h2 class="delete-request-subtitle">거래처 삭제 요청</h2>
          <table class="delete-request-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>거래처 코드</th>
                <th>거래처명</th>
                <th>대표자명</th>
                <th>요청 사유</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(request, index) in filteredAccountDeleteRequests" :key="request.accountDeleteRequestId"  @click="goToAccountDetail(request.accountDeleteRequestId)">
                <td>{{ filteredAccountDeleteRequests.length - index }}</td>
                <td>{{ request.account.accountCode }}</td>
                <td>{{ request.account.accountName }}</td>
                <td>{{ request.account.accountRepresentative }}</td>
                <td>{{ request.accountDeleteRequestReason }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </template>

      <template v-if="selectedDeleteRequestType === 'order'">
        <div class="delete-request-table-wrapper">
          <h2 class="delete-request-subtitle">수주 삭제 요청</h2>
          <table class="delete-request-table">
            <thead>
              <tr>
                <th>번호</th>
                <th>프로젝트 코드</th>
                <th>거래처명</th>
                <th>마감 일자</th>
                <th>요청 사유</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(request, index) in filteredOrderDeleteRequests" :key="request.orderDeleteRequestId" @click="goToOrderDetail(request.orderDeleteRequestId)">
                <td>{{ filteredOrderDeleteRequests.length - index }}</td>
                <td>{{ request.order.transaction.transactionCode  }}</td>
                <td>{{ request.order.account.accountName }}</td>
                <td>{{ request.order.orderDueDate }}</td>
                <td>{{ request.orderDeleteRequestReason }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </template>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed, watch } from 'vue';
import DeleteService from '@/components/delete/DeleteService';
import { useRouter } from 'vue-router';

const router = useRouter();
// 데이터 및 변수 선언
const salesOppDeleteRequests = ref([]);
const contractDeleteRequests = ref([]);
const quotationDeleteRequests = ref([]);
const accountDeleteRequests = ref([]);
const orderDeleteRequests = ref([]);
const searchQuery = ref('');
const selectedDeleteRequestType = ref('salesOpp');
const filteredsalesOppDeleteRequests = ref([]);
const filteredContractDeleteRequests = ref([]);
const filteredQuotationDeleteRequests = ref([]);
const filteredAccountDeleteRequests = ref([]);
const filteredOrderDeleteRequests = ref([]);

// 플레이스홀더 텍스트를 동적으로 설정
const placeholderText = computed(() => {
  switch (selectedDeleteRequestType.value) {
    case 'salesOpp':
      return '영업기회 코드로 조회';
    case 'contract':
      return '계약서 코드로 조회';
    case 'quotation':
      return '견적서 코드로 조회';
    case 'account':
      return '거래처 코드로 조회';
    case 'order':
      return '수주 코드로 조회';
    default:
      return '코드로 조회';
  }
});

// 검색어를 대문자로 변환
const toUpperCase = () => {
  searchQuery.value = searchQuery.value.toUpperCase();
};

// 데이터 가져오기 및 필터링 적용
onMounted(async () => {
  await fetchDeleteRequests();
  applyFilter();
});

// 선택된 삭제 요청 유형이 변경될 때 필터 적용
watch([selectedDeleteRequestType, searchQuery], () => {
  applyFilter();
});

// 각 삭제 요청 데이터 가져오기
const fetchDeleteRequests = async () => {
  try {
    // Fetch all delete requests using DeleteService
    salesOppDeleteRequests.value = (await DeleteService.getSalesOppDeleteRequests()).data;
    contractDeleteRequests.value = (await DeleteService.getContractDeleteRequests()).data;
    quotationDeleteRequests.value = (await DeleteService.getQuotationDeleteRequests()).data;
    accountDeleteRequests.value = (await DeleteService.getAccountDeleteRequests()).data;
    orderDeleteRequests.value = (await DeleteService.getOrderDeleteRequests()).data;
    
    // Initialize filtered data with the full data
    filteredsalesOppDeleteRequests.value = salesOppDeleteRequests.value;
    filteredContractDeleteRequests.value = contractDeleteRequests.value;
    filteredQuotationDeleteRequests.value = quotationDeleteRequests.value;
    filteredAccountDeleteRequests.value = accountDeleteRequests.value;
    filteredOrderDeleteRequests.value = orderDeleteRequests.value;
    console.log('Loaded contract delete requests:', contractDeleteRequests.value);
  } catch (error) {
    console.error('Error fetching delete requests:', error);
  }
};

// 필터링 로직
const applyFilter = () => {
  const filterQuery = searchQuery.value;

  filteredsalesOppDeleteRequests.value = salesOppDeleteRequests.value.filter(request =>
    (!filterQuery || request.salesOpp.salesOppCode.includes(filterQuery)) && request.salesOppDeleteRequestStatus === 'N'
  );

  filteredContractDeleteRequests.value = contractDeleteRequests.value.filter(request =>
    (!filterQuery || request.contract.contractCode.includes(filterQuery)) && request.contractDeleteRequestStatus === 'N'
  );

  filteredQuotationDeleteRequests.value = quotationDeleteRequests.value.filter(request =>
    (!filterQuery || request.quotation.quotationCode.includes(filterQuery)) && request.quotationDeleteRequestStatus === 'N'
  );

  filteredAccountDeleteRequests.value = accountDeleteRequests.value.filter(request =>
    (!filterQuery || request.account.accountCode.includes(filterQuery)) && request.accountDeleteRequestStatus === 'N'
  );

  filteredOrderDeleteRequests.value = orderDeleteRequests.value.filter(request =>
    (!filterQuery || request.order.orderCode.includes(filterQuery)) && request.orderDeleteRequestStatus === 'N'
  );
};
const goToQuotationDetail = (quotationDeleteRequestId) => {
  router.push({ path: `/delete/quotation/${quotationDeleteRequestId}` });
};
const goToOrderDetail = (orderDeleteRequestsId) => {
  router.push({ path: `/delete/order/${orderDeleteRequestsId}` });
};
const goToSalesOppDetail = (salesOppDeleteRequestId) => {
  router.push({ path: `/delete/salesOpp/${salesOppDeleteRequestId}` });
};
const goToAccountDetail = (accountDeleteRequestId) => {
  router.push({ path: `/delete/account/${accountDeleteRequestId}` });
};
const goToContractDetail = (contractDeleteRequestId) => {
  router.push({ path: `/delete/contract/${contractDeleteRequestId}` });
};
</script>

<style scoped>

.delete-request-content {
  padding: 20px;
  max-width: 1200px; /* 페이지 중앙에 고정된 넓이 */
  margin: 0 auto; /* 페이지 중앙에 정렬 */
}

.delete-request-search {
  margin-bottom: 20px;
}

.delete-request-title {
  font-size: 24px;
  font-weight: bold;
  text-align: center; /* 제목을 중앙에 정렬 */
}

.filter-box {
  margin-bottom: 20px;
  display: flex;
  justify-content: center; /* 필터 박스를 중앙에 정렬 */
}

.filter-controls {
  display: flex;
  align-items: center;
  gap: 10px; /* 요소 간의 간격 */
}

.delete-type-select,
.delete-request-search-input {
  padding: 5px;
  font-size: 16px; /* 입력 필드의 폰트 크기 조정 */
}

.delete-request-search-btn {
  padding: 5px 10px;
  font-size: 16px; /* 버튼의 폰트 크기 조정 */
}

.delete-request-list {
  margin-top: 20px;
  display: flex;
  justify-content: center; /* 테이블을 중앙에 정렬 */
}

.delete-request-table-wrapper {
  margin-bottom: 40px; /* 각 테이블 섹션의 아래에 여백 추가 */
}

.delete-request-subtitle {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 10px; /* 테이블 위에 여백 추가 */
  text-align: center; /* 부제목을 중앙에 정렬 */
}

.delete-request-table {
  width: 100%; /* 테이블을 부모 요소에 맞게 확장 */
  max-width: 1200px; /* 최대 넓이를 설정 */
  border-collapse: collapse;
  text-align: left; /* 텍스트를 왼쪽에 정렬 */
  margin: 0 auto; /* 테이블을 중앙에 정렬 */
}

.delete-request-table th, 
.delete-request-table td {
  border: 1px solid #ddd;
  padding: 8px;
}

.delete-request-table th {
  background-color: #f2f2f2;
  font-weight: bold;
}

.delete-request-table th:first-child,
.delete-request-table td:first-child {
  width: 50px; /* 번호 열의 폭을 고정 */
  text-align: center; /* 번호 열의 텍스트를 중앙에 정렬 */
}

.delete-request-table th:nth-child(2),
.delete-request-table td:nth-child(2) {
  width: 150px; /* 코드 열의 폭을 고정 */
}

.delete-request-table th:nth-child(3),
.delete-request-table td:nth-child(3) {
  width: 200px; /* 거래처명 열의 폭을 고정 */
}

.delete-request-table th:nth-child(4),
.delete-request-table td:nth-child(4) {
  width: 150px; /* 요청일자 열의 폭을 고정 */
}

.delete-request-table th:nth-child(5),
.delete-request-table td:nth-child(5) {
  width: 300px; /* 요청 사유 열의 폭을 고정 */
}

.delete-request-table td {
  word-break: break-word; /* 긴 텍스트를 줄 바꿈 */
}
</style>
