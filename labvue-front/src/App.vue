<template>
    <div>
        <Navigation></Navigation>
    </div>
    <RouterView></RouterView>
</template>

<script setup>
    import "bootstrap/dist/css/bootstrap.min.css";
    import "bootstrap/dist/js/bootstrap.bundle.min.js";
    import "bootstrap-icons/font/bootstrap-icons.min.css";

    import { RouterView } from 'vue-router';
    import Navigation from '@/views/Navigation.vue';

</script>

<style>

</style>