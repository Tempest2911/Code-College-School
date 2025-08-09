<template>
    <h1 class="text-center mt-5">Bài 4</h1>
  <div class="container mb-5 text-center">
    <LoginComponent v-if="!isLoggedIn" @loginSuccess="handleLogin" />
    <CommentComponent v-else :username="loggedInUser" />
  </div>
</template>

<script setup>
import { ref } from 'vue'
import LoginComponent from './LoginComponent.vue'
import CommentComponent from './CommentComponent.vue'

const isLoggedIn = ref(false)
const loggedInUser = ref('')

const handleLogin = (username) => {
  isLoggedIn.value = true
  loggedInUser.value = username
}
</script>
