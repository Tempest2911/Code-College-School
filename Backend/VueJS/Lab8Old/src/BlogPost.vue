<template>
  <div>
    <h1>{{ post.title }}</h1>
    <p>{{ post.content }}</p>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
const post = ref({ title: '', content: '' });

onMounted(() => {
  const postId = route.params.id;
  // Simulate fetching post data
  post.value = {
    title: `Blog Post ${postId}`,
    content: `This is the content of blog post ${postId}.`,
  };
});
</script>

<style scoped>
h1 {
  color: #42b983;
}
</style>
