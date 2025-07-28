<script setup>
import { ref, computed, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import authManager from './utils/auth.js'

const router = useRouter()

// Authentication state
const isAuthenticated = ref(false)
const currentUser = ref(null)

// Check for existing session on mount
onMounted(() => {
  const savedUser = authManager.getCurrentUser()
  if (savedUser) {
    isAuthenticated.value = true
    currentUser.value = savedUser
  }
})

// Watch for authentication changes
watch(isAuthenticated, (newValue) => {
  if (newValue) {
    router.push('/profile')
  } else {
    router.push('/login')
  }
})

// Event handlers
const handleLoginSuccess = (userData) => {
  console.log('Login successful:', userData)
  isAuthenticated.value = true
  currentUser.value = userData
}

const handleRegisterSuccess = (userData) => {
  console.log('Registration successful:', userData)
  isAuthenticated.value = true
  currentUser.value = userData
}

const handleLogout = () => {
  authManager.logout()
  isAuthenticated.value = false
  currentUser.value = null
}

const handleProfileUpdated = (updatedUser) => {
  currentUser.value = updatedUser
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
              <!-- <li><router-link class="dropdown-item" to="/profile"><i class="bi bi-person me-2"></i>Profile</router-link></li> -->
              <!-- <li><hr class="dropdown-divider"></li> -->
              <li><a class="dropdown-item" href="#" @click.prevent="handleLogout"><i class="bi bi-box-arrow-right me-2"></i>Logout</a></li>
            </ul>
          </div>
        </div>
      </div>
    </nav>

    <!-- Main Content -->
  <main>
      <router-view @login-success="handleLoginSuccess" @register-success="handleRegisterSuccess" @profile-updated="handleProfileUpdated" />
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

