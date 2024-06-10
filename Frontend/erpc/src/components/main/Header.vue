<template>
    <header class="header">
        <div class="logo">
            <img src="@/assets/img/logo2.png" class="logoimage"  @click="main">
        </div>
    </header>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { inject, onMounted } from 'vue';

const router = useRouter();

function main() {
    router.push("/");
}

</script>

<style>
    @import url('@/assets/css/main/Header.css');
</style>