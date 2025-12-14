<template>
    <div class="d-flex justify-content-center align-items-center vh-100 bg-light">
        <div class="card shadow p-4" style="width: 400px;">
            <h3 class="text-center mb-4 text-primary fw-bold">ĐĂNG NHẬP</h3>

            <div v-if="error" class="alert alert-danger text-center">{{ error }}</div>

            <form @submit.prevent="handleLogin">
                <div class="mb-3">
                    <label class="form-label">Tài khoản (MSSV)</label>
                    <input v-model="username" type="text" class="form-control" placeholder="Ví dụ: TH03089" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Mật khẩu (Lớp)</label>
                    <input v-model="password" type="password" class="form-control" placeholder="Ví dụ: SD20202"
                        required>
                </div>
                <button type="submit" class="btn btn-primary w-100" :disabled="loading">
                    {{ loading ? 'Đang kiểm tra...' : 'Đăng Nhập' }}
                </button>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

// Định nghĩa sự kiện để báo cho App.vue biết khi đăng nhập thành công
const emit = defineEmits(['login-success']);

const username = ref("");
const password = ref("");
const error = ref("");
const loading = ref(false);

const handleLogin = async () => {
    loading.value = true;
    error.value = "";

    // Tạo config Basic Auth
    const authConfig = {
        auth: {
            username: username.value,
            password: password.value
        }
    };

    try {
        // Gọi thử 1 API bất kỳ của Backend để kiểm tra tài khoản
        // Nếu sai pass -> Backend trả về 401 -> Nhảy vào catch
        await axios.get('http://localhost:8080/api/don-hang/page?page=0', authConfig);

        // Nếu thành công:
        // 1. Lưu thông tin vào LocalStorage để reload trang không bị mất
        localStorage.setItem('user_auth', JSON.stringify(authConfig.auth));

        // 2. Báo cho App cha biết
        emit('login-success');

    } catch (err) {
        if (err.response && err.response.status === 401) {
            error.value = "Sai tài khoản hoặc mật khẩu!";
        } else {
            error.value = "Lỗi kết nối Server (" + err.message + ")";
        }
    } finally {
        loading.value = false;
    }
};
</script>