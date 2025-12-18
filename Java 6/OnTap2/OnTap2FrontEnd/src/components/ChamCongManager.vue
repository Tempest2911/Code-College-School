<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const listChamCong = ref([])

onMounted(async () => {
    const authConfig = {
        auth: {
            username: 'TH03089',
            password: 'SD20202'
        }
    }

    const response = await axios.get('http://localhost:8080/api/chamCong', authConfig)

    listChamCong.value = response.data
})
</script>

<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ngày chấm</th>
                    <th>Số giờ làm </th>
                    <th>Phạt</th>
                    <th>Tên nhân viên</th>
                    <th>Phòng ban</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="cc in listChamCong">
                    <td>{{ cc.id }}</td>
                    <td>{{ cc.ngayCham }}</td>
                    <td>{{ cc.soGioLam }}</td>
                    <td>{{ cc.phat }}</td>
                    <td>{{ cc.tenNhanVien }}</td>
                    <td>{{ cc.phongBan }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>