import { createRouter, createWebHistory } from 'vue-router'
import DongHoPage from '@/components/DongHoPage.vue'
import Page from '@/components/Page.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/dong-ho',
      component: DongHoPage
    },
    {
      path: '/page',
      component: Page
    },
        {
      path: '/dong-ho/xoa/:id',
      component: DongHoPage
    },
        {
      path: '/dong-ho/add',
      component: DongHoPage
    },
    {
      path: "/",
      redirect: "/dong-ho"
    }
  ],
})

export default router
