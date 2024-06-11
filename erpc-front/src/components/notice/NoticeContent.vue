<template>
  <div class="wrapper">
    <div class="noticevboard">
      <div class="clickdiv" @click="mainBoard()">
        <h1 class="boardname">공지사항</h1> 
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
        <button type="button" @click="confirmDelete" class="deletebtn">삭제</button>
      </div>
    </div>
    <hr class="titleLine">
    <div class="maincontent">
      <div v-if="notice.noticeFile && notice.noticeFile.length > 0" class="image-container">
        <img v-for="(image, index) in notice.noticeFile" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="notice-image">
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
const route = useRoute();

const fetchNotice = async (noticeId) => {
  try {
    const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_board/${noticeId}`);
    notice.value = response.data;
    console.log('서버로부터 받은 정보:', response.data);
  } catch (error) {
    console.error("Error fetching notice:", error);
  }
};

onMounted(() => {
  const noticeId = route.params.noticeId;
  fetchNotice(noticeId);
});

function mainBoard() {
  router.push('/notice/list');
}

async function deletePost() {
  const noticeId = route.params.noticeId;
  try {
    const response = await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_board/delete/${noticeId}`);
    console.log('게시물이 성공적으로 삭제되었습니다.');
    router.push('/notice/list');
  } catch (error) {
    console.error("Error deleting post:", error);
  }
}
function confirmDelete() {
  if (confirm('게시물을 삭제하시겠습니까?')) {
    deletePost();
  }
}
function editPost() {
  router.push({ path: `/notice/modify/${route.params.noticeId}` });
}
</script>

<style scoped>
template {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
}

button {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    background-color: #6c7aa1;
    color: rgb(229, 227, 227);
    border: 1px solid  rgb(229, 227, 227);
}

.free-board{
    display: flex;
    justify-content: left;
    width: 1100px;
    height: 70px;
}

.clickdiv {
    width: 190px;
}

.category {
    font-size: 15px;
    margin-right: 720px;
    padding-top: 17px;
    color: rgb(142, 140, 140);
}

.boardtitleLine {
    width:1110px;
    height: 3px;
    border: 0;
    background-color: #6c7aa1;
}

.titleLine {
    width:1110px;
    height: 3px;
    border: 0;
    background-color: #6c7aa1;
}

.wrapper {
    height: auto;
    min-height: 100%;
    width: 68%;
    margin-top: 50px;
    padding-bottom: 100px;
    margin-left: 20%;
    margin-right: 16%;
}

.boardname {
    font-size: 32px;
    cursor: pointer;
}

.boardtitle {
    font-size: 130%;
    margin-top: 18px;  
    margin-left: 10px;
}

.writer {
    font-size: 12px;
}

.allboard {
    display: flex;
    flex-direction: rows;
    justify-content: center;
    width: 1100px;
}

.boardtitlediv {
    padding-left: 10px;
    padding-right: 10px;
    margin-left: 0%;
    width: 500px;
}

.writerdiv {
    width:  18%;
    font-size: 13px;
    margin-top: 13px;
}
.writer {
    font-size: 16px; /* 변경된 글자 크기 */
}

.datediv {
    width:  18%;
    float:right;
    font-size: 13px;
    margin-top: 13px;
    margin-left: 30px;
}
.editbtn {
  width: 80px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;
    margin-right: 15px;
}

.deletebtn {
  width: 80px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;
    margin-right: 15px;
}

.maincontent {
    max-width: 80%;
    margin-right: 15%;
    margin-top: 3%;
}

.image-container {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(150px, 1fr)); 
    grid-auto-rows: 1fr; 
    align-items: stretch; 
    margin-bottom: 20px;
    gap: 10px;
    width: 1100px;
}

.image-container img {
    width: 100%; 
    object-fit: cover; 
    border-radius: 5px;
    border: 2px solid black;
}

.content {
    clear: both;        
    width: 1100px;
}
.image-container {
  width: 100%;
}

.notice-image {
  max-width: 100%;
  height: auto;
}
.edit, .delete {

  margin-right: 10px;
}

/* 회색 배경으로 버튼 스타일 */
.editbtn, .deletebtn {
  width: 80px;
  height: 40px;
  cursor: pointer;
  font-family: 'GmarketSansMedium';
  background-color: #6c7aa1; /* 기본 회색 배경 */
  color: white; /* 흰색 글자 */
  border: none; /* 테두리 없음 */
  border-radius: 5px; /* 모서리 둥글게 */
  transition: background-color 0.3s ease, color 0.3s ease;
}

.editbtn:hover, .deletebtn:hover {
  background-color: #5a6781; /* 호버 시 약간 어두운 회색 */
}

</style>