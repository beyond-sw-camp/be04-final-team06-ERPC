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
                    <button class="deposit-date-box2" id="deposit-date-box2">6,000,000</button>
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
.cbtext {
    margin-bottom: 50px;
}

.cb-search-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.cb-search,
.cb-search2 {
    text-align: center;
    margin-top: 3%;
}

.cb-search-box,
.cb-search-box2 {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    border: 2px solid #ccc;
    box-sizing: border-box;
    background-color: whitesmoke;
    height: auto;
    width: 100%;
    margin: 20px auto;
}

.cb-search-box {
    max-width: 400px;
}

.cb-search-box2 {
    max-width: 800px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: stretch;
    /* 자식 요소들을 부모 높이에 맞춰 늘림 */
    width: 100%;
    /* 전체 너비 사용 */
    max-width: 800px;
    /* 최대 너비 설정 */
    margin: 20px auto;
    gap: 80px;
    /* 섹션 사이의 간격을 조정 */
}


.left,
.right {
    display: flex;
    flex-direction: column;
    width: 50%;
    padding: 10px;
    box-sizing: border-box;
    flex-grow: 1;
    /* 양쪽 높이를 동일하게 맞춤 */
    align-items: stretch;
    /* 내부 요소 높이를 맞춤 */
}

input[type="text"],
.search-btn,
.regist-btn {
    padding: 13px 12px;
    /* 상하 좌우 패딩 */
    margin: 5px 0;
    /* 상하 마진 조정으로 중앙 정렬 보완 */
}

.project-code2,
.customer-code2,
.business-number2,
.business-name2,
.ceo-name2,
.billing2,
.deposit-code2,
.deposit-date2,
.corporation2,
.industry2,
.industry-detail2,
.address2,
.call2,
.email2,
.deposit-amount2,
.remark2 {
    display: flex;
    flex-direction: column;
    align-items: center;
    flex-grow: 1;
}

.deposit-date2,
.remark2 {
    margin-bottom: 15px;
}

.project-code-text2,
.customer-code-text2,
.business-number-text2,
.business-name-text2,
.ceo-name-text2,
.billing-text2,
.deposit-code-text2,
.deposit-date-text2,
.corporation-text2,
.industry-text2,
.industry-detail-text2,
.address-text2,
.call-text2,
.email-text2,
.deposit-amount-text2,
.remark-text2 {
    min-width: 50px;
    margin-right: 10px;
    margin-bottom: 2px;
}

.project-code-text2,
.customer-code-text2,
.business-number-text2,
.business-name-text2,
.ceo-name-text2,
.billing-text2,
.deposit-code-text2,
.deposit-date-text2 {
    margin-top: 16px;
    row-gap: 12px;
}

.corporation-text2,
.industry-text2,
.industry-detail-text2,
.address-text2,
.call-text2,
.email-text2,
.deposit-amount-text2,
.remark-text2 {
    margin-top: 16.5px;
}

.deposit-code-text2,
.remark-text2 {
    margin-bottom: -4px;
}

.project-code-box2,
.customer-code-box2,
.business-number-box2,
.business-name-box2,
.ceo-name-box2,
.billing-box2,
.deposit-code-box2,
.deposit-date-box2,
.corporation-box2,
.industry-box2,
.industry-detail-box2,
.address-box2,
.call-box2,
.email-box2,
.deposit-amount-box2,
.remark-box2 {
    width: 100%;
    max-width: 320px;
    flex-grow: 1;
    padding: 12px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 320px;
    text-align: left;
    font-family: GmarketSansMedium;
    font-size: 16px;
    cursor: default;
    background-color: white;
}

.deposit-code-btn2 {
    margin-left: -60px;
    margin-right: 20px;
    background-color: #BEE7FF;
    border: 2px solid #BEE7FF;
    border-radius: 10px;
    width: 50px;
    height: 30px;
}

.order1-pdf {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    font-size: 17px;
    flex-grow: 1;
    padding: 10px;
    background-color: #BEE7FF;
    border: 2px solid #BEE7FF;
    border-radius: 10px;
    box-sizing: border-box;
    width: 800px;
    height: 90px;
    margin-bottom: 20px;
    font-family: GmarketSansMedium;
    font-size: 17px;
    gap: 70px;
    margin-top: 30px;
}

.order1-pdf1,
.order1-pdf2 {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: black;
    font-weight: bold;
    width: 330px;
}

.order1-pdf1:hover,
.order1-pdf2:hover {
    background-color: #d5e6ff;
}

.pdfimage1,
.pdfimage2 {
    width: 30px;
    height: auto;
    margin-left: 5px;
    margin-right: -10px;
}

.search-btn-div1,
.regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.search-btn1,
.regist-btn2 {
    padding: 10px 20px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 5px;
    margin-bottom: 5px;
}

.search-btn1 {
    max-width: 320px;
}

.regist-btn2 {
    width: 350px;
    font-size: 18px;
    margin-top: 30px;
    margin-bottom: 7%;
}
</style>