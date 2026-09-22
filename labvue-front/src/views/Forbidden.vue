<template>
    <h3>權限不足</h3>
    
</template>
    
<script setup>
    import { onMounted } from "vue";
    import useUserStore from "@/stores/user.js";
    const userStore = useUserStore();
    onMounted(() => {
        userStore.setEmail("");
        userStore.setToken("");
    });
</script>
    
<style>
    
</style>