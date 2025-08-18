<script setup>
import { ref, reactive, computed } from "vue";

const list = ref([
    { id: 1, tenDongHo: "Rolex Sigma", loai: "Đồng hồ thời trang", diaChi: "USA", gia: 999 },
    { id: 2, tenDongHo: "Casio common", loai: "Đồng hồ trẻ em", diaChi: "Vietnam", gia: 200 },
    { id: 3, tenDongHo: "Rolex LALALA", loai: "Đồng hồ thời trang", diaChi: "Japan", gia: 999 }
]);

const form = reactive({
    id: null,
    tenDongHo: "",
    loai: "Đồng hồ trẻ em",
    diaChi: "USA",
    gia: 0
});

const isEdit = ref(false);
let current = -1;
const keyword = ref("");
const keywordInput = ref("");

const filteredList = computed(() =>
    list.value.filter((p) =>
        p.tenDongHo.toLowerCase().includes(keyword.value.toLowerCase())
    )
);

function reset() {
    Object.assign(form, { id: null, tenDongHo: "", loai: "Đồng hồ trẻ em", diaChi: "USA", gia: 0 });
    isEdit.value = false;
    current = -1;
}

function validateDongHo(DongHo) {

    if (!DongHo.tenDongHo.trim()) {
        alert("❌ Không được bỏ trống các trường");
        return false;
    }

    if (!Number.isInteger(DongHo.gia) || DongHo.gia <= 0) {
        window.alert("❌ Giá phải là số nguyên dương!");
        return false;
    }
    return true;
}

const addDongHo = () => {
    if (!validateDongHo(form)) return;
    list.value.push({ ...form });
    window.alert("✅ Thêm đồng hồ thành công!");
    reset();
};

const editDongHo = (i) => {
    Object.assign(form, filteredList.value[i]);
    current = list.value.findIndex((p) => p.id === form.id);
    isEdit.value = true;
};

const updateDongHo = () => {
    if (!validateDongHo(form)) return;
    if (current >= 0) list.value[current] = { ...form };
    window.alert("✅ Cập nhật đồng hồ thành công!");
    reset();
};

const deleteDongHo = (i) => {
    const item = filteredList.value[i];
    if (confirm("Bạn có chắc muốn xoá đồng hồ này?")) {
        list.value = list.value.filter((p) => p.id !== item.id);
        window.alert("🗑️ Xoá thành công!");
    }
};

const sortAsc = () => {
    list.value.sort((a, b) => a.gia - b.gia);
};

const sortDesc = () => {
    list.value.sort((a, b) => b.gia - a.gia);
};

const doSearch = () => {
    keyword.value = keywordInput.value.trim();
};

</script>

<template>
    <div class="container mt-4">
        <h2>Quản lý đồng hồ</h2>


        <form @submit.prevent="isEdit ? updateDongHo() : addDongHo()" class="mb-4">
            <div class="mb-2">
                <label class="form-label">Tên:</label>
                <input v-model="form.tenDongHo" class="form-control" />
            </div>

            <div class="mb-2">
                <label class="form-label">Loại:</label>
                <div class="d-flex">
                    <div class="form-check me-3" v-for="h in ['Đồng hồ trẻ em', 'Đồng hồ thời trang']" :key="h">
                        <input class="form-check-input" type="radio" v-model="form.loai" :value="h" :id="h" />
                        <label class="form-check-label" :for="h">{{ h }}</label>
                    </div>
                </div>
            </div>

            <div class="mb-2">
                <label class="form-label">Địa chỉ:</label>
                <select v-model="form.diaChi" class="form-select">
                    <option>USA</option>
                    <option>Vietnam</option>
                    <option>Japan</option>
                    <option>South Korea</option>
                </select>
            </div>

            <div class="mb-2">
                <label class="form-label">Giá:</label>
                <input v-model.number="form.gia" type="number" class="form-control" />
            </div>

            <button class="btn btn-primary">{{ isEdit ? 'Update' : 'Add' }}</button>
            <button v-if="isEdit" type="button" class="btn btn-secondary ms-2" @click="reset">Cancel</button>
        </form>

        <div class="mb-3">
            <button class="btn btn-success me-2" @click="sortAsc">Sắp xếp giá ↑</button>
            <button class="btn btn-warning" @click="sortDesc">Sắp xếp giá ↓</button>
        </div>

        <div class="mb-3 d-flex">
            <input v-model="keywordInput" type="text" placeholder="Tìm theo tên..." class="form-control me-2" />
            <button type="button" class="btn btn-primary" @click="doSearch">Search</button>
        </div>

        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Tên</th>
                    <th>Loại</th>
                    <th>Địa chỉ</th>
                    <th>Giá</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(p, i) in filteredList">
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.tenDongHo }}</td>
                    <td>{{ p.loai }}</td>
                    <td>{{ p.diaChi }}</td>
                    <td>{{ p.gia }}</td>
                    <td>
                        <button class="btn btn-info btn-sm" @click="editDongHo(i)">Edit</button>
                        <button class="btn btn-danger btn-sm ms-1" @click="deleteDongHo(i)">Remove</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>