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

const onClick = () => {
        message.value = 'Click thông thường hoặc là có Ctrl'
    }

    const onCtrlClick  =  () => {
        message.value = 'Chỉ Ctrl được ấn, không có Shift, Alt hoặc Meta'
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

   <div class="container mb-3">
    <h4>5. Các phím hệ thống</h4>

    <!-- Input để bắt phím -->
    <input
      @keyup.alt.c="onAltC"
      @keyup.ctrl.a="onCtrlA"
      placeholder="Nhấn Alt + C hoặc Ctrl + A"
      class="form-control"
    />

    <!-- Div hiển thị thông báo -->
    <div style="margin-top: 10px; cursor: pointer; background-color: aquamarine; padding: 2rem;">
      {{ message }}
    </div>
  </div>

<div class="container mb-3">
        <h4>6. Phím Ctrl và các phím khác</h4>
        <!--Sẽ kích hoạt ngay cả khi ấn phím Ctrl + Alt hoặc Shift-->
        <button @click.ctrl="onClick">(A Ctrl)</button>
        <!---Chỉ kích hoạt khi mà chỉ có Ctrl (Không Alt, Shift,..)-->
        <button @click.ctrl.exact="onCtrlClick">A (Ctrl + exac)</button>
        <!--Kích hoạt khi không có modifier nào-->
        <button @click.exact="onClick">A (exac - không có modifier)</button>
        <p v-if="message">{{ message }}</p>
    </div>
</template>

<script>
export default {
  data() {
    return {
      message: 'Chưa nhấn phím hệ thống nào',
    };
  },
  methods: {
    onAltC() {
      this.message = 'Bạn đã nhấn Alt + C!';
    },
    onCtrlA(event) {
      event.preventDefault(); // ngăn trình duyệt lưu trang
      this.message = 'Bạn đã nhấn Ctrl + A!';
    },
  },
};
</script>