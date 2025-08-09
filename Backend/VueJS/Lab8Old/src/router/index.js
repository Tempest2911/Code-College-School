import { createRouter, createWebHistory } from 'vue-router'
import Home from '../Home.vue'
import BlogList from '../BlogList.vue'
import BlogPost from '../BlogPost.vue'
import Dashboard from '../Dashboard.vue'
import Login from '../Login.vue'
import UserProfile from '../UserProfile.vue'
import { store } from '../store.js'

// Các thành phần con cho /profile
import UserInfo from '../UserInfo.vue'     // Tạo file này: hiển thị info cơ bản
import UserSettings from '../UserSettings.vue' // Tạo file này: hiển thị cài đặt

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/blog',
        name: 'BlogList',
        component: BlogList
    },
    {
        path: '/blog/:id',
        name: 'BlogPost',
        component: BlogPost,
        props: true // để BlogPost nhận props id
    },
    {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        beforeEnter: (to, from, next) => {
            if (!store.isAuthenticated) {
                next({ name: 'Login' });
            } else {
                next();
            }
        }
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/profile',
        name: 'UserProfile',
        component: UserProfile,
        alias: '/me',        // alias route
        children: [
            {
                path: 'info',    // /profile/info
                component: UserInfo
            },
            {
                path: 'settings',// /profile/settings
                component: UserSettings
            }
        ]
    }
];

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router



