import { defineStore } from 'pinia';
import { ref } from 'vue';

const useUserStore = defineStore('user', function() {
        const token = ref('');
        function setToken(value) {
            token.value = value;
        }

        const email = ref('');
        function setEmail(value) {
            email.value = value;
        }

        return {
            token, setToken, email, setEmail
        }
    },
    {
        persist: {
            storage: sessionStorage
        }
    }
);

export default useUserStore;
