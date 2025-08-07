<template>
  <div class="app-container">
    <header class="app-header">
      <h1 class="app-title">MyBlog</h1>
      <nav class="app-nav">
        <router-link to="/" class="nav-link">🏠 Home</router-link>
        <router-link to="/blog" class="nav-link">📰 Blog</router-link>
        <router-link to="/profile/info" class="nav-link">👤 Profile</router-link>
        <router-link to="/dashboard" class="nav-link">📊 Dashboard</router-link>
        <router-link to="/login" class="nav-link">🔒 Login</router-link>
      </nav>
    </header>
    <main class="app-main">
      <router-view />
    </main>
    <footer class="app-footer">
      <small>© 2025 MyBlog. All rights reserved.</small>
    </footer>
  </div>
</template>

<script>
export default {
  name: "AppRoot"
};
</script>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  background: #f9f9f9;
}
.app-header {
  background: #3498db;
  color: white;
  padding: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.app-title {
  margin: 0;
  font-size: 1.5rem;
}
.app-nav {
  display: flex;
  gap: 1rem;
}
.nav-link {
  color: white;
  text-decoration: none;
  font-weight: bold;
  transition: color 0.2s;
}
.nav-link:hover {
  color: #a3d5f7;
}
.app-main {
  flex: 1;
  padding: 2rem;
}
.app-footer {
  background: #2c3e50;
  color: white;
  text-align: center;
  padding: 1rem;
}
</style>