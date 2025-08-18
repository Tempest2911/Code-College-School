<script setup>
import { ref, reactive, computed } from "vue";

const list = ref([
    { id: 1, tenXeMay: "Ducati Lamborghini", hang: "Lamborghini", dungTich: 586, loaiXe: "Xe số" },
    { id: 2, tenXeMay: "Aston Martin baka", hang: "Aston Martin", dungTich: 496, loaiXe: "Xe số" },
    { id: 3, tenXeMay: "McLaren 720S", hang: "McLaren", dungTich: 291, loaiXe: "Xe ga" }
]);

const form = reactive({
    id: null,
    tenXeMay: "",
    hang: "",
    dungTich: 0,
    loaiXe: "Xe số"
});

const isEdit = ref(false);
let current = ref(-1);
const keyword = ref("");
const keywordInput = ref("");

const filteredList = computed(() =>
    list.value.filter((p) =>
        p.dungTich.toString().includes(keyword.value.toLowerCase())
    )
);

function reset() {
    Object.assign(form, { id: null, tenXeMay: "", hang: "", dungTich: 0, loaiXe: "Xe số" });
    isEdit.value = false;
    current = -1;
}


const addXeMay = () => {
    list.value.push({ ...form });
    reset();
};


const deleteXeMay = (i) => {
    const item = filteredList.value[i];
    list.value = list.value.filter((p) => p.id !== item.id);

};

const doSearch = () => {
    keyword.value = keywordInput.value.trim();
}

</script>

<template>
    <div class="container mt-4">
        <h1 class="text-center">Quản lý xe máy</h1>

        <form @submit.prevent="isEdit ? updateXeMay() : addXeMay()" class="mb-4">
            <div class="mb-2">
                <label class="form-label">Tên:</label>
                <input v-model="form.tenXeMay" class="form-control" />
            </div>

            <div class="mb-2">
                <label class="form-label">Hãng:</label>
                <input v-model="form.hang" class="form-control" />
            </div>

            <div class="mb-2">
                <label class="form-label">Dung Tích:</label>
                <input v-model.number="form.dungTich" type="number" class="form-control" />
            </div>

            <div class="mb-2">
                <label class="form-label">Loại xe:</label>
                <div class="d-flex">
                    <div class="form-check me-3" v-for="h in ['Xe số', 'Xe ga']" :key="h">
                        <input class="form-check-input" type="radio" v-model="form.loaiXe" :value="h" :id="h" />
                        <label class="form-check-label" :for="h">{{ h }}</label>
                    </div>
                </div>
            </div>

            <button class="btn btn-primary">{{ isEdit ? 'Update' : 'Add' }}</button>
            <button v-if="isEdit" type="button" class="btn btn-secondary ms-2" @click="reset">Cancel</button>
        </form>

        <div class="mb-3 d-flex">
            <input v-model="keywordInput" type="text" placeholder="Tìm theo dung tích..." class="form-control me-2" />
            <button type="button" class="btn btn-primary" @click="doSearch">Search</button>
        </div>

        <table class="table table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>#</th>
                    <th>Tên</th>
                    <th>Hãng</th>
                    <th>Dung tích</th>
                    <th>Loại</th>
                    <th>Hành động</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(p, i) in filteredList">
                    <td>{{ i + 1 }}</td>
                    <td>{{ p.tenXeMay }}</td>
                    <td>{{ p.hang }}</td>
                    <td>{{ p.dungTich }}</td>
                    <td>{{ p.loaiXe }}</td>
                    <td>
                        <button class="btn btn-warning btn-sm ms-1" @click="deleteXeMay(i)">Remove</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>