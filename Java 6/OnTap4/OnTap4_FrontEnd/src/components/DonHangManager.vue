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
                <tr v-for="donHang in donHangs">
                    <td>{{ donHang.id }}</td>
                    <td>{{ donHang.maDonHang }}</td>
                    <td>{{ donHang.ngayDat }}</td>
                    <td>{{ donHang.tongTien }}</td>
                    <td>{{ donHang.tenKhachHang }}</td>
                    <td>{{ donHang.diaChi }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getAllDonHang } from "./DonHangService.ts";

const donHangs = ref([]);

onMounted(async () => {
    const getters = await getAllDonHang();

    donHangs.value = getters;
});

</script>

<style lang="css" scoped></style>