<template>
    <div class="regist-content">
        <div class="order-regist">
            <h1>수주 등록</h1>
        </div>
        <div class="order-search-box">
            <h3>계약서 불러오기</h3>
            <div class="contract-number">
                <p class="contract-number-text">계약서 코드</p>
                <input type="text" id="contract-number-box" class="contract-number-box" placeholder="계약서 코드를 입력해주세요.">
            </div>
            <div class="order-search-btn-div1">
                <button class="order-search-btn1">조회하기</button>
            </div>
        </div>
        <div class="order-list-box1">
            <table class="order-table1">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>AC-20230311001</td>
                        <td>OO상사</td>
                        <td>15,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table2">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>합계</th>
                        <th>기타</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>COM-001</td>
                        <td>LG 콤퓨타</td>
                        <td>5</td>
                        <td>1,800,000</td>
                        <td>9,000,000</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table3">
                <thead>
                    <tr>
                        <th>창고 코드</th>
                        <th>창고명</th>
                        <th>구분</th>
                        <th>창고 주소</th>
                        <th>사용 여부</th>
                        <th>생산 공정명</th>
                        <th>외주 거래처명</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>WH-001</td>
                        <td>강남 창고</td>
                        <td>창고</td>
                        <td>서울특별시 강남구 강남대로 11</td>
                        <td>Y</td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table4">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th>계약금</th>
                        <th>중도금</th>
                        <th>잔금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test">분할 납부</td>
                        <td>900,000</td>
                        <td>4,100,000</td>
                        <td>4,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table5">
                <thead>
                    <tr>
                        <th>계약 일자</th>
                        <th>계약 납기일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test2">2024.04.30</td>
                        <td class="test2">2024.05.10</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order-attachment">
            <div class="order-attachment-header">
                <h2 class="order-file">첨부파일</h2>
                <img src="@/assets/img/pdf.png" class="order-pdfimage">
            </div>
            <div class="order-attachment-content">
                <div class="file-list">
                    <span class="file-icon">📄</span>
                    <span class="file-name">견적서.pdf</span>
                    <span class="file-icon">📄</span>
                    <span class="file-name">계약서.pdf</span>
                </div>
            </div>
        </div>
        <div class="order-regist-btn-div1">
            <button class="order-regist-btn1">수주 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const searchBy = ref('일시 납부 or 분할 납부');

function setSearchBy(criteria) {
    searchBy.value = criteria;
}
</script>



<style>
    @import url('@/assets/css/order/OrderRegist.css');
</style>