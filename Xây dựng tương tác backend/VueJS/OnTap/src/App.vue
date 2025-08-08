<template>
  <div>
    <header>
      <Header></Header>
    </header>

    <body>
      <TacGiaComponent></TacGiaComponent>
    </body>
    <footer>
      <Footer></Footer>
    </footer>
  </div>


</template>

<script setup>
import Header from './layout/Header.vue';
import Footer from './layout/Footer.vue';
import TacGiaComponent from './components/TacGiaComponent.vue';
</script>

<style scoped>
div{
  width: 100%;
  height: 100%; 
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  
}
</style>
