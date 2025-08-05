<script setup>
import { ref } from 'vue'
import Lab5Bai1 from './lab5Bai1.vue';
import Lab5Bai2 from './Lab5Bai2.vue';
import Lab5Bai3 from './Lab5Bai3.vue';
import CreatePost from './CreatePost.vue';
import PostList from './PostList.vue';

const posts = ref([])

function addPost(post) {
  posts.value.push(post)
}
</script>

<template>
 <main>
  <Lab5Bai1></Lab5Bai1>
  <Lab5Bai2></Lab5Bai2>
  <Lab5Bai3></Lab5Bai3>
    <div id="app">
    <CreatePost @add-post="addPost" />
    <PostList :posts="posts" />
  </div>
 </main>
</template>





