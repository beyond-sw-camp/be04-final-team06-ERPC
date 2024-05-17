<script setup>
import { ref } from 'vue';

import Header from './components/main/Header.vue';
import Footer from './components/main/Footer.vue';

import CustomerRegist from './components/customer/CustomerRegist.vue';
import CustomerList from './components/customer/CustomerList.vue';
import CustomerContents from './components/customer/CustomerContents.vue';

import OrderRegist from './components/order/OrderRegist.vue';
import OrderList from './components/order/OrderList.vue';
import OrderContents from './components/order/OrderContents.vue';

import Deposit from './components/bill/Deposit.vue';
import CBRequest from './components/bill/CBRequest.vue';
import CBList from './components/bill/CBList.vue';
import CBContents from './components/bill/CBContents.vue';

import YearPerformance from './components/performance/YearPerformance.vue';
import TeamPerformance from './components/performance/TeamPerformance.vue';

import ContractRegist from './components/contract/ContractRegist.vue';
import ContractList from './components/contract/ContractList.vue';
import ContractContent from './components/contract/ContractContent.vue';

import EstimateRegist from './components/estimate/EstimateRegist.vue';

const menuItems = ref([
  '공지 사항',
  '결재 관리',
  '영업 기회',
  '품목 관리',
  '거래처 관리',
  '견적서 관리',
  '계약서 관리',
  '수주 관리',
  '전표 관리',
  '실적 관리'
]);

const toggleButtonText = (event) => {
  const button = event.currentTarget;
  const img = button.querySelector('img');
  if (img.src.includes('plus.png')) {
    img.src = require('@/assets/img/minus.png');
  } else {
    img.src = require('@/assets/img/plus.png');
  }
};
</script>

<template>
  <Header></Header>
  <Main class="container">
    <div class="main1">
      <ul class="menu">
        <li class="menu-item" v-for="(item, index) in menuItems" :key="index">
          <span class="menu-button-text">{{ item }}</span>
          <button class="menu-button" @click="toggleButtonText">
            <img src="@/assets/img/plus.png" class="menuimage">
          </button>
        </li>
      </ul>
    </div>
    <div class="main2">
      <!-- <CustomerRegist></CustomerRegist> -->
      <!-- <CustomerList></CustomerList> -->
      <!-- <CustomerContents></CustomerContents> -->
      <!-- <OrderRegist></OrderRegist> -->
      <!-- <OrderList></OrderList> -->
      <OrderContents></OrderContents>
      <!-- <Deposit></Deposit> -->
      <!-- <CBRequest></CBRequest> -->
      <!-- <CBList></CBList> -->
      <!-- <CBContents></CBContents> -->
      <!-- <YearPerformance></YearPerformance> -->
      <!-- <TeamPerformance></TeamPerformance> -->
      <!-- <ContractRegist></ContractRegist> -->
      <!-- <ContractList></ContractList>  -->
      <!-- <ContractContent></ContractContent> -->
    </div>
  </Main>
  <Footer></Footer>
</template>

<script>
export default {
  data() {
    return {
      menuItems: [
        '공지 사항',
        '결재 관리',
        '영업 기회',
        '품목 관리',
        '거래처 관리',
        '견적서 관리',
        '계약서 관리',
        '수주 관리',
        '전표 관리',
        '실적 관리'
      ]
    };
  }
};
</script>

<style>
@font-face {
  font-family: 'GmarketSansMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
}

* {
  font-family: GmarketSansMedium;
}

.container {
  display: flex;
  align-items: flex-start;
  justify-content: center;
}

.main1 {
  flex: 0.2;
  min-width: 200px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  padding: 20px;
}

.menu {
  list-style-type: none;
  padding: 0;
  margin: 0;
  width: 100%;
}

.menu-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #ccc;
}

.menu-button {
  width: 30px;
  height: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: none;
  border: 2px solid #0C2092;
  cursor: pointer;
  transition: transform 0.3s;
}

.menu-button:hover {
  transform: rotate(90deg);
}

.menu-button-text {
  cursor: pointer;
}

.main2 {
  flex: 1.8;
  min-width: 600px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.menuimage {
  width: 15px;
  height: 15px;
}
</style>
