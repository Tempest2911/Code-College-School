<template>
  <div class="app-container">
    <NhanVienManager v-if="isLoggedIn" />
  </div>
</template>

<script setup>
import { ref } from 'vue'; // 1. Import ref
import NhanVienManager from './components/NhanVienManager.vue';

const isLoggedIn = ref(true);

</script>

<style>
/* CSS chung toàn app */
.app-container {
  padding: 20px;
}
</style>