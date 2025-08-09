<script setup>
import { ref } from 'vue';

// Biến đếm và tên
const count = ref(0);
const name = ref('');
const submitted = ref(false);
const showModal = ref(false);

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
  showModal.value = true;
  console.log('Đã gửi:', name.value);
}

// Đóng modal
const closeModal = () => {
  showModal.value = false;
};
const handleKey = () => {
  alert('Bạn vừa nhấn Enter!');
};

const message = ref('');

function onClick(msg) {
  message.value = msg;
}

function handleMouseClick(event) {
  switch (event.button) {
    case 0:
      message.value = '🖱 Bạn đã nhấn chuột trái (Left)';
      break;
    case 1:
      message.value = '🖱 Bạn đã nhấn chuột giữa (Middle)';
      break;
    case 2:
      message.value = '🖱 Bạn đã nhấn chuột phải (Right)';
      break;
    default:
      message.value = '❓ Không xác định loại chuột';
  }
}
</script>

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
    onCtrlS(event) {
      event.preventDefault(); // ngăn trình duyệt lưu trang
      this.message = 'Bạn đã nhấn Ctrl + S!';
    },
  },
};


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

  <!-- Modal hiển thị sau khi gửi -->
  <div v-if="showModal" class="modal-backdrop">
    <div class="modal-box">
      <h5>Thông báo</h5>
      <p>Bạn đã nhập: <strong>{{ name }}</strong></p>
      <button class="btn btn-secondary mt-2" @click="closeModal">Đóng</button>
    </div>
  </div>

<div class="container mt-4">
    <div class="card shadow-sm">
      <div class="card-body">
        <h5 class="card-title mb-3">Xác nhận bằng phím Enter</h5>
        <input
          type="text"
          class="form-control"
          placeholder="Nhập gì đó rồi nhấn Enter để xác nhận"
          @keyup.enter="handleKey"
        />
        <small class="text-muted d-block mt-2">Mẹo: Bạn có thể nhấn Enter thay vì bấm nút</small>
      </div>
    </div>
  </div>
  <div class="container mb-3">
    <h4>5. Các phím hệ thống</h4>

    <!-- Input để bắt phím -->
    <input
      @keyup.alt.c="onAltC"
      @keyup.ctrl.s="onCtrlS"
      placeholder="Nhấn Alt + C hoặc Ctrl + S"
      class="form-control"
    />

    <!-- Div hiển thị thông báo -->
    <div style="margin-top: 10px; cursor: pointer; background-color: aquamarine; padding: 2rem;">
      {{ message }}
    </div>
  </div>

  <div class="container mb-3">
    <h4>6. Phím Ctrl và các phím khác</h4>

    <!-- Kích hoạt ngay cả khi ấn Ctrl + Alt hoặc Shift -->
    <button @click.ctrl="onClick('A - Có Ctrl')">A (Ctrl)</button>

    <!-- Chỉ kích hoạt khi chỉ có Ctrl (Không Alt, Shift, ...) -->
    <button @click.ctrl.exact="onClick('B - Chỉ Ctrl (exact)')">B (Ctrl + exact)</button>

    <!-- Kích hoạt khi không có modifier nào -->
    <button @click.exact="onClick('C - Không có modifier nào')">C (exact - không modifier)</button>

    <p v-if="message" class="mt-3 text-info">{{ message }}</p>
  </div>

  <div class="container mt-4">
    <h4>Phân biệt chuột trái / giữa / phải</h4>
    <button
      class="btn btn-outline-primary"
      @mousedown="handleMouseClick"
      @contextmenu.prevent
    >
      Nhấn chuột vào đây
    </button>

    <p class="mt-3 text-info">{{ message }}</p>
  </div>
</template>

<style scoped>
.modal-backdrop {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 999;
}

.modal-box {
  background-color: white;
  padding: 20px;
  border-radius: 8px;
  width: 300px;
  text-align: center;
}
</style>
