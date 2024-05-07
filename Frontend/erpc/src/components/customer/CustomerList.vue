<template>
    <div class="list-content">
        <div class="customer-list">
            <h1>거래처 목록</h1>
        </div>
        <div class="list-box">
            <p>목록</p>
        </div>
    </div>
</template>

<script setup>

</script>

<style>
.list-content {
    margin-top: 4%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
}

.customer-list {
    text-align: center;
    margin-top: 3%;
}

.list-box {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin: 15px;
    margin-bottom: 100px;
    border-radius: 10px;
    border: 2px solid #ccc;
    box-sizing: border-box;
    background-color: whitesmoke;
    height: auto;
    width: 100%;
    max-width: 1400px;
    margin: 20px auto;
    gap: 1px;
}
</style>