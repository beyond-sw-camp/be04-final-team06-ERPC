<template>
  <div>
    <table class="table">
      <!-- 테이블 헤더 -->
      <tr class="top">
        <td class="post" colspan="3">
          <h1 class="boardTitle">공지사항</h1>
        </td>
        <td></td>
      </tr>
      <tr class="header2">
        <td></td>
        <td class="search" id="search-1">
          <!-- 검색 드롭다운 및 입력 -->
          <div id="searchDropdown">
            <select class="form-control" name="search_type" v-model="search_type" style="height: 30px; font-size: 12px;">
              <option value="titleContent">제목 내용</option>
              <option value="title">제목</option>
              <option value="content">내용</option>
              <option value="writerId">작성자</option>
            </select>
          </div>
          <div>
            <input type="text" id='searchText' class="form-control" placeholder="Search..."
              v-model="search_condition" @keyup.enter="search"
              style="height: 24px; font-size: 12px;">
            <button @click="search" class="searchButton">검색</button>
          </div>
        </td>
      </tr>
      <tr class="header1">
        <td class="num">번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성부서</td>
        <td>작성일자</td>
      </tr>
      <!-- 페이징 처리된 공지사항 목록 -->
      <tbody>
        <tr v-for="(item, index) in paginatedNotice" :key="item.noticeId" class="allpost" @click="changeRouter(item.noticeId)">
          <td>{{ index + 1 }}</td>
          <td class="boardname">{{ item.noticeTitle }}</td>
          <td>{{ item.employee.employeeName }}</td>
          <td>{{ item.team.teamCode }}</td>
          <td>{{ item.noticeDate }}</td>
        </tr>
      </tbody>
    </table>
    <!-- 페이지네이션 -->
    <div class="pagination">
      <button @click="changePage(currentPage - 1)" :disabled="currentPage === 1">이전</button>
      <button v-for="page in totalPages" :key="page" @click="changePage(page)" :class="{ active: currentPage === page }">{{ page }}</button>
      <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages">다음</button>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, watch, computed } from "vue";
import axios from "axios";
import router from "@/router/mainRouter";

const notice = ref([]);
const search_condition = ref("");
const search_type = ref("titleContent");
const filteredNotice = ref([]);

// 페이지 관련 변수
const itemsPerPage = 10;
const currentPage = ref(1);

onMounted(() => {
  fetchData();
});

function fetchData() {
  axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_board`)
    .then(response => {
      notice.value = response.data;
      filteredNotice.value = response.data;
    })
    .catch(error => {
      console.error("Error fetching notice:", error);
    });
}

function changeRouter(noticeId) {
  router.push(`/notice/${noticeId}`);
}

// 검색 기능
function search() {
  filterNotice();
}

watch([search_condition, search_type], () => {
  if (search_condition.value && search_type.value) {
    filterNotice();
  } else {
    filteredNotice.value = notice.value;
  }
});

function filterNotice() {
  const condition = search_condition.value.toLowerCase();
  const type = search_type.value;

  if (type === "titleContent") {
    filteredNotice.value = notice.value.filter(item =>
      item.noticeTitle.toLowerCase().includes(condition) ||
      item.noticeContent.toLowerCase().includes(condition)
    );
  } else if (type === "title") {
    filteredNotice.value = notice.value.filter(item =>
      item.noticeTitle.toLowerCase().includes(condition)
    );
  } else if (type === "content") {
    filteredNotice.value = notice.value.filter(item =>
      item.noticeContent.toLowerCase().includes(condition)
    );
  } else if (type === "writerId") {
    filteredNotice.value = notice.value.filter(item =>
      item.employee.employeeName.toLowerCase().includes(condition)
    );
  }

  // 페이지를 첫 페이지로 변경
  currentPage.value = 1;
}

// 페이징 처리된 공지사항 목록
const paginatedNotice = computed(() => {
  const startIndex = (currentPage.value - 1) * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  return filteredNotice.value.slice(startIndex, endIndex);
});

// 전체 페이지 수 계산
const totalPages = computed(() => {
  return Math.ceil(filteredNotice.value.length / itemsPerPage);
});

// 페이지 변경 함수
function changePage(page) {
  if (page > 0 && page <= totalPages.value) {
    currentPage.value = page;
  }
}
</script>

<style scoped>
@import url('@/assets/css/notice/NoticeList.css');
</style>
  
  <style scoped>
     @import url('@/assets/css/notice/NoticeList.css'); 
  </style>