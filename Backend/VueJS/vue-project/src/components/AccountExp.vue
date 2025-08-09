<template>
    <h4>Name: {{ user.name }}</h4>
    <p>Age: {{ user.age }}</p>
    <p>Active: {{ user.isActive }}</p>
    <p>Class: {{ user.class }}</p>
    <p>Address: {{ user.address }}</p>
    <p>Phone: {{ user.phone }}</p>
    <button class="btn btn-danger" @click="updateUser">Update User</button>

</template>

<script setup>
import { reactive } from 'vue';
const user = reactive({ name: 'Nam', age: 20, isActive: true, class: '10A1', address: '123 Main St', phone: '0123456789' })
const updateUser = () => {
    user.name = 'Nguyen Van A';
    user.age = 21;
    user.isActive = false;
    user.class = '10A2';
    user.address = '456 Main St';
    user.phone = '9876543210';
};
</script>