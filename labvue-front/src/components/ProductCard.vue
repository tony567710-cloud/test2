<template>
    <div class="card">
        <img class="card-img-top" :src="`${path}${product.id}`" :alt="product.name">
        <div class="card-body">
            <h5 class="card-title">{{ product.name }}</h5>
            <div class="card-text text-danger text-end">NT$ {{ product.price}}</div>
            <div class="row">
                <div class="col text-start">
                    <button class="btn btn-primary" @click="emits('openUpdate', 'update', product.id)">
                        <i class="bi bi-folder2-open"></i> 開啟修改
                    </button>
                </div>
                <div class="col text-end">
                    <button class="btn btn-danger" @click="emits('customDelete', product.id)">
                        <i class="bi bi-trash"></i> 刪除
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    const path = import.meta.env.VITE_DETAIL_URL;
    const props = defineProps(["product"]);
    const emits = defineEmits(["openUpdate", "customDelete"]);

</script>

<style>

</style>
