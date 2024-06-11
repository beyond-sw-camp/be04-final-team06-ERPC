<template>
  <Header></Header>
  <main class="container">
    <div class="main1">
      <ul class="menu">
        <li class="menu-item" v-for="(item, index) in filteredMenuItems" :key="index">
          <div class="menu-header">
            <span class="menu-button-text">{{ item.title }}</span>
            <button class="menu-button" @click="() => { toggleSubItems(index); }">
              <img src="@/assets/img/plus.png" class="menuimage">
            </button>
          </div>
          <ul v-if="item.isOpen" class="sub-menu">
            <li v-for="(subItem, subIndex) in item.subItems" :key="subIndex">
              <RouterLink :to="subItem.path" class="sub-menu-link">{{ subItem.title }}</RouterLink>
            </li>
          </ul>
        </li>
      </ul>
    </div>
    <div class="main2">
      <RouterView />
    </div>
  </main>
  <Footer></Footer>
</template>

<script setup>
import Header from './components/main/Header.vue';
import Footer from './components/main/Footer.vue';
import { RouterView, RouterLink } from 'vue-router';
import { ref, computed } from 'vue';
import { requirePermission } from '@/components/auth';
const userId = ref(localStorage.getItem('userId'));


const menuItems = ref([
  {
    title: '공지 사항',
    subItems: [
      { title: '공지사항 작성', path: '/notice/regist' },
      { title: '공지사항 목록', path: '/notice/list' }
    ],
    isOpen: false,
  },
  {
    title: '결재 관리',
    subItems: [
      { title: '승인 요청 목록', path: '/approval' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '영업 기회',
    subItems: [
      { title: '영업 기회 등록', path: '/salesopp/regist' },
      { title: '영업 기회 목록', path: '/salesopp/list' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '품목 관리',
    subItems: [
      { title: '품목 목록', path: '/item/list' },
      { title: '창고 목록', path: '/storage/list' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '거래처 관리',
    subItems: [
      { title: '거래처 등록', path: '/customer/regist' },
      { title: '거래처 목록', path: '/customer' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '견적서 관리',
    subItems: [
      { title: '견적서 등록', path: '/estimate/regist' },
      { title: '견적서 목록', path: '/estimate' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '계약서 관리',
    subItems: [
      { title: '계약서 등록', path: '/contract/regist' },
      { title: '계약서 목록', path: '/contract' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '수주 관리',
    subItems: [
      { title: '수주 등록', path: '/order/regist' },
      { title: '수주 목록', path: '/order' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '전표 관리',
    subItems: [
      { title: '수금 조회', path: '/bill/deposit' },
      { title: 'CB 요청', path: '/bill/request' }
    ],
    isOpen: false,
    isVisible: true // 항상 보이는 항목
  },
  {
    title: '실적 관리',
    subItems: [
      { title: '연간 실적 조회', path: '/performance/year' }
    ],
    isOpen: false,
    isVisible: true
  },
  {
    title: '관리자 페이지',
    subItems: [
      { title: '사원 리스트', path: '/employees' },
      { title: '권한 신청', path: '/employees/access/apply' },
      // { title: '권한 등록', path: '/employees/access' },
      { title: '삭제 신청', path: '/delete' }
    ],
    isOpen: false,
    async isVisible() {
    try {
        const accessIds = await requirePermission(22);
        console.log('Access IDs:', accessIds);
        return accessIds.includes(22);
    } catch (error) {
        console.error('Error checking permission:', error);
        return false;
    }
}

}

]);

const toggleSubItems = (index) => {
  menuItems.value[index].isOpen = !menuItems.value[index].isOpen;
};

const filteredMenuItems = computed(() => {
  return menuItems.value.filter(item => item.isVisible !== false);
});
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
  margin-top: 20px;
  padding: 20px;
}

.main2 {
  flex: 1.8;
  min-width: 600px;
  display: flex;
  align-items: flex-start;
  justify-content: center;
  padding-bottom: 20px;
  padding-left: 20px;
  padding-right: 20px;
}

.menu {
  list-style-type: none;
  padding: 0;
  margin: 0;
  width: 100%;
}

.menu-item {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  padding: 10px 0;
  border-bottom: 1px solid #ccc;
}

.menu-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
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
  margin-left: 20px;
}

.menuimage {
  width: 15px;
  height: 15px;
}

.sub-menu {
  list-style-type: none;
  width: 156px;
  padding: 20px;
  margin: 10px 0 0 20px;
  margin-left: 1px;
  border: 2px solid #E5F7FE;
  background-color: #E5F7FE;
}

.sub-menu li {
  padding: 5px 0;
  color: #0C2092;
}

.sub-menu-link {
  text-decoration: none;
  color: #0C2092;
}

.sub-menu-link:hover {
  text-decoration: underline;
}
</style>