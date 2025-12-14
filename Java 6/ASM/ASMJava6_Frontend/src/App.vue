<script setup>
import { ref, onMounted, computed, watch } from 'vue';
import axios from 'axios';

// --- STATE ---
const products = ref([]);
const categories = ref([]);
const accounts = ref([]);
const orders = ref([]);
const cart = ref([]);
const loading = ref(true);
const currentView = ref('home');
const address = ref('');
const currentUser = ref(null);
const selectedProduct = ref(null); // Sản phẩm đang xem chi tiết

// --- THÊM BIẾN MỚI CHO TÌM KIẾM ---
const searchText = ref('');
const selectedCategory = ref(null); // null nghĩa là chọn tất cả

// --- THÊM HÀM LỌC SẢN PHẨM ---
const filteredProducts = computed(() => {
  return products.value.filter(p => {
    // 1. Lọc theo danh mục (Nếu có chọn)
    const matchCategory = selectedCategory.value ? p.category.id === selectedCategory.value : true;
    // 2. Lọc theo tên tìm kiếm
    const matchSearch = p.name.toLowerCase().includes(searchText.value.toLowerCase());

    return matchCategory && matchSearch;
  });
});

// Form data
const loginForm = ref({ username: '', password: '' });
const productForm = ref({ id: null, name: '', price: 0, image: '', category: { id: 'CAR' } });
const profileForm = ref({ fullname: '', email: '', password: '', newPassword: '' }); // Form đổi thông tin

// --- AUTH ---
const login = async () => {
  try {
    const resp = await axios.post('http://localhost:8080/rest/accounts/login', loginForm.value);
    currentUser.value = resp.data;
    localStorage.setItem('user', JSON.stringify(resp.data));
    alert("Xin chào " + resp.data.fullname);
    currentView.value = 'home';
  } catch (e) { alert("Lỗi: " + (e.response?.data?.message || "Sai thông tin!")); }
};

const logout = () => { currentUser.value = null; localStorage.removeItem('user'); currentView.value = 'login'; };
const isAdmin = computed(() => currentUser.value?.roles?.includes('ADMIN'));

// --- PRODUCT DETAILS (1.1) ---
const viewProduct = (p) => {
  selectedProduct.value = p;
  currentView.value = 'product-detail';
}

// --- PROFILE MANAGEMENT (1.3) ---
const openProfile = () => {
  // Copy thông tin hiện tại vào form
  profileForm.value = {
    fullname: currentUser.value.fullname,
    email: currentUser.value.email,
    password: '', newPassword: ''
  };
  currentView.value = 'profile';
}

const updateProfile = async () => {
  try {
    const updateData = {
      fullname: profileForm.value.fullname,
      email: profileForm.value.email
    };

    // Nếu có nhập mật khẩu mới
    if (profileForm.value.newPassword) {
      updateData.password = "{noop}" + profileForm.value.newPassword; // Thêm {noop} cho khớp DB mẫu
    }

    const { data } = await axios.put(`http://localhost:8080/rest/accounts/${currentUser.value.username}`, updateData);

    // Cập nhật lại session
    currentUser.value = { ...currentUser.value, ...data };
    localStorage.setItem('user', JSON.stringify(currentUser.value));
    alert("Cập nhật hồ sơ thành công!");
  } catch (e) { alert("Lỗi cập nhật!"); }
}

// --- ADMIN FEATURES ---
const loadAccounts = async () => { const { data } = await axios.get('http://localhost:8080/rest/accounts'); accounts.value = data; };
const toggleActive = async (acc) => {
  acc.activated = !acc.activated;
  try { await axios.put(`http://localhost:8080/rest/accounts/${acc.username}`, { activated: acc.activated }); }
  catch (e) { acc.activated = !acc.activated; alert("Lỗi!"); }
};
// (Các hàm Product CRUD giữ nguyên như cũ)
const saveProduct = async () => { /* ...code cũ... */
  try {
    if (productForm.value.id) await axios.put(`http://localhost:8080/rest/products/${productForm.value.id}`, productForm.value);
    else await axios.post('http://localhost:8080/rest/products', productForm.value);
    alert("Lưu thành công!"); loadProducts(); currentView.value = 'admin-products';
  } catch (e) { alert("Lỗi!"); }
};
const deleteProduct = async (id) => { if (confirm("Xóa?")) { await axios.delete(`http://localhost:8080/rest/products/${id}`); loadProducts(); } };
const createNewProduct = () => { productForm.value = { id: null, name: '', price: 0, image: 'new.jpg', category: { id: 'CAR' } }; currentView.value = 'product-form'; };
const editProduct = (p) => { productForm.value = { ...p, category: { id: p.category.id } }; currentView.value = 'product-form'; };

// --- ORDERS & COMMON ---
const loadOrders = async () => {
  let url = isAdmin.value ? 'http://localhost:8080/rest/orders' : `http://localhost:8080/rest/orders/user/${currentUser.value.username}`;
  const { data } = await axios.get(url); orders.value = data.sort((a, b) => b.id - a.id);
};

const checkout = async () => {
  // 1. Kiểm tra giỏ hàng có trống không (QUAN TRỌNG)
  if (cart.value.length === 0) {
    alert("Giỏ hàng đang trống! Vui lòng chọn sản phẩm trước.");
    return; // Dừng lại ngay, không cho chạy tiếp
  }

  // 2. Kiểm tra đã đăng nhập chưa
  if (!currentUser.value) {
    alert("Bạn cần đăng nhập để mua hàng!");
    currentView.value = 'login'; // Chuyển hướng người dùng sang trang login luôn cho tiện
    return;
  }

  // 3. Kiểm tra đã nhập địa chỉ chưa
  if (!address.value.trim()) { // .trim() để tránh trường hợp người dùng chỉ nhập dấu cách
    alert("Vui lòng nhập địa chỉ nhận hàng!");
    return;
  }

  // 4. Gửi yêu cầu đặt hàng lên Server
  try {
    const orderData = {
      username: currentUser.value.username,
      address: address.value,
      cart: cart.value
    };

    await axios.post('http://localhost:8080/rest/orders', orderData);

    // Thành công
    alert("Đặt hàng thành công!");
    cart.value = []; // Xóa sạch giỏ hàng
    address.value = ''; // Xóa địa chỉ (nếu muốn)
    currentView.value = 'my-orders'; // Chuyển sang trang xem đơn hàng
    loadOrders(); // Tải lại danh sách đơn hàng mới nhất
  } catch (e) {
    console.error(e);
    alert("Lỗi đặt hàng! Vui lòng thử lại sau.");
  }
};

const loadProducts = async () => { const { data } = await axios.get('http://localhost:8080/rest/products'); products.value = data; loading.value = false; };
const loadCategories = async () => { const { data } = await axios.get('http://localhost:8080/rest/categories'); categories.value = data; };
// Tìm hàm addToCart cũ và thay bằng hàm này:
const addToCart = (p) => {
  // 1. Kiểm tra đăng nhập
  if (!currentUser.value) {
    alert("Bạn cần đăng nhập để mua hàng!");
    currentView.value = 'login'; // Chuyển ngay sang trang login
    return;
  }

  // 2. Nếu đã đăng nhập thì cho thêm vào giỏ
  const idx = cart.value.findIndex(i => i.id === p.id);
  if (idx !== -1) {
    cart.value[idx].qty++;
  } else {
    cart.value.push({ ...p, qty: 1 });
  }
  alert("Đã thêm " + p.name + " vào giỏ!");
}; const removeFromCart = (id) => cart.value = cart.value.filter(i => i.id !== id);
const totalQty = computed(() => cart.value.reduce((t, i) => t + i.qty, 0));
const totalAmount = computed(() => cart.value.reduce((t, i) => t + (i.qty * i.price), 0));
const formatPrice = (v) => v?.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".") + ' đ';
const getImageUrl = (img) => img ? `http://localhost:8080/images/${img}` : '';
const formatDate = (date) => new Date(date).toLocaleString('vi-VN');

watch(cart, (v) => localStorage.setItem('cart', JSON.stringify(v)), { deep: true });
onMounted(() => { loadProducts(); loadCategories(); const c = localStorage.getItem('cart'); if (c) cart.value = JSON.parse(c); const u = localStorage.getItem('user'); if (u) currentUser.value = JSON.parse(u); });
</script>


<template>
  <div class="container-fluid p-0 bg-light min-vh-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary px-4 shadow-sm sticky-top">
      <a class="navbar-brand fw-bold" href="#" @click.prevent="currentView = 'home'">J6 SHOP</a>
      <div class="ms-auto d-flex align-items-center gap-2">

        <div v-if="isAdmin" class="btn-group me-2">
          <button class="btn btn-danger fw-bold" @click="currentView = 'admin-products'">QL Hàng</button>
          <button class="btn btn-danger fw-bold" @click="loadAccounts(); currentView = 'admin-accounts'">QL
            User</button>
          <button class="btn btn-danger fw-bold" @click="loadOrders(); currentView = 'admin-orders'">QL Đơn</button>
        </div>

        <div v-if="!currentUser">
          <button class="btn btn-light text-primary fw-bold" @click="currentView = 'login'">
            <i class="fas fa-sign-in-alt me-1"></i> Đăng nhập
          </button>
        </div>

        <div v-else class="text-white d-flex align-items-center gap-2">
          <button class="btn btn-outline-light border-0 fw-bold" @click="openProfile">
            <i class="fas fa-user-circle me-1"></i> {{ currentUser.fullname }}
          </button>

          <button class="btn btn-sm btn-outline-light" @click="loadOrders(); currentView = 'my-orders'">
            <i class="fas fa-history me-1"></i> Đơn hàng
          </button>

          <button class="btn btn-sm
           btn-warning position-relative  ms-2 " @click="currentView = 'cart'">
            <i class="fas fa-shopping-cart me-1"></i> Giỏ hàng
            <span
              class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger border border-light">
              {{ totalQty }}
            </span>
          </button>

          <button class="btn btn-sm btn-dark ms-2" @click="logout">
            <i class="fas fa-sign-out-alt"></i> Đăng xuất
          </button>
        </div>
      </div>
    </nav>

    <div v-if="currentView === 'home'" class="container py-5">

      <div class="row mb-4 align-items-center">
        <div class="col-md-8 mb-2">
          <div class="btn-group shadow-sm">
            <button class="btn fw-bold" :class="selectedCategory === null ? 'btn-primary' : 'btn-outline-primary'"
              @click="selectedCategory = null">
              Tất cả
            </button>
            <button v-for="c in categories" :key="c.id" class="btn fw-bold"
              :class="selectedCategory === c.id ? 'btn-primary' : 'btn-outline-primary'"
              @click="selectedCategory = c.id">
              {{ c.name }}
            </button>
          </div>
        </div>

        <div class="col-md-4 mb-2">
          <div class="input-group shadow-sm">
            <input v-model="searchText" class="form-control" placeholder="Tìm siêu xe, máy bay...">
            <button class="btn btn-primary"><i class="fas fa-search"></i></button>
          </div>
        </div>
      </div>

      <div class="row g-4">
        <div class="col-12 text-center" v-if="filteredProducts.length === 0">
          <p class="text-muted">Không tìm thấy sản phẩm nào!</p>
        </div>

        <div class="col-md-3" v-for="p in filteredProducts" :key="p.id">
          <div class="card h-100 shadow-sm border-0 product-card">
            <div class="card-img-wrapper" @click="viewProduct(p)"
              style="cursor:pointer; height:200px;display:flex;align-items:center;justify-content:center;padding:10px;background:#fff;">
              <img :src="getImageUrl(p.image)" style="max-height:100%;max-width:100%;object-fit:contain;">
            </div>
            <div class="card-body d-flex flex-column">
              <div class="mb-2">
                <span class="badge bg-light text-dark border">{{ p.category.name }}</span>
              </div>
              <h6 class="fw-bold text-truncate" @click="viewProduct(p)" style="cursor:pointer" :title="p.name">{{ p.name
                }}</h6>
              <div class="mt-auto">
                <p class="text-danger fw-bold fs-5 mb-2">{{ formatPrice(p.price) }}</p>
                <button class="btn btn-primary w-100 rounded-pill" @click="addToCart(p)">
                  <i class="fas fa-cart-plus me-1"></i> Thêm vào giỏ
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="currentView === 'product-detail'" class="container py-5">
      <button class="btn btn-outline-secondary mb-3" @click="currentView = 'home'"><i class="fas fa-arrow-left"></i>
        Quay
        lại</button>
      <div class="card border-0 shadow-lg overflow-hidden">
        <div class="row g-0">
          <div class="col-md-6 bg-white d-flex align-items-center justify-content-center p-5">
            <img :src="getImageUrl(selectedProduct.image)" class="img-fluid" style="max-height: 400px;">
          </div>
          <div class="col-md-6 p-5">
            <h2 class="fw-bold text-primary">{{ selectedProduct.name }}</h2>
            <p class="text-muted">Mã SP: #{{ selectedProduct.id }} | Danh mục: {{ selectedProduct.category.id }}</p>
            <h1 class="text-danger fw-bold my-4">{{ formatPrice(selectedProduct.price) }}</h1>
            <p class="lead">Siêu phẩm đẳng cấp dành cho giới thượng lưu. Bảo hành trọn đời.</p>
            <div class="d-grid gap-2 mt-5">
              <button class="btn btn-primary btn-lg" @click="addToCart(selectedProduct)">THÊM VÀO GIỎ HÀNG</button>
              <button class="btn btn-outline-primary btn-lg">MUA TRẢ GÓP 0%</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="currentView === 'profile'" class="container py-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card border-0 shadow">
            <div class="card-header bg-primary text-white text-center py-3">
              <h4 class="mb-0">HỒ SƠ CÁ NHÂN</h4>
            </div>
            <div class="card-body p-4">
              <div class="mb-3">
                <label class="fw-bold">Tài khoản:</label>
                <input :value="currentUser.username" class="form-control" disabled>
              </div>
              <div class="mb-3">
                <label class="fw-bold">Họ và tên:</label>
                <input v-model="profileForm.fullname" class="form-control">
              </div>
              <div class="mb-3">
                <label class="fw-bold">Email:</label>
                <input v-model="profileForm.email" class="form-control">
              </div>
              <hr>
              <h5 class="text-primary mb-3">Đổi mật khẩu</h5>
              <div class="mb-3">
                <label>Mật khẩu mới (Bỏ trống nếu không đổi):</label>
                <input v-model="profileForm.newPassword" type="password" class="form-control"
                  placeholder="Nhập pass mới...">
              </div>
              <button class="btn btn-success w-100 mt-2" @click="updateProfile">CẬP NHẬT HỒ SƠ</button>
              <button class="btn btn-outline-secondary w-100 mt-2" @click="currentView = 'home'">Hủy</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div v-if="currentView === 'admin-accounts'" class="container py-4">
      <h3 class="text-primary mb-4">QUẢN LÝ KHÁCH HÀNG & USER</h3>
      <div class="card shadow-sm">
        <table class="table mb-0">
          <thead>
            <tr>
              <th>User</th>
              <th>Họ tên</th>
              <th>Email</th>
              <th>Role</th>
              <th>Trạng thái</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="a in accounts" :key="a.username">
              <td>{{ a.username }}</td>
              <td>{{ a.fullname }}</td>
              <td>{{ a.email }}</td>
              <td><span v-for="r in a.roles" class="badge bg-info me-1">{{ r }}</span></td>
              <td>
                <div class="form-check form-switch"><input class="form-check-input" type="checkbox"
                    :checked="a.activated" @change="toggleActive(a)" :disabled="a.username === currentUser.username">
                </div>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>

    <div v-if="currentView === 'login'" class="container py-5">
      <div class="card mx-auto p-4 shadow" style="max-width:400px">
        <h3 class="text-center">ĐĂNG NHẬP</h3><input v-model="loginForm.username" class="form-control mb-2"
          placeholder="User"><input v-model="loginForm.password" type="password" class="form-control mb-3"
          placeholder="Pass"><button @click="login" class="btn btn-primary w-100">Login</button>
      </div>
    </div>
    <div v-if="currentView === 'cart'" class="container py-5">
      <div class="row">
        <div class="col-lg-8">
          <table class="table">
            <thead>
              <tr>
                <th>SP</th>
                <th>Giá</th>
                <th>SL</th>
                <th>Tổng</th>
                <th>X</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in cart" :key="item.id">
                <td>{{ item.name }}</td>
                <td>{{ formatPrice(item.price) }}</td>
                <td><button @click="item.qty--" class="btn btn-sm btn-outline-secondary">-</button><span class="mx-2">{{
                  item.qty }}</span><button @click="item.qty++" class="btn btn-sm btn-outline-secondary">+</button>
                </td>
                <td>{{ formatPrice(item.qty * item.price) }}</td>
                <td><button @click="removeFromCart(item.id)" class="btn btn-sm btn-danger">X</button></td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-lg-4">
          <div class="card p-3">
            <h4>Thanh toán</h4><textarea v-model="address" class="form-control mb-3" placeholder="Địa chỉ"></textarea>
            <h3 class="text-danger text-end">{{ formatPrice(totalAmount) }}</h3>
            <button @click="checkout" class="btn btn-success w-100" :disabled="cart.length === 0">
              MUA NGAY
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="currentView === 'admin-products'" class="container py-4">
      <div class="d-flex justify-content-between mb-3">
        <h3>QL Hàng</h3><button @click="createNewProduct" class="btn btn-success">Thêm</button>
      </div>
      <table class="table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Ảnh</th>
            <th>Tên</th>
            <th>Giá</th>
            <th>Thao tác</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="p in products" :key="p.id">
            <td>{{ p.id }}</td>
            <td><img :src="getImageUrl(p.image)" width="40"></td>
            <td>{{ p.name }}</td>
            <td>{{ formatPrice(p.price) }}</td>
            <td><button @click="editProduct(p)" class="btn btn-sm btn-primary me-1">Sửa</button><button
                @click="deleteProduct(p.id)" class="btn btn-sm btn-danger">Xóa</button></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="currentView === 'product-form'" class="container py-5">
      <div class="card mx-auto p-4" style="max-width:500px">
        <h3>Thông tin SP</h3><input v-model="productForm.name" class="form-control mb-2" placeholder="Tên"><input
          v-model="productForm.price" type="number" class="form-control mb-2" placeholder="Giá"><input
          v-model="productForm.image" class="form-control mb-2" placeholder="Ảnh"><select
          v-model="productForm.category.id" class="form-control mb-3">
          <option v-for="c in categories" :value="c.id">{{ c.name }}</option>
        </select><button @click="saveProduct" class="btn btn-primary">Lưu</button>
      </div>
    </div>
    <div v-if="currentView === 'admin-orders' || currentView === 'my-orders'" class="container py-4">
      <h3>{{ currentView === 'admin-orders' ? 'QL Đơn' : 'Đơn của tôi' }}</h3>
      <div v-for="o in orders" :key="o.id" class="card mb-3">
        <div class="card-header d-flex justify-content-between"><span>Đơn #{{ o.id }} ({{ new
          Date(o.createDate).toLocaleString() }}) - {{ o.username.fullname }}</span><span
            class="badge bg-success">OK</span></div>
        <div class="card-body">
          <div v-for="d in o.orderDetails" :key="d.id">{{ d.product.name }} x {{ d.quantity }} =
            {{ formatPrice(d.price * d.quantity) }}</div>
        </div>
      </div>
    </div>
  </div>
</template>