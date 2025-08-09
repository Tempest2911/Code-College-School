<template>
  <div>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="username">Username:</label>
        <input id="username" v-model="username" type="text" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input id="password" v-model="password" type="password" required />
      </div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { store } from '../store';

const username = ref('');
const password = ref('');
const router = useRouter();

const login = () => {
  store.isAuthenticated = true;
  alert('Login successful!');
  router.push('/dashboard');
};

const logout = () => {
  store.isAuthenticated = false;
  alert('Logged out successfully!');
  router.push('/home');
};
</script>

<style scoped>
form {
  max-width: 300px;
  margin: auto;
}

div {
  margin-bottom: 15px;
}
</style>
