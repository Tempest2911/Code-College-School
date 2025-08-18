<script setup>
import { reactive, ref } from "vue";
import FormTacGia from "./FormTacGia.vue";
import TableTacGia from "./TableTacGia.vue";

const list = ref([
  { ten: "Nguyễn Duy Phong", gioiTinh: true,  diaChi: "Hà Nội",  tuoi: 20 },
  { ten: "Nguyễn Bảo Trí",   gioiTinh: false, diaChi: "TP.HCM", tuoi: 22 },
  { ten: "Trần Hoàng Cường", gioiTinh: true,  diaChi: "Đà Nẵng", tuoi: 25 }
]);

const form = reactive(newForm());
const isEdit = ref(false);
let current = -1;

function newForm() { return { ten: "", gioiTinh: true, diaChi: "", tuoi: 0 }; }
function reset() { Object.assign(form, newForm()); isEdit.value = false; current = -1; }

const addTacGia    = (tg) => (list.value.push({ ...tg }), reset());
const editTacGia   = (i)  => (Object.assign(form, list.value[i]), current=i, isEdit.value=true);
const updateTacGia = (tg) => (current>=0 ? list.value[current] = { ...tg } : null, reset());
const deleteTacGia = (i)  => list.value.splice(i,1);
</script>

<template>
  <div class="container mt-4">
    <h2>Quản lý Tác Giả</h2>

    <FormTacGia
      :modelValue="form"
      :isEdit="isEdit"
      @add="addTacGia"
      @update="updateTacGia"
      @cancel="reset"
    />

    <TableTacGia
      :list="list"
      @edit="editTacGia"
      @delete="deleteTacGia"
    />
  </div>
</template>
