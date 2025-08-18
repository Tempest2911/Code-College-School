import { createRouter, createWebHistory } from "vue-router";
import PhonePage from "@/components/PhonePage.vue";
import Page from "@/components/Page.vue";

const routes = [
    { path: "/phone", component: PhonePage },
    { path: "/page", component: Page },
    { path: "/phone/add", component: PhonePage },
    { path: "/phone/:id/detail", component: PhonePage },
    { path: "/", redirect: "/phone" }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;
