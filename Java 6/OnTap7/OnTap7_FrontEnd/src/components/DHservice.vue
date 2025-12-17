<script setup>
  import { ref, onMounted } from 'vue'
  import axios from 'axios'

const listDonHang = ref([])

const fetchDonHangs = async () => {
    const authConfig = {
      auth: {
        username: 'TH03089',
        password: 'SD20202'
      }
    }

    const response = await axios.get('http://localhost:8080/api/donHang', authConfig)
    
    listDonHang.value = response.data
}
onMounted(() => {
  fetchDonHangs()
})
</script>

<template>
  <div>
        <table border="1">
          <thead>
            <tr>
              <th>ID</th>
              <th>Mã đơn hàng</th>
              <th>Ngày đặt</th>
              <th>Tổng tiền</th>
              <th>Tên Khách hàng</th>
              <th>Địa chỉ</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="lc in listDonHang" :key="lc.id">
              <td>{{ lc.id }}</td>
              <td>{{ lc.maDonHang }}</td>
              <td>{{ lc.ngayDat }}</td>
              <td>{{ lc.tongTien }}</td>
              <td>{{ lc.tenKhachHang }}</td>
              <td>{{ lc.diaChi }}</td>
            </tr>
          </tbody>
        </table>
  </div>
</template>