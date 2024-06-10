<template>
    <div class="cb-search-content">
        <div class="cb-search">
            <h1 class="cbtext">세금계산서 요청 내역</h1>
            <h2>승인 결과</h2>
        </div>
        <div class="cb-status">
            <button class="cb-status-result">반려</button>
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
                        <td>DP-20240331030</td>
                        <td>2024-03-31</td>
                        <td>6,000,000</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order1-pdf">
            <button class="order1-pdf1">
                견적서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage1">
            </button>
            <button class="order1-pdf2">
                계약서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage2">
            </button>
        </div>
        <div class="reason-box">
            <h3 class="reason-text">반려 사유</h3>
            <button class="reason-detail">계약서 첨부해 주세요~</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/bill/CBContents.css');
</style>