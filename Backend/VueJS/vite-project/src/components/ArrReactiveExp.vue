<script setup>
    import {reactive, ref} from 'vue';
    const fruits = reactive(['Táo', 'Chuối', 'Cam', 'Nho']);
    const newFruit = ref('');
    const addFruit = () => {
        if (newFruit.value.trim() !== '') {
            fruits.push(newFruit.value.trim());
            newFruit.value = '';
        }
    };
    const removeFruit = (index) => {
        fruits.splice(index, 1);
    };
</script>

<template>
    <div class="container mt-3">
        <h2>Danh sách trái cây</h2>
        <ul>
            <li v-for="(fruit, index) in fruits" :key="index">
                {{ fruit }}
                <button @click="removeFruit(index)">Xóa</button>
            </li>
        </ul>
        <input type="text" v-model="newFruit" placeholder="Thêm trái cây mới">
        <button @click="addFruit">Thêm</button>
    </div>
</template>