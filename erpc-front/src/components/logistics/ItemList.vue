<template>
  <div class="contract-content123">
    <div class="contract-list">
      <h1>품목 목록</h1>
    </div>
    <div class="contract-list-box123">
      <div class="search-bar">
        <!-- 검색 카테고리 선택 추가 -->
        <select v-model="searchCategory">
          <option value="productCode">상품 코드</option>
          <option value="productName">상품 이름</option>
          <option value="productMainCategory">메인 카테고리</option>
          <option value="productSubCategory">서브 카테고리</option>
        </select>
        <input type="text" v-model="searchKeyword" placeholder="검색어를 입력하세요">
        <button @click="search">검색</button>
      </div>
      <table class="contract-table123">
        <thead>
          <tr class="header1">
            <th>번호</th>
            <th>메인 카테고리</th>
            <th>서브 카테고리</th>
            <th>상품 코드</th>
            <th>상품 이름</th>
            <th>단위</th>
            <th>가격</th>
            <th>상태</th>
            <th>재고</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(product, index) in paginatedProductList" :key="product.productId">
            <td>{{ (currentPage - 1) * itemsPerPage + index + 1 }}</td>
            <td>{{ product.productMainCategory }}</td>
            <td>{{ product.productSubCategory }}</td>
            <td>{{ product.productCode }}</td>
            <td>{{ product.productName }}</td>
            <td>{{ product.productUnit }}</td>
            <td>{{ product.productPrice.toLocaleString() }}</td>
            <td>{{ product.productStatus }}</td>
            <td>{{ product.productInventory }}</td>
          </tr>
          <tr v-if="filteredProductList.length === 0">
            <td colspan="9" class="no-result">검색 결과가 없습니다.</td>
          </tr>
        </tbody>
      </table>
      <div class="pagination">
            <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
            <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
            <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
        </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      productList: [],
      currentPage: 1,
      itemsPerPage: 10,
      searchKeyword: '',
      searchCategory: 'productCode' // 기본 검색 카테고리를 '상품 코드'로 설정
    };
  },
  computed: {
    paginatedProductList() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = this.currentPage * this.itemsPerPage;
      return this.filteredProductList.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.filteredProductList.length / this.itemsPerPage);
    },
    filteredProductList() {
      const keyword = this.searchKeyword.trim().toLowerCase();
      const category = this.searchCategory;
      
      if (!keyword) {
        return this.productList;
      }

      return this.productList.filter(product => {
        if (category === 'productCode') {
          return product.productCode.toLowerCase().includes(keyword);
        } else if (category === 'productName') {
          return product.productName.toLowerCase().includes(keyword);
        } else if (category === 'productMainCategory') {
          return product.productMainCategory.toLowerCase().includes(keyword);
        } else if (category === 'productSubCategory') {
          return product.productSubCategory.toLowerCase().includes(keyword);
        } else {
          return false;
        }
      });
    }
  },
  mounted() {
    this.fetchProductList();
  },
  methods: {
    async fetchProductList() {
      try {
        const response = await axios.get('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/product');
        this.productList = response.data;
      } catch (error) {
        console.error('품목 목록을 불러오는 중 에러 발생:', error);
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

<style>
.contract-content123 {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.contract-list {
    text-align: center;
}

.contract-list-search {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 10px;
}

.contract-dropdown {
    position: relative;
    display: inline-block;
}

.contract-dropdown-btn {
    width: 200px;
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 14px;
    cursor: pointer;
    outline: none;
    color: #0C2092;
}

.contract-dropdown-content {
    display: none;
    position: absolute;
    background-color: white;
    border: 1px solid #ccc;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
    border-radius: 10px;
    width: 100%;
    font-size: 13px;
}

.contract-dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    border-bottom: 1px solid #ccc;
}

.contract-dropdown-content a:hover {
    background-color: #f1f1f1;
}

.contract-dropdown:hover .contract-dropdown-content {
    display: block;
}

.contract-search-input {
    max-width: 300px;
    height: 15px;
    padding: 8px;
    border: 2px solid #0C2092;
    border-radius: 10px;
    outline: none;
    font-size: 14px;
    background-color: #e5F0FF;
    color: #0C2092;
}

.contract-search-btn7 {
    background-color: #0C2092;
    border: none;
    color: white;
    padding: 8px 5px;
    width: 60px;
    font-size: 14px;
    border-radius: 10px;
    cursor: pointer;
    outline: none;
}

.contract-list-box123 {
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

.contract-table123 {
  width: 100%;
  border-collapse: collapse;
  margin: 10px 0;
  font-size: 12px;
  table-layout: fixed; /* 추가 */
}

.contract-table123 th,
.contract-table123 td {
  text-align: center;
  border: 1px solid #ccc;
  padding: 6px;
  font-family: GmarketSansMedium;
  width: 11%; /* 추가 */
}

.contract-table123 th:nth-child(1),
.contract-table123 td:nth-child(1) {
  width: 5%; /* 번호 열 너비 */
}

.contract-table123 th:nth-child(4),
.contract-table123 td:nth-child(4) {
  width: 14%; /* 상품 코드 열 너비 */
}

.contract-table123 th:nth-child(5),
.contract-table123 td:nth-child(5) {
  width: 20%; /* 상품 이름 열 너비 */
}


.contract-table123 th {
    background-color: #0C2092;
    color: white;
    font-size: 14px;
    padding: 8px;
}

.contract-table123 tr:hover {
    background-color: #d5e6ff;
    cursor: pointer;
}


.search-bar {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
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
  height: 30px; /* 높이 추가 */
  line-height: 1; /* 텍스트 중앙 정렬 */
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
