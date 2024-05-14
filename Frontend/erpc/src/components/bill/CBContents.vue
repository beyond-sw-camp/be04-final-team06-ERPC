<template>
    <div class="cb-search-content">
        <div class="cb-search">
            <h1 class="cbtext">세금계산서 요청 내역</h1>
            <h2>승인 결과</h2>
        </div>
        <div class="cb-status">
            <button class="cb-status-result">반려</button>
        </div>
        <div class="cb-search3">
            <h2>세금계산서 발행 정보</h2>
        </div>
        <div class="cb-search-box3">
            <div class="left3">
                <div class="project-code3">
                    <div class="project-code-text3">프로젝트 코드</div>
                    <button class="project-code-box3">PJ-30340508001</button>
                </div>
                <div class="customer-code3">
                    <div class="customer-code-text3">거래처 코드</div>
                    <button class="customer-code-box3">AC-30340430001</button>
                </div>
                <div class="business-number3">
                    <p class="business-number-text3">사업자 번호</p>
                    <button class="business-number-box3" id="business-number-box3">133-45-67890</button>
                </div>
                <div class="business-name3">
                    <p class="business-name-text3">사업자명</p>
                    <button class="business-name-box3" id="business-name-box3">A-회사</button>
                </div>
                <div class="ceo-name3">
                    <p class="ceo-name-text3">대표자명</p>
                    <button class="ceo-name-box3" id="ceo-name-box3">게이츠</button>
                </div>
                <div class="billing3">
                    <p class="billing-text3">Billing 잔액</p>
                    <button class="billing-box3" id="billing-box3">6,000,000</button>
                </div>
                <div class="deposit-code3">
                    <p class="deposit-code-text3">입금 코드</p>
                    <button class="deposit-code-box3" id="deposit-code-box3">DP-20240430001</button>
                </div>
                <div class="deposit-date3">
                    <p class="deposit-date-text3">입금 일자</p>
                    <button class="deposit-date-box3" id="deposit-date-box3">3034-03-31</button>
                </div>
            </div>
            <div class="right3">
                <div class="corporation3">
                    <p class="corporation-text3">법인 여부</p>
                    <button class="corporation-box3" id="corporation-box3">C</button>
                </div>
                <div class="industry3">
                    <p class="industry-text3">업태</p>
                    <button class="industry-box3" id="industry-box3">제조업</button>
                </div>
                <div class="industry-detail3">
                    <p class="industry-detail-text3">종목</p>
                    <button class="industry-detail-box3" id="industry-detail-box3">컴퓨터외</button>
                </div>
                <div class="address3">
                    <p class="address-text3">소재지</p>
                    <button class="address-box3" id="address-box3">서울 강남구</button>
                </div>
                <div class="call3">
                    <p class="call-text3">전화 번호</p>
                    <button class="call-box3" id="call-box3">000-0000-0000</button>
                </div>
                <div class="email3">
                    <p class="email-text3">이메일</p>
                    <button class="email-box3" id="email-box3">ABC@gmail.com</button>
                </div>
                <div class="deposit-amount3">
                    <p class="deposit-amount-text3">입금 금액</p>
                    <button id="deposit-amount-box3" class="deposit-amount-box3">6,000,000</button>
                </div>
                <div class="remark3">
                    <p class="remark-text3">비고</p>
                    <button id="remark-box3" class="remark-box3">잔금 발행</button>
                </div>
            </div>
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