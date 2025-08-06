<script setup></script>

<template>
  <div>
    <header>
      <nav>
        <router-link to="/">Home</router-link> |
        <router-link to="/blog">Blog</router-link> |
        <router-link to="/profile">Profile</router-link>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
  </div>
</template>

<style scoped>
header {
  background-color: #42b983;
  padding: 10px;
  color: white;
}

nav a {
  margin-right: 10px;
  text-decoration: none;
  color: white;
}

nav a.router-link-active {
  font-weight: bold;
}

main {
  padding: 20px;
}
</style>
