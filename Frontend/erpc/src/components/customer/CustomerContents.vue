<template>
    <div class="customer-content">
        <div class="customer-search">
            <h1 class="maintext">거래처 정보 조회 내역</h1>
            <div class="customer-btn">
                <img src="@/assets/img/pdf.png" class="pdfimage">
                <button class="customer-edit">수정</button>
                <button class="customer-delete">삭제</button>
            </div>
        </div>
        <div class="customer-box">
            <div class="customer-code">
                <div class="customer-code-text">거래처 코드</div>
                <button class="customer-code-box">AC-20240430001</button>
            </div>
        </div>
        <div class="customer-content2">
            <div class="customer-list-box1">
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
                            <td>123-45-67890</td>
                            <td>A-회사</td>
                            <td>김이원</td>
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
                            <td>정상</td>
                            <td>P</td>
                            <td>서울시 양천구 목동서로</td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table3">
                    <thead>
                        <tr>
                            <th>업태</th>
                            <th>종목</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>판매업</td>
                            <td>판매업2</td>
                            <td>000-0000-0000</td>
                            <td>abc@gmail.com</td>
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
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="customer-box3">
            <h3>조회된 수주 정보</h3>
            <div class="project-number">
                <p class="project-number-text">프로젝트 번호</p>
                <button class="project-number-box" id="project-number-box">PJ-20240508001</button>
            </div>
            <div class="project-employee">
                <p class="project-employee-text">담당자</p>
                <button class="project-employee-box" id="project-employee-box">민중원</button>
            </div>
        </div>
        <div class="customer-process-box">
            <h1 class="customer-process-text">Process</h1>
            <div class="customer-process-box-detail">
                <div class="customer-process-info">
                    <h4 class="customer-process-writer">민중원 과장</h4>
                    <p class="customer-process-date">2024-04-01</p>
                </div>
                <button class="customer-process-detail">
                    해당 거래처는 납기 일자 꼭 지켜야 됩니다~ 벌써 클레임 전화 10통 받았어요.
                </button>
                <div class="customer-process-btn">
                    <button class="customer-process-edit">수정</button>
                    <button class="customer-process-delete">삭제</button>
                </div>
                <div class="customer-process-reply">
                    <input type="text" id="customer-process-reply-box" class="customer-process-reply-box"
                        placeholder="내용을 입력해주세요.">
                    <button class="customer-process-regist">등록하기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const currentRoute = useRoute();
const router = useRouter();

</script>

<style>
@import url('@/assets/css/customer/CustomerContents.css');
</style>