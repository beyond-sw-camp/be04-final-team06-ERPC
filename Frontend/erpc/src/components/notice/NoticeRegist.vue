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
      const memberIdCookie = document.cookie.split('; ')
        .find(cookie => cookie.startsWith('memberId='));
  
      if (!memberIdCookie) {
        console.error('사용자 정보가 없습니다.');
        return;
      }
  
      const memberId = memberIdCookie.split('=')[1];
      if (!memberId) {
        console.error('사용자 아이디가 없습니다.');
        return;
      }
  
      const formData = new FormData();
      formData.append('freeBoard', JSON.stringify({
        freeTitle: freeTitle.value,
        freeContent: freeContent.value,
        // member: { memberId: memberId },
      }));
  
      if (images.value.length > 0) {
        images.value.forEach(image => {
          formData.append('images', image);
        });
      } else {
        formData.append('images', new Blob(), 'empty_image');
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