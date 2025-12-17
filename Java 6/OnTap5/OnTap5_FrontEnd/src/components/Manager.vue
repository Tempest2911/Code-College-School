<template>
    <div>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã đơn hàng</th>
                    <th>Ngày đặt</th>
                    <th>Tổng tiền</th>
                    <th>Tên sản phẩm</th>
                    <th>Nhà sản xuất</th>
                

                </tr>
            </thead>
            <tbody>
                <tr v-for="donHang in donHangs">
                    <td>{{ donHang.id }}</td>
                    <td>{{ donHang.maDonHang }}</td>
                    <td>{{ donHang.ngayDat }}</td>
                    <td>{{ donHang.tongTien }}</td>
                    <td>{{ donHang.tenSanPham }}</td>
                    <td>{{ donHang.nhaSanXuat }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getAllDonDatHang } from "./Service.ts";

const donHangs = ref([]);

onMounted(async () => {
    const getters = await getAllDonDatHang();

    donHangs.value = getters;
});

</script>