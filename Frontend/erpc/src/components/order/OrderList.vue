<template>
    <div class="order-content">
        <div class="order-list">
            <h1>수주 목록</h1>
        </div>
        <div class="order-list-search">
            <div class="order-dropdown">
                <button class="order-dropdown-btn">프로젝트 코드 ▼</button>
                <div class="order-dropdown-content">
                    <a href="#">프로젝트 코드</a>
                    <a href="#">담당자</a>
                </div>
            </div>
            <input type="text" class="order-search-input" placeholder="검색어를 입력하세요">
            <button class="order-search-btn">검색</button>
        </div>
        <div class="order-list-box">
            <table class="order-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>프로젝트 코드</th>
                        <th>수주금액</th>
                        <th>작성일자</th>
                        <th>납기일자</th>
                        <th>결재상태</th>
                        <th>담당자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>PJ-20240508001</td>
                        <td>600,000</td>
                        <td>2024-04-01</td>
                        <td>2025-04-04</td>
                        <td>진행중</td>
                        <td>유관순</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>PJ-20240508002</td>
                        <td>500,000</td>
                        <td>2024-04-01</td>
                        <td>2025-04-04</td>
                        <td>진행중</td>
                        <td>이순신</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
</script>

<style>
.order-list-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.order-list {
    text-align: center;
    margin-top: 3%;
}

.order-list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.order-dropdown {
    position: relative;
    display: inline-block;
}

.order-dropdown-btn {
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.order-dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
}

.order-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.order-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.order-dropdown:hover .order-dropdown-content {
    display: block;
}

.order-search-input {
    max-width: 300px;
    padding: 10px;
    border: 2px solid #0C2092;
    border-radius: 10px;
    outline: none;
    font-size: 16px;
    background-color: #E5F0FF;
    color: #0C2092;
}

.order-search-btn {
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

.order-list-box {
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
    margin-bottom: 7%;
    gap: 1px;
}

.order-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.order-table th,
.order-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.order-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.order-table tr:hover {
    background-color: #d5e6ff;
}
</style>