<script setup>
    const isActive = true;
</script>
<style scoped>
   .baseStyle {
    font: 2.5rem;
    border: 2px dashed royalblue;
   }
</style>
<template>
    <div :class="[isActive ? 'baseStyle' : '', 'text-success', 'text-center', ]">Hello</div>
</template>