<template>
    <h3>登入</h3>
    <table>
        <tbody>
            <tr>
                <td>帳號：</td>
                <td><input type="text" v-model="username" @blur="check()" @focus="error = ''"></td>
                <td><span class="error">{{ error }}</span></td>
            </tr>
            <tr>
                <td>密碼：</td>
                <td><input type="text" v-model="password"></td>
                <td></td>
            </tr>
            <tr>
                <td> </td>
                <td align="right">
                    <button type="button" class="btn btn-primary" @click="login()">
                        <i class="bi bi-box-arrow-in-right"></i> 登入
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</template>
    
<script setup>
    import axiosapi from '@/plugins/axios.js'
    import Swal from 'sweetalert2'
    import { ref } from 'vue'
    import useUserStore from "@/stores/user.js";

    const userStore = useUserStore();
    const username = ref('');
    const password = ref('');
    const error = ref('');

    async function login() {
        const body = {
            username: username.value,
            password: password.value
        };
        try {
            const response = await axiosapi.post("/ajax/secure/login", body);
            console.log("response", response);
            if(response.data.success) {
                await Swal.fire({
                    title: response.data.message,
                    icon: "success"
                });
                userStore.setToken(response.data.token);
                userStore.setEmail(response.data.email);
                window.location.href="/";
            } else {
                Swal.fire({
                    title: response.data.message,
                    icon: "warning"
                });
            }
        } catch(error) {
            console.log("error", error);
            Swal.fire({
                title: "錯誤:"+ error.message,
                icon: "error"
            });
        }
    }
    async function check() {
        if(username.value!="") {
            try {
                const response = await axiosapi.get(`/ajax/secure/check/${username.value}`);
                console.log("response", response);
                error.value = response.data;
            } catch(error) {
                console.log("error", error);
                Swal.fire({
                    title: "錯誤:"+ error.message,
                    icon: "error"
                });
            }
        }
    }
</script>

<style scoped>
    .error {
        color: red;
    }
</style>