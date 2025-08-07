<template>
  <div class="container mt-4">
    <h2 class="mb-3">Thông tin Tác Giả</h2>

    <form @submit.prevent="isEdit ? updateTacGia() : addTacGia()">
      <div class="mb-2">
        <label>Mã:</label>
        <input type="number" v-model="form.ma" class="form-control" required />
      </div>

      <div class="mb-2">
        <label>Tên:</label>
        <input type="text" v-model="form.ten" class="form-control" required />
      </div>

      <div class="mb-2">
        <label>Giá:</label>
        <input type="number" v-model="form.gia" class="form-control" required />
      </div>

      <div class="mb-2">
        <label>Hãng:</label>
        <select v-model="form.hang" class="form-select">
          <option value="Yamaha">Yamaha</option>
          <option value="Honda">Honda</option>
          <option value="Suzuki">Suzuki</option>
        </select>
      </div>

      <div class="mb-2">
        <label>Giới tính:</label>
        <select v-model="form.gioiTinh" class="form-select">
          <option value="Nam">Nam</option>
          <option value="Nữ">Nữ</option>
        </select>
      </div>

      <div>
        <button class="btn btn-primary" type="submit" :disabled="isEdit">Thêm</button>
        <button class="btn btn-warning ms-2" type="button" @click="updateTacGia" :disabled="!isEdit">Sửa</button>
      </div>
    </form>

    <hr />

    <h3>Danh sách Tác Giả</h3>
    <table class="table table-bordered mt-3">
      <thead class="table-dark">
        <tr>
          <th>Mã</th>
          <th>Tên</th>
          <th>Giá</th>
          <th>Hãng</th>
          <th>Giới tính</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(t, index) in list" :key="t.ma">
          <td>{{ t.ma }}</td>
          <td>{{ t.ten }}</td>
          <td>{{ t.gia }}</td>
          <td>{{ t.hang }}</td>
          <td>{{ t.gioiTinh }}</td>
          <td>
            <button class="btn btn-info btn-sm" @click="editTacGia(index)">Chi tiết</button>
            <button class="btn btn-danger btn-sm ms-1" @click="deleteTacGia(index)">Xoá</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { reactive, ref } from 'vue';
import createEmptyTacGia from '../data/tacgia.js';

const list = ref([]);
const form = reactive(createEmptyTacGia());
const isEdit = ref(false);
let currentIndex = -1;

const addTacGia = () => {
  list.value.push({ ...form });
  Object.assign(form, createEmptyTacGia());
};

const editTacGia = (index) => {
  Object.assign(form, list.value[index]);
  currentIndex = index;
  isEdit.value = true;
};

const updateTacGia = () => {
  list.value[currentIndex] = { ...form };
  isEdit.value = false;
  Object.assign(form, createEmptyTacGia());
};

const deleteTacGia = (index) => {
  list.value.splice(index, 1);
};
</script>
