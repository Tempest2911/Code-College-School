<template>
    <div class="container">
        <h3>{{ name }}</h3>
        <p>Age: {{ age }}</p>
        <p>Status: {{ isActive ? 'Active' : 'nonActive' }}</p>
    </div>
</template>

<script setup>
defineProps(
    {
        name: {
            type: String,
            required: true
        },
        age: {
            type: Number,
            default: 18
        },
        isActive: {
            type: Boolean,
            default: false
        }
    }
);
</script>