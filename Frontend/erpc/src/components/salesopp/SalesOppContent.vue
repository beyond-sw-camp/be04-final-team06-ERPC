<template>
    <div class="order-content">
        <div class="order-search">
            <h1 class="maintext">영업기회 조회 내역</h1>
            <h3 class="maintext2">기회인지</h3>
            <div class="order-btn">
                <button class="order-request" @click="openPopup">상태변경</button>
                <button class="order-edit">수정</button>
                <button class="order-delete">삭제</button>
            </div>
            <div class="order-list-box2">
                <table class="order2-table1">
                    <thead>
                        <tr>
                            <th>거래처명</th>
                            <th>거래처 담당자</th>
                            <th>거래처 주소</th>
                            <th>연락처</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>OO상사</td>
                            <td>홍길동</td>
                            <td>서울특별시 동작구 신대방로 87 SFC 빌딩 3층</td>
                            <td>010-1234-5678</td>
                        </tr>
                    </tbody>
                </table>
                <table class="order2-table2">
                    <thead>
                        <tr>
                            <th>담당자 명</th>
                            <th>부서</th>
                            <th>직급</th>
                            <th>연락처</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
                <table class="order2-table3">
                    <thead>
                        <tr>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>5월8일 18:00 방문 희망하심</td>
                        </tr>
                    </tbody>
                    </table>
                    <div class="order-process-box">
            <h1 class="order-process-text">Process</h1>
            <div class="order-process-box-detail">
                <div class="order-process-info">
                    <h4 class="order-process-writer">민중원 과장</h4>
                    <p class="order-process-date">2024-04-01</p>
                </div>
                <button class="order-process-detail">
                    프로젝트 진행 정보 공유합니다~
                </button>
                <div class="order-process-btn">
                    <button class="order-process-edit">수정</button>
                    <button class="order-process-delete">삭제</button>
                </div>
                <div class="order-process-reply">
                    <input type="text" id="order-process-reply-box" class="order-process-reply-box" placeholder="내용을 입력해주세요.">
                    <button class="order-process-regist">등록하기</button>
                </div>
            </div>
            </div>
            </div>
        </div>
    </div>

    <!-- 팝업 창 -->
    <div class="popup" v-if="isPopupOpen">
        <div class="popup-content">
            <!-- 팝업 창에 표시될 내용 -->
            <p>팝업 창에 표시될 내용</p>
            <button class="close-btn" @click="closePopup">닫기</button>
        </div>
    </div>
</template>

<script setup>
    import { ref } from 'vue';

    const isPopupOpen = ref(false);

    function openPopup() {
        isPopupOpen.value = true;
    }

    function closePopup() {
        isPopupOpen.value = false;
    }
</script>

<style>
    @import url('@/assets/css/order/OrderContents.css');
    .popup {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        background-color: rgba(0, 0, 0, 0.5);
        z-index: 999;
    }

    .popup-content {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: white;
        padding: 20px;
        border-radius: 5px;
    }

    .close-btn {
        margin-top: 10px;
        padding: 5px 10px;
        background-color: #ccc;
        border: none;
        border-radius: 3px;
        cursor: pointer;
    }
</style>