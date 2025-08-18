import { createRouter, createWebHistory } from "vue-router";
import TacGiaPage from "@/components/TacGiaPage.vue";
import Page from "@/components/Page.vue";

const routes = [
  { path: "/tac-gia", component: TacGiaPage },
  { path: "/page", component: Page },
  { path: "/tac-gia/add", component: TacGiaPage },
  { path: "/tac-gia/:id/remove", component: TacGiaPage }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
