import { createRouter, createWebHistory } from 'vue-router';

import CustomerRegist from '@/components/customer/CustomerRegist.vue';
import CustomerList from '@/components/customer/CustomerList.vue';
import CustomerContents from '@/components/customer/CustomerContents.vue';

import OrderRegist from '@/components/order/OrderRegist.vue';
import OrderList from '@/components/order/OrderList.vue';
import OrderContents from '@/components/order/OrderContents.vue';

import Deposit from '@/components/bill/Deposit.vue';
import CBRequest from '@/components/bill/CBRequest.vue';
import CBList from '@/components/bill/CBList.vue';
import CBContents from '@/components/bill/CBContents.vue';

import YearPerformance from '@/components/performance/YearPerformance.vue';
import TeamPerformance from '@/components/performance/TeamPerformance.vue';

import ContractRegist from '@/components/contract/ContractRegist.vue';
import ContractList from '@/components/contract/ContractList.vue';
import ContractContent from '@/components/contract/ContractContent.vue';

import EstimateRegist from '@/components/estimate/EstimateRegist.vue';

const routes = [
    {
        path: '/customer/regist',
        component: CustomerRegist
    },
    {
        path: '/customer/list',
        component: CustomerList
    },
    {
        path: '/customer/contents',
        component: CustomerContents
    },
    {
        path: '/order/regist',
        component: OrderRegist
    },
    {
        path: '/order/list',
        component: OrderList
    },
    {
        path: '/order/contents',
        component: OrderContents
    },
    {
        path: '/bill/deposit',
        component: Deposit
    },
    {
        path: '/bill/request',
        component: CBRequest
    },
    {
        path: '/bill/list',
        component: CBList
    },
    {
        path: '/bill/contents',
        component: CBContents
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
