<template>
    <div class="contract-content">
        <div class="contract-list">
            <h1>견적서 목록</h1>
        </div>
        <div class="contract-list-search">
            <div class="contract-dropdown">
                <button class="contract-dropdown-btn">계약서 코드 ▼</button>
                <div class="contract-dropdown-content">
                    <a href="#">견적서 코드</a>
                    <a href="#">담당자</a>
                </div>
            </div>
            <input type="text" class="contract-search-input" placeholder="검색어를 입력하세요">
            <button class="contract-search-btn">검색</button>
        </div>
        <div class="contract-list-box">
            <table class="contract-table">
                <thead>
                    <tr class="header1">
                        <th>번호</th>
                        <th>견적서 코드</th>
                        <th>총 비용</th>
                        <th>작성 일자</th>
                        <th>마감 일자</th>
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
        </div>
        </div>
</template>

<style>
    @import url('@/assets/css/contract/ContractList.css');
</style>