<template>
  <h1 class="text-center mt-5">Bài 1</h1>
  <div class="container">
    <div class="card shadow p-4 mx-auto" style="max-width: 500px;">
      <h3 class="text-center mb-3 text-primary">Quản lý công việc</h3>
      <form @submit.prevent="addTask" class="mb-3">
        <input type="text" v-model="newTask" placeholder="Nhập công việc" class="form-control mb-3" required />
        <button type="submit" class="btn btn-primary w-100">Thêm công việc</button>
      </form>
      <ul class="list-group">
        <li v-for="(task, index) in tasks" :key="index" class="list-group-item d-flex justify-content-between align-items-center">
          {{ task }}
          <button class="btn btn-sm btn-danger" @click="deleteTask(index)">Xóa</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const newTask = ref('')
const tasks = ref([])

const addTask = () => {
  tasks.value.push(newTask.value)
  newTask.value = ''
}

const deleteTask = (index) => {
  tasks.value.splice(index, 1)
}
</script>
