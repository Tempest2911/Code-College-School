import { createRouter, createWebHistory } from 'vue-router';
// import views
import HomeView from '../views/HomeView.vue';
import ContactView from '../views/ContactView.vue';
import AboutView from '../views/AboutView.vue';

const routers = [
    {path: '/', component: HomeView, name:'home'},
    {path: '/about', component: AboutView, name:'about'},
    {path: '/contact', component: ContactView, name:'contact'}
]

const router = createRouter({
    history:createWebHistory(),
    routes:routers
})

export default router;