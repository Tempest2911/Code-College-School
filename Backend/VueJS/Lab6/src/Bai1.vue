<template>
    <div class="box">
        <h1>Bài 1</h1>
        <div class="content-box">
            <div>
                <h2>Xếp loại học sinh</h2>
                <input class="mb-3" type="number" v-model.number="diemTrungBinh" placeholder="Nhập điểm trung bình"
                    min="0" max="10" step="0.1" />

                <p v-if="xepLoai">Xếp loại: <strong>{{ xepLoai }}</strong></p>
            </div>

        </div>
    </div>

</template>

<script setup>
import { ref, computed } from 'vue'

const diemTrungBinh = ref(null)

const xepLoai = computed(() => {
    const diemtb = diemTrungBinh.value
    if (diemtb === null || diemtb == '') return ''
    if (diemtb < 0 || diemtb > 10) return 'Điểm không hợp lệ'
    if (diemtb >= 9) return 'Xuất sắc'
    if (diemtb >= 8) return 'Giỏi'
    if (diemtb >= 6.5) return 'K    há'
    if (diemtb >= 5) return 'Trung bình'
    if (diemtb < 5) return 'Yếu'
})
</script>

<style scoped>
.box {
  width: 100%;
  max-width: 400px;
  padding: 20px;
  border: none;
  margin: 0 auto;
  text-align: center;
}

.content-box {
  border: 2px solid #ccc;
  border-radius: 10px;
  padding: 20px;
  min-height: 180px; /* cố định chiều cao tối thiểu */
}

input {
  width: 100%;
  padding: 8px;
}

</style>
