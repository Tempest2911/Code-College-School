<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã nhân viên</th>
                    <th>Họ Tên</th>
                    <th>Giới tính</th>
                    <th>Ngày sinh</th>
                    <th>Mã chức vụ</th>
                    <th>Tên chức vụ</th>

                </tr>
            </thead>
            <tbody>
                <tr v-for="nhanVien in nhanViens">
                    <td>{{ nhanVien.id }}</td>
                    <td>{{ nhanVien.maNhanVien }}</td>
                    <td>{{ nhanVien.hoTen }}</td>
                    <td>{{ nhanVien.gioiTinh }}</td>
                    <td>{{ nhanVien.ngaySinh }}</td>
                    <td>{{ nhanVien.maChucVu }}</td>
                    <td>{{ nhanVien.tenChucVu }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getAllNhanVien } from "./NhanVienService.ts";

const nhanViens = ref([]);

onMounted(async () => {
    const getters = await getAllNhanVien();

    nhanViens.value = getters;
});

</script>

<style lang="css" scoped></style>