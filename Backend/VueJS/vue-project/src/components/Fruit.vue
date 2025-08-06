<template>
    <div class="container">
        <p>Qua oc cho: {{ item[0] }}</p>
        <p>Qua tao: {{ item[1] }}</p>
        <p>Qua cam: {{ item[2] }}</p>
        <p>Qua chuoi: {{ item[3] }}</p>
    </div>
</template>

<script setup>
defineProps(
    {
        item: {
            type: Array,
            required: true
        }
    }
);
</script>