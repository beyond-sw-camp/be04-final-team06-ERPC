<template>
    <div class="contract-content">
      <div class="contract-list">
        <h1>창고 목록</h1>
      </div>
      <div class="contract-list-box7">
        <table class="contract-table7">
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
            <tr v-for="(warehouse, index) in warehouseList" :key="index">
              <td>{{ index + 1 }}</td>
              <td>{{ warehouse.warehouseName }}</td>
              <td>{{ warehouse.warehouseLocation }}</td>
              <td>{{ warehouse.warehouseType }}</td>
              <td>{{ warehouse.warehouseUsage }}</td>
              <td>{{ warehouse.productionLineName }}</td>
              <td>{{ warehouse.outsourceName }}</td>
            </tr>
            <tr v-if="warehouseList.length === 0">
              <td colspan="8" class="no-result">검색 결과가 없습니다.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        warehouseList: []
      };
    },
    mounted() {
      this.fetchWarehouseList();
    },
    methods: {
      async fetchWarehouseList() {
        try {
          const response = await axios.get('http://localhost:7775/warehouse');
          this.warehouseList = response.data;
        } catch (error) {
          console.error('창고 목록을 불러오는 중 에러 발생:', error);
        }
      }
    }
  };
  </script>
  
  <style scoped>
  @import url('@/assets/css/contract/ContractList.css');
  </style>