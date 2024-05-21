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
                        <td><input type="text" class="estimate-test1"></td>
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
                        <td><input type="text" class="estimate-test2"></td>
                        <td><input type="text"></td>
                        <td><input type="text" class="estimate-test3"></td>
                        <td><input type="text"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="estimate-attachment">
            <div class="estimate-attachment-header">
                <h2 class="estimate-file">첨부파일</h2>
                <img src="@/assets/img/pdf.png" class="estimate-pdfimage">
            </div>
            <div class="estimate-attachment-content">
                <div class="file-list">
                    <span class="file-icon">📄</span>
                    <span class="file-name">제품 카탈로그.pdf</span>
                </div>
            </div>
        </div>
        <div class="estimate-regist-btn-div">
            <button class="estimate-regist-btn">견적 등록하기</button>
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

.estimate-test1 {
    width: 70px;
}

.estimate-test2,
.estimate-test3 {
    width: 100px;
}

.estimate-attachment {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
    width: 100%;
    height: 200px;
    background-color: #d5e6ff;
    border-radius: 10px;
    margin-bottom: 50px;
}

.estimate-attachment-header {
    display: flex;
    align-items: center;
    padding: 5px;
    margin-bottom: -20px;
}

.estimate-pdfimage {
    width: 30px;
    padding-bottom: 5px;
    padding-left: 5px;
}

.estimate-attachment-content {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

.file-list {
    display: flex;
    align-items: center;
    background-color: white;
    width: 90%;
    height: 70px;
    border-radius: 10px;
    padding: 20px;
    margin-top: -5px;
}

.file-icon {
    font-size: 24px;
    margin-right: 5px;
}

.file-name {
    font-size: 18px;
}

.estimate-regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.estimate-regist-btn {
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
    width: 320px;
    font-size: 18px;
    margin-top: 20px;
    margin-bottom: 100px;
}

</style>