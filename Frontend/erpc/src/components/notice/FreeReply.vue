<template>
    <hr class="replyregistline">
    <div class="replys">
      <div class="allreply" v-for="reply in replies" :key="reply.notice_comment_id" >
        <div class="replywriterdiv">
          <span v-if="reply.member">{{ reply.member.nickname }}</span>
        </div>
        <div class="replycontentdiv">
          <p>{{ reply.comment_content }}</p>
        </div>
        <div class="replydatediv">
          <p>작성일: {{ reply.comment_date }}</p>
        </div>
        <div class="closebuttondiv">
          <form action="" name="deleteReply" method="post">
            <button type="button" class="closebutton" @click="removeReply(reply.comment_id)">
            </button>
          </form>
        </div>
      </div>
      <div class="noreply" v-if="!replies.length">
        <p>댓글이 없습니다.</p>
      </div>
      <div class="registreplywriterdiv">
        <span>{{ nickname }}</span>
      </div>
      <div class="registreplydiv">
        <form id="comment" @submit.prevent="submitReply">
          <div class="commentbox">
            <textarea type="text" class="box" id="reply" v-model="newComment" placeholder="타인의 권리를 침해하거나 명예를 훼손하는 댓글은 운영원칙 및 관련 법률에 의거, 제재를 받을 수 있습니다." required></textarea>
          </div>
          <input type="submit" class="btn" value="등록">
        </form>
      </div>
    </div>
  </template>
  
<script setup>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const replies = ref([]);
const newComment = ref('');

const extractNicknameFromCookie = () => {
  try {
    const nicknameCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('nickname='));
    if (!nicknameCookie) {
      console.error('사용자 닉네임 정보가 없습니다.');
      return null;
    }

    const nicknameValue = nicknameCookie.split('=')[1];
    if (!nicknameValue) {
      console.error('사용자 닉네임이 없습니다.');
      return null;
    }

    return decodeURIComponent(nicknameValue);
  } catch (error) {
    console.error('사용자 닉네임 추출 중 에러 발생:', error);
    return null;
  }
};

const nickname = ref(extractNicknameFromCookie());

onMounted(() => {
  nickname.value = extractNicknameFromCookie();
});

const submitReply = async () => {
  try {
    const memberIdCookie = document.cookie.split('; ').find(cookie => cookie.startsWith('memberId='));
    if (!memberIdCookie) {
      console.error('사용자 정보가 없습니다.');
      return;
    }

    const memberId = memberIdCookie.split('=')[1];
    if (!memberId) {
      console.error('사용자 아이디가 없습니다.');
      return;
    }

    const freeId = router.currentRoute.value.params.freeId;

    const response = await axios.post('http://localhost:7075/notice_comment/regist', {
      commentContent: newComment.value,
      member: { memberId },
      freeId:  freeId
    });

    replies.value.push(response.data);
    newComment.value = '';
  } catch (error) {
    console.error('댓글 작성 중 에러 발생:', error);
  }
};

const removeReply = async (commentId) => {
  try {
    if (confirm('댓글을 삭제하시겠습니까?')) {
      await axios.patch(`http://localhost:7075/notice_comment/delete/${noticeCommentId}`,{
        noticeCommentId: noticeCommentId
      });
      
      replies.value = replies.value.filter(reply => reply.notice_comment_id !== notice_comment_id);
    }
  } catch (error) {
    console.error('댓글 삭제 중 에러 발생:', error);ff 
  }
};

onMounted(async () => {
  try {
    const freeId = router.currentRoute.value.params.freeId;
    
    const response = await axios.get(`http://localhost:7075/free_comment/?freeId=${freeId}`);
    replies.value = response.data.filter(comment => {
  const commentfreeId = parseInt(comment.freeId);
  const routerfreeId = parseInt(freeId);
  
  return commentfreeId === routerfreeId;
    });

console.log('서버로부터 받은 정보:', replys.value);
  } catch (error) {
    console.error('데이터 가져오는 중 에러 발생:', error);
  }
});
</script>

<style scoped>
  /* @import url('@/assets/css/PostAndReply/Reply.css'); */
</style>