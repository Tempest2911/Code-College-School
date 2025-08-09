<template>
    <h1 class="text-center mt-5">Bài 3</h1>
  <div class="container">
    <div class="row border p-4">
      <!-- Form Đăng Ký -->
      <div class="col-md-6">
        <h3 class="text-primary mb-3">Form Đăng Ký</h3>
        <form @submit.prevent="register">
          <div class="mb-2">
            <label>Họ tên:</label>
            <input type="text" class="form-control" v-model="form.name" required />
          </div>
          <div class="mb-2">
            <label>Email:</label>
            <input type="email" class="form-control" v-model="form.email" required />
          </div>
          <div class="mb-2">
            <label>Mật khẩu:</label>
            <input type="password" class="form-control" v-model="form.password" required />
          </div>
          <div class="mb-2">
            <label>Ngày sinh:</label>
            <input type="date" class="form-control" v-model="form.dob" />
          </div>
          <div class="mb-2">
            <label>Giới tính:</label><br />
            <input type="radio" value="Nam" v-model="form.gender" /> Nam
            <input type="radio" value="Nữ" v-model="form.gender" class="ms-2" /> Nữ
            <input type="radio" value="Khác" v-model="form.gender" class="ms-2" /> Khác
          </div>
          <div class="mb-3">
            <label>Ngôn ngữ:</label><br />
            <input type="checkbox" value="Tiếng Việt" v-model="form.languages" /> Tiếng Việt
            <input type="checkbox" value="Tiếng Anh" v-model="form.languages" class="ms-2" /> Tiếng Anh
            <input type="checkbox" value="Tiếng Nhật" v-model="form.languages" class="ms-2" /> Tiếng Nhật
          </div>
          <button type="submit" class="btn btn-primary">Đăng ký</button>
        </form>
      </div>

      <!-- Kết quả hiển thị -->
      <div class="col-md-6" v-if="isSubmitted">
        <h3 class="text-dark mb-3">Thông tin đã đăng ký:</h3>
        <p><strong>Họ tên:</strong> {{ form.name }}</p>
        <p><strong>Email:</strong> {{ form.email }}</p>
        <p><strong>Ngày sinh:</strong> {{ form.dob }}</p>
        <p><strong>Giới tính:</strong> {{ form.gender }}</p>
        <p><strong>Ngôn ngữ:</strong> {{ form.languages.join(', ') }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const form = ref({
  name: '',
  email: '',
  password: '',
  dob: '',
  gender: '',
  languages: []
})

const isSubmitted = ref(false)

const register = () => {
  isSubmitted.value = true
}
</script>
