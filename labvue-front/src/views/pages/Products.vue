<template>
    <h3>產品功能</h3>
    <div class="row">
        <div class="col-4">
            <button type="button" class="btn btn-primary" @click="openModal('insert')">
                <i class="bi bi-folder2-open"></i> 開啟新增
            </button>
        </div>
        <div class="col-4">
            <input type="text" placeholder="請輸入產品名稱" v-model="findName" @input="callFind(1)">
        </div>
        <div class="col-4">
            <ProductRows :total="total" :options="[2, 3, 4, 5]" @custom-change="callFind(1)" v-model="rows"></ProductRows>
        </div>
    </div>
    <br>

    <div class="row">
        <div class="col-6" v-show="total>0">
            <Paginate prev-text="&lt;" next-text="&gt;" :first-last-button="true"
                    first-button-text="&lt;&lt;" last-button-text="&gt;&gt;"
                    :page-count="pages" :click-handler="callFind" :initial-page="current" v-model="current">
            </Paginate>
        </div>
    </div> 
    <br>

    <div class="row">
        <div v-for="item in products" :key="item.id"
                class="col-12 col-sm-6 col-md-6 col-lg-4 col-xl-3 col-xxl-3">
            <ProductCard :product="item" @open-update="openModal" @custom-delete="callRemove"></ProductCard>
        </div>
    </div>

    <ProductModal ref="productModal" :isShowInsert="isShowInsert" v-model="product"
                    @custom-insert="callCreate" @custom-update="callModify">
    </ProductModal>
</template>

<script setup>
    import ProductRows from "@/components/ProductRows.vue";
    import ProductCard from "@/components/ProductCard.vue";
    import Paginate from "vuejs-paginate-next"
    import Swal from "sweetalert2";
    import axiosapi from "@/plugins/axios.js";
    import { ref, onMounted } from "vue";
    import useUserStore from "@/stores/user.js";

    // modal begin
    import ProductModal from "@/components/ProductModal.vue";
    const productModal = ref(null);
    const isShowInsert = ref(true);
    const product = ref({});
    const lastPageRows = ref(0);
    // modal end

    const userStore = useUserStore();
    const products = ref([]);
    // 分頁 begin
    const findName = ref("");
    const total = ref(0);
    const rows = ref(3);
    const pages = ref(0);
    const start = ref(0);
    const current = ref(1);
    // 分頁 end

    function openModal(action, id) {
        console.log("openModal", action, id);
        if(action==='insert') {
            isShowInsert.value = true;
            product.value = { };
        } else {
            isShowInsert.value = false;
            callFindById(id);
        }
        productModal.value.showModal();
    }
    async function callCreate() {
        Swal.fire({
            title: "執行中......",
            allowOutsideClick: false,
            showConfirmButton: false,
        });

        if(product.value.id==="") {
            product.value.id = null;
        }
        if(product.value.name==="") {
            product.value.name = null;
        }
        if(product.value.price==="") {
            product.value.price = null;
        }
        if(product.value.make==="") {
            product.value.make = null;
        }
        if(product.value.expire==="") {
            product.value.expire = null;
        }
        try {
            const response = await axiosapi.post("/ajax/pages/products", product.value, {
                headers: {
                    "Authorization": `Bearer ${userStore.token}`
                }
            });
            if(response.data.success) {
                await Swal.fire({
                    icon: 'success',
                    title: response.data.message,
                });
                productModal.value.hideModal();
                callFind(current.value);
            } else {
                Swal.fire({
                    icon: 'warning',
                    title: response.data.message,
                });
            }
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: '錯誤：'+error,
            });
        }
    }

    async function callModify() {
        Swal.fire({
            title: "執行中......",
            allowOutsideClick: false,
            showConfirmButton: false,
        });
        if(product.value.id==="") {
            product.value.id = null;
        }
        if(product.value.name==="") {
            product.value.name = null;
        }
        if(product.value.price==="") {
            product.value.price = null;
        }
        if(product.value.make==="") {
            product.value.make = null;
        }
        if(product.value.expire==="") {
            product.value.expire = null;
        }

        try {
            const response = await axiosapi.put(`/ajax/pages/products/${product.value.id}`, product.value, {
                headers: {
                    "Authorization": `Bearer ${userStore.token}`
                }
            });
            if(response.data.success) {
                await Swal.fire({
                    icon: 'success',
                    title: response.data.message,
                });
                productModal.value.hideModal();
                callFind(current.value);                
            } else {
                Swal.fire({
                    icon: 'warning',
                    title: response.data.message,
                });
            }
        } catch(error) {
            Swal.fire({
                icon: 'error',
                title: '錯誤：'+error,
            });
        }
    }
    async function callRemove(id) {
        console.log("callRemove", id);
        const result = await Swal.fire({
            icon: 'question',
            title: "確定要刪除嗎？",
            showDenyButton: true,
        });
        if(result.isConfirmed) {
            try {
                const response = await axiosapi.delete(`/ajax/pages/products/${id}`, {
                    headers: {
                        "Authorization": `Bearer ${userStore.token}`
                    }
                });
                if(response.data.success) {
                    await Swal.fire({
                        icon: 'success',
                        title: response.data.message,
                    });
                    if(lastPageRows.value===1 && current.value>1) {
                        current.value =  current.value - 1;
                    }
                    callFind(current.value);
                } else {
                    Swal.fire({
                        icon: 'warning',
                        title: response.data.message,
                    });
                }
            } catch(error) {
                Swal.fire({
                    icon: 'error',
                    title: '錯誤：'+error,
                });
            }
        }
    }
    function callFindById(id) {
        Swal.fire({
            title: "執行中......",
            allowOutsideClick: false,
            showConfirmButton: false,
        });
        axiosapi.get(`/ajax/pages/products/${id}`, {
            headers: {
                "Authorization": `Bearer ${userStore.token}`
            }
        }).then((response) => {
            product.value = response.data.list[0];

            setTimeout(function() {
                Swal.close();
            }, 100);
        }).catch((error) => {
            Swal.fire({
                icon: 'error',
                title: '錯誤：'+error,
            });
        });
    }
    function callFind(page) {
        console.log("callFind", page);
        if(page) {
            current.value = page;
            start.value = (page - 1) * rows.value;
        } else {
            current.value = 1;
            start.value = 0;
        }
        Swal.fire({
            title: "執行中......",
            allowOutsideClick: false,
            showConfirmButton: false,
        });
        if(findName.value==="") {
            findName.value = null;
        }
        const body = {
            "start": start.value,
            "rows": rows.value,
            "sort": "id",
            "dir": false,
            "name": findName.value
        };
        axiosapi.post("/ajax/pages/products/find", body, {
            headers: {
                "Authorization": `Bearer ${userStore.token}`
            }
        }).then(function(response) {
            console.log("response", response);
            total.value = response.data.count;
            products.value = response.data.list;

            pages.value = Math.ceil(total.value / rows.value);
            lastPageRows.value = total.value % rows.value;

            setTimeout(function() {
                Swal.close();
            }, 500);
        }).catch(function(error) {
            Swal.fire({
                icon: 'error',
                title: '錯誤：'+error,
            });
        });
    }
    onMounted(() => {
        callFind();
    });
</script>
    
<style>

</style>