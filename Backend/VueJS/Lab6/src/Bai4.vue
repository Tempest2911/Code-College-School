<template>
    <h1 class="text-center mt-5">Bài 4</h1>
    <div class="container  mb-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <form @submit.prevent="submitForm" >
                    <h3>Thêm học sinh</h3>
                    <div class="mb-3 mt-3">
                        <label for="name">Họ tên: </label>
                        <input type="text" class="form-control" id="name" v-model="student.name" required />
                    </div>

                    <div class="mb-3">
                        <label for="name">Điểm: </label>
                        <input type="number" class="form-control" id="score" v-model="student.score" min="0" max="10"
                            step="0.1" required />
                    </div>

                    <div class="mb-3">
                        <label for="name">Ngày sinh: </label>
                        <input type="date" class="form-control" id="dob" v-model="student.dob" required />
                    </div>

                    <button type="submit" class="btn btn-success">{{ isEditing ? 'Cập nhật' : 'Thêm' }}</button>
                </form>
            </div>

            <div class="col-md-7">
                <h3 class="mt-5">Danh sách học sinh</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Họ tên</th>
                            <th>Điểm</th>
                            <th>Ngày sinh</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(student, index) in students" :key="index">
                            <td>{{ student.name }}</td>
                            <td>{{ student.score }}</td>
                            <td>{{ student.dob }}</td>
                            <td>
                                <button class="btn btn-primary" @click="editStudent(index)">Sửa</button>
                                <button class="btn btn-danger" @click="deleteStudent(index)">Xóa</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


</template>

<script setup>
import { ref } from 'vue'

const students = ref([]);
const student = ref({ name: '', score: null, dob: '' });
const isEditing = ref(false);
const currentIndex = ref(null);
const submitForm = () => {
    if (isEditing.value) {
        students.value[currentIndex.value] = { ...student.value };
    } else {
        students.value.push({ ...student.value });
    }
    resetForm();
};
const resetForm = () => {
    student.value = { name: '', score: null, dob: '' };
    isEditing.value = false;
    currentIndex.value = null;
};
const editStudent = (index) => {
    student.value = { ...students.value[index] };
    isEditing.value = true;
    currentIndex.value = index;
};
const deleteStudent = (index) => {
    students.value.splice(index, 1);
    resetForm();
};
</script>