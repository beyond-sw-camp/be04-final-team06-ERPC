<script setup>
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
import CBList from './components/bill/CBList.vue'
import CBContents from './components/bill/CBContents.vue';

import YearPerformance from './components/performance/YearPerformance.vue';

import ContractRegist from './components/contract/ContractRegist.vue';
import ContractList from './components/contract/ContractList.vue';
import ContractContent from './components/contract/ContractContent.vue';

import EstimateRegist from './components/estimate/EstimateRegist.vue';
</script>

<template>
  <Header></Header>
  <!-- <CustomerRegist></CustomerRegist> -->
  <!-- <CustomerList></CustomerList> -->
  <!-- <CustomerContents></CustomerContents> -->
  <!-- <OrderRegist></OrderRegist> -->
  <!-- <OrderList></OrderList> -->
  <!-- <OrderContents></OrderContents> -->
  <!-- <Deposit></Deposit> -->
  <!-- <CBRequest></CBRequest> -->
  <!-- <CBList></CBList> -->
  <!-- <CBContents></CBContents> -->
  <YearPerformance></YearPerformance>
  <!-- <ContractRegist></ContractRegist> -->
  <!-- <ContractList></ContractList>  -->
  <!-- <ContractContent></ContractContent> -->
  <Footer></Footer>
</template>

<style>
@font-face {
  font-family: 'GmarketSansMedium';
  src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
}

* {
  font-family: GmarketSansMedium;
}
</style>