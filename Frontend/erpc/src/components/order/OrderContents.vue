<template>
    <div class="order-content">
        <div class="order-search">
            <h1 class="maintext">수주 정보 조회 내역</h1>
            <h3 class="maintext2">결재 승인</h3>
            <div class="order-btn">
                <button class="order-request">결재 요청</button>
                <button class="order-edit">수정</button>
                <button class="order-delete">삭제</button>
            </div>
            <div class="order-pdf">
                <button class="order-pdf1">
                    견적서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage1">
                </button>
                <button class="order-pdf2">
                    계약서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage2">
                </button>
            </div>
        </div>
        <div class="order-list-box2">
            <table class="order2-table1">
                <thead>
                    <tr>
                        <th>작성 일자</th>
                        <th>삭제 일자</th>
                        <th>결재 일자</th>
                        <th>계약 일자</th>
                        <th>입금 완료일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2024.04.30</td>
                        <td></td>
                        <td>2024.04.30</td>
                        <td>2024.04.30</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table2">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                        <th>Billing 잔액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>AC-20240430001</td>
                        <td>A-회사</td>
                        <td>15,000,000</td>
                        <td>6,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="order2-table3">
                <thead>
                    <tr>
                        <th>품목 코드</th>
                        <th>품목 이름</th>
                        <th>수량</th>
                        <th>단가</th>
                        <th>합계</th>
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
            <table class="order2-table4">
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
            <table class="order2-table5">
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
            <table class="order2-table6">
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
            <table class="order2-table7">
                <thead>
                    <tr>
                        <th>입금 번호</th>
                        <th>입금자명</th>
                        <th>입금 금액</th>
                        <th>입금 일자</th>
                        <th>세금계산서 발행 금액</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240430002</td>
                        <td>OO상사</td>
                        <td>1,500,000</td>
                        <td>2024.04.30</td>
                        <td>1,500,000</td>
                        <td>계약금</td>
                    </tr>
                    <tr>
                        <td>PJ-20240501002</td>
                        <td>OO상사</td>
                        <td>7,000,000</td>
                        <td>2024.05.01</td>
                        <td>7,000,000</td>
                        <td>중도금(선금)</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="shipment-search">
            <h1 class="maintext3">출하 정보</h1>
            <h3 class="maintext4">출하 처리</h3>
        </div>
        <div class="shipment-list-box">
            <table class="shipment-table1">
                <thead>
                    <tr>
                        <th>주문일</th>
                        <th>출고 예정일</th>
                        <th>실사 출고일</th>
                        <th>수령 예정일</th>
                        <th>수령 확정일</th>
                        <th>계약금 입금일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>2024.04.30</td>
                        <td>2024.05.03</td>
                        <td>2024.05.03</td>
                        <td>2024.05.07</td>
                        <td>2024.05.07</td>
                        <td>2024.04.30</td>
                    </tr>
                </tbody>
            </table>
            <table class="shipment-table2">
                <thead>
                    <tr>
                        <th>수령인</th>
                        <th>배송 주소</th>
                        <th>전화번호</th>
                        <th>배송 정보</th>
                        <th>추적 번호</th>
                        <th>배송 업체</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>강철원</td>
                        <td>경기 용인시 처인구 포곡읍 에버랜드로 199</td>
                        <td>031-320-8600</td>
                        <td>택배</td>
                        <td>172319573105</td>
                        <td>CJ 대한통운</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order-process-box">
            <h1 class="order-process-text">Process</h1>
            <div class="order-process-box-detail">
                <div class="order-process-info">
                    <h4 class="order-process-writer">민중원 과장</h4>
                    <p class="order-process-date">2024-04-01</p>
                </div>
                <button class="order-process-detail">
                    프로젝트 진행 정보 공유합니다~
                </button>
                <div class="order-process-btn">
                    <button class="order-process-edit">수정</button>
                    <button class="order-process-delete">삭제</button>
                </div>
                <div class="order-process-reply">
                    <input type="text" id="order-process-reply-box" class="order-process-reply-box" placeholder="내용을 입력해주세요.">
                    <button class="order-process-regist">등록하기</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/order/OrderContents.css');
</style>