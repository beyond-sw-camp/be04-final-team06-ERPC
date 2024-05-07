<template>
    <header class="header">
        <div class="logo">
            <img src="@/assets/img/logo.png" class="logoimage">
        </div>
    </header>
</template>

<script setup>

</script>

<style>
* {
    font-family: 'GmarketSansMedium';
}

body {
    margin: 0 !important;
}

.header {
    display: flex;
    justify-content: center; /* 이 부분을 center로 변경 */
    align-items: center;
    padding: 25px 50px;
    width: 100vw;
    box-sizing: border-box;
    border-bottom: 3px solid #0C2092;
}


.logo .logoimage {
    display: flex;
    align-items: center;
    width: 100px;
    height: auto;
    cursor: pointer;
}
</style>