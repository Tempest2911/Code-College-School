<template>
    <div class="container">
        <button class="btn btn-primary mb-3" @click="text++">
            ban da an so lan là : {{ text }} lan
        </button>
    </div>
    <div class="container">
        <div class="row mb-2">
            <div class="col-md-6 mb-2">
            <label class="form-label">ID</label>
            <input type="text" class="form-control" v-model="Studen.id" placeholder="Nhập ID">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Tên</label>
            <input type="text" class="form-control" v-model="Studen.name" placeholder="Nhập tên">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Tuổi</label>
            <input type="number" class="form-control" v-model="Studen.age" placeholder="Nhập tuổi">
            </div>
            <div class="col-md-6 mb-2 d-flex align-items-center">
            <input type="checkbox" class="form-check-input me-2" v-model="Studen.isActive">
            <label class="form-check-label">Hoạt động</label>
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Điểm</label>
            <input type="number" class="form-control" v-model="Studen.score" placeholder="Nhập điểm">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Địa chỉ</label>
            <input type="text" class="form-control" v-model="Studen.address" placeholder="Nhập địa chỉ">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Lớp</label>
            <input type="text" class="form-control" v-model="Studen.class" placeholder="Nhập lớp">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Khoa</label>
            <input type="text" class="form-control" v-model="Studen.department" placeholder="Nhập khoa">
            </div>
            <div class="col-md-12 mb-2">
            <label class="form-label">Ghi chú</label>
            <input type="text" class="form-control" v-model="Studen.note" placeholder="Nhập ghi chú">
            </div>
            <div class="col-md-6 mb-2">
            <label class="form-label">Ngày sinh</label>
            <input type="date" class="form-control" v-model="Studen.birthday" placeholder="Nhập ngày sinh">
            </div>
        </div>
        <p><strong>ID:</strong> {{ Studen.id }}</p>
        <p><strong>Tên:</strong> {{ Studen.name }}</p>
        <p><strong>Tuổi:</strong> {{ Studen.age }}</p>
        <p><strong>Hoạt động:</strong> {{ Studen.isActive ? 'Có' : 'Không' }}</p>
        <p><strong>Điểm:</strong> {{ Studen.score }}</p>
        <p><strong>Địa chỉ:</strong> {{ Studen.address }}</p>
        <p><strong>Lớp:</strong> {{ Studen.class }}</p>
        <p><strong>Khoa:</strong> {{ Studen.department }}</p>
        <p><strong>Ghi chú:</strong> {{ Studen.note }}</p>
        <p><strong>Ngày sinh:</strong> {{ Studen.birthday }}</p>

    </div>
</template>

<script setup>
    import {ref} from 'vue';
    const text = ref(0);
    const Studen = ref({
        id: '',
        name: '',
        age: null,
        isActive: true,
        score : null,
        address: '',
        class: '',
        department: '',
        note: '',
        birthday: "2003-01-01"
        
    });
</script>