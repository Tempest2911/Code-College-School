import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import './assets/style.css';

const app = createApp(App);

// Global state for authentication and user profile
window.isAuthenticated = false;
window.userProfile = null;

app.use(router);
app.mount('#app');
