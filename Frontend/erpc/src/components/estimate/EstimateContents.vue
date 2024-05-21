<template>
    <div class="regist-content">
        <div class="order-search">
            <h1 class="maintext">견적서 정보 조회 내역</h1>
        <div class="estimate-btn">
            <button class="estimate-request">결재 요청</button>
            <button class="estimate-edit">수정</button>
            <button class="estimate-delete">삭제</button>
        </div>
        <div class="estimate-pdf">
            <button class="estimate-pdf1">
                제품 카탈로그 다운로드<img src="@/assets/img/pdf.png" class="pdfimage1">
            </button>
        </div>
        </div>
        <div class="estimate-list-box">
            <table class="estimate2-table1">
                <thead>
                    <tr>
                        <th>견적서 코드</th>
                        <th>견적 금액</th>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>마감 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>QO-20240508001</td>
                        <td>6,000,000</td>
                        <td>2024-05-18</td>
                        <td></td>
                        <td>2024-05-30</td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate2-table2">
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
            <table class="estimate2-table3">
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
            <table class="estimate2-table4">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처명</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>OO상사</td>
                        <td class="estimate-contents-test1"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-process-box">
            <h1 class="estimate-process-text">Process</h1>
            <div class="estimate-process-box-detail">
                <div class="estimate-process-info">
                    <h4 class="estimate-process-writer">민중원 과장</h4>
                    <p class="estimate-process-date">2024-04-01</p>
                </div>
                <button class="estimate-process-detail">
                    견적서 양식이 일부 수정되었습니다~
                </button>
                <div class="estimate-process-btn">
                    <button class="estimate-process-edit">수정</button>
                    <button class="estimate-process-delete">삭제</button>
                </div>
                <div class="estimate-process-reply">
                    <input type="text" id="estimate-process-reply-box" class="estimate-process-reply-box" placeholder="내용을 입력해주세요.">
                    <button class="estimate-process-regist">등록하기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/estimate/EstimateContents.css');
</style>