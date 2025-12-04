<template>
    <div class="container">
        <p>id:<input v-model="form.id"></p>
        <p>Name:<input v-model="form.name"></p>
        <p>Gender:
            <input v-model="form.gender" type="radio" :value="true">Male
            <input v-model="form.gender" type="radio" :value="false">Female
        </p>
        <p>Mark:<input v-model="form.mark"></p>
        
        <button @click="ctrl.create()">Create</button>
        <button @click="ctrl.update()">Update</button>
        <button @click="ctrl.delete(form)">Delete</button> 
        <button @click="ctrl.reset()">Reset</button>

        <table class="table" border="1">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Mark</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="e in list" :key="e.id">
                    <td>{{ e.id }}</td>
                    <td>{{ e.name }}</td>
                    <td>{{ e.gender ? 'Male' : 'Female' }}</td>
                    <td>{{ e.mark }}</td>
                    <td>
                        <a @click.stop.prevent="ctrl.edit(e)" href="#">Edit</a> | 
                        <a @click.stop.prevent="ctrl.delete(e)" href="#">Delete</a>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios'; // 1. Phải import axios

const form = ref({});
const list = ref([]);
const host = "http://localhost:8080/student"; // Gom URL gốc vào đây cho gọn

const ctrl = {
    init() {
        this.load();
        this.reset(); // 2. Thêm dấu () để chạy hàm
    },
    reset() { // 3. Đổi tên từ rest -> reset cho đúng chuẩn
        form.value = { id: "", name: "", gender: true, mark: 0 };
    },
    load() {
        axios.get(host).then(resp => {
            list.value = resp.data;
        }).catch(err => console.log(err));
    },
    edit(entity) {
        // Copy dữ liệu ra object mới để không bị dính 2 chiều khi chưa bấm Update
        form.value = { ...entity }; 
    },
    create() {
        var entity = form.value;
        axios.post(host, entity).then(resp => {
            this.load();
            this.reset();
            alert("Thêm thành công!");
        }).catch(err => alert("Lỗi thêm mới"));
    },
    update() {
        var entity = form.value;
        var url = `${host}/${entity.id}`;
        axios.put(url, entity).then(resp => {
            this.load();
            this.reset();
            alert("Cập nhật thành công!");
        }).catch(err => alert("Lỗi cập nhật"));
    },
    delete(entity) {
        if(!confirm("Bạn có chắc muốn xóa?")) return;
        var url = `${host}/${entity.id}`;
        axios.delete(url).then(resp => {
            this.load();
            this.reset();
        }).catch(err => alert("Lỗi xóa"));
    }
}

// Gọi hàm init khi component được load
onMounted(() => {
    ctrl.init();
});
</script>