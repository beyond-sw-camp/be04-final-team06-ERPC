<template>
    <div class="regist-content">
        <div class="contract-regist">
            <h1>계약서 등록</h1>
        </div>
        <div class="contract-search-box">
            <h3>견적서 불러오기</h3>
            <div class="contract-number">
                <p class="contract-number-text">견적서 코드</p>
                <input type="text" id="contract-number-box" class="contract-number-box" placeholder="견적서 코드를 입력해주세요.">
            </div>
            <div class="contract-search-btn-div1">
                <button class="contract-search-btn1">조회하기</button>
            </div>
        </div>
        <div class="contract-list-box1">
            <table class="contract-table1">
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
            <table class="contract-table2">
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
                        <td class="contract-test1">창고</td>
                        <td>서울특별시 강남구 강남대로 11</td>
                        <td class="contract-test2">Y</td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="contract-table3">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처명</th>
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
                        <td><input type="text" class="contract-test3"></td>
                        <td><input type="text" class="contract-test4"></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <table class="contract-table4">
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
                        <td class="test1">
                            <div class="contract-dropdown1">
                                <button class="contract-dropdown-btn1">{{ searchBy }} ▼</button>
                                <div class="contract-dropdown-content1">
                                    <a href="#" @click.prevent="setSearchBy('일시 납부')">일시 납부</a>
                                    <a href="#" @click.prevent="setSearchBy('분할 납부')">분할 납부</a>
                                </div>
                            </div>
                        </td>
                        <td><input type="text" class="contract-test5"></td>
                        <td><input type="text" class="contract-test6"></td>
                        <td><input type="text" class="contract-test7"></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="contract-attachment1">
            <div class="contract-attachment-header1">
                <h2 class="contract-file1">첨부파일</h2>
                <img src="@/assets/img/pdf.png" class="contract-pdfimage1">
            </div>
            <div class="contract-attachment-content1">
                <div class="contract-file-list1">
                    <span class="contract-file-icon1">📄</span>
                    <span class="contract-file-name1">견적서.pdf</span>
                </div>
            </div>
        </div>
        <div class="contract-regist-btn-div1">
            <button class="contract-regist-btn1">계약서 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const searchBy = ref('일시 납부 or 분할 납부');

function setSearchBy(criteria) {
    searchBy.value = criteria;
}
</script>



<style>
.regist-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.contract-regist {
    text-align: center;
    margin-top: 3%;
}

.contract-list-box1 {
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

.contract-table1,
.contract-table2,
.contract-table3,
.contract-table4,
.contract-table5 {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.contract-test1,
.contract-test2 {
    width: 100px;
}

.contract-table1 th,
.contract-table1 td,
.contract-table2 th,
.contract-table2 td,
.contract-table3 th,
.contract-table3 td,
.contract-table4 th,
.contract-table4 td,
.contract-table5 th,
.contract-table5 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.contract-table1 th,
.contract-table1 td,
.contract-table3 th,
.contract-table3 td,
.contract-table4 th,
.contract-table4 td,
.contract-table5 th,
.contract-table5 td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
    width: 160px;
}

.contract-table1 th,
.contract-table2 th,
.contract-table3 th,
.contract-table4 th,
.contract-table5 th {
    background-color: whitesmoke;
    color: black;
    font-size: 18px;
    padding: 10px;
    height: 60px;
}

.contract-table1 td,
.contract-table2 td,
.contract-table3 td,
.contract-table4 td,
.contract-table5 td {
    height: 40px;
}

.contract-dropdown1 {
    position: relative;
    display: inline-block;
    width: 160px;
}

.contract-dropdown-btn1,
.contract-pdf1-btn {
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.contract-dropdown-content1 {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
}

.contract-dropdown-content1 a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.contract-dropdown-content1 a:hover {
    background-color: #d5e6ff;
}

.contract-dropdown1:hover .contract-dropdown-content1 {
    display: block;
}

.contract-search-box {
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

.contract-search-btn-div1,
.contract-regist-btn-div1 {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
}

.contract-search-btn1,
.contract-regist-btn1 {
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

.contract-search-btn1 {
    max-width: 320px;
}

.contract-regist-btn1 {
    width: 320px;
    font-size: 18px;
    margin-top: 30px;
    margin-bottom: 100px;
}

.contract-attachment1 {
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

.contract-attachment-header1 {
    display: flex;
    align-items: center;
    padding: 5px;
    margin-bottom: -20px;
}

.contract-pdfimage1 {
    width: 30px;
    padding-bottom: 5px;
    padding-left: 5px;
}

.contract-attachment-content1 {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
}

.contract-file-list1 {
    display: flex;
    align-items: center;
    background-color: white;
    width: 90%;
    height: 70px;
    border-radius: 10px;
    padding: 20px;
    margin-top: -5px;
}

.contract-file-icon1 {
    font-size: 24px;
    margin-right: 5px;
}

.contract-file-name1 {
    font-size: 18px;
}
</style>