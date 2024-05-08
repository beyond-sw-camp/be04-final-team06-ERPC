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
.list-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.customer-list {
    text-align: center;
    margin-top: 3%;
}

.list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-btn {
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.dropdown-content a:hover {
    background-color: #f1f1f1;
}

.dropdown:hover .dropdown-content {
    display: block;
}

.search-input {
    max-width: 300px;
    padding: 10px;
    border: 2px solid #0C2092;
    border-radius: 10px;
    outline: none;
    font-size: 16px;
    background-color: #E5F0FF;
    color: #0C2092;
}

.search-btn {
    background-color: #0C2092;
    border: none;
    color: white;
    padding: 10px 8px;
    width: 70px;
    margin-top: 15px;
    margin-bottom: 16px;
    font-size: 16px;
    border-radius: 10px;
    cursor: pointer;
    outline: none;
}

.list-box {
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
    max-width: 1400px;
    margin: 20px auto;
    gap: 1px;
}

.customer-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.customer-table th,
.customer-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.customer-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.customer-table tr:hover {
    background-color: #d5e6ff;
}
</style>