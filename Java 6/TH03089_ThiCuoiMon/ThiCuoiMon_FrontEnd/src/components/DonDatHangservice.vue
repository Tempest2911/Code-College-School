<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const listDonDatHang = ref([])

onMounted(async () => {
    const authConfig = {
        auth: {
            username: 'TH03089',
            password: 'SD20202'
        }
    }

    const response = await axios.get('http://localhost:8080/api/donDatHang', authConfig)

    listDonDatHang.value = response.data
})
</script>

<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã Đơn Hàng</th>
                    <th>Ngày Đặt</th>
                    <th>Tổng tiền</th>
                    <th>Tên sản phẩm</th>
                    <th>Nhà sản xuất</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="ddh in listDonDatHang">
                    <td>{{ ddh.id }}</td>
                    <td>{{ ddh.maDonHang }}</td>
                    <td>{{ ddh.ngayDat }}</td>
                    <td>{{ ddh.tongTien }}</td>
                    <td>{{ ddh.tenSanPham }}</td>
                    <td>{{ ddh.nhaSanXuat }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>