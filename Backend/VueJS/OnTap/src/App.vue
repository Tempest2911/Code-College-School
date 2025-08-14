<script setup>
import Header from './layout/Header.vue'
import Footer from './layout/Footer.vue'
import { ref } from 'vue';
import TacGiaManager from './components/TacGiaManager.vue';
</script>

<template>
  <header> <Header></Header> </header>
  <body>
    <TacGiaManager />
    
  </body>
  <footer> <Footer></Footer> </footer>
</template>

<style scoped></style>
