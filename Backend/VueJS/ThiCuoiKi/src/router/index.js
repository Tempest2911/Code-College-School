import { createRouter, createWebHistory } from 'vue-router'
import Page from '../components/Page.vue'
import XeMayPage from '../components/XeMayPage.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/xe-may',component: XeMayPage,
    },
    {
      path: '/about', component: Page,
    },
    {
      path: '/xe-may/add', component: XeMayPage,
    },
    {
      path: '/xe-may/:id/remove', component: XeMayPage,
    },
    {
      path: '/', redirect: '/xe-may',
    }
  ],
})

export default router
