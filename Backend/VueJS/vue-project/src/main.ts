import { createApp } from 'vue'
import App from './App.vue'
import './bootstrap.min.css'
import './bootstrap.bundle.min.js'

import router from './router'

createApp(App).use(router).mount('#app')
