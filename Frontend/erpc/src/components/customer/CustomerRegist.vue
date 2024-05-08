<template>
    <div class="search-content">
        <div class="customer-search">
            <h1>사업자 신용 정보 조회</h1>
        </div>
        <div class="search-box">
            <div class="business-number">
                <p class="business-number-text">사업자 번호</p>
                <input type="text" id="business-number-box" class="business-number-box" placeholder="사업자 번호를 입력해주세요.">
            </div>
            <div class="search-btn-div">
                <button class="search-btn">조회하기</button>
            </div>
        </div>
        <div class="customer-search2">
            <h1>거래처 등록</h1>
        </div>
        <div class="search-box2">
            <div class="left">
                <div class="business-number">
                    <p class="business-number-text">사업자 번호</p>
                    <input type="text" id="business-number-box" class="business-number-box"
                        placeholder="사업자 번호를 입력해주세요.">
                </div>
                <div class="business-name">
                    <p class="business-name-text">사업자명</p>
                    <input type="text" id="business-name-box" class="business-name-box" placeholder="사업자명을 입력해주세요.">
                </div>
                <div class="ceo-name">
                    <p class="ceo-name-text">대표자명</p>
                    <input type="text" id="ceo-name-box" class="ceo-name-box" placeholder="대표자명을 입력해주세요.">
                </div>
                <div class="status">
                    <p class="status-text">기업 상태 (휴/폐업 여부)</p>
                    <input type="text" id="status-box" class="status-box" placeholder="기업 상태를 입력해주세요.">
                </div>
                <div class="grade">
                    <p class="grade-text">기업 신용등급</p>
                    <input type="text" id="grade-box" class="grade-box" placeholder="기업 신용등급을 입력해주세요.">
                </div>
                <div class="corporation">
                    <p class="corporation-text">법인 여부</p>
                    <input type="text" id="corporation-box" class="corporation-box" placeholder="법인 여부를 입력해주세요.">
                </div>
            </div>
            <div class="right">
                <div class="industry">
                    <p class="industry-text">업태</p>
                    <input type="text" id="industry-box" class="industry-box" placeholder="업태를 입력해주세요.">
                </div>
                <div class="industry-detail">
                    <p class="industry-detail-text">종목</p>
                    <input type="text" id="industry-detail-box" class="industry-detail-box" placeholder="종목을 입력해주세요.">
                </div>
                <div class="address">
                    <p class="address-text">소재지</p>
                    <input type="text" id="address-box" class="address-box" placeholder="소재지를 입력해주세요.">
                </div>
                <div class="call">
                    <p class="call-text">전화 번호</p>
                    <input type="text" id="call-box" class="call-box" placeholder="전화 번호를 입력해주세요.">
                </div>
                <div class="email">
                    <p class="email-text">이메일</p>
                    <input type="text" id="email-box" class="email-box" placeholder="이메일 주소를 입력해주세요.">
                </div>
                <div class="remark">
                    <p class="remark-text">비고</p>
                    <input type="text" id="remark-box" class="remark-box">
                </div>
            </div>
        </div>
        <div class="regist-btn-div">
            <button class="regist-btn">거래처 등록하기</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/customer/CustomerRegist.css');
=======
.search-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.customer-search,
.customer-search2 {
    text-align: center;
    margin-top: 3%;
}

.search-box,
.search-box2 {
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
    gap: 1px;
}

.search-box {
    max-width: 400px;
}

.search-box2 {
    max-width: 800px;
}

.search-box2 {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: stretch; /* 자식 요소들을 부모 높이에 맞춰 늘림 */
    width: 100%; /* 전체 너비 사용 */
    max-width: 800px; /* 최대 너비 설정 */
    margin: 20px auto;
    gap: 20px; /* 섹션 사이의 간격을 조정 */
}

.left, .right {
    display: flex;
    flex-direction: column;
    width: 50%; /* 왼쪽과 오른쪽 섹션 너비 동일하게 설정 */
    padding: 10px; /* 패딩으로 내부 여백 추가 */
    box-sizing: border-box; /* 패딩과 테두리를 너비에 포함 */
}

input[type="text"], .search-btn, .regist-btn {
    padding: 8px 12px; /* 상하 좌우 패딩 */
    margin: 5px 0; /* 상하 마진 조정으로 중앙 정렬 보완 */
}

.business-number,
.business-name,
.ceo-name,
.status,
.grade,
.corporation,
.industry,
.industry-detail,
.address,
.call,
.email,
.remark {
    display: flex;
    flex-direction: column;
    align-items: center; /* 수직 방향 중앙 정렬 */
}

.corporation,
.remark {
    margin-bottom: 15px;
}

.business-number-text,
.business-name-text,
.ceo-name-text,
.status-text,
.grade-text,
.corporation-text,
.industry-text,
.industry-detail-text,
.address-text,
.call-text,
.email-text,
.remark-text {
    min-width: 50px;
    margin-right: 10px;
    margin-bottom: 2px;
}

.industry-text,
.industry-detail-text,
.address-text,
.call-text,
.email-text,
.remark-text {
    margin-top: 18px;
}

.business-number-box,
.business-name-box,
.ceo-name-box,
.status-box,
.grade-box,
.corporation-box,
.industry-box,
.industry-detail-box,
.address-box,
.call-box,
.email-box,
.remark-box {
    flex-grow: 1;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 320px;
}

.search-btn-div,
.regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.search-btn,
.regist-btn {
    width: 320px;
    padding: 12px 20px;
    font-size: 16px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-bottom: 15px;
}

.regist-btn {
    margin-bottom: 7%;
}
</style>