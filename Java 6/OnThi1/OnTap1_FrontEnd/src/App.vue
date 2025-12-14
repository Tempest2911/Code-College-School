<template>
  <div class="app-container">
    <DonHangManager v-if="isLoggedIn" @logout="handleLogout" />

    <LoginForm v-else @login-success="handleLoginSuccess" />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import DonHangManager from './components/DonHangManager.vue';
import LoginForm from './components/LoginForm.vue';

const isLoggedIn = ref(false);

const checkLoginStatus = () => {
  // Kiểm tra xem trong LocalStorage đã có thông tin user chưa
  const auth = localStorage.getItem('user_auth');
  isLoggedIn.value = !!auth; // Nếu có auth thì = true, ngược lại false
};

const handleLoginSuccess = () => {
  isLoggedIn.value = true;
};

const handleLogout = () => {
  isLoggedIn.value = false;
};

onMounted(() => {
  checkLoginStatus();
});
</script>

<style>
/* CSS chung toàn app */
</style>