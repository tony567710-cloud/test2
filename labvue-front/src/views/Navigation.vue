<template>
    <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">    
            <span class="navbar-brand">Vue串接</span>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <RouterLink class="nav-link active" aria-current="page" to="/">首頁</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink class="nav-link" v-show="userStore.email===''" to="/secure/login">
                            <i class="bi bi-box-arrow-in-right"></i> 登入
                        </RouterLink>
                        <button class="nav-link" v-show="userStore.email!==''" @click="logout">
                            <i class="bi bi-box-arrow-left"></i> 登出
                        </button>
                    </li>
                    <li class="nav-item dropdown" v-show="userStore.email!==''">
                        <span class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            功能A
                        </span>
                        <ul class="dropdown-menu">
                            <li><RouterLink class="dropdown-item" to="/pages/products">產品</RouterLink></li>
                        </ul>

                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>
    
<script setup>
    import { RouterLink } from "vue-router"
    import useUserStore from "@/stores/user.js";

    const userStore = useUserStore();
    function logout() {
        userStore.setEmail("");
        userStore.setToken("");
        window.location.href="/";
    }
</script>
    
<style>
    
</style>