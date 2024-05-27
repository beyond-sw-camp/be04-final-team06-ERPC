import { createRouter, createWebHistory } from 'vue-router';

import Main from '@/components/main/Main.vue';

import CustomerRegist from '@/components/customer/CustomerRegist.vue';
import CustomerList from '@/components/customer/CustomerList.vue';
import CustomerContents from '@/components/customer/CustomerContents.vue';

import EstimateRegist from '@/components/estimate/EstimateRegist.vue';
import EstimateList from '@/components/estimate/EstimateList.vue';
import EstimateContents from '@/components/estimate/EstimateContents.vue';

import ContractRegist from '@/components/contract/ContractRegist.vue';
import ContractList from '@/components/contract/ContractList.vue';
import ContractContents from '@/components/contract/ContractContents.vue';

import OrderRegist from '@/components/order/OrderRegist.vue';
import OrderList from '@/components/order/OrderList.vue';
import OrderContents from '@/components/order/OrderContents.vue';

import Deposit from '@/components/bill/Deposit.vue';
import CBRequest from '@/components/bill/CBRequest.vue';
import CBList from '@/components/bill/CBList.vue';
import CBContents from '@/components/bill/CBContents.vue';

import YearPerformance from '@/components/performance/YearPerformance.vue';
import TeamPerformance from '@/components/performance/TeamPerformance.vue';

import ApprovalList from '@/components/approval/ApprovalList.vue';

import SalesOppRegist from '@/components/salesopp/SalesOppRegist.vue';
import SalesOppList from '@/components/salesopp/SalesOppList.vue';
import SalesOppContent from '@/components/salesopp/SalesOppContent.vue';

import ItemList from '@/components/logistics/ItemList.vue';
import StorageList from '@/components/logistics/StorageList.vue';

import NoticeRegist from '@/components/notice/NoticeRegist.vue';
import NoticeList from '@/components/notice/NoticeList.vue';
import NoticeContent from '@/components/notice/NoticeContent.vue';

const routes = [
    {
        path: '/',
        component: Main
    },
    {
        path: '/customer/regist',
        component: CustomerRegist
    },
    {
        path: '/customer/list',
        component: CustomerList
    },
    {
        path: '/customer/contents/:accountId',
        component: CustomerContents
    },
    {
        path: '/estimate/regist',
        component: EstimateRegist
    },
    {
        path: '/estimate/list',
        component: EstimateList
    },
    {
        path: '/estimate/contents',
        component: EstimateContents
    },
    {
        path: '/contract/regist',
        component: ContractRegist
    },
    {
        path: '/contract/list',
        component: ContractList
    },
    {
        path: '/contract/contents',
        component: ContractContents
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
    },
    {
        path: '/performance/year',
        component: YearPerformance
    },
    {
        path: '/performance/team',
        component: TeamPerformance
    },
    {
        path: '/approval/list',
        component: ApprovalList
    },
    {
        path: '/salesopp/regist',
        component: SalesOppRegist
    },
    {
        path: '/salesopp/list',
        component: SalesOppList
    },
    {
        path: '/salesopp/contents',
        component: SalesOppContent
    },
    {
        path: '/item/list',
        component: ItemList
    },
    {
        path: '/storage/list',
        component: StorageList
    },
    {
        path: '/notice/regist',
        component: NoticeRegist
    },
    {
        path: '/notice/list',
        component: NoticeList
    },
    {
        path: '/notice/:notice_id',
        component: NoticeContent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
