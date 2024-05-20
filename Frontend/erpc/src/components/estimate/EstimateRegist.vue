<template>
    <div class="regist-content">
        <div class="estimate-regist">
            <h1>견적서 등록</h1>
        </div>
        <div class="estimate-list-box">
            <table class="estimate-table1">
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
                        <td><input type="text"></td>
                        <td>LG 콤퓨타</td>
                        <td><input type="text" class="test1"></td>
                        <td>1,800,000</td>
                        <td>9,000,000</td>
                        <td><input type="text"></td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate-table2">
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
                        <td><input type="text"></td>
                        <td>강남 창고</td>
                        <td>창고</td>
                        <td>서울특별시 강남구 강남대로 11</td>
                        <td>Y</td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="estimate-table3">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처</th>
                        <th>마감일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td><input type="text" class="test2"></td>
                        <td><input type="text"></td>
                        <td><input type="text" class="test3"></td>
                        <td><input type="text"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-attachment">
            <h3>첨부파일<img src="@/assets/img/pdf.png" class="pdfimage1"></h3>
            <div class="estimate-attachment-content">견적서 pdf</div>
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

.estimate-regist {
    text-align: center;
    margin-top: 3%;
}

.estimate-pdf {
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

.estimate-pdf1,
.estimate-pdf2 {
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

.estimate-pdf1:hover,
.estimate-pdf2:hover {
    background-color: #d5e6ff;
}

.pdfimage1,
.pdfimage2 {
    width: 30px;
    height: auto;
    margin-left: 5px;
    margin-right: -10px;
}

.estimate-list-box {
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
    max-width: 1200px;
    margin: 20px auto;
    margin-bottom: 20px;
    gap: 1px;
}

.estimate-table1,
.estimate-table2,
.estimate-table3 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.estimate-table1 th,
.estimate-table1 td,
.estimate-table2 th,
.estimate-table2 td,
.estimate-table3 th,
.estimate-table3 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.estimate-table1 th,
.estimate-table2 th,
.estimate-table3 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.estimate-table1 td,
.estimate-table2 td,
.estimate-table3 td {
    height: 40px;
}

.test1 {
    width: 70px;
}

.test2,
.test3 {
    width: 100px;
}

.estimate-regist-btn-div1 {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.estimate-regist-btn1 {
    padding: 10px 20px;
    font-size: 16px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-bottom: 15px;
    margin-top: 15px;
}

.estimate-regist-btn1 {
    width: 320px;
    font-size: 18px;
    margin-top: 30px;
    margin-bottom: 7%;
}
</style>