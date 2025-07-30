<template>
  <div class="card shadow p-4 mx-auto" style="max-width: 400px;">
    <h4 class="text-center text-primary mb-3">Đăng nhập</h4>
    <form @submit.prevent="handleLogin">
      <input class="form-control mb-2" v-model="username" placeholder="Tên đăng nhập" required />
      <input class="form-control mb-3" v-model="password" type="password" placeholder="Mật khẩu" required />
      <button class="btn btn-primary w-100">Đăng nhập</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const emit = defineEmits(['loginSuccess'])

const username = ref('')
const password = ref('')

const handleLogin = () => {
  if (username.value && password.value) {
    emit('loginSuccess', username.value)
  }
}
</script>
