<template>
    <!-- Hiển thị lời chào -->
    <h1>{{ greeting }}</h1>
    <!-- Tạo nút thay đổi nội dung -->
    <button v-bind:title="buttonTitle" @click="changeGreeting">Click để thay
        đổi</button>
</template>

<script setup>
import { ref } from 'vue';

const buttonTitle = ref('xin chào')
const greeting = ref('Lời chào ban đầu')

const changeGreeting = () => {
    buttonTitle.value = 'Hi'
    greeting.value = 'Lời chào đã được thay đổi'
} 
</script>