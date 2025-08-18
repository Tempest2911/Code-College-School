<script setup>
import { ref, reactive } from "vue";

const list = ref([
  { id: 1, ten: "Xiaomi K60", hang: "Xiaomi", dungLuong: "256GB", gia: 10000000 },
  { id: 2, ten: "iPhone 15", hang: "iPhone", dungLuong: "128GB", gia: 25000000 },
  { id: 3, ten: "Samsung S24", hang: "Samsung", dungLuong: "512GB", gia: 22000000 }
]);

const form = reactive({
  id: null,
  ten: "",
  hang: "Samsung",
  dungLuong: "64GB",
  gia: 0
});

const isEdit = ref(false);
let current = -1;

function reset() {
  Object.assign(form, { id: null, ten: "", hang: "Samsung", dungLuong: "64GB", gia: 0 });
  isEdit.value = false;
  current = -1;
}

function validateGia(gia) {
  if (!Number.isInteger(gia) || gia <= 100) {
    window.alert("❌ Giá phải là số nguyên dương lớn hơn 100!");
    return false;
  }
  return true;
}

const addPhone = () => {
  if (!validateGia(form.gia)) return;
  list.value.push({ ...form, id: Date.now() });
  window.alert("✅ Thêm điện thoại thành công!");
  reset();
};

const editPhone = (i) => {
  Object.assign(form, list.value[i]);
  current = i;
  isEdit.value = true;
};

const updatePhone = () => {
  if (!validateGia(form.gia)) return;
  if (current >= 0) list.value[current] = { ...form };
  window.alert("✅ Cập nhật điện thoại thành công!");
  reset();
};

const deletePhone = (i) => {
  if (confirm("Bạn có chắc muốn xoá điện thoại này?")) {
    list.value.splice(i, 1);
    window.alert("🗑️ Xoá thành công!");
  }
};
</script>

<template>
  <div class="container mt-4">
    <h2>Quản lý Điện thoại</h2>

    <!-- Form -->
    <form @submit.prevent="isEdit ? updatePhone() : addPhone()" class="mb-4">
      <div class="mb-2">
        <label class="form-label">Tên:</label>
        <input v-model="form.ten" class="form-control" required />
      </div>

      <div class="mb-2">
        <label class="form-label">Hãng:</label>
        <div class="d-flex">
          <div class="form-check me-3" v-for="h in ['Samsung', 'iPhone', 'Xiaomi', 'Oppo']" :key="h">
            <input class="form-check-input" type="radio" v-model="form.hang" :value="h" :id="h" />
            <label class="form-check-label" :for="h">{{ h }}</label>
          </div>
        </div>
      </div>

      <div class="mb-2">
        <label class="form-label">Dung lượng:</label>
        <select v-model="form.dungLuong" class="form-select">
          <option>64GB</option>
          <option>128GB</option>
          <option>256GB</option>
          <option>512GB</option>
          <option>1TB</option>
        </select>
      </div>

      <div class="mb-2">
        <label class="form-label">Giá:</label>
        <input v-model.number="form.gia" type="number" class="form-control" required />
      </div>

      <button class="btn btn-primary">{{ isEdit ? 'Cập nhật' : 'Thêm' }}</button>
      <button v-if="isEdit" type="button" class="btn btn-secondary ms-2" @click="reset">Huỷ</button>
    </form>

    <!-- Table -->
    <table class="table table-bordered">
      <thead class="table-dark">
        <tr>
          <th>#</th>
          <th>Tên</th>
          <th>Hãng</th>
          <th>Dung lượng</th>
          <th>Giá</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(p, i) in list" :key="p.id">
          <td>{{ i + 1 }}</td>
          <td>{{ p.ten }}</td>
          <td>{{ p.hang }}</td>
          <td>{{ p.dungLuong }}</td>
          <td>{{ p.gia.toLocaleString() }} đ</td>
          <td>
            <button class="btn btn-info btn-sm" @click="editPhone(i)">Sửa</button>
            <button class="btn btn-danger btn-sm ms-1" @click="deletePhone(i)">Xoá</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
