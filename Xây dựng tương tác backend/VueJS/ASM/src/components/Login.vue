<template>
  <div class="login-container">
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-md-6 col-lg-4">
          <div class="card shadow-lg border-0 rounded-3">
            <div class="card-body p-5">
              <h2 class="text-center mb-4">Login</h2>
              
              <form @submit.prevent="handleLogin">
                <div class="mb-3">
                  <label for="username" class="form-label">Username or Email</label>
                  <input
                    type="text"
                    class="form-control"
                    id="username"
                    v-model="formData.username"
                    placeholder="Enter username"
                    required
                  />
                </div>

                <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input
                    type="password"
                    class="form-control"
                    id="password"
                    v-model="formData.password"
                    placeholder="Enter password"
                    required
                  />
                </div>

                <div class="form-check mb-3">
                  <input
                    type="checkbox"
                    class="form-check-input"
                    id="rememberMe"
                    v-model="formData.rememberMe"
                  />
                  <label class="form-check-label" for="rememberMe">Remember me</label>
                </div>

                <div class="d-grid">
                  <button type="submit" class="btn btn-primary" :disabled="loading">
                    <span v-if="loading" class="spinner-border spinner-border-sm me-2" role="status"></span>
                    {{ loading ? 'Logging in...' : 'Login' }}
                  </button>
                </div>
              </form>
              
              <div class="text-center mt-4">
                <p class="mb-0">Don't have an account?
                  <router-link to="/register" class="text-decoration-none">Sign up now</router-link>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import api from '../utils/api'

export default {
  name: "Login",
  setup() {
    const router = useRouter()
    return { router }
  },
  data() {
    return {
      formData: {
        username: "",
        password: "",
        rememberMe: false,
      },
      loading: false,
    };
  },
  methods: {
    async handleLogin() {
      this.loading = true;
      try {
        // Simulate loading
        await new Promise((resolve) => setTimeout(resolve, 500));

        // Lấy danh sách user từ API
        const res = await api.getUsers();
        const user = res.data.find(
          u =>
            (u.username === this.formData.username || u.email === this.formData.username) &&
            u.password === this.formData.password
        );

        if (user) {
          this.$emit('login-success', user);
        } else {
          alert('Invalid username or password');
        }
      } catch (error) {
        console.error("Login error:", error);
        alert("An error occurred during login");
      } finally {
        this.loading = false;
      }
    },
    handleUseAccount(accountData) {
      this.formData.username = accountData.username;
      this.formData.password = accountData.password;
    }
  },
};
</script>

<style scoped>
.login-container {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 2rem 0;
}

.card {
  border: none;
  border-radius: 15px;
}

.card-body {
  padding: 2rem;
}

.form-control {
  border-radius: 8px;
  padding: 12px 16px;
  border: 2px solid #e9ecef;
  transition: all 0.3s ease;
}

.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}

.btn {
  border-radius: 8px;
  padding: 12px 24px;
  font-weight: 500;
  transition: all 0.3s ease;
}

.btn:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-check-input:checked {
  background-color: #0d6efd;
  border-color: #0d6efd;
}

.text-decoration-none {
  color: #0d6efd;
  font-weight: 500;
}

.text-decoration-none:hover {
  color: #0a58ca;
}
</style>
