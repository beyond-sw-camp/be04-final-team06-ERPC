<template>
    <div class="post-form">
      <h2>게시물 작성</h2>
      <form @submit.prevent="submitPost" class="form">
        <div class="form-group">
          <label for="freeTitle">제목:</label>
          <input type="text" id="freeTitle" v-model="freeTitle" required>
        </div>
        
        <div class="form-group">
          <label for="freeContent">내용:</label>
          <textarea id="freeContent" v-model="freeContent" required></textarea>
        </div>
  
        <div class="form-group">
          <label for="images">사진 업로드:</label>
          <input type="file" id="images" multiple accept="image/*" @change="handleFileChange">
        </div>
  
        <button type="submit" class="submit-btn" :disabled="submitting">게시물 작성</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  import router from '@/router/mainRouter';
  
  const freeTitle = ref('');
  const freeContent = ref('');
  const images = ref([]);
  const submitting = ref(false);
  
  const submitPost = async () => {
    if (submitting.value) return;
    submitting.value = true;
  
    try {
    // 로컬 스토리지에서 userId 가져오기
    const userId = localStorage.getItem('userId');
    if (!userId) {
      console.error('사용자 정보가 없습니다.');
      return;
    }
  
      const formData = new FormData();
      formData.append('noticeBoard', JSON.stringify({
        noticeTitle: freeTitle.value,
        noticeContent: freeContent.value,
        employee: { employeeId: userId },
        team: { teamCodeId: 1 }  
      }));
  
      if (images.value.length > 0) {
        images.value.forEach(image => {
          formData.append('files', image);
        });
      } else {
        formData.append('files', new Blob(), 'empty_image');
      }
  
      await axios.post('http://localhost:7775/notice_board/regist', formData);
  
      console.log('게시물 작성 완료');
      router.push('/notice/list');
    } catch (error) {
      console.error('게시물 작성 오류:', error);
    } finally {
      submitting.value = false;
    }
  };
  
  const handleFileChange = (event) => {
    images.value = Array.from(event.target.files);
  };
  </script>
  
  <style scoped>
    @import url('@/assets/css/notice/NoticeRegist.css');
  </style>