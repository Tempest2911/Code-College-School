<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import api from './utils/api'

const router = useRouter()
const route = useRoute()

const isAuthenticated = ref(false)
const currentUser = ref(null)

onMounted(() => {
  const savedUser = JSON.parse(localStorage.getItem('currentUser'))
  if (savedUser) {
    isAuthenticated.value = true
    currentUser.value = savedUser
  }
})

watch(isAuthenticated, (newValue) => {
  if (newValue) {
    router.push('/profile')
  } else {
    router.push('/login')
  }
})

const handleLoginSuccess = (userData) => {
  isAuthenticated.value = true
  currentUser.value = userData
  localStorage.setItem('currentUser', JSON.stringify(userData))
}

const handleRegisterSuccess = (userData) => {
  isAuthenticated.value = true
  currentUser.value = userData
  localStorage.setItem('currentUser', JSON.stringify(userData))
}

const handleLogout = () => {
  localStorage.removeItem('currentUser')
  isAuthenticated.value = false
  currentUser.value = null
}

const handleProfileUpdated = (updatedUser) => {
  currentUser.value = updatedUser
  localStorage.setItem('currentUser', JSON.stringify(updatedUser))
}
</script>

<template>
  <div id="app">
    <!-- Navigation Header -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm" v-if="isAuthenticated">
      <div class="container">
        <router-link class="navbar-brand fw-bold" to="/profile">
          <i class="bi bi-journal-text me-2"></i>
          Blog App
        </router-link>
        <div class="navbar-nav ms-auto">
          <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" role="button" data-bs-toggle="dropdown">
              <img
                :src="currentUser?.avatar || 'https://via.placeholder.com/32x32/ffffff/0d6efd?text=U'"
                class="rounded-circle me-2"
                width="32"
                height="32"
                alt="Profile"
              />
              {{ currentUser?.name || 'User' }}
            </a>
            <ul class="dropdown-menu">
              <li>
                <a class="dropdown-item" href="#" @click.prevent="handleLogout">
                  <i class="bi bi-box-arrow-right me-2"></i>Logout
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
    <main>
      <router-view
        v-slot="{ Component }"
        @login-success="handleLoginSuccess"
        @register-success="handleRegisterSuccess"
        @profile-updated="handleProfileUpdated"
      >
        <component :is="Component" :currentUser="currentUser" @profile-updated="handleProfileUpdated" />
      </router-view>
    </main>
  </div>
</template>

<style>
body {
  background-color: #f8f9fa;
  margin: 0;
  padding: 0;
}

.navbar {
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.dropdown-menu {
  border: none;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
  border-radius: 0.5rem;
}
#app {
  min-height: 100vh;
}
</style>

