<template>
  <div class="contract-content123">
    <div class="contract-list">
      <h1>창고 목록</h1>
    </div>
    <div class="contract-list-box778">
      <div class="search-bar">
        <select v-model="searchCategory">
          <option value="warehouseName">창고 이름</option>
          <option value="warehouseLocation">창고 위치</option>
          <option value="warehouseUsage">창고 용도</option>
        </select>
        <input type="text" v-model="searchKeyword" placeholder="검색어를 입력하세요">
        <button @click="search">검색</button>
      </div>

      <table class="contract-table778">
        <thead>
          <tr class="header1">
            <th>번호</th>
            <th>창고 이름</th>
            <th>창고 위치</th>
            <th>창고 유형</th>
            <th>창고 용도</th>
            <th>생산 라인</th>
            <th>외주 업체</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(warehouse, index) in paginatedWarehouseList" :key="warehouse.warehouseId">
            <td>{{ (currentPage - 1) * pageSize + index + 1 }}</td>
            <td>{{ warehouse.warehouseName }}</td>
            <td>{{ warehouse.warehouseLocation }}</td>
            <td>{{ warehouse.warehouseType }}</td>
            <td>{{ warehouse.warehouseUsage }}</td>
            <td>{{ warehouse.productionLineName }}</td>
            <td>{{ warehouse.outsourceName }}</td>
          </tr>
          <tr v-if="filteredWarehouseList.length === 0">
            <td colspan="7" class="no-result">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="pagination">
      <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
      <button v-for="page in totalPages" :key="page" @click="changePage(page)"
        :class="{ active: currentPage === page }">{{ page }}</button>
      <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      warehouseList: [],
      currentPage: 1,
      pageSize: 10,
      searchKeyword: '',
      searchCategory: 'warehouseName' // 기본 검색 카테고리를 '창고 이름'으로 설정
    };
  },
  computed: {
    filteredWarehouseList() {
      const keyword = this.searchKeyword.trim().toLowerCase();
      const category = this.searchCategory;

      if (!keyword) {
        return this.warehouseList;
      }

      return this.warehouseList.filter(warehouse => {
        if (category === 'warehouseName') {
          return warehouse.warehouseName.toLowerCase().includes(keyword);
        } else if (category === 'warehouseLocation') {
          return warehouse.warehouseLocation.toLowerCase().includes(keyword);
        } else if (category === 'warehouseUsage') {
          return warehouse.warehouseUsage.toLowerCase().includes(keyword);
        } else {
          return false;
        }
      });
    },
    paginatedWarehouseList() {
      const startIndex = (this.currentPage - 1) * this.pageSize;
      const endIndex = this.currentPage * this.pageSize;
      return this.filteredWarehouseList.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredWarehouseList.length / this.pageSize);
    }
  },
  mounted() {
    this.fetchWarehouseList();
  },
  methods: {
    async fetchWarehouseList() {
      try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/warehouse');
        this.warehouseList = response.data;
      } catch (error) {
        console.error('창고 목록을 불러오는 중 에러 발생:', error);
      }
    },
    changePage(page) {
      if (page > 0 && page <= this.totalPages) {
        this.currentPage = page;
      }
    },
    search() {
      this.currentPage = 1;
    }
  }
};
</script>


<style scoped>
.contract-content123 {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  width: 90%;
}

.contract-list {
  text-align: center;
  width: 100%;
}

.contract-list-search {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  gap: 10px;
}

.contract-list-box778 {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 15px;
  border-radius: 10px;
  box-sizing: border-box;
  background-color: white;
  max-width: 1400px;
  overflow-x: auto;
}

.contract-table778 {
  width: 100%;
  border-collapse: collapse;
  margin: 10px 0;
  font-size: 14px;
}

.contract-table778 th,
.contract-table778 td {
  text-align: center;
  border: 1px solid #ccc;
  padding: 8px;
  font-family: GmarketSansMedium;
}

.contract-table778 th {
  background-color: #0C2092;
  color: white;
  font-size: 14px;
  padding: 10px;
}

.contract-table778 tr:hover {
  background-color: #d5e6ff;
  cursor: pointer;
}

.contract-table778 th:nth-child(1),
.contract-table778 td:nth-child(1) {
  width: 7%; /* 번호 열 너비 */
}

.contract-table778 th:nth-child(2),
.contract-table778 td:nth-child(2) {
  width: 15%; /* 창고 이름 열 너비 */
}

.contract-table778 th:nth-child(3),
.contract-table778 td:nth-child(3) {
  width: 30%; /* 창고 위치 열 너비 */
}

.contract-table778 th:nth-child(4),
.contract-table778 td:nth-child(4) {
  width: 10%; /* 창고 유형 열 너비 */
}

.contract-table778 th:nth-child(5),
.contract-table778 td:nth-child(5) {
  width: 10%; /* 창고 용도 열 너비 */
}

.contract-table778 th:nth-child(6),
.contract-table778 td:nth-child(6) {
  width: 10%; /* 생산 라인 열 너비 */
}

.contract-table778 th:nth-child(7),
.contract-table778 td:nth-child(7) {
  width: 18%; /* 외주 업체 열 너비 */
}

.search-bar {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  width: 100%;
  justify-content: center;
}

.search-bar select {
  padding: 6px;
  margin-right: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  outline: none;
  font-size: 12px;
}

.search-bar input[type="text"] {
  flex: 1;
  padding: 6px;
  border: 1px solid #ccc;
  border-radius: 5px;
  outline: none;
  font-size: 12px;
  max-width: 200px;
}

.search-bar button {
  padding: 6px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  outline: none;
  font-size: 12px;
  height: 30px;
  line-height: 1;
  margin-left: 10px;
}

.search-bar button:hover {
  background-color: #0056b3;
}

.search-bar button:active {
  background-color: #0056b3;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 10px;
}

.pagination button {
  padding: 8px 12px;
  margin: 0 5px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 12px;
}

.pagination button:disabled {
  background-color: #cccccc;
  cursor: not-allowed;
}

.pagination .active {
  background-color: #0C2092 !important;
  color: white;
}
</style>
