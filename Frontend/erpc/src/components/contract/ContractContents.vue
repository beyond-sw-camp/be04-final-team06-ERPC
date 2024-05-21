<template>
    <div class="contract-content">
        <div class="contract-search">
            <h1 class="maintext">계약서 정보 조회 내역</h1>
            <div class="contract-btn">
                <button class="contract-request">결재 요청</button>
                <button class="contract-edit">수정</button>
                <button class="contract-delete">삭제</button>
            </div>
            <div class="contract-pdf">
                <button class="contract-pdf2">
                    견적서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage2">
                </button>
            </div>
        </div>
        <div class="contract-list-box2">
            <table class="contract2-table1">
                <thead>
                    <tr>
                        <th>계약서 코드</th>
                        <th>계약 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>CO-20240427001</td>
                        <td>24,000,000</td>
                        <td>2024-05-18</td>
                        <td></td>
                        <td>2024-05-30</td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table2">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>공급가액</th>
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
            <table class="contract2-table3">
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
            <table class="contract2-table4">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처</th>
                        <th>수주 금액</th>
                        <th>납기 일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>OO상사</td>
                        <td>15,000,000</td>
                        <td>2024-05-30</td>
                        <td class="contract-contents-test1"></td>
                    </tr>
                </tbody>
            </table>
            <table class="contract2-table5">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th>계약금</th>
                        <th>중도금(선금)</th>
                        <th>잔금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test">분할 납부</td>
                        <td>1,500,000</td>
                        <td>7,000,000</td>
                        <td>6,500,000</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="contract-process-box">
            <h1 class="contract-process-text">Process</h1>
            <div class="contract-process-box-detail">
                <div class="contract-process-info">
                    <h4 class="contract-process-writer">민중원 과장</h4>
                    <p class="contract-process-date">2024-04-01</p>
                </div>
                <button class="contract-process-detail">
                    계약 진행 정보 공유합니다~
                </button>
                <div class="contract-process-btn">
                    <button class="contract-process-edit">수정</button>
                    <button class="contract-process-delete">삭제</button>
                </div>
                <div class="contract-process-reply">
                    <input type="text" id="contract-process-reply-box" class="contract-process-reply-box" placeholder="내용을 입력해주세요.">
                    <button class="contract-process-regist">등록하기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/contract/ContractContents.css');
</style>