<template>
    <div>
      <table class="table">
        <tr class="top">
            <td class="post" colspan="3">
            <h1 class="boardTitle">계약서 조회 내역</h1>
          </td>
          <td></td>
        </tr>
        <tr class="header1">
          <td>계약서 번호</td>
          <td>총 비용</td>
          <td>작성 일자</td>
          <td>삭제 일자</td>
          <td>납기일자</td>
        </tr>
        <tbody>
    <tr>
      <td>CD-2024</td>
      <td>24000000</td>
      <td>2024-05-08</td>
      <td></td>
      <td>2024-05-30</td>
    </tr>
    </tbody>
        <tr class="header1">
          <td>품목 코드</td>
          <td>품목 이름</td>
          <td>수량</td>
          <td>단가</td>
          <td>합계</td>
          <td>기타</td>
        </tr>
        <tbody>
    <tr>
      <td>CDV-001</td>
      <td>LG컴퓨타</td>
      <td>5</td>
      <td>1000000</td>
      <td>5000000</td>
      <td></td>
    </tr>
    </tbody>
    <tr class="header1">
          <td>창고 코드</td>
          <td>창고명</td>
          <td>구분</td>
          <td>창고주소</td>
          <td>사용여부</td>
          <td>생산 공정명</td>
          <td>외주 거래처명</td>
        </tr>
        <tbody>
    <tr>
      <td>WH-001</td>
      <td>강남 창고</td>
      <td>창고</td>
      <td>서울특별시 강남구 강남대로11</td>
      <td>Y</td>
      <td></td>
      <td></td>
    </tr>
    </tbody>
    <tr class="header1">
          <td>프로젝트 코드</td>
          <td>담당자</td>
          <td>거래처</td>
          <td>수주금액</td>
          <td>납기일자</td>
          <td>비고</td>
        </tr>
        <tbody>
    <tr>
      <td>PJ-20240508</td>
      <td>유관순</td>
      <td>거래처</td>
      <td>5000000</td>
      <td>2024-05-30</td>
      <td></td>
    </tr>
    </tbody>
    <tr class="header1">
          <td>납부 형태</td>
          <td>계약금</td>
          <td>중도금</td>
          <td>잔금</td>
        </tr>
        <tbody>
    <tr>
      <td>일시납부</td>
      <td>2400000</td>
      <td>1600000</td>
      <td>1000000</td>
    </tr>
    </tbody>
      </table>
    </div>
  </template>
<style>
.table {
  width: 100%;
  border-collapse: collapse;
}

.table td,
.table th {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

.table tr:nth-child(even) {
  background-color: #f2f2f2;
}

.box {
  width: 100%;
  padding: 5px;
  box-sizing: border-box;
}

.writebutton {
  padding: 10px;
  background-color: #4caf50;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.writebutton:hover {
  background-color: #45a049;
}
</style>