<script setup>
// 1. Nhận dữ liệu từ cha thông qua props
defineProps(['modelValue'])
// 2. tạo một biến emit để phát sự kiện update:modelValue
const emit = defineEmits(['update:modelValue'])
// 3. khi người dùng nhập vào ô input, sẽ gọi hàm updateValue
const updateValue = (event) => {
    emit('update:modelValue', event.target.value)
}
</script>

<template>
    <input :value="modelValue" @input="updateValue" />
</template>