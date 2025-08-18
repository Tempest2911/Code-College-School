<template>
  <form @submit.prevent="isEdit ? $emit('update', form) : $emit('add', form)">
    <div class="row mb-3">
      <label class="col-sm-2 col-form-label">Tên</label>
      <div class="col-sm-10"><input v-model="form.ten" class="form-control" required></div>
    </div>

    <div class="row mb-3">
      <label class="col-sm-2 col-form-label">Giới tính</label>
      <div class="col-sm-10">
        <label class="me-3"><input type="radio" v-model="form.gioiTinh" :value="true"> Nam</label>
        <label><input type="radio" v-model="form.gioiTinh" :value="false"> Nữ</label>
      </div>
    </div>

    <div class="row mb-3">
      <label class="col-sm-2 col-form-label">Địa chỉ</label>
      <div class="col-sm-10"><input v-model="form.diaChi" class="form-control"></div>
    </div>z 

    <div class="row mb-3">
      <label class="col-sm-2 col-form-label">Tuổi</label>
      <div class="col-sm-10"><input v-model.number="form.tuoi" type="number" class="form-control"></div>
    </div>

    <div class="row">
      <div class="col-sm-10 offset-sm-2">
        <button class="btn btn-primary">{{ isEdit ? 'Cập nhật' : 'Thêm' }}</button>
        <button v-if="isEdit" type="button" class="btn btn-secondary ms-2" @click="$emit('cancel')">Huỷ</button>
      </div>
    </div>
  </form>
</template>

<script setup>
import { reactive, watch } from "vue";

const props = defineProps({ modelValue: Object, isEdit: Boolean });
defineEmits(["add", "update", "cancel"]);

const form = reactive({ ...props.modelValue });
watch(() => props.modelValue, (v) => Object.assign(form, v), { deep: true });
</script>
