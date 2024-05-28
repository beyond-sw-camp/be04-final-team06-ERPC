<template>
    <hr class="replyregistline">
    <div class="replys">
      <div class="allreply" v-for="reply in replies" :key="reply.notice_comment_id">
        <div class="replywriterdiv">
          <span v-if="reply.employee">{{ reply.employee.employeeName }}</span>
        </div>
        <div class="replycontentdiv">
          <p>{{ reply.comment_content }}</p>
        </div>
        <div class="replydatediv">
          <p>작성일: {{ reply.comment_date }}</p>
        </div>
        <div class="closebuttondiv">
          <form action="" name="deleteReply" method="post">
            <button type="button" class="closebutton" @click="removeReply(reply.comment_id)"></button>
          </form>
        </div>
      </div>
      <div class="noreply" v-if="!replies.length">
        <p>댓글이 없습니다.</p>
      </div>
      <div class="registreplywriterdiv">
        <span>{{ employeeName }}</span>
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
  const employeeName = ref('');
  const employeeId = ref('');
  
  const extractEmployeeNameFromLocalStorage = () => {
    try {
      const name = localStorage.getItem('employeeName');
      if (!name) {
        console.error('사용자 닉네임 정보가 없습니다.');
        return null;
      }
      return name;
    } catch (error) {
      console.error('사용자 닉네임 추출 중 에러 발생:', error);
      return null;
    }
  };
  
  const extractUserIdFromLocalStorage = () => {
    try {
      const id = localStorage.getItem('userId');
      if (!id) {
        console.error('사용자 ID 정보가 없습니다.');
        return null;
      }
      return id;
    } catch (error) {
      console.error('사용자 ID 추출 중 에러 발생:', error);
      return null;
    }
  };
  
  onMounted(() => {
    const name = extractEmployeeNameFromLocalStorage();
    console.log('onMounted에서 가져온 employeeName:', name);
    if (name) {
      employeeName.value = name;
    } else {
      localStorage.setItem('employeeName', 'null');
      employeeName.value = 'null';
    }
  
    const userId = extractUserIdFromLocalStorage();
    console.log('onMounted에서 가져온 userId:', userId);
    if (userId) {
      employeeId.value = userId;
    } else {
      console.error('로컬 스토리지에서 사용자 ID를 가져올 수 없습니다.');
    }
  });
  
  const submitReply = async () => {
    try {
      const noticeId = router.currentRoute.value.params.noticeId;
      console.log('submitReply에서 사용되는 employeeName:', employeeName.value);
  

      await getEmployeeId();
  
      console.log('사용되는 employeeId:', employeeId.value);
      console.log(noticeId)
      console.log(newComment.value)
  
      const response = await axios.post('http://localhost:7775/notice_comment/regist', {
        commentContent: newComment.value,
        employee: {
                employeeId: employeeId.value,
               
            },
        noticeId: noticeId  
      });
  
      replies.value.push(response.data);
      newComment.value = '';
    } catch (error) {
      console.error('댓글 작성 중 에러 발생:', error);
    }
  };
  
  // employeeId 값을 가져오는 비동기 함수
  const getEmployeeId = async () => {
    const userId = localStorage.getItem('userId');
    console.log('onMounted에서 가져온 userId:', userId);
    if (userId) {
      employeeId.value = userId; 
    } else {
      console.error('로컬 스토리지에서 사용자 ID를 가져올 수 없습니다.');
  
    }
  };
    
  const removeReply = async (commentId) => {
    try {
      if (confirm('댓글을 삭제하시겠습니까?')) {
        await axios.patch(`http://localhost:7775/notice_comment/delete/${commentId}`);
        replies.value = replies.value.filter(reply => reply.noticeCommentId !== commentId);
      }
    } catch (error) {
      console.error('댓글 삭제 중 에러 발생:', error);
    }
  };
    
  onMounted(async () => {
    try {
      const noticeId = router.currentRoute.value.params.noticeId;
      const response = await axios.get(`http://localhost:7775/notice_comment`);
      replies.value = response.data.filter(comment => parseInt(comment.noticeId) === parseInt(noticeId));
      console.log('서버로부터 받은 정보:', replies.value);
    } catch (error) {
      console.error('데이터 가져오는 중 에러 발생:', error);
    }
  });
  </script>
  
  <style scoped>
  /* 스타일 시트 */
  </style>