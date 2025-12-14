<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Ngày Chấm</th>
                    <th>Số Giờ Làm</th>
                    <th>Phạt</th>
                    <th>Tên Nhân Viên</th>
                    <th>Tên Phòng Ban</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="chamCong in chamCongs">
                    <td>{{ chamCong.id }}</td>
                    <td>{{ chamCong.ngayCham }}</td>
                    <td>{{ chamCong.soGioLam }}</td>
                    <td>{{ chamCong.phat }}</td>
                    <td>{{ chamCong.tenNhanVien }}</td>
                    <td>{{ chamCong.phongBan }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getAllChamCong } from "@/components/ChamCongService";

const chamCongs = ref([]);

onMounted(async () => {
    const getters = await getAllChamCong();
    
    chamCongs.value = getters;
});

</script>

<style lang="css" scoped>
</style>