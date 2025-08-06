<template>
  <div>
    <h1>Blog Posts</h1>
    <ul>
      <li v-for="post in posts" :key="post.id">
        <router-link :to="`/blog/${post.id}`">{{ post.title }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const posts = ref([
  { id: 1, title: 'First Blog Post' },
  { id: 2, title: 'Second Blog Post' },
  { id: 3, title: 'Third Blog Post' },
]);
</script>

<style scoped>
ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 10px 0;
}
</style>
