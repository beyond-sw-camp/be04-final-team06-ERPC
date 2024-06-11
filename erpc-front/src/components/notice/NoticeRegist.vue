<template>
  <div class="post-form">
    <h2 class="title">공지사항 등록</h2>

    <form @submit.prevent="submitPost" class="form">
      <div class="form-group">
        <label for="freeTitle" class="label">제목</label>
        <input type="text" id="freeTitle" v-model="freeTitle" class="input" required>
      </div>
      <div class="form-group">
        <label for="freeContent" class="label">내용</label>
        <textarea id="freeContent" v-model="freeContent" class="textarea" required></textarea>
      </div>

      <div class="file-upload-area">
        <label for="images" class="file-upload-label">
          <i class="fas fa-cloud-upload-alt"></i> 첨부 파일 추가
        </label>
        <input type="file" id="images" multiple accept="image/*" @change="handleFileChange" hidden>
        <div v-if="images.length > 0" class="file-list">
          <div v-for="(image, index) in images" :key="index" class="file-item">
            {{ image.name }}
            <button @click="removeImage(index)" class="remove-btn">삭제</button>
          </div>
        </div>
      </div>

      <button type="submit" class="submit-btn" :disabled="submitting">등록</button>
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
      submitting.value = false;
      return;
    }

    const formData = new FormData();
    formData.append('noticeBoard', JSON.stringify({
      noticeTitle: freeTitle.value,
      noticeContent: freeContent.value,
      employee: { employeeId: userId },
      team: { teamCodeId: 1 }
    }));

    // 프록시 객체를 실제 배열로 변환하여 FormData에 추가
    const actualImagesArray = Array.from(images.value);
    if (actualImagesArray.length > 0) {
      actualImagesArray.forEach(image => {
        console.log(`Appending file: ${image.name}`);
        formData.append('files', image);
      });
    } else {
      // 이미지가 없을 경우 빈 파일 추가
      formData.append('files', new Blob(), 'empty_image');
    }

    await axios.post('http://erpc-back-ver2-env.eba-3inzi7ji.ap-northeast-2.elasticbeanstalk.com/notice_board/regist', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    console.log('게시물 작성 완료');
    router.push('/notice/list');
  } catch (error) {
    console.error('게시물 작성 오류:', error.response ? error.response.data : error.message);
  } finally {
    submitting.value = false;
  }
};

const handleFileChange = (event) => {
  // input[type="file"]에서 파일을 가져와 images 배열에 저장
  images.value = Array.from(event.target.files);
  console.log('Files selected:', images.value);
};
</script>

<style scoped>
.post-form {
  width: 800px; 
  margin: 40px auto; 
  padding: 30px;
  background-color: #fff; /* 흰색 배경 */
  border: 1px solid #ddd;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

.post-form h2 {
  text-align: center;
  margin-bottom: 30px;
  font-size: 28px; /* 제목 크기 조절 */
  color: #333;
}

.form-group {
  margin-bottom: 25px;
}

.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 600; /* 폰트 굵기 조절 */
  color: #444; /* 진한 회색 */
}

.form-group input[type="text"],
.form-group textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box; /* 테두리 포함하여 너비 계산 */
  font-size: 16px;
}

.form-group textarea {
  height: 200px;
  resize: vertical;
}

.form-group input[type="file"] {
  display: block;
}

/* 파일 입력 스타일 */
.file-input-wrapper {
  display: flex;
  align-items: center;
}

.file-input-label {
  background-color: #f5f5f5; /* 연한 회색 배경 */
  padding: 10px 15px;
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px; /* 파일 이름과 간격 */
}

.file-input-name {
  font-size: 14px;
  color: #777; /* 연한 회색 */
}

.submit-btn {
  display: block; /* 블록 요소로 변경 */
  width: auto; 
  margin: 0 auto; /* 가운데 정렬 */
  padding: 12px 20px;
  font-size: 18px;
  background-color: #0C2092; 
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}
.post-form {
  max-width: 1200px; /* 폼의 최대 너비를 1200px로 설정 */
  background-color: #f0f8ff; /* 하늘색 배경 */
}

.file-upload-area {
  border: 2px dashed #007bff; 
  padding: 40px;
  text-align: center;
  margin-bottom: 20px;
  border-radius: 8px; 
}


.remove-btn {
  background-color: #e74c3c; /* 빨간색 */
  color: white;
  border: none;
  border-radius: 4px;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.remove-btn:hover {
  background-color: #c0392b; /* 어두운 빨간색 */
}
</style>