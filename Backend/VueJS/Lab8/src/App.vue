<template>
  <div class="container">
    <header>
      <h1>📝 MyBlog</h1>
      <nav>
        <router-link to="/">🏠 Home</router-link>
        <router-link to="/blog">📰 Blog</router-link>
        <router-link to="/profile/info">👤 Profile</router-link>
        <router-link to="/dashboard">📊 Dashboard</router-link>
        <router-link to="/login">🔐 Login</router-link>
      </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <small>© 2025 MyBlog. All rights reserved.</small>
    </footer>
  </div>
</template>

<script>
export default {
  name: "AppRoot"
};
</script>