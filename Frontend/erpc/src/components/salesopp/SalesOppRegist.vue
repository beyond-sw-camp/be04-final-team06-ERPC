<template>
    <div class="regist-content">
        <div class="order-regist">
            <h1>영업기회 등록</h1>
        </div>
        <div class="order-list-box1">
            <table class="order-table1">
                <thead>
                    <tr>
                        <th>거래처 명</th>
                        <th>거래처 담당자</th>
                        <th>거래처 주소</th>
                        <th>연락처</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>OO상사</td>
                        <td>홍길동</td>
                        <td>서울특별시 동작구 신대방로 87 SFC빌딩 3층</td>
                        <td>010-1234-5678</td>
                    </tr>
                </tbody>
            </table>
            <table class="order-table2">
                <thead>
                    <tr>
                        <th>비고</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>5월 8일 18:00 방문 희망하심</td>
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
            <button class="estimate-regist-btn">영업기회 등록하기</button>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/order/OrderRegist.css');
</style>