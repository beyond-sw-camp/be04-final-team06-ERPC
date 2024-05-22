<template>
    <div class="contract-content">
        <div class="contract-list">
            <h1>영업기회 목록</h1>
        </div>
        <div class="contract-list-search">
            <div class="contract-dropdown">
                <button class="contract-dropdown-btn">거래처 명 </button>
            </div>
            <input type="text" class="contract-search-input" placeholder="검색어를 입력하세요">
            <button class="contract-search-btn">검색</button>
        </div>
        <div class="contract-list-box">
            <table class="contract-table">
                <thead>
                    <tr class="header1">
                        <th>번호</th>
                        <th>거래처 명</th>
                        <th>영업기회 작성일</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
            <tr>
                <td colspan="4" class="no-result">검색 결과가 없습니다.</td>
            </tr>
        </tbody>
    </table>
        </div>
        </div>
</template>

<style>
    @import url('@/assets/css/contract/ContractList.css');
</style>