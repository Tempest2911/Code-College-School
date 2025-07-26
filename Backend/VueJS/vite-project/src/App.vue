<script setup>
import BoostrapButton from './components/BoostrapButton.vue';
import TopMenu from './components/TopMenu.vue';
</script>

<template>
  <div class="app">
    <TopMenu/>
    <main>
      <BoostrapButton/>
    </main>
  </div>
</template>

<style scoped>
.app{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.main{
  padding: 2rem;
}
</style>
