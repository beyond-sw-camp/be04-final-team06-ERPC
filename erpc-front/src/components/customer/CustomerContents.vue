<template>
    <div class="customer-content">
        <div class="customer-search">
            <h1>거래처 정보 조회 내역</h1>
            <div class="customer-btn">
                <button class="customer-edit" @click="handleEditAccount" :disabled="deleteRequested">수정</button>
                <button class="customer-delete" v-if="showDeleteButton" @click="deleteAccount">삭제요청</button>
            </div>
        </div>
        <div class="customer-box">
            <div class="customer-code">
                <div class="customer-code-text">거래처 코드</div>
                <div class="customer-code-box">{{ accountData.accountCode }}</div>
            </div>
        </div>
        <div class="customer-content2">
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
                            <td>{{ accountData.accountName }}</td>
                            <td>{{ accountData.accountRepresentative }}</td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table2">
                    <thead>
                        <tr>
                            <th>기업 상태 (휴/폐업 여부)</th>
                            <th>법인 여부</th>
                            <th>소재지</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>{{ accountData.accountStatus?.accountStatus }}</td>
                            <td>{{ accountData.corporationStatus }}</td>
                            <td>{{ accountData.accountLocation }}</td>
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
                            <td>{{ accountData.accountType }}</td>
                            <td>{{ accountData.accountContact }}</td>
                            <td>{{ accountData.accountEmail }}</td>
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
                            <td>{{ accountData.accountNote }}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div v-if="orderData.length > 0" class="customer-orders-wrapper">
            <h2>조회된 수주 정보</h2>
            <div class="customer-orders">
                <div class="order-item" v-for="order in orderData" :key="order.orderRegistrationId" @click="goToOrderPage(order.orderRegistrationId)">
                    <div class="project-number">
                        <p class="project-number-text">프로젝트 번호</p>
                        <button class="project-number-box">{{ order.transaction.transactionCode }}</button>
                    </div>
                    <div class="project-employee">
                        <p class="project-employee-text">담당자</p>
                        <button class="project-employee-box">{{ order.employee.employeeName }}</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="customer-process-box">
            <h1 class="customer-process-text">Process</h1>
            <div v-for="note in filteredAccountNotes" :key="note.accountNoteId" class="customer-process-box-detail">
                <div class="customer-process-info">
                    <h4 class="customer-process-writer">{{ employeeName }}</h4>
                    <p class="customer-process-date">{{ note.accountNoteDate }}</p>
                </div>
                <div class="customer-process-detail">
                    {{ note.accountNote }}
                </div>
                <div class="customer-process-btn">
                    <button class="customer-process-delete" @click="deleteNote(note.accountNoteId)">삭제하기</button>
                </div>
            </div>
            <div class="customer-process-reply">
                <input type="text" v-model="newNote" id="customer-process-reply-box" class="customer-process-reply-box" placeholder="내용을 입력해주세요.">
                <button class="customer-process-regist" @click="addNote">등록하기</button>
            </div>
        </div>
        <div v-if="showPopup" class="popup-overlay">
            <div class="popup-content">
                <h2>삭제 요청 사유 입력</h2>
                <textarea v-model="deleteReason" placeholder="삭제 사유를 입력하세요"></textarea>
                <button @click="confirmDelete" class="confirm-btn">확인</button>
                <button @click="closePopup" class="cancel-btn">취소</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';

const route = useRoute();
const router = useRouter();
const accountData = ref({});
const accountNoteData = ref([]);
const showPopup = ref(false);
const deleteReason = ref('');
const newNote = ref('');
const employeeName = ref(''); // 추가: Employee Name을 저장하기 위한 ref
const showDeleteButton = ref(true);
const deleteRequested = ref(false); // 삭제 요청 상태를 저장
const orderData = ref([]); // 추가: Order 데이터를 저장하기 위한 ref

const filteredAccountNotes = computed(() => {
    return accountNoteData.value.filter(note => note.accountDeleteDate === null);
});

onMounted(async () => {
    const accountId = route.params.accountId;
    const userId = localStorage.getItem('userId'); // 추가: userId를 localStorage에서 가져오기

    try {
        const accountResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/${accountId}`);
        accountData.value = accountResponse.data;

        const noteResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account_note/${accountId}`);
        accountNoteData.value = noteResponse.data;

        // 추가: userId로 employeeName 가져오기
        const employeeResponse = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/employees/${userId}`);
        employeeName.value = employeeResponse.data.employeeName;

        // 전체 삭제 요청 데이터를 가져오는 API 호출
        const deleteResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/delete/account');
        const deleteData = deleteResponse.data;

        // 현재 거래처에 해당하는 삭제 요청 상태를 찾기
        const currentDeleteRequest = deleteData.find(deleteRequest => deleteRequest.account.accountId === parseInt(accountId));
        if (currentDeleteRequest) {
            showDeleteButton.value = false; // 삭제 요청이 있으면 삭제 버튼 숨기기
            deleteRequested.value = true; // 삭제 요청 상태를 true로 설정
        }

        // 추가: Order 데이터 가져오기
        const orderResponse = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/order');
        const allOrders = orderResponse.data;
        orderData.value = allOrders.filter(order => order.account.accountId === parseInt(accountId)); // accountId가 일치하는 Order 데이터 필터링

    } catch (error) {
        console.error('Error fetching data:', error);
    }
});

const handleEditAccount = () => {
    if (!deleteRequested.value) {
        goToEditPage();
    }
};

const goToEditPage = () => {
    router.push({ path: `/customer/modify/${route.params.accountId}` });
};

const deleteAccount = () => {
    showPopup.value = true;
};

const closePopup = () => {
    showPopup.value = false;
};

const confirmDelete = async () => {
    const accountId = route.params.accountId;
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account/delete', {
            accountDeleteRequestReason: deleteReason.value,
            account: accountData.value
        });
        console.log('Account delete request sent successfully:', response.data);
        showDeleteButton.value = false; // 삭제 요청 후 삭제 버튼 숨기기
        deleteRequested.value = true; // 삭제 요청 상태를 true로 설정
        closePopup();
    } catch (error) {
        console.error('Error sending delete request:', error);
        alert('삭제 요청 중 오류가 발생했습니다.');
    }
};

const addNote = async () => {
    const accountId = route.params.accountId;
    const userId = localStorage.getItem('userId'); // 추가: userId를 localStorage에서 가져오기
    try {
        const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account_note/regist', {
            accountNote: newNote.value,
            account: { accountId: accountData.value.accountId },
            employee: { employeeId: userId } // 수정: employeeId를 userId로 설정
        });
        console.log('Account note added successfully:', response.data);
        accountNoteData.value.push(response.data);
        newNote.value = ''; 
        location.reload(); // 페이지 새로고침 추가
    } catch (error) {
        console.error('Error adding account note:', error);
        alert('노트 추가 중 오류가 발생했습니다.');
    }
};

const deleteNote = async (accountNoteId) => {
    try {
        const response = await axios.patch('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/account_note/delete', null, {
            params: {
                accountNoteId
            }
        });
        const updatedNote = response.data;
        const noteIndex = accountNoteData.value.findIndex(note => note.accountNoteId === accountNoteId);
        if (noteIndex !== -1) {
            accountNoteData.value[noteIndex] = updatedNote;
        }
    } catch (error) {
        console.error('Error deleting note:', error);
        alert('노트 삭제 중 오류가 발생했습니다.');
    }
};

const goToOrderPage = (orderRegistrationId) => {
    router.push({ path: `/order/${orderRegistrationId}` });
};
</script>

<style>
    @import url('@/assets/css/customer/CustomerContents.css');
</style>
