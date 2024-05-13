<template>
    <div class="order-content">
        <div class="order-search">
            <h1 class="maintext">수주 정보 조회 내역</h1>
            <h3 class="maintext2">결재 완료</h3>
            <div class="order-btn">
                <button class="order-request">결재 요청</button>
                <button class="order-edit">수정</button>
                <button class="order-delete">삭제</button>
            </div>
            <div class="order-pdf">
                <button class="order-pdf1">
                    견적서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage1">
                </button>
                <button class="order-pdf2">
                    계약서 다운로드<img src="@/assets/img/pdf.png" class="pdfimage2">
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
.order-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.order-search {
    text-align: center;
    margin-top: 3%;
}

.maintext,
.maintext2 {
    display: flex;
    align-items: center;
    justify-content: center;
}

.maintext2 {
    color: #0C2092;
}

.order-btn {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 40px;
    margin-bottom: 20px;
}

.pdfimage {
    width: 50px;
    height: auto;
    cursor: pointer;
}

.order-edit,
.order-delete {
    width: 60px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;
}

.order-delete {
    margin-right: 15px;
}

.order-request {
    width: 80px;
    height: 40px;
    cursor: pointer;
    margin-left: 15px;   
}

.order-pdf {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    font-size: 17px;
    flex-grow: 1;
    padding: 10px;
    background-color: #BEE7FF;
    border: 2px solid #BEE7FF;
    border-radius: 10px;
    box-sizing: border-box;
    width: 350px;
    height: 150px;
    margin-bottom: 20px;
    font-family: GmarketSansMedium;
    font-size: 17px;
    gap: 20px;
    margin-top: 40px;
}

.order-pdf1,
.order-pdf2 {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    background-color: white;
    border: 2px solid #0C2092;
    border-radius: 10px;
    padding: 6px 30px;
    font-size: 16px;
    cursor: pointer;
    outline: none;
    color: black;
    font-weight: bold;
    width: 270px;
}

.order-pdf1:hover,
.order-pdf2:hover {
    background-color: #d5e6ff;
}

.pdfimage1,
.pdfimage2 {
    width: 30px;
    height: auto;
    margin-left: 5px;
    margin-right: -10px;
}


</style>