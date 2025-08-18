<template>
  <table class="table table-bordered mt-3">
    <thead class="table-dark">
      <tr>
        <th>#</th><th>Tên</th><th>Giới tính</th><th>Địa chỉ</th><th>Tuổi</th><th>Hành động</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(t,i) in list" :key="i">
        <td>{{ i+1 }}</td>
        <td>{{ t.ten }}</td>
        <td>{{ t.gioiTinh ? 'Nam':'Nữ' }}</td>
        <td>{{ t.diaChi }}</td>
        <td>{{ t.tuoi }}</td>
        <td>
          <button class="btn btn-info btn-sm" @click="$emit('edit', i)">Sửa</button>
          <button class="btn btn-danger btn-sm ms-1" @click="$emit('delete', i)">Xoá</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
defineProps({ list: Array });
defineEmits(["edit", "delete"]);
</script>
