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
                            <td>123-45-67890</td>
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
    @import url('@/assets/css/customer/CustomerRegist.css');
</style>