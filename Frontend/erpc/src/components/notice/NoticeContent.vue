<template>
    <div class="wrapper">
        <div class="freevboard">
            <div class="clickdiv" @click="mainBoard()">
                <h1 class="boardname">자유 게시판</h1> 
            </div>
        </div>
            <hr class="boardtitleLine">
        <div class="allboard" >
            <div class="boardtitlediv">
                <h2 class="boardtitle"> {{ free.freeTitle }}</h2>
            </div>
            <div class="datediv">
                <h3 class="date">작성일: {{ free.freeDate }}</h3>
            </div>
            <div class="writerdiv">
                <h3 class="writer">작성자: {{ free && free.member ? free.member.nickname : 'Unknown' }}</h3>
            </div>
            <div class="viewandlike">
                <h3 class="viewandlike">조회수: {{ free.freeViewCount }}</h3>
            </div>
            <div class="edit">
                <button type="button" @click="editPost" class="editbtn">
                    수정
                </button>
            </div>
            <div class="delete">
                <button type="button" @click="deletePost" class="deletebtn">
                    삭제
                </button>
            </div>
        </div>
        <hr class="titleLine">
        <div class="maincontent">
            <div v-if="free.images && free.images.length > 0" class="image-container">
                <img v-for="(image, index) in free.images" :key="index" :src="image.accessUrl" :alt="'Image ' + (index + 1)" class="free-image">
            </div>
        </div>
        <div class="content">
            <p v-html="free.freeContent"></p>
        </div>
        <FreeReply/>
    </div>
</template>

<script setup>
import router from '@/router/mainRouter';
import FreeReply from "@/components/notice/FreeReply.vue";
import axios from "axios";
import { ref, onBeforeMount } from "vue";
import { useRoute } from "vue-router";

const free = ref({});
const freeId = useRoute();

const fetchfree = () => {
    axios.get(`http://localhost:8081/free_board/${freeId.params.freeId}`)
    .then(response => {
        free.value = response.data;
        console.log('서버로부터 받은 정보:', response.data);
    })
    .catch(error => {
        console.error("Error fetching free:", error);
    });
}

onBeforeMount(() => {
    fetchfree();
});

function mainBoard() {
    router.push('/free_board');
}

function deletePost() {
    axios.patch(`http://localhost:8081/free_board/delete/${freeId.params.freeId}`)
    .then(response => {
        console.log('게시물이 성공적으로 삭제되었습니다.');
        router.push('/free_board');
    })
    .catch(error => {
        console.error("Error deleting post:", error);
    });
}
function editPost() {
    router.push("/ready");
}
</script>

<style scoped>
    /* @import url('@/assets/css/PostAndReply/FreePost.css'); */
</style>