<template>
    <p>Name: <input type="text" v-bind:value="name""></input></p>
    <a class="btn btn-primary" v-bind:href="url">Click me</a>
</template>

<script setup>
const name = 'Vue';
const url = 'roblox.com';

</script>