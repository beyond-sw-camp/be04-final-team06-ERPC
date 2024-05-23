<template>
    <div class="cb-search-content">
        <div class="cb-search">
            <h1 class="cbtext">세금계산서 발행 요청</h1>
            <h2>프로젝트 조회</h2>
        </div>
        <div class="cb-search-box">
            <div class="business-number">
                <p class="business-number-text">프로젝트 코드</p>
                <input type="text" id="business-number-box" class="business-number-box" placeholder="프로젝트 코드를 입력해주세요.">
            </div>
            <div class="search-btn-div1">
                <button class="search-btn1">조회하기</button>
            </div>
        </div>
        <div class="cb-search2">
            <h2>세금계산서 발행 정보</h2>
        </div>
        <div class="cb-list-box1">
            <table class="cb-table1">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>거래처 코드</th>
                        <th>법인 여부</th>
                        <th>Billing 잔액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>AC-20240430001</td>
                        <td>C</td>
                        <td>6,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table2">
                <thead>
                    <tr>
                        <th>사업자 번호</th>
                        <th>사업자명</th>
                        <th>대표자명</th>
                        <th>업태</th>
                        <th>종목</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>123-45-67890</td>
                        <td>A-회사</td>
                        <td>게이츠</td>
                        <td>제조업</td>
                        <td>컴퓨터외</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table3">
                <thead>
                    <tr>
                        <th>소재지</th>
                        <th>전화 번호</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>서울 강남구</td>
                        <td>000-0000-0000</td>
                        <td>ABC@gmail.com</td>
                    </tr>
                </tbody>
            </table>
            <table class="cb-table4">
                <thead>
                    <tr>
                        <th>입금 코드</th>
                        <th>입금 일자</th>
                        <th>입금 금액</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <div class="deposit-code-div2">
                                <input type="text" id="deposit-code-box2" class="deposit-code-box2" placeholder="입금 코드를 입력해주세요.">
                                <button class="deposit-code-btn2">확인</button>
                            </div>
                        </td>
                        <td>2024-03-31</td>
                        <td>6,000,000</td>
                        <td><input type="text" id="remark-box2" class="remark-box2" placeholder="비고란 입력 필수"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order1-pdf">
            <button class="order1-pdf1">
                견적서 업로드<img src="@/assets/img/pdf.png" class="pdfimage1">
            </button>
            <button class="order1-pdf2">
                계약서 업로드<img src="@/assets/img/pdf.png" class="pdfimage2">
            </button>
        </div>
        <div class="regist-btn-div">
            <button class="regist-btn2">발행 요청하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const currentRoute = useRoute();
const router = useRouter();
</script>

<style>
    @import url('@/assets/css/bill/CBRequest.css');
</style>
