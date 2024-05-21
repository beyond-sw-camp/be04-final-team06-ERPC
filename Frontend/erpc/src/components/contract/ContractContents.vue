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
.contract-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.contract-search {
    text-align: center;
    margin-top: 3%;
}

.maintext,
.maintext2,
.maintext3,
.maintext4 {
    display: flex;
    align-items: center;
    justify-content: center;
}

.maintext2,
.maintext4 {
    color: #0C2092;
}

.contract-btn {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 40px;
    margin-bottom: 20px;
}

.pdfimage {
    width: 50px;
    height: auto;
    cursor: pointer;
}

.contract-edit,
.contract-delete {
    width: 60px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;
}

.contract-delete {
    margin-right: 15px;
}

.contract-request {
    width: 80px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;   
}

.contract-pdf {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 17px;
    flex-grow: 1;
    padding: 10px;
    background-color: #BEE7FF;
    border: 2px solid #BEE7FF;
    border-radius: 10px;
    box-sizing: border-box;
    width: 350px;
    height: 150px;
    margin-bottom: 20px;
    font-family: GmarketSansMedium;
    font-size: 17px;
    gap: 20px;
    margin-top: 40px;
}

.contract-pdf1,
.contract-pdf2 {
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
    width: 270px;
}

.contract-pdf1:hover,
.contract-pdf2:hover {
    background-color: #d5e6ff;
}

.pdfimage1,
.pdfimage2 {
    width: 30px;
    height: auto;
    margin-left: 5px;
    margin-right: -10px;
}

.contract-list-box2,
.shipment-list-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    box-sizing: border-box;
    background-color: white;
    height: auto;
    max-width: 1200px;
    margin: 20px auto;
    margin-bottom: 20px;
    gap: 1px;
}

.contract2-table1,
.contract2-table2,
.contract2-table3,
.contract2-table4,
.contract2-table5,
.contract2-table6,
.contract2-table7,
.shipment-table1,
.shipment-table2 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.contract2-table1 th,
.contract2-table1 td,
.contract2-table2 th,
.contract2-table2 td,
.contract2-table3 th,
.contract2-table3 td,
.contract2-table4 th,
.contract2-table4 td,
.contract2-table5 th,
.contract2-table5 td,
.contract2-table6 th,
.contract2-table6 td,
.contract2-table7 th,
.contract2-table7 td,
.shipment-table1 th,
.shipment-table1 td,
.shipment-table2 th,
.shipment-table2 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.contract2-table1 th,
.contract2-table2 th,
.contract2-table3 th,
.contract2-table4 th,
.contract2-table5 th,
.contract2-table6 th,
.contract2-table7 th,
.shipment-table1 th,
.shipment-table2 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.contract2-table1 td,
.contract2-table2 td,
.contract2-table3 td,
.contract2-table4 td,
.contract2-table5 td,
.contract2-table6 td,
.contract2-table7 td,
.shipment-table1 td,
.shipment-table2 td {
    height: 40px;
}

.test {
    background-color: #d5e6ff;
}

.shipment-table1 th,
.shipment-table1 td {
    background-color: #D3F9E0;
}

.shipment-table2 {
    margin-bottom: 7%;
}

.contract-process-box {
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 17px;
    flex-grow: 1;
    padding: 10px;
    background-color: #CCEAFF;
    border: 2px solid #CCEAFF;
    border-radius: 10px;
    box-sizing: border-box;
    width: 1200px;
    margin-bottom: 20px;
    font-family: GmarketSansMedium;
    font-size: 17px;
    margin-top: 30px;
    height: auto;
    flex-direction: column;
    margin-bottom: 7%;
}

.contract-process-text {
    margin-bottom: 20px;
    color: #0C2092;
}

.contract-process-box-detail {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
    width: 100%;
}

.contract-process-info {
    display: flex;
    width: 100%;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
}

.contract-process-writer {
    margin: 0;
    margin-left: 45px;
}

.contract-process-detail {
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 10px;
    font-size: 15px;
    outline: none;
    color: black;
    font-weight: bold;
    width: 93%;
    height: auto;
    margin-left: 40px;
    margin-top: -10px;
    font-weight: normal;
}

.contract-process-date {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #F6E5FF;
    border: 2px solid #F6E5FF;
    border-radius: 10px;
    padding: 5px 10px;
    font-size: 12px;
    font-weight: normal;
    color: black;
    margin-right: 45px;
}

.contract-process-btn {
    display: flex;
    width: 100%;
    justify-content: flex-end;
    align-items: flex-end;
    gap: 7px;
}

.contract-process-edit,
.contract-process-delete {
    background-color: #0C2092;
    border: 2px solid #0C2092;
    color: white;
    border-radius: 10px;
    padding: 5px 7px;
    margin-top: 4px;
    cursor: pointer;
}

.contract-process-delete {
    margin-right: 46px;
}

.contract-process-reply {
    display: flex;
    width: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 10px;
}

.contract-process-reply-box {
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 10px;
    font-size: 15px;
    outline: none;
    color: black;
    width: 90.5%;
    height: auto;
    font-weight: normal;
}

.contract-process-regist {
    background-color: #0C2092;
    border: 2px solid #0C2092;
    width: 95px;
    color: white;
    border-radius: 10px;
    padding: 5px 7px;
    margin-left: 992px;
    cursor: pointer;
    margin-bottom: 7px;
}

.contract-contents-test1 {
    width: 300px;
}
</style>