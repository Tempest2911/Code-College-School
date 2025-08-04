<script setup>
import { ref } from 'vue';

// Biến đếm và tên
const count = ref(0);
const name = ref('');
const submitted = ref(false);

// Định nghĩa emit
const emit = defineEmits(['customEvent']);

// Hàm gửi emit sự kiện
const sendEvent = () => {
  emit('customEvent', count.value);
};

// Hàm tăng số đếm
const increment = () => {
  count.value += 1;
};

// Hàm xử lý submit form
function handleSubmitted() {
  submitted.value = true;
  console.log('Đã gửi:', name.value);
}
</script>

<template>
  <div class="container mb-3">
    <h4>1. Khái niệm event</h4>
    <button class="btn btn-dark" @click="increment">
      Count is {{ count }}
    </button>
  </div>

  <div class="container mb-3">
    <h4>2. Định nghĩa sự kiện bằng Emit</h4>
    <button class="btn btn-success" @click="sendEvent">
      Gửi Emit sự kiện
    </button>
  </div>

  <div class="container mb-3">
    <h4>3. Truyền tham số inline</h4>
    <button class="btn btn-warning" @click="handleSubmitted">
      Submit trực tiếp
    </button>
  </div>

  <div class="container mb-3">
    <h4>4. Submit form</h4>
    <form @submit.prevent="handleSubmitted">
      <div class="form-group">
        <label for="name">Tên:</label>
        <input
          type="text"
          v-model="name"
          class="form-control"
          id="name"
          name="name"
          required
        />
      </div>
      <button type="submit" class="btn btn-primary mt-2">Gửi</button>
    </form>

    <div v-if="submitted" class="alert alert-info mt-2">
      Bạn đã gửi: {{ name }}
    </div>
  </div>
</template>