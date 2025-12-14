<template>
  <div class="app-container">
    <ChamCongManager v-if="isLoggedIn" />
  </div>
</template>

<script setup>
import { ref } from 'vue'; // 1. Import ref
import ChamCongManager from './components/ChamCongManager.vue';

// 2. Khai báo biến isLoggedIn và set bằng true luôn để hiện bảng
const isLoggedIn = ref(true);

</script>

<style>
/* CSS chung toàn app */
.app-container {
  padding: 20px;
}
</style>