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
            <div class="contract-attachment">
            <h3>첨부파일<img src="@/assets/img/pdf.png" class="pdf"></h3>
            <div class="contract-attachment-content">거래처 정보</div>
        </div>
                  
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/order/OrderRegist.css');
</style>