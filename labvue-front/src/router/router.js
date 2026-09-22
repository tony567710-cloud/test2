import { createWebHistory, createRouter } from 'vue-router'

import Home from '@/views/Home.vue'
import NotFound from '@/views/NotFound.vue'
import Forbidden from '@/views/Forbidden.vue'
import Login from '@/views/secure/Login.vue'
import Products from '@/views/pages/Products.vue'

const routes = [
    { path: "/", component: Home, },
    { path: "/:pathMatch(.*)", component: NotFound, },
    { path: "/403", component: Forbidden, },
    { path: "/secure/login", component: Login, },
    { path: "/pages/products", component: Products, },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
