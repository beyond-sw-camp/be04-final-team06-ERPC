<template>
    <div class="list-content">
        <div class="customer-list">
            <h1>거래처 목록</h1>
        </div>
        <div class="list-search">
            <div class="dropdown">
                <button class="dropdown-btn">거래처명 ▼</button>
                <div class="dropdown-content">
                    <a href="#">거래처명</a>
                    <a href="#">거래처 코드</a>
                </div>
            </div>
            <input type="text" class="search-input" placeholder="검색어를 입력하세요">
            <button class="search-btn">검색</button>
        </div>
        <div class="list-box">
            <table class="customer-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>거래처 코드</th>
                        <th>사업자명</th>
                        <th>사업자 번호</th>
                        <th>대표자명</th>
                        <th>업태</th>
                        <th>종목</th>
                        <th>전화번호</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>AC-20240430001</td>
                        <td>A-회사</td>
                        <td>123-45-67890</td>
                        <td>홍길동</td>
                        <td>소매업</td>
                        <td>백화점</td>
                        <td>000-0000-0000</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>AC-20240430002</td>
                        <td>B-회사</td>
                        <td>234-56-78901</td>
                        <td>이순신</td>
                        <td>도매업</td>
                        <td>가전제품</td>
                        <td>111-1111-1111</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
    @import url('@/assets/css/customer/CustomerList.css');
</style>