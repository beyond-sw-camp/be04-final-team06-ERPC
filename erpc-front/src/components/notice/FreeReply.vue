<template>
  <hr class="replyregistline">
  <div class="replys">
    <div class="allreply" v-for="reply in replies" :key="reply.notice_comment_id">
      <div class="replywriterdiv">
        <span>{{ reply.employee.employeeName }}</span>
      </div>
      <div class="replycontentdiv">
        <p>{{ reply.commentContent }}</p>
      </div>
      <div class="replydatediv">
        <p>작성일: {{ reply.commentDate }}</p>
      </div>
      <div class="closebuttondiv">
        <form action="" name="deleteReply" method="post">
          <button type="button" class="closebutton2" @click="removeReply(reply.noticeCommentId)">X</button>
        </form>
      </div>
    </div>
    <div class="noreply" v-if="!replies.length">
      <p>댓글이 없습니다.</p>
    </div>
    <!-- <div class="registreplywriterdiv">
       <span>{{ reply.employee.employeeName }}</span> 
    </div>  -->
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

// 사용자 이름 추출 함수
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

// 사용자 ID 추출 함수
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

// 페이지 로드 시 실행되는 로직
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

// 댓글 등록 함수
const submitReply = async () => {
  try {
    const noticeId = router.currentRoute.value.params.noticeId;

    await getEmployeeId();

    console.log('사용되는 employeeId:', employeeId.value);
    console.log(noticeId)
    console.log(newComment.value)

    // API로 댓글 등록 요청
    const response = await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_comment/regist', {
      commentContent: newComment.value,
      employee: {
        employeeId: employeeId.value,
      },
      noticeId: noticeId  
    });

    // 등록된 댓글을 replies 배열에 추가
    replies.value.push(response.data);
    newComment.value = '';

    // 댓글을 등록한 후 페이지를 새로고침합니다.
    location.reload();
  } catch (error) {
    console.error('댓글 작성 중 에러 발생:', error);
  }
};

// 사용자 ID 가져오는 함수
const getEmployeeId = async () => {
  const userId = localStorage.getItem('userId');
  console.log('onMounted에서 가져온 userId:', userId);
  if (userId) {
    employeeId.value = userId; 
  } else {
    console.error('로컬 스토리지에서 사용자 ID를 가져올 수 없습니다.');
  }
};
  
// 댓글 삭제 함수
const removeReply = async (commentId) => {
  try {
    if (confirm('댓글을 삭제하시겠습니까?')) {
      await axios.patch(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_comment/delete/${commentId}`);
      // 삭제된 댓글을 화면에서 제거
      replies.value = replies.value.filter(reply => reply.noticeCommentId !== commentId);
    }
  } catch (error) {
    console.error('댓글 삭제 중 에러 발생:', error);
  }
};
  
// 페이지 로드 시 실행되는 로직
onMounted(async () => {
  try {
    const noticeId = router.currentRoute.value.params.noticeId;
    // 해당 공지에 대한 댓글 가져오기
    const response = await axios.get(`http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_comment`);
    // 가져온 댓글을 화면에 표시
    replies.value = response.data.filter(comment => parseInt(comment.noticeId) === parseInt(noticeId));
    console.log('서버로부터 받은 정보:', replies.value);
  } catch (error) {
    console.error('데이터 가져오는 중 에러 발생:', error);
  }
});
</script>

<style scoped>
textarea {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
    padding-left: 10px;
    padding-top: 5px;
}
.btn {
    font-family: 'GmarketSansMedium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
}

.box {
    width: 1000px; 
    max-width: 1000px; 
    height: 100px;
    max-height: 100px;
    /* margin-left: 250px; */
}



.btn {
    background-color: #6c7aa1;
    color: white;
    padding: 14px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
    font-style: bold;
    margin-left: auto; 
    display: block; 
}

.replyregistline, .replyregistbottomline {
    width: 1100px;
    height: 3px;
    border: 0;
    background-color: #6c7aa1;
    margin-bottom: 30px;
}

.new-replyline{
    width: 1100px; 
    height: 2px;
    background-color: gray; 
    margin-right: auto; 
}

.replyregistline, .replyregistbottomline {
    margin-top: 3%;
}

.allreply {
    display: flex;
    align-items: center; 
    width: 1000px;
    font-size: 15px;
    margin-top: 10px;
    margin-bottom: 10px;
    margin-left: 20px;
    border-bottom: 1px solid #6c7aa1dc;
    padding-left: 60px;
}

.replywriterdiv {
    margin-left: 30px;
}

.replydatediv{
    margin-left: 110px;
}

.closebuttondiv {
    padding-right: 100px;
}

.replywriterdiv {
    flex-basis: 11%; 
}

.replycontentdiv {
    flex-basis: 60%;
    margin-left: 100px;   
}

.replydatediv {
    flex-basis: 20%;
    font-size: 12px;
    margin-left: 100px;
}

.closebuttondiv {
    flex-basis: 10%;
    justify-content: flex-end;
}

.noreply {
    text-align: center;
    font-size: 20px;
    margin-top: 50px;
    margin-left: 250px
}

.registreplywriterdiv, .registreplydiv {
    float: none;
    clear: both;
    width: 100%;
}

.registreplywriterdiv {
    margin-left: 80px;
    margin-top: 30px;
}

.registreplydiv {
    display: flex;
    justify-content: center; 
    margin-top: 10px;
}

textarea::placeholder {
    font-size: 12px;
}
.closebutton2 {
    background-color: white; /* 삭제 버튼 배경색을 흰색으로 설정 */
    color: black; /* 삭제 버튼 글자색을 검은색으로 설정 */
    border: none;
    cursor: pointer;
    font-size: 15px;
    padding: 5px 10px;
}

.closebutton2:hover {
    background-color: #ccc; /* 삭제 버튼에 마우스를 올렸을 때 배경색을 회색으로 변경 */
}

</style >