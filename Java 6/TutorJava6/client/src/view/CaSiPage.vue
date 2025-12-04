<template>
    <FormCaSiComponent></FormCaSiComponent>
    <table border="1" cellpadding="10" cellspacing="0">
        <thead>
            <tr>
                <th>STT</th>
                <th>Tên ca sĩ</th>
                <th>Quê</th>
                <th>Tuổi</th>
                <th>SĐT</th>
            </tr>
        </thead>
        <tbody>
            <template v-for="(item, index) in danhSachCaSi" :key="item.id">
                <tr>
                    <td>{{index + 1}}</td>
                    <td>{{item.tenCaSi}}</td>
                    <td>{{item.que}}</td>
                    <td>{{item.tuoi}}</td>
                    <td>{{item.soDienThoai}}</td>
                    <td>
                        <button @click="handleRemove(item.id)">Xóa</button>
                    </td>
                </tr>
            </template>
        </tbody>
    </table>
</template>

<script setup>
import FormCaSiComponent from '@/components/FormCaSiComponent.vue';
import { getDanhSachCaSi, deleteCaSi } from '@/service/CaSiService';
import { handleError, onMounted, ref } from 'vue';
const danhSachCaSi = ref([]);
//goi ham getDanhSachCaSi
const fetchDanhSachCaSi = async () => {
   danhSachCaSi.value = await getDanhSachCaSi();
};
// vao 1 phat khoi tao gia tri luon => onMounted
onMounted(fetchDanhSachCaSi)

const handleRemove =  async (id) => {
    await deleteCaSi(id);
    await fetchDanhSachCaSi();
    console.log("Xoa ca si: ", id);
};
</script>

<style scoped></style>