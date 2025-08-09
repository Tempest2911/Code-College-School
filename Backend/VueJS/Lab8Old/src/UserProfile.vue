<template>
  <div>
    <h1>User Profile</h1>
    <nav>
      <router-link to="/profile/info">Info</router-link> |
      <router-link to="/profile/settings">Settings</router-link>
    </nav>
    <router-view></router-view>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const user = ref({
  name: 'John Doe',
  email: 'john.doe@example.com',
});
</script>

<style scoped>
h1 {
  color: #42b983;
}

nav {
  margin-bottom: 20px;
}

nav a {
  margin-right: 10px;
  text-decoration: none;
  color: #42b983;
}

nav a.router-link-active {
  font-weight: bold;
}
</style>
