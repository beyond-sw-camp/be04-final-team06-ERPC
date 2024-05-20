<template>
    <div class="mainpage">
        <div class="mainbanner">
            <img src="@/assets/img/mainbanner.png" class="mainbannerimage" />
            <div class="mainbannertxt">함께 만드는 건강한 영업 환경</div>
            <p class="mainbannertxt2">모든 구성원들의 건강한 몸과 마음은 최고의 가치입니다.</p>
            <p class="mainbannertxt3">모든 직원분들께서 격려와 배려, 열린 의사소통으로</p>
            <p class="mainbannertxt4">서로 존중하는 사내 문화를 지키는 약속에 함께 동참하여 주시기를 당부 드립니다.</p>
        </div>
        <div class="mainbanner2">
            <div class="inmainbanner">
                <img src="@/assets/img/mainbanner5.jpg" class="mainbannerimage2" />
            </div>
            <div class="inmainbanner2">
                <div class="login-container">
                    <h1>ERPC</h1>
                    <label for="employee-id">Employee ID</label>
                    <input type="text" id="employee-id" name="employee-id">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password">
                    <button type="submit">로그인</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
</script>

<style>

.mainpage {
    display: flex;
    flex-direction: column;
    align-items: center;
    max-width: 1200px;
    margin: auto;
    padding: 15px;
}

.mainbanner {
    position: relative;
    width: 1230px;
    height: 240px;
    margin-bottom: 20px;
}

.mainbannerimage {
    width: 100%;
    height: 100%;
    padding: 15px 10px;
    border-radius: 30px;
}

.mainbannertxt {
    position: absolute;
    top: 50%;
    left: 30%;
    transform: translate(-50%, -50%);
    color: white;
    font-size: 40px;
    font-weight: bold;
    text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}

.mainbannertxt2, .mainbannertxt3, .mainbannertxt4 {
    position: absolute;
    color: white;
    font-weight: bold;
}

.mainbannertxt2 {
    top: 61%;
    left: 29%;
    transform: translate(-50%, -50%);
    font-size: 20px;
}

.mainbannertxt3 {
    top: 74%;
    left: 23.7%;
    transform: translate(-50%, -50%);
    font-size: 16px;
}

.mainbannertxt4 {
    top: 83%;
    left: 32.2%;
    transform: translate(-50%, -50%);
    font-size: 16px;
}

.mainbanner2 {
    position: relative;
    width: 1230px;
    height: 300px;
    margin: auto;
}

.mainbanner2 {
    display: flex;
    align-items: center;
    width: 1210px;
    height: 100%;
    margin-left: -5px;
    margin-top: 20px;
    padding: 1px 10px;
    background-color: #CCEAFF;
    border-radius: 17px;
}

.mainbannerimage2 {
    width: 550px;
    height: 320px;
    padding: 15px 10px;
    border-radius: 30px;
    margin: 10px;
    margin-top: 17px;
}

.inmainbanner2 {
    width: 565px;
    height: 285px;
    padding: 15px 10px;
    border-radius: 20px;
    margin: 10px;
    background-color: white;
    border: 3px solid #0C2092;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-container {
    text-align: center;
}

.login-container h1 {
    font-size: 24px;
    color: #0C2092;
    margin-bottom: 10px;
    margin-top: -5px;
}

.login-container label {
    display: block;
    margin: 10px 0 5px;
    font-size: 16px;
    color: #0C2092;
}

.login-container input {
    width: 80%;
    padding: 10px;
    margin-bottom: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.login-container button {
    width: 85%;
    padding: 10px;
    background-color: #0C2092;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
}

.login-container button:hover {
    background-color: #051A80;
}


</style>