<template>
    <div class="regist-content">
        <div class="contract-regist">
            <h1>견적서 조회내역</h1>
        </div>
        <div class="contract-state">
            <button>결제요청</button>
            <button>수정</button>
            <button>삭제요청</button>
        </div>
        <div class="contract-list-box">
            <table class="contract-table">
                <thead>
                    <tr>
                        <th>견적서번호</th>
                        <th>총비용</th>
                        <th>작성일자</th>
                        <th>삭제일자</th>
                        <th>마감일자</th>
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
            <table class="contract-table2">
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
            <table class="contract-table3">
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
            <table class="contract-table4">
                <thead>
                    <tr>
                        <th>프로젝트 코드</th>
                        <th>담당자</th>
                        <th>거래처</th>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>PJ-20240508001</td>
                        <td>유관순</td>
                        <td>OO상사</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="contract-attachment">
            <h3>첨부파일<img src="@/assets/img/pdf.png" class="pdf"></h3>
            <div class="contract-attachment-content">견적서 pdf</div>
        </div>
    </div>
</template>

<script setup>

</script>
<style>
    @import url('@/assets/css/contract/ContractContent.css');
</style>