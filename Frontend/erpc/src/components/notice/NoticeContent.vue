<template>
    <div class="wrapper">
      <div class="noticevboard">
        <div class="clickdiv" @click="mainBoard()">
          <h1 class="boardname">영업 게시판</h1> 
        </div>
      </div>
      <hr class="boardtitleLine">
      <div class="allboard">
        <div class="boardtitlediv">
          <h2 class="boardtitle">{{ notice.noticeTitle }}</h2>
        </div>
        <div class="datediv">
          <h3 class="date">작성일: {{ notice.noticeDate }}</h3>
        </div>
        <div class="writerdiv">
          <h3 class="writer">작성자: {{ notice && notice.employee ? notice.employee.employeeName : 'Unknown' }}</h3>
        </div>
        <div class="edit">
          <button type="button" @click="editPost" class="editbtn">수정</button>
        </div>
        <div class="delete">
          <button type="button" @click="deletePost" class="deletebtn">삭제</button>
        </div>
      </div>
      <hr class="titleLine">
      <div class="maincontent">
        <div v-if="notice.images && notice.images.length > 0" class="image-container">
          <img v-for="(image, index) in notice.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="notice-image">
        </div>
      </div>
      <div class="content">
        <p v-html="notice.noticeContent"></p>
      </div>
      <FreeReply/>
    </div>
  </template>
  
  <script setup>
import router from '@/router/mainRouter';
import FreeReply from "@/components/notice/FreeReply.vue";
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";

const notice = ref({});
let noticeId = null;

const setupNoticeId = () => {
  // useRoute()를 호출하기 전에 value가 존재하는지 확인
  const route = useRoute();
  if (route && route.params && route.params.noticeId) {
    noticeId = route.params.noticeId;
  } else {
    console.error('Notice ID가 존재하지 않습니다.');
  }
};

const fetchNotice = () => {
  axios.get(`http://localhost:7775/notice_board/${noticeId}`)
    .then(response => {
      notice.value = response.data;
      console.log('서버로부터 받은 정보:', response.data);
    })
    .catch(error => {
      console.error("Error fetching notice:", error);
    });
};

onMounted(() => {
  setupNoticeId(); // useRoute()를 호출하여 noticeId 설정
  if (noticeId) {
    fetchNotice();
  }
});

function mainBoard() {
  router.push('/notice_board');
}

function deletePost() {
  if (noticeId) {
    axios.patch(`http://localhost:7775/notice_board/delete/${noticeId}`)
      .then(response => {
        console.log('게시물이 성공적으로 삭제되었습니다.');
        router.push('/notice_board');
      })
      .catch(error => {
        console.error("Error deleting post:", error);
      });
  }
}

function editPost() {
  router.push("/ready");
}
  </script>
  
  <style scoped>
  @import url('@/assets/css/notice/NoticeContent.css'); 
  </style>