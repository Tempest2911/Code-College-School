<template>
  <div class="container mt-4">
    <h2 class="mb-3">Thông tin Tác Giả</h2>

    <form @submit.prevent="isEdit ? updateTacGia() : addTacGia()">
      <div v-for="(label, key) in { ma: 'Mã', ten: 'Tên', gia: 'Giá' }" :key="key" class="mb-2">
        <label>{{ label }}:</label>
        <input :type="key === 'ma' || key === 'gia' ? 'number' : 'text'" v-model="form[key]" class="form-control" required />
      </div>

      <div class="mb-2">
        <label>Hãng:</label>
        <select v-model="form.hang" class="form-select">
          <option v-for="h in ['Yamaha', 'Honda', 'Suzuki']" :key="h" :value="h">{{ h }}</option>
        </select>
      </div>

      <div class="mb-2">
        <label>Giới tính:</label>
        <select v-model="form.gioiTinh" class="form-select">
          <option>Nam</option>
          <option>Nữ</option>
        </select>
      </div>

      <button class="btn btn-primary" type="submit" :disabled="isEdit">Thêm</button>
      <button class="btn btn-warning ms-2" type="button" @click="updateTacGia" :disabled="!isEdit">Sửa</button>
    </form>

    <hr />

    <h3>Danh sách Tác Giả</h3>
    <table class="table table-bordered mt-3">
      <thead class="table-dark">
        <tr>
          <th>Mã</th><th>Tên</th><th>Giá</th><th>Hãng</th><th>Giới tính</th><th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(t, i) in list" :key="t.ma">
          <td>{{ t.ma }}</td><td>{{ t.ten }}</td><td>{{ t.gia }}</td><td>{{ t.hang }}</td><td>{{ t.gioiTinh }}</td>
          <td>
            <button class="btn btn-info btn-sm" @click="editTacGia(i)">Chi tiết</button>
            <button class="btn btn-danger btn-sm ms-1" @click="deleteTacGia(i)">Xoá</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';

const list = ref([]);
const form = reactive({ ma: '', ten: '', gia: '', hang: 'Yamaha', gioiTinh: 'Nam' });
const isEdit = ref(false);
let currentIndex = -1;

const resetForm = () => Object.assign(form, { ma: '', ten: '', gia: '', hang: 'Yamaha', gioiTinh: 'Nam' });

const addTacGia = () => {
  list.value.push({ ...form });
  resetForm();
};

const editTacGia = (i) => {
  Object.assign(form, list.value[i]);
  currentIndex = i;
  isEdit.value = true;
};

const updateTacGia = () => {
  list.value[currentIndex] = { ...form };
  isEdit.value = false;
  resetForm();
};

const deleteTacGia = (i) => list.value.splice(i, 1);
</script>   