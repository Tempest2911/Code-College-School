<template>
    <div class="box">
        <h1>Bài 2</h1>
        <div class="content-box">
            <div>
                <h2>Nhập tháng của bạn</h2>
                <input class="mb-3" type="number" v-model.number="thang" placeholder="Nhập tháng" min="1" max="12"
                    step="1" />
                <p v-if="thang === null || thang == ''"><strong></strong></p>
                <p v-else-if="thang < 1 || thang > 12" style="color: red;"><strong>Vui lòng nhập tháng từ 1 đến
                        12!</strong>
                </p>
                <p v-else-if="thang <= 3"><strong>Mùa Xuân</strong></p>
                <p v-else-if="thang <= 6"><strong>Mùa Hè</strong></p>
                <p v-else-if="thang <= 9"><strong>Mùa Thu</strong></p>
                <p v-else-if="thang <= 12"><strong>Mùa Đông</strong></p>
            </div>
        </div>
    </div>

</template>

<script setup>
import { ref } from 'vue'

const thang = ref(null)
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
    min-height: 180px;
    /* cố định chiều cao tối thiểu */
}

input {
    width: 100%;
    padding: 8px;
}
</style>
