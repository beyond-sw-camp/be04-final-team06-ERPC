<template>
    <div class="customer-search">
        <h1>계약서 조회</h1>
    </div>

    <div class="search-box">
        <div class="business-number">
            <p class="business-number-text">
                <select class="form-control" name="search_type" v-model="search_type">
                    <option value="titleContent">계약서 번호</option>
                    <option value="title">계약서 코드</option>
                </select>
            </p>
            <input type="text" placeholder="검색어를 입력하세요">
        </div>
        <div class="search-btn-div">
            <button class="search-btn">조회하기</button>
        </div>
    </div>

    <div class="align">
        <label for="sort">정렬:</label>
        <select id="sort" class="form-control" name="sort_type" v-model="sort_type">
            <option value="date">작성 일자</option>
            <option value="number">계약서 번호</option>
        </select>
    </div>

    <table class="contract-table">
        <thead>
            <tr class="header1">
                <th>번호</th>
                <th>계약서 코드</th>
                <th>총 비용</th>
                <th>작성 일자</th>
                <th>납기 일자</th>
                <th>결제 상태</th>
                <th>담당자</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="7" class="no-result">검색 결과가 없습니다.</td>
            </tr>
        </tbody>
    </table>
</template>

<style>
    .customer-search {
        text-align: center;
        margin-bottom: 20px;
    }

    .search-box {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 20px;
    }

    .business-number {
        display: flex;
        align-items: center;
    }

    .form-control {
        height: 30px;
        font-size: 12px;
        margin-right: 10px;
    }

    .search-btn {
        padding: 8px 16px;
        font-size: 14px;
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    .align {
        margin-bottom: 20px;
    }

    .contract-table {
        width: 100%;
        border-collapse: collapse;
    }

    .contract-table th,
    .contract-table td {
        padding: 8px;
        border: 1px solid #ddd;
        text-align: center;
    }

    .contract-table .no-result {
        text-align: center;
        padding: 10px;
    }
</style>