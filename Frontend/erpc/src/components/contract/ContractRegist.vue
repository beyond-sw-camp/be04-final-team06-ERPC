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
                        <td><input type="text" class="contract-test5"></td>
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
                        <td><input type="text" class="contract-test6"></td>
                        <td><input type="text" class="contract-test7"></td>
                        <td><input type="text" class="contract-test8"></td>
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
            <button class="contract-regist-btn1">계약 등록하기</button>
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
    @import url('@/assets/css/contract/ContractRegist.css');
</style>