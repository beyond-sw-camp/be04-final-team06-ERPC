import { createApp } from 'vue'
import App from './App.vue'
// import axios from 'axios'
import router from './router/mainRouter.js'

const app = createApp(App)
app.use(router);
app.mount('#app');
// app.provide('$axios', axios);
