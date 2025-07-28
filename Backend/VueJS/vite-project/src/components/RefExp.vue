<template>
    <div class="container">
        <button class="btn btn-primary mb-3" @click="count++">You click {{ count }} times</button>
    </div>

</template>

<script setup>
import { ref } from 'vue';
const count = ref(0);
</script>