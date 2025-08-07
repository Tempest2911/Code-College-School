<template>
  <div class="profile-settings">
    <div class="card">
      <div class="card-header">
        <h2>Profile Settings</h2>
        <p>Update your username and email address below.</p>
      </div>
      <div v-if="user" class="card-body">
        <form @submit.prevent="save" class="form">
          <div class="form-group">
            <label for="username">Username</label>
            <input id="username" v-model.trim="form.username" required class="form-control" />
          </div>
          <div class="form-group">
            <label for="email">Email</label>
            <input id="email" v-model.trim="form.email" required type="email" class="form-control" />
          </div>
          <div class="form-actions">
            <button type="submit" class="btn btn-primary" :disabled="saving">
              <span v-if="!saving">Save Changes</span>
              <span v-else class="spinner"></span>
            </button>
          </div>
          <div v-if="message" class="alert alert-success">{{ message }}</div>
        </form>
      </div>
      <div v-else class="card-body empty-state">
        <p>Please log in to update your profile.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfileSettings",
  data() {
    return {
      form: {
        username: window.userProfile?.username || "",
        email: window.userProfile?.email || "",
      },
      saving: false,
      message: "",
    };
  },
  computed: {
    user() {
      return window.userProfile || null;
    },
  },
  methods: {
    save() {
      if (!this.form.username || !this.form.email) return;
      if (!/^\S+@\S+\.\S+$/.test(this.form.email)) {
        this.message = "Email không hợp lệ";
        return;
      }
      this.saving = true;
      setTimeout(() => {
        window.userProfile = {
          username: this.form.username,
          email: this.form.email,
        };
        this.message = "Thông tin đã được cập nhật!";
        this.saving = false;
        this.$emit("updated");
        setTimeout(() => (this.message = ""), 2000);
      }, 400);
    },
  },
};
</script>

<style scoped>
.profile-settings {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: #f4f6f9;
  padding: 2rem;
}
.card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 500px;
  overflow: hidden;
}
.card-header {
  background: #3498db;
  color: #fff;
  padding: 1.5rem;
  text-align: center;
}
.card-header h2 {
  margin: 0;
  font-size: 1.5rem;
}
.card-header p {
  margin: 0.5rem 0 0;
  font-size: 0.9rem;
}
.card-body {
  padding: 1.5rem;
}
.form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.form-group {
  display: flex;
  flex-direction: column;
}
.form-group label {
  margin-bottom: 0.5rem;
  font-size: 0.9rem;
  color: #333;
}
.form-control {
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
  outline: none;
  transition: border-color 0.2s;
}
.form-control:focus {
  border-color: #3498db;
}
.form-actions {
  display: flex;
  justify-content: flex-end;
}
.btn {
  padding: 0.75rem 1.5rem;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.2s;
}
.btn-primary {
  background: #3498db;
  color: #fff;
}
.btn-primary:disabled {
  background: #a3c9f1;
  cursor: not-allowed;
}
.spinner {
  width: 16px;
  height: 16px;
  border: 2px solid #fff;
  border-top-color: rgba(255, 255, 255, 0.4);
  border-radius: 50%;
  animation: spin 0.8s linear infinite;
}
@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
.alert {
  margin-top: 1rem;
  padding: 0.75rem;
  border-radius: 8px;
  font-size: 0.9rem;
}
.alert-success {
  background: #d4edda;
  color: #155724;
  border: 1px solid #c3e6cb;
}
.empty-state {
  text-align: center;
  color: #666;
  font-size: 1rem;
}
</style>
