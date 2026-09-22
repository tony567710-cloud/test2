<template>
    <div ref="modalRef" class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title fs-5">產品資料</h3>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <table>
                        <tbody>
                            <tr>
                                <td>Id：</td>
                                <td><input type="text" v-model="model.id"></td>
                            </tr>
                            <tr>
                                <td>Name：</td>
                                <td><input type="text" v-model="model.name"></td>
                            </tr>
                            <tr>
                                <td>Price：</td>
                                <td><input type="text" v-model="model.price"></td>
                            </tr>
                            <tr>
                                <td>Make：</td>
                                <td><input type="text" v-model="model.make"></td>
                            </tr>
                            <tr>
                                <td>Expire：</td>
                                <td><input type="text" v-model="model.expire"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" v-show="isShowInsert" @click="emits('customInsert')">
                        <i class="bi bi-plus-square"></i> 新增
                    </button>
                    <button type="button" class="btn btn-primary" v-show="!isShowInsert" @click="emits('customUpdate')">
                        <i class="bi bi-plus-square-dotted"></i> 修改
                    </button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</template>
    
<script setup>
    const props = defineProps(["isShowInsert"])
    const emits = defineEmits(["customInsert", "customUpdate"]);
    const model = defineModel();

    import bootstrap from "bootstrap/dist/js/bootstrap.bundle.min.js";
    import { ref, onMounted } from 'vue';
    const modalRef = ref(null);
    const modal = ref(null);
    onMounted(() => {
        modal.value = new bootstrap.Modal(modalRef.value);
    });
    function showModal() {
        modal.value.show();
    }
    function hideModal() {
        modal.value.hide(); 
    }
    defineExpose({
        showModal, hideModal
    });
</script>
    
<style>
    
</style>