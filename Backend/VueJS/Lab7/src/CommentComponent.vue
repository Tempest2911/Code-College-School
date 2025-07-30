<template>
  <div class="card shadow p-4 mx-auto" style="max-width: 600px;">
    <h4 class="mb-3 text-success">Chào, {{ username }}!</h4>

    <textarea class="form-control mb-3" v-model="newComment" placeholder="Nhập bình luận..."></textarea>
    <button class="btn btn-success w-100 mb-3" @click="addComment">Gửi bình luận</button>

    <div v-if="comments.length">
      <h5>Bình luận đã gửi:</h5>
      <ul class="list-group">
        <li v-for="(c, i) in comments" :key="i" class="list-group-item">
          {{ c }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
defineProps(['username'])

const newComment = ref('')
const comments = ref([])

const addComment = () => {
  if (newComment.value.trim()) {
    comments.value.push(newComment.value.trim())
    newComment.value = ''
  }
}
</script>
