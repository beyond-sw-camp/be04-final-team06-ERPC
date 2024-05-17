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
        <div class="customer-box2">
            <div class="left">
                <div class="business-number">
                    <p class="business-number-text">사업자 번호</p>
                    <button class="business-number-box" id="business-number-box">123-45-67890</button>
                </div>
                <div class="business-name">
                    <p class="business-name-text">사업자명</p>
                    <button class="business-name-box" id="business-name-box">A-회사</button>
                </div>
                <div class="ceo-name">
                    <p class="ceo-name-text">대표자명</p>
                    <button class="ceo-name-box" id="ceo-name-box">게이츠</button>
                </div>
                <div class="status">
                    <p class="status-text">기업 상태 (휴/폐업 여부)</p>
                    <button class="status-box" id="status-box">정상</button>
                </div>
                <div class="grade">
                    <p class="grade-text">기업 신용등급</p>
                    <button class="grade-box" id="grade-box">B+</button>
                </div>
                <div class="corporation">
                    <p class="corporation-text">법인 여부</p>
                    <button class="corporation-box" id="corporation-box">C</button>
                </div>
            </div>
            <div class="right">
                <div class="industry">
                    <p class="industry-text">업태</p>
                    <button class="industry-box" id="industry-box">제조업</button>
                </div>
                <div class="industry-detail">
                    <p class="industry-detail-text">종목</p>
                    <button class="industry-detail-box" id="industry-detail-box">컴퓨터외</button>
                </div>
                <div class="address">
                    <p class="address-text">소재지</p>
                    <button class="address-box" id="address-box">서울 강남구</button>
                </div>
                <div class="call">
                    <p class="call-text">전화 번호</p>
                    <button class="call-box" id="call-box">000-0000-0000</button>
                </div>
                <div class="email">
                    <p class="email-text">이메일</p>
                    <button class="email-box" id="email-box">ABC@gmail.com</button>
                </div>
                <div class="remark">
                    <p class="remark-text">비고</p>
                    <button class="remark-box" id="remark-box">담당 영업사원 퇴사</button>
                </div>
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
            <h2 class="customer-process-text">Process</h2>
            <div class="customer-process-box-detail">
                <div class="process-info">
                <h4 class="customer-process-writer">민중원 과장</h4>
                <p class="process-date">2024-04-01</p>
            </div>
                <button class="customer-process-detail">
                    해당 거래처는 납기 일자 꼭 지켜야 됩니다~ 벌써 클레임 전화 10통 받았어요.
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
@import url('@/assets/css/customer/CustomerContents.css');
</style>