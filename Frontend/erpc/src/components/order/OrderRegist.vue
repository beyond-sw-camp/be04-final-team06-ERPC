<template>
    <div class="regist-content">
        <div class="order-regist">
            <h1>수주 등록</h1>
            <div class="order-pdf">
                <button class="order-pdf1">
                    견적서 업로드<img src="@/assets/img/pdf.png" class="pdfimage1">
                </button>
                <button class="order-pdf2">
                    계약서 업로드<img src="@/assets/img/pdf.png" class="pdfimage2">
                </button>
            </div>
        </div>
        <div class="order-search-box">
            <h3>계약서 불러오기</h3>
            <div class="contract-number">
                <p class="contract-number-text">계약서 코드</p>
                <input type="text" id="contract-number-box" class="contract-number-box" placeholder="계약서 코드를 입력해주세요.">
            </div>
            <div class="order-search-btn-div">
                <button class="order-search-btn">조회하기</button>
            </div>
        </div>
        <div class="order-list-box">
            <table class="order-table">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처 코드</th>
                        <th>거래처명</th>
                        <th>수주 금액</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>AC-20230311001</td>
                        <td>OO상사</td>
                        <td>15,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table2">
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
            <table class="order-table3">
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
            <table class="order-table4">
                <thead>
                    <tr>
                        <th>납부 형태</th>
                        <th>계약금</th>
                        <th>중도금</th>
                        <th>잔금</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test">
                            <div class="order-dropdown">
                                <button class="order-dropdown-btn">일시 납부 or 분할 납부 ▼</button>
                                <div class="order-dropdown-content">
                                    <a href="#">일시 납부</a>
                                    <a href="#">분할 납부</a>
                                </div>
                            </div>
                        </td>
                        <td>900,000</td>
                        <td>4,100,000</td>
                        <td>4,000,000</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table5">
                <thead>
                    <tr>
                        <th>계약 일자</th>
                        <th>계약 납기일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="test2">2024.04.30</td>
                        <td class="test2">2024.05.10</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="order-regist-btn-div">
            <button class="order-regist-btn">수주 등록</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
.regist-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.order-regist {
    text-align: center;
    margin-top: 3%;
}

.order-pdf {
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

.order-pdf1,
.order-pdf2 {
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

.order-pdf1:hover,
.order-pdf2:hover {
    background-color: #d5e6ff;
}

.pdfimage1,
.pdfimage2 {
    width: 30px;
    height: auto;
    margin-left: 5px;
    margin-right: -10px;
}

.order-list-box {
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
    width: 100%;
    max-width: 1400px;
    margin: 20px auto;
    margin-bottom: 7%;
    gap: 1px;
}

.order-table,
.order-table2,
.order-table3,
.order-table4,
.order-table5 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.order-table th,
.order-table td,
.order-table2 th,
.order-table2 td,
.order-table3 th,
.order-table3 td,
.order-table4 th,
.order-table4 td,
.order-table5 th,
.order-table5 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.order-table th,
.order-table2 th,
.order-table3 th,
.order-table4 th,
.order-table5 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.order-table td,
.order-table2 td,
.order-table3 td,
.order-table4 td,
.order-table5 td {
    height: 40px;
}

.order-dropdown {
    position: relative;
    display: inline-block;
}

.order-dropdown-btn,
.order-pdf1-btn {
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.order-dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
}

.order-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.order-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.order-dropdown:hover .order-dropdown-content {
    display: block;
}

.test,
.test2 {
    width: 300px;
}

.order-search-box {
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
    margin: 20px auto;
    gap: 1px;
    max-width: 400px;
}

.contract-number {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* 수직 방향 중앙 정렬 */
}

.contract-number-text {
    display: flex;
    align-items: center;
    text-align: center;
    margin-top: 2px;
    margin-bottom: 2px;
}

.contract-number-box {
    flex-grow: 1;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 320px;
    font-size: 18px;
}

.order-search-btn-div,
.order-regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.order-search-btn,
.order-regist-btn {
    padding: 10px 20px;
    font-size: 16px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 15px;
    margin-bottom: 15px;
}

.order-search-btn {
    max-width: 320px;
}

.order-regist-btn {
    width: 320px;
    font-size: 18px;
}

</style>