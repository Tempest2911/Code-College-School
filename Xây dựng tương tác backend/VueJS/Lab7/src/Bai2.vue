<template>
    <h1 class="text-center mt-5">Bài 2</h1>
    <div class="container ">
        <div class="card shadow p-4 mx-auto" style="max-width: 500px;">
            <div v-if="!isLoggedIn">
                <h3 class="text-center text-primary mb-4">Form Đăng nhập</h3>
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label>Email:</label>
                        <input type="email" class="form-control" v-model="email" placeholder="Nhập email">
                        <p v-if="emailError" class="text-danger mt-1">{{ emailError }}</p>
                    </div>
                    <div class="mb-3">
                        <label>Mật khẩu:</label>
                        <input type="password" class="form-control" v-model="password" placeholder="Nhập mật khẩu">
                        <p v-if="passwordError" class="text-danger mt-1">{{ passwordError }}</p>
                    </div>
                    <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                </form>
            </div>

            <div v-else class="text-center">
                <h4 class="text-success">Chào mừng, {{ email }}!</h4>
                <button @click="logout" class="btn btn-outline-primary mt-3">Đăng xuất</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

const isLoggedIn = ref(false)
const email = ref('')
const password = ref('')
const emailError = ref('')
const passwordError = ref('')

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/

const login = () => {
    emailError.value = ''
    passwordError.value = ''

    if (!email.value) {
        emailError.value = 'Email là bắt buộc.'
    } else if (!emailRegex.test(email.value)) {
        emailError.value = 'Email không hợp lệ.'
    }

    if (!password.value) {
        passwordError.value = 'Mật khẩu là bắt buộc.'
    }

    if (!emailError.value && !passwordError.value) {
        isLoggedIn.value = true
    }
}

const logout = () => {
    isLoggedIn.value = false
    email.value = ''
    password.value = ''
}
</script>
