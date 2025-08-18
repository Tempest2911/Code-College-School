<script setup>
import { ref, reactive } from "vue";

const list = ref([
  { id: 1, tenKhoaHoc: "CNTT", tenGiangVien: "Phong", thoiGianHoc: "1 tháng", soLuongHoc: 200 },
  { id: 2, tenKhoaHoc: "TKDH", tenGiangVien: "Hưng", thoiGianHoc: "3 tháng", soLuongHoc: 100 },
  { id: 3, tenKhoaHoc: "Marketing", tenGiangVien: "Quyết", thoiGianHoc: "6 tháng", soLuongHoc: 220 }
]);

const form = reactive({
  id: null,
  tenKhoaHoc: "",
  tenGiangVien: "Phong",
  thoiGianHoc: "1 tháng",
  soLuongHoc: 0
});

const isEdit = ref(false);
let current = -1;

function reset() {
  Object.assign(form, { id: null, tenKhoaHoc: "", tenGiangVien: "Phong", thoiGianHoc: "1 tháng", soLuongHoc: 0 });
  isEdit.value = false;
  current = -1;
}

function validateSoLuong(soLuongHoc) {
  if (!Number.isInteger(soLuongHoc) || soLuongHoc <= 0) {
    window.alert("❌ Số lượng học phải là số nguyên dương!");
    return false;
  }
  return true;
}

const addSoLuongHoc = () => {
  if (!validateSoLuong(form.soLuongHoc)) return;
  list.value.push({ ...form, id: Date.now() });
  window.alert("✅ Thêm số lượng học thành công!");
  reset();
};

const editSoLuongHoc = (i) => {
  Object.assign(form, list.value[i]);
  current = i;
  isEdit.value = true;
};

const updateSoLuongHoc = () => {
  if (!validateSoLuong(form.soLuongHoc)) return;
  if (current >= 0) list.value[current] = { ...form };
  window.alert("✅ Cập nhật số lượng học thành công!");
  reset();
};

const deleteSoLuongHoc = (i) => {
  if (confirm("Bạn có chắc muốn xoá số lượng học này?")) {
    list.value.splice(i, 1);
    window.alert("🗑️ Xoá thành công!");
  }
};
</script>

<template>
  <div class="container mt-4">
    <h2>Quản lý Khóa học</h2>

    <!-- Form -->
    <form @submit.prevent="isEdit ? updateSoLuongHoc() : addSoLuongHoc()" class="mb-4">
      <div class="mb-2">
        <label class="form-label">Tên khóa học:</label>
        <input v-model="form.tenKhoaHoc" class="form-control" required />
      </div>

      <div class="mb-2">
        <label class="form-label">Thời gian học:</label>
        <select v-model="form.thoiGianHoc" class="form-select">
          <option>1 tháng</option>
          <option>3 tháng</option>
          <option>6 tháng</option>
          <option>9 tháng</option>
        </select>
      </div>

      <div class="mb-2">
        <label class="form-label">Tên giảng viên:</label>
        <div class="d-flex">
          <div class="form-check me-3" v-for="h in ['Phong', 'Hưng', 'Quyết', 'Tú']" :key="h">
            <input class="form-check-input" type="radio" v-model="form.tenGiangVien" :value="h" :id="h" />
            <label class="form-check-label" :for="h">{{ h }}</label>
          </div>
        </div>
      </div>

      <div class="mb-2">
        <label class="form-label">Số lượng học:</label>
        <input v-model.number="form.soLuongHoc" type="number" class="form-control" required />
      </div>

      <button class="btn btn-primary">{{ isEdit ? 'Update' : 'Add' }}</button>
      <button v-if="isEdit" type="button" class="btn btn-secondary ms-2" @click="reset">Cancel</button>
    </form>

    <!-- Table -->
    <table class="table table-bordered">
      <thead class="table-dark">
        <tr>
          <th>#</th>
          <th>Tên khóa học</th>
          <th>Tên giảng viên</th>
          <th>Thời gian học</th>
          <th>Số lượng học</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(p, i) in list" :key="p.id">
          <td>{{ i + 1 }}</td>
          <td>{{ p.tenKhoaHoc }}</td>
          <td>{{ p.tenGiangVien }}</td>
          <td>{{ p.thoiGianHoc }}</td>
          <td>{{ p.soLuongHoc }}</td>
          <td>
            <button class="btn btn-info btn-sm" @click="editSoLuongHoc(i)">Edit</button>
            <button class="btn btn-danger btn-sm ms-1" @click="deleteSoLuongHoc(i)">Remove</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
