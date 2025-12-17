<script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'

const listLichChieu = ref([])

const fetchLichChieus = async () => {
    const authConfig = {
      auth: {
        username: 'TH03089',
        password: 'SD20202'
      }
    }

    const response = await axios.get('http://localhost:8080/api/lichChieu', authConfig)
    
    listLichChieu.value = response.data
}
onMounted(() => {
  fetchLichChieus()
})
</script>

<template>
  <div>
    <h1>Danh Sách Lịch Chiếu</h1>
        <table border="1">
          <thead>
            <tr>
              <th>ID</th>
              <th>Ngày chiếu</th>
              <th>Phòng chiếu</th>
              <th>Giá vé</th>
              <th>Tên phim</th>
              <th>Đạo diễn</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="lc in listLichChieu" :key="lc.id">
              <td>{{ lc.id }}</td>
              <td>{{ lc.ngayChieu }}</td>
              <td>{{ lc.phongChieu }}</td>
              <td>{{ lc.giaVe }}</td>
              <td>{{ lc.tenPhim }}</td>
              <td>{{ lc.daoDien }}</td>
            </tr>
          </tbody>
        </table>
  </div>
</template>

<style scoped>
</style>