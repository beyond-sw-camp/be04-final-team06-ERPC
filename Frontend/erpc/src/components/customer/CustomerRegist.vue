<template>
    <div class="search-content">
        <div class="customer-search">
            <h1>사업자 신용 정보 조회</h1>
        </div>
        <div class="search-box">
            <div class="business-number">
                <p class="number-text">사업자 번호</p>
                <input type="text" id="number-box" class="number-box" placeholder="사업자 번호를 입력해주세요.">
            </div>
            <div class="search-btn-div">
                <button class="search-btn">조회하기</button>
            </div>
        </div>
        <div class="search-box2"></div>
    </div>
</template>

<script setup>

</script>

<style>
.search-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    border-radius: 10px;
    border: 2px solid #ccc;
    box-sizing: border-box;
    background-color: whitesmoke;
    height: 350px;
    width: 100%;
    max-width: 450px; 
    margin: 20px auto;
    gap: 15px;
}

.customer-search {
    text-align: center;
    margin-top: 6%;
}

.search-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    /* margin: 15px; */
    margin-bottom: 100px;
}

.number-text {
    min-width: 50px;
    margin-right: 10px;
    margin-bottom: 2px;
}

.number-box {
    flex-grow: 1;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
    width: 320px;
}

.search-btn-div {
    display: flex;
    justify-content: center;
    width: 100%;
}

.search-btn {
    width: 320px;
    padding: 10px 0;
    border: none;
    border-radius: 10px;
    background-color: #0C2092;
    color: white;
    cursor: pointer;
    transition: background-color 0.3s ease;
}


</style>