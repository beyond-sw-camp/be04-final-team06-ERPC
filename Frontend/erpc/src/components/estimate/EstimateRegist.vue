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
                        <td>
                            <div class="item-code-div2">
                                <input type="text" id="item-code-box2" class="item-code-box2" placeholder="품목 코드를 입력해주세요.">
                                <button class="item-code-btn2">확인</button>
                            </div>
                        </td>
                        <td>LG 콤퓨타</td>
                        <td><input type="text" class="estimate-test2"></td>
                        <td>1,800,000</td>
                        <td>9,000,000</td>
                        <td><input type="text" class="estimate-test3"></td>
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
                        <td>
                            <div class="storage-code-div2">
                                <input type="text" id="storage-code-box2" class="storage-code-box2" placeholder="품목 코드를 입력해주세요.">
                                <button class="storage-code-btn2">확인</button>
                            </div>
                        </td>
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
                        <th>거래처명</th>
                        <th>마감일자</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td><input type="text" class="estimate-test5"></td>
                        <td><input type="text" class="estimate-test6"></td>
                        <td><input type="text" class="estimate-test7"></td>
                        <td><input type="text" class="estimate-test8"></td>
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
    @import url('@/assets/css/estimate/EstimateRegist.css');
</style>