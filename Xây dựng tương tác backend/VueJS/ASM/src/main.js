import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './CssResource/bootstrap.min.css'
import './JSResource/bootstrap.bundle.min.js'

const app = createApp(App)
app.use(router)
app.mount('#app')
