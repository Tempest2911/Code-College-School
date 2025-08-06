<script setup>
import { ref } from 'vue';
const isActive = ref(true);
const hasError = ref(true);

</script>

<template>
    <div v-bind:class="{active:isActive, 'text-danger':hasError}">Hello</div>
</template>