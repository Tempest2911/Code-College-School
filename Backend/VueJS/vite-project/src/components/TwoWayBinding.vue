<template>
    <div class="container">
        <input type="text" v-model="text" lapceholder="Enter text here" />
        <p>Xin chao ban: {{ text }}</p>
    </div>
</template>
<script setup>
import { ref } from 'vue';
const text = ref('VueJS');
</script>