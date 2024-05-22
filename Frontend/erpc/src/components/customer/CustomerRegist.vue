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
            <div class="search-btn-div1">
                <button class="search-btn1">조회하기</button>
            </div>
        </div>
        <div class="regist-content">
            <div class="customer-regist">
                <h1>거래처 등록</h1>
            </div>
            <div class="customer-list-box1">
                <table class="customer-table1">
                    <thead>
                        <tr>
                            <th>사업자 번호</th>
                            <th>사업자명</th>
                            <th>대표자명</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>110-1234-5678</td>
                            <td><input type="text"></td>
                            <td><input type="text"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table2">
                    <thead>
                        <tr>
                            <th>기업 상태 (휴/폐업 여부)</th>
                            <th>법인 여부</th>
                            <th>소재지</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>정상</td>
                            <td><input type="text" class="customer-test2"></td>
                            <td><input type="text" class="customer-test3"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table3">
                    <thead>
                        <tr>
                            <th>업태</th>
                            <th>종목</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text"></td>
                            <td><input type="text"></td>
                            <td><input type="text"></td>
                            <td><input type="text"></td>
                        </tr>
                    </tbody>
                </table>
                <table class="customer-table4">
                    <thead>
                        <tr>
                            <th>비고</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" class="customer-test4"></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="customer-regist-btn-div">
            <button class="customer-regist-btn">거래처 등록하기</button>
        </div>
    </div>
</template>



<script setup>
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router'

const currentRoute = useRoute();
const router = useRouter();

</script>

<style>
.search-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.customer-search {
    text-align: center;
    margin-top: 3%;
}

.search-box {
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
    max-width: 400px;
}

.regist-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.customer-regist {
    text-align: center;
}

.search-btn-div1,
.regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.search-btn1,
.customer-regist-btn {
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

.customer-regist-btn {
    width: 320px;
    font-size: 18px;
    margin-top: 20px;
    margin-bottom: 100px;
}

.customer-list-box1 {
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

.customer-table1,
.customer-table2,
.customer-table3,
.customer-table4 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.customer-table1 th,
.customer-table1 td,
.customer-table2 th,
.customer-table2 td,
.customer-table3 th,
.customer-table3 td,
.customer-table4 th,
.customer-table4 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.customer-table1 th,
.customer-table2 th,
.customer-table3 th,
.customer-table4 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.customer-table1 td,
.customer-table2 td,
.customer-table3 td,
.customer-table4 td {
    height: 40px;
}

.customer-test1,
.customer-test2 {
    width: 170px;
}


.customer-test3 {
    width: 450px;
}

.customer-test4 {
    width: 700px;
}

.customer-attachment {
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

.customer-attachment-header {
    display: flex;
    align-items: center;
    padding: 5px;
    margin-bottom: -20px;
}

.customer-pdfimage {
    width: 30px;
    padding-bottom: 5px;
    padding-left: 5px;
}

.customer-attachment-content {
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

.customer-regist-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.customer-regist-btn {
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
    margin-bottom: 100px;
}
</style>