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
        <div class="customer-search2">
            <h1>거래처 등록</h1>
        </div>
        <div class="search-box2">
            <div class="left">
                <div class="business-number">
                    <p class="business-number-text">사업자 번호</p>
                    <input type="text" id="business-number-box" class="business-number-box"
                        placeholder="사업자 번호를 입력해주세요.">
                </div>
                <div class="business-name">
                    <p class="business-name-text">사업자명</p>
                    <input type="text" id="business-name-box" class="business-name-box" placeholder="사업자명을 입력해주세요.">
                </div>
                <div class="ceo-name">
                    <p class="ceo-name-text">대표자명</p>
                    <input type="text" id="ceo-name-box" class="ceo-name-box" placeholder="대표자명을 입력해주세요.">
                </div>
                <div class="status">
                    <p class="status-text">기업 상태 (휴/폐업 여부)</p>
                    <input type="text" id="status-box" class="status-box" placeholder="기업 상태를 입력해주세요.">
                </div>
                <div class="grade">
                    <p class="grade-text">기업 신용등급</p>
                    <input type="text" id="grade-box" class="grade-box" placeholder="기업 신용등급을 입력해주세요.">
                </div>
                <div class="corporation">
                    <p class="corporation-text">법인 여부</p>
                    <input type="text" id="corporation-box" class="corporation-box" placeholder="법인 여부를 입력해주세요.">
                </div>
            </div>
            <div class="right">
                <div class="industry">
                    <p class="industry-text">업태</p>
                    <input type="text" id="industry-box" class="industry-box" placeholder="업태를 입력해주세요.">
                </div>
                <div class="industry-detail">
                    <p class="industry-detail-text">종목</p>
                    <input type="text" id="industry-detail-box" class="industry-detail-box" placeholder="종목을 입력해주세요.">
                </div>
                <div class="address">
                    <p class="address-text">소재지</p>
                    <input type="text" id="address-box" class="address-box" placeholder="소재지를 입력해주세요.">
                </div>
                <div class="call">
                    <p class="call-text">전화 번호</p>
                    <input type="text" id="call-box" class="call-box" placeholder="전화 번호를 입력해주세요.">
                </div>
                <div class="email">
                    <p class="email-text">이메일</p>
                    <input type="text" id="email-box" class="email-box" placeholder="이메일 주소를 입력해주세요.">
                </div>
                <div class="remark">
                    <p class="remark-text">비고</p>
                    <input type="text" id="remark-box" class="remark-box" placeholder="비고를 입력해주세요.">
                </div>
            </div>
        </div>
        <div class="regist-btn-div">
            <button class="regist-btn">거래처 등록하기</button>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import {useRoute, useRouter} from 'vue-router'

const currentRoute = useRoute();
const router = useRouter();

</script>

<style>
    @import url('@/assets/css/customer/CustomerRegist.css');
</style>