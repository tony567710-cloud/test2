<template>
    <span>
        <span v-if="total<=0">查無資料，</span>
        <span v-else>查到{{ total }}筆資料，</span>
        每頁顯示
        <select v-model="model" @change="emits('customChange')">
            <option v-for="option in options" :key="option" :value="option">{{option}}</option>
        </select>
    筆</span>
</template>
    
<script setup>
    const props = defineProps(["total", "options"]);
    const emits = defineEmits(["customChange"]);
    const model = defineModel();

</script>
    
<style>
    
</style>