import { createRouter, createWebHistory } from 'vue-router'
import KhoaHocPage from '@/components/KhoaHocPage.vue'
import About from '@/components/About.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/khoa-hoc', component: KhoaHocPage },
    { path: '/about', component: About },
    { path: '/khoa-hoc/add', component: KhoaHocPage },
    { path: '/khoa-hoc/:id/remove', component: KhoaHocPage },
    { path: "/", redirect: "/khoa-hoc" }
  ],
})

export default router
