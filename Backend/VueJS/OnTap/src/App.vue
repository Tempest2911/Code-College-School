<script setup>
import Header from './layout/Header.vue'
import Footer from './layout/Footer.vue'
</script>

<template>
  <Header></Header>
  
  <nav class="nav bg-light p-2">
    <router-link to="/tac-gia" class="nav-link">Tác Giả</router-link>
    <router-link to="/page" class="nav-link">Page</router-link>
  </nav>

  <router-view />

  <Footer></Footer>
</template>

<style scoped>
.nav-link {
  margin-right: 10px;
}
</style>
