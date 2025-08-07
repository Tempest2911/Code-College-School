<template>
  <div>
    <h1>Welcome to MyBlog!</h1>
    <p>Your one-stop destination for amazing blog posts.</p>
    <nav>
      <router-link to="/blog">Blog List</router-link> |
      <router-link to="/login">Login</router-link> |
      <router-link to="/dashboard">Dashboard</router-link>
    </nav>
  </div>
</template>

<style scoped>
nav {
  margin-top: 20px;
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
