<template>
    <div class="deposit-search-content">
        <div class="deposit-search">
            <h1>수금 조회</h1>
        </div>
        <div class="deposit-box">
            <div class="search-date">
                <p class="search-start-date-text">조회 시작 일자</p>
                <input type="date" class="search-start-date-box" id="search-start-date-box">
                <p class="search-end-date-text">조회 종료 일자</p>
                <input type="date" class="search-end-date-box" id="search-end-date-box">
            </div>
            <div class="deposit-search-btn-div">
                <button class="deposit-search-btn">조회하기</button>
            </div>
        </div>
        <div class="deposit-search2">
            <h1>입금 내역</h1>
        </div>
        <div class="deposit-list-box">
            <table class="deposit-table">
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
                    <tr v-for="(customer, index) in filteredCustomers" :key="index">
                        <td>{{ index + 1 }}</td>
                        <td>{{ customer.code }}</td>
                        <td>{{ customer.name }}</td>
                        <td>{{ customer.registration }}</td>
                        <td>{{ customer.representative }}</td>
                        <td>{{ customer.businessType }}</td>
                        <td>{{ customer.industry }}</td>
                        <td>{{ customer.phone }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
.deposit-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.deposit-search,
.deposit-search2 {
    text-align: center;
    margin-top: 3%;
}

.deposit-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    border: 2px solid #ccc;
    box-sizing: border-box;
    background-color: whitesmoke;
    height: auto;
    width: 100%;
    margin: 20px auto;
    gap: 1px;
    max-width: 300px;
}

.search-date {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.search-start-date-text,
.search-end-date-text {
    min-width: 50px;
    margin-right: 10px;
    margin-bottom: 2px;
}

.search-start-date-box,
.search-end-date-box {
    flex-grow: 1;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 220px;
}

.deposit-search-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
    margin-top: 10px;
}

.deposit-search-btn {
    padding: 10px 20px;
    text-align: center;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
    margin-top: 5px;
    margin-bottom: 5px;
    max-width: 320px;
}

.deposit-list-box {
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

.deposit-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.deposit-table th,
.deposit-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.deposit-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.deposit-table tr:hover {
    background-color: #d5e6ff;
}

</style>