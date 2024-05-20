<template>
    <div>
      <table class="table">
        <tr class="top">
            <td class="post" colspan="3">
            <h1 class="boardTitle">공지사항</h1>
          </td>
          <td></td>
          <td class="thing">
            <button @click="goToWritePage" class="writebutton">게시글 작성</button>
          </td>
        </tr>
        <tr class="header2">
            <td></td>
          <td class="search" id="search-1">
            <div id="searchDropdown">
              <select class="form-control" name="search_type" v-model="search_type"
                style="height: 30px; font-size: 12px;">
                <option value="titleContent">제목 내용</option>
                <option value="title">제목</option>
                <option value="content">내용</option>
                <option value="writerId">작성자</option>
              </select>
            </div>
            <div>
              <input type="text" id='searchText' class="form-control" placeholder="Search..."
                v-model="search_condition" @keyup.enter="callData">
            </div>
          </td>
        </tr>
        <tr class="header1">
          <td class="num">게시글 번호</td>
          <td>게시글 제목</td>
          <td>조회수</td>
          <td>작성자</td>
          <td>작성일자</td>
        </tr>
        <tbody>
    <tr v-for="(item, index) in filteredfree" :key="item.freeId" class="allpost" @click="changeRouter(item.freeId)">
      <td>{{ item.freeId}}</td>
      <td class="boardname">{{ item.freeTitle }}</td>
      <td>{{ item.freeViewCount }}</td>
      <td>{{ item.member.nickname }}</td>
      <td>{{ item.freeDate }}</td>
    </tr>
  </tbody>
      </table>
    </div>
  </template>
  
  <script setup>
  import { onMounted, ref, watch } from "vue";
  // import axios from "axios";
  import router from "@/router/mainRouter";
  
  const free = ref([]);
  const index = 1;
  const search_condition = ref("");
  const search_type = ref("titleContent");
  const filteredfree = ref([]);
  
  const fetchfree = () => {
    axios.get(`http://localhost:8081/free_board/list`)
      .then(response => {
        free.value = response.data;
        filteredfree.value = response.data; 
        console.log(free.value);
      })
      .catch(error => {
        console.error("Error fetching free:", error);
      });
  };
  
  onMounted(() => {
    fetchfree();
  });
  
  function changeRouter(freeId) {
    router.push(`/free_board/${freeId}`);
  }
  
  function goToWritePage(){
    router.push(`/free_board/regist`);
  }

  watch([search_condition, search_type], () => {
    if (search_condition.value && search_type.value) {
      filterfree();
    } else {
      filteredfree.value = free.value;
    }
  });
  
  function filterfree() {
    const condition = search_condition.value.toLowerCase();
    const type = search_type.value;
  
    if (type === "titleContent") {
      filteredfree.value = free.value.filter(item =>
        item.freeTitle.toLowerCase().includes(condition) ||
        item.freeContent.toLowerCase().includes(condition)
      );
    } else if (type === "title") {
      filteredfree.value = free.value.filter(item =>
        item.freeTitle.toLowerCase().includes(condition)
      );
    } else if (type === "content") {
      filteredfree.value = free.value.filter(item =>
        item.freeContent.toLowerCase().includes(condition)
      );
    } else if (type === "writerId") {
      filteredfree.value = free.value.filter(item =>
        item.member.nickname.toLowerCase().includes(condition)
      );
    }
  }
  </script>
  
  <style scoped>
    /* @import url('@/assets/css/PostMain/freePostMain.css'); */
  </style>