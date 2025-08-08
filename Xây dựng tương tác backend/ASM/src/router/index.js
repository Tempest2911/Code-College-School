import { createRouter, createWebHistory } from 'vue-router'
import Login from '../components/Login.vue'
import Register from '../components/Register.vue'
import Profile from '../components/Profile.vue'
import authManager from '../utils/auth.js'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { requiresGuest: true }
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
    meta: { requiresGuest: true }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile,
    meta: { requiresAuth: true },
    props: (route) => ({
      currentUser: authManager.getCurrentUser()
    })
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// Navigation guards
router.beforeEach((to, from, next) => {
  const currentUser = authManager.getCurrentUser()
  
  if (to.meta.requiresAuth && !currentUser) {
    next('/login')
  } else if (to.meta.requiresGuest && currentUser) {
    next('/profile')
  } else {
    next()
  }
})

export default router 