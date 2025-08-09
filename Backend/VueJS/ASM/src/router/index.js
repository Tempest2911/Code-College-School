import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Profile from '../components/Profile.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', name: 'Login', component: Login, meta: { requiresGuest: true } },
  { path: '/register', name: 'Register', component: Register, meta: { requiresGuest: true } },
  { path: '/profile', name: 'Profile', component: Profile, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Navigation guard: kiểm tra đăng nhập qua localStorage (có thể chuyển sang Vuex hoặc API nếu muốn)
router.beforeEach((to, from, next) => {
  const currentUser = JSON.parse(localStorage.getItem('currentUser'))
  if (to.meta.requiresAuth && !currentUser) {
    next('/login')
  } else if (to.meta.requiresGuest && currentUser) {
    next('/profile')
  } else {
    next()
  }
})

export default router