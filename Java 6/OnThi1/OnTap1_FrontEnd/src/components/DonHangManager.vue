<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0 mb-4">
            <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
                <h3 class="text-center text-uppercase fw-bold m-0 text-primary">Quản Lý Đơn Hàng</h3>
                <button @click="logout" class="btn btn-outline-danger btn-sm">
                    Đăng Xuất <i class="bi bi-box-arrow-right"></i>
                </button>
            </div>

            <div class="card-body">
                <div class="row g-3 mb-4">
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Mã Đơn Hàng</label>
                        <input v-model="form.maDonHang" class="form-control" placeholder="Nhập mã đơn...">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Ngày Đặt</label>
                        <input v-model="form.ngayDat" type="date" class="form-control">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Tổng Tiền</label>
                        <input v-model="form.tongTien" type="number" class="form-control" placeholder="Nhập số tiền...">
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">ID Khách Hàng</label>
                        <input v-model="form.khachHangId" type="number" class="form-control" placeholder="Nhập ID...">
                    </div>
                </div>

                <div class="d-flex justify-content-center gap-2 mb-4">
                    <button @click="ctrl.create()" class="btn btn-success px-4">Thêm Mới</button>
                    <button @click="ctrl.update()" class="btn btn-warning px-4 text-white">Cập Nhật</button>
                    <button @click="ctrl.reset()" class="btn btn-secondary px-4">Làm Mới</button>
                </div>

                <div v-if="message" :class="{ 'alert-success': !isError, 'alert-danger': isError }"
                    class="alert text-center py-2">
                    {{ message }}
                </div>

                <div class="table-responsive">
                    <table class="table table-bordered table-hover align-middle shadow-sm">
                        <thead class="table-dark text-center">
                            <tr>
                                <th>ID</th>
                                <th>Mã Đơn</th>
                                <th>Ngày Đặt</th>
                                <th>Tổng Tiền</th>
                                <th>Khách Hàng</th>
                                <th>Địa Chỉ</th>
                                <th>Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="dh in listDonHang" :key="dh.id">
                                <td class="text-center fw-bold">{{ dh.id }}</td>
                                <td class="text-center text-primary">{{ dh.maDonHang }}</td>
                                <td class="text-center">{{ dh.ngayDat }}</td>
                                <td class="text-end fw-bold">{{ dh.tongTien.toLocaleString() }} đ</td>
                                <td>{{ dh.tenKhachHang }}</td>
                                <td>{{ dh.diaChi }}</td>
                                <td class="text-center">
                                    <div class="btn-group">
                                        <button @click="ctrl.edit(dh)"
                                            class="btn btn-sm btn-warning text-white">Sửa</button>
                                        <button @click="ctrl.delete(dh.id)" class="btn btn-sm btn-danger">Xóa</button>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="d-flex justify-content-center align-items-center gap-2 mt-4 p-2 bg-light rounded">
                    <button class="btn btn-outline-secondary" @click="changePage(currentPage - 1)"
                        :disabled="currentPage === 0">&laquo; Trước</button>
                    <span class="fw-bold px-3 text-primary">Trang {{ currentPage + 1 }} / {{ totalPages }}</span>
                    <button class="btn btn-outline-secondary" @click="changePage(currentPage + 1)"
                        :disabled="currentPage === totalPages - 1">Sau &raquo;</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';

// Emit sự kiện logout ra ngoài App.vue
const emit = defineEmits(['logout']);

const listDonHang = ref([]);
const form = ref({ id: null, maDonHang: "", ngayDat: "", tongTien: 0, khachHangId: 1 });
const message = ref("");
const isError = ref(false);
const currentPage = ref(0);
const totalPages = ref(0);

const API_BASE = 'http://localhost:8080/api/don-hang';

// --- HÀM LẤY CONFIG TỪ LOCALSTORAGE ---
const getAuthConfig = () => {
    const storedAuth = localStorage.getItem('user_auth');
    if (storedAuth) {
        return { auth: JSON.parse(storedAuth) };
    }
    return null;
};

// Hàm đăng xuất
const logout = () => {
    if (confirm("Bạn muốn đăng xuất?")) {
        localStorage.removeItem('user_auth'); // Xóa token
        emit('logout'); // Báo cho App cha biết để chuyển về màn hình Login
    }
};

const loadData = async (page = 0) => {
    const config = getAuthConfig();
    if (!config) { logout(); return; } // Nếu không có pass thì logout luôn

    try {
        const resp = await axios.get(`${API_BASE}/page?page=${page}`, config);
        listDonHang.value = resp.data.content;
        totalPages.value = resp.data.totalPages;
        currentPage.value = resp.data.number;
    } catch (err) { handleError(err); }
};

const changePage = (page) => {
    if (page >= 0 && page < totalPages.value) loadData(page);
};

const ctrl = {
    edit(entity) {
        form.value = { ...entity, khachHangId: 1 };
        window.scrollTo({ top: 0, behavior: 'smooth' });
    },
    reset() {
        form.value = { id: null, maDonHang: "", ngayDat: "", tongTien: 0, khachHangId: 1 };
        message.value = "";
    },
    async create() {
        const config = getAuthConfig();
        const data = { ...form.value, khachHang: { id: form.value.khachHangId } };
        try {
            await axios.post(API_BASE, data, config);
            showMessage("Thêm mới thành công!");
            loadData(0);
            this.reset();
        } catch (err) { handleError(err); }
    },
    async update() {
        if (!form.value.id) return showMessage("Chưa chọn đơn hàng!", true);
        const config = getAuthConfig();
        const data = { ...form.value, khachHang: { id: form.value.khachHangId } };
        try {
            await axios.put(`${API_BASE}/${form.value.id}`, data, config);
            showMessage("Cập nhật thành công!");
            loadData(currentPage.value);
        } catch (err) { handleError(err); }
    },
    async delete(id) {
        if (!confirm("Xóa đơn hàng này?")) return;
        const config = getAuthConfig();
        try {
            await axios.delete(`${API_BASE}/${id}`, config);
            showMessage("Xóa thành công!");
            loadData(currentPage.value);
        } catch (err) { handleError(err); }
    }
};

const showMessage = (msg, error = false) => {
    message.value = msg;
    isError.value = error;
    if (!error) setTimeout(() => message.value = "", 3000);
};

const handleError = (err) => {
    if (err.response && err.response.status === 401) {
        alert("Phiên đăng nhập hết hạn!");
        logout();
        return;
    }
    // Code xử lý lỗi cũ...
    let msg = "Có lỗi xảy ra!";
    if (err.response && err.response.data) {
        const data = err.response.data;
        if (Array.isArray(data)) msg = data.map(e => e.defaultMessage).join(", ");
        else if (data.message) msg = data.message;
        else msg = String(data);
    } else msg = err.message;
    showMessage("Lỗi: " + msg, true);
};

onMounted(() => { loadData(0); });
</script>