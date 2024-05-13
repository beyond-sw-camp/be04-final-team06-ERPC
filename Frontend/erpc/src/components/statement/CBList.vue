<template>
    <div class="cb-search-content">
        <div class="cb-search">
            <h1 class="cb-search-text">세금계산서 요청 목록</h1>
        </div>
        <div class="cb-box">
            <div class="search-date">
                <p class="search-start-date-text">조회 시작 일자</p>
                <input type="date" class="search-start-date-box" id="search-start-date-box" v-model="startDate">
                <p class="search-end-date-text">조회 종료 일자</p>
                <input type="date" class="search-end-date-box" id="search-end-date-box" v-model="endDate">
            </div>
            <div class="cb-search-btn-div">
                <button class="cb-search-btn" @click="applyFilter">조회하기</button>
            </div>
        </div>
        <div class="cb-list-box">
            <table class="cb-table">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>입금 코드</th>
                        <th>입금자명</th>
                        <th>입금 계좌</th>
                        <th>입금 금액</th>
                        <th>입금 일자</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(cb, index) in filteredcbs" :key="index">
                        <td>{{ index + 1 }}</td>
                        <td>{{ cb.code }}</td>
                        <td>{{ cb.cbor }}</td>
                        <td>{{ cb.account }}</td>
                        <td>{{ cb.amount }}</td>
                        <td>{{ cb.date }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const cbs = ref([
    { code: 'DP-20240430001', cbor: '홍길동', account: '0000-00-0000000', amount: '600,000', date: '2024-04-01' },
    { code: 'DP-20240430002', cbor: '이순신', account: '0000-00-0000000', amount: '500,000', date: '2024-04-01' },
    { code: 'DP-20240430003', cbor: '장보고', account: '0000-00-0000000', amount: '7,000,000', date: '2024-03-31' }
]);
const startDate = ref('');
const endDate = ref('');
const filteredcbs = ref(cbs.value);  

function applyFilter() {
    if (startDate.value && endDate.value) {
        filteredcbs.value = cbs.value.filter(cb => {
            return cb.date >= startDate.value && cb.date <= endDate.value;
        });
    } else {
        filteredcbs.value = cbs.value;
    }
}
</script>

<style>
.cb-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.cb-search {
    text-align: center;
    margin-top: 3%;
}

.cb-search-text {
    margin-top: 5%;
    margin-bottom: 30px;
}

.cb-box {
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

.cb-search-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
    margin-bottom: 10px;
    margin-top: 10px;
}

.cb-search-btn {
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

.cb-list-box {
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

.cb-table {
    width: 100%;
    border-collapse: collapse;
    margin: 20px 0;
    font-size: 16px;
}

.cb-table th,
.cb-table td {
    text-align: center;
    border: 1px solid #ccc;
    padding: 8px;
    font-family: GmarketSansMedium;
}

.cb-table th {
    background-color: #0C2092;
    color: white;
    font-size: 18px;
    padding: 10px;
}

.cb-table tr:hover {
    background-color: #d5e6ff;
}
</style>