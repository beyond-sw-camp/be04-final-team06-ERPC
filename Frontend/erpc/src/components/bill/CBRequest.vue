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
        <div class="cb-search-box2">
            <div class="left2">
                <div class="project-code2">
                    <div class="project-code-text2">프로젝트 코드</div>
                    <button class="project-code-box2">PJ-20240508001</button>
                </div>
                <div class="customer-code2">
                    <div class="customer-code-text2">거래처 코드</div>
                    <button class="customer-code-box2">AC-20240430001</button>
                </div>
                <div class="business-number2">
                    <p class="business-number-text2">사업자 번호</p>
                    <button class="business-number-box2" id="business-number-box2">123-45-67890</button>
                </div>
                <div class="business-name2">
                    <p class="business-name-text2">사업자명</p>
                    <button class="business-name-box2" id="business-name-box2">A-회사</button>
                </div>
                <div class="ceo-name2">
                    <p class="ceo-name-text2">대표자명</p>
                    <button class="ceo-name-box2" id="ceo-name-box2">게이츠</button>
                </div>
                <div class="billing2">
                    <p class="billing-text2">Billing 잔액</p>
                    <button class="billing-box2" id="billing-box2">6,000,000</button>
                </div>
                <div class="deposit-code2">
                    <p class="deposit-code-text2">입금 코드</p>
                    <div class="deposit-code-div2">
                        <input type="text" id="deposit-code-box2" class="deposit-code-box2"
                            placeholder="입금 코드를 입력해주세요.">
                        <button class="deposit-code-btn2">확인</button>
                    </div>
                </div>
                <div class="deposit-date2">
                    <p class="deposit-date-text2">입금 일자</p>
                    <button class="deposit-date-box2" id="deposit-date-box2">2024-03-31</button>
                </div>
            </div>
            <div class="right2">
                <div class="corporation2">
                    <p class="corporation-text2">법인 여부</p>
                    <button class="corporation-box2" id="corporation-box2">C</button>
                </div>
                <div class="industry2">
                    <p class="industry-text2">업태</p>
                    <button class="industry-box2" id="industry-box2">제조업</button>
                </div>
                <div class="industry-detail2">
                    <p class="industry-detail-text2">종목</p>
                    <button class="industry-detail-box2" id="industry-detail-box2">컴퓨터외</button>
                </div>
                <div class="address2">
                    <p class="address-text2">소재지</p>
                    <button class="address-box2" id="address-box2">서울 강남구</button>
                </div>
                <div class="call2">
                    <p class="call-text2">전화 번호</p>
                    <button class="call-box2" id="call-box2">000-0000-0000</button>
                </div>
                <div class="email2">
                    <p class="email-text2">이메일</p>
                    <button class="email-box2" id="email-box2">ABC@gmail.com</button>
                </div>
                <div class="deposit-amount2">
                    <p class="deposit-amount-text2">입금 금액</p>
                    <button id="deposit-amount-box2" class="deposit-amount-box2">6,000,000</button>
                </div>
                <div class="remark2">
                    <p class="remark-text2">비고</p>
                    <input type="text" id="remark-box2" class="remark-box2" placeholder="비고란 입력 필수">
                </div>
            </div>
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

</script>

<style>
    @import url('@/assets/css/bill/CBRequest.css');
</style>