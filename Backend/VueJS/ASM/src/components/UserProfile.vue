<template>
  <div class="container mt-4">
    <div v-if="!userProfile" class="text-center py-5">
      <div class="spinner-border text-primary" role="status"></div>
      <p class="mt-3 text-muted">Loading profile...</p>
    </div>
    <div v-else class="row">
      <!-- Sidebar -->
      <div class="col-lg-4 mb-4">
        <div class="card shadow-lg border-0 rounded-3">
          <div class="card-body text-center p-4">
            <div class="position-relative d-inline-block mb-4">
              <img :src="userProfile.avatar || defaultAvatar" class="rounded-circle" width="150" height="150" />
              <button class="btn btn-primary position-absolute bottom-0 end-0" @click="showAvatarUpload = true">
                <i class="bi bi-camera"></i>
              </button>
            </div>
            <h4 class="mb-2">{{ userProfile.name || 'User' }}</h4>
            <p class="text-muted mb-3">{{ userProfile.email || 'user@example.com' }}</p>
            <div class="row text-center mb-3">
              <div class="col-4">
                <h4 class="text-primary mb-1">{{ stats.totalPosts }}</h4>
                <small class="text-muted">Total Posts</small>
              </div>
              <div class="col-4">
                <h4 class="text-success mb-1">{{ stats.publishedPosts }}</h4>
                <small class="text-muted">Published</small>
              </div>
              <div class="col-4">
                <h4 class="text-warning mb-1">{{ stats.draftPosts }}</h4>
                <small class="text-muted">Drafts</small>
              </div>
            </div>
            <div class="text-muted"><i class="bi bi-calendar me-1"></i>Member since {{ formatDate(userProfile.createdAt) }}</div>
          </div>
        </div>
        <div class="card shadow-lg border-0 rounded-3 mt-3">
          <div class="card-body">
            <h6 class="card-title mb-3">Quick Actions</h6>
            <div class="d-grid gap-2">
              <button class="btn btn-outline-primary" @click="activeTab = 'personal'"><i class="bi bi-person me-2"></i>Edit Profile</button>
              <button class="btn btn-outline-secondary" @click="activeTab = 'security'"><i class="bi bi-shield-lock me-2"></i>Security</button>
            </div>
          </div>
        </div>
      </div>
      <!-- Main Content -->
      <div class="col-lg-8">
        <!-- Personal Tab -->
        <div v-if="activeTab === 'personal'" class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-primary text-white"><h5 class="mb-0"><i class="bi bi-person me-2"></i>Personal Information</h5></div>
          <div class="card-body p-4">
            <form @submit.prevent="updatePersonalInfo">
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="firstName" class="form-label">First Name</label>
                  <input type="text" class="form-control" id="firstName" v-model="formData.firstName"
                    :class="inputClass('firstName')" @input="validate('firstName')" placeholder="Enter first name" required />
                  <div class="invalid-feedback" v-if="validation.firstName.error">{{ validation.firstName.error }}</div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="lastName" class="form-label">Last Name</label>
                  <input type="text" class="form-control" id="lastName" v-model="formData.lastName"
                    :class="inputClass('lastName')" @input="validate('lastName')" placeholder="Enter last name" required />
                  <div class="invalid-feedback" v-if="validation.lastName.error">{{ validation.lastName.error }}</div>
                </div>
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input type="email" class="form-control" id="email" v-model="formData.email"
                  :class="inputClass('email')" @input="validate('email')" placeholder="Enter email address" required />
                <div class="invalid-feedback" v-if="validation.email.error">{{ validation.email.error }}</div>
              </div>
              <div class="mb-3">
                <label for="bio" class="form-label">Bio</label>
                <textarea class="form-control" id="bio" v-model="formData.bio" rows="3" maxlength="500"
                  placeholder="Tell us about yourself..."></textarea>
                <div class="form-text">{{ formData.bio.length }}/500 characters</div>
              </div>
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="location" class="form-label">Location</label>
                  <input type="text" class="form-control" id="location" v-model="formData.location" placeholder="Enter your location" />
                </div>
                <div class="col-md-6 mb-3">
                  <label for="website" class="form-label">Website</label>
                  <input type="url" class="form-control" id="website" v-model="formData.website" placeholder="https://yourwebsite.com" />
                </div>
              </div>
              <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary" :disabled="loading.personal">
                  <span v-if="loading.personal" class="spinner-border spinner-border-sm me-2"></span>
                  <i v-else class="bi bi-check-circle me-2"></i>
                  {{ loading.personal ? 'Saving...' : 'Save Changes' }}
                </button>
              </div>
            </form>
          </div>
        </div>
        <!-- Security Tab -->
        <div v-if="activeTab === 'security'" class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-warning text-dark"><h5 class="mb-0"><i class="bi bi-shield-lock me-2"></i>Security Settings</h5></div>
          <div class="card-body p-4">
            <form @submit.prevent="updatePassword">
              <div class="mb-3">
                <label for="currentPassword" class="form-label">Current Password</label>
                <div class="input-group">
                  <input :type="showPassword.current ? 'text' : 'password'" class="form-control" id="currentPassword"
                    v-model="passwordData.currentPassword" :class="inputClass('currentPassword', true)"
                    @input="validate('currentPassword', true)" placeholder="Enter current password" required />
                  <button class="btn btn-outline-secondary" type="button" @click="showPassword.current = !showPassword.current">
                    <i :class="showPassword.current ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.currentPassword.error">{{ validation.currentPassword.error }}</div>
              </div>
              <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <div class="input-group">
                  <input :type="showPassword.new ? 'text' : 'password'" class="form-control" id="newPassword"
                    v-model="passwordData.newPassword" :class="inputClass('newPassword', true)"
                    @input="validate('newPassword', true)" placeholder="Enter new password" required />
                  <button class="btn btn-outline-secondary" type="button" @click="showPassword.new = !showPassword.new">
                    <i :class="showPassword.new ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.newPassword.error">{{ validation.newPassword.error }}</div>
                <div class="mt-2" v-if="passwordData.newPassword">
                  <div class="progress" style="height: 8px;">
                    <div class="progress-bar" :class="passwordStrengthClass" :style="{ width: passwordStrength + '%' }"></div>
                  </div>
                  <small class="text-muted">{{ passwordStrengthText }}</small>
                </div>
              </div>
              <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm New Password</label>
                <div class="input-group">
                  <input :type="showPassword.confirm ? 'text' : 'password'" class="form-control" id="confirmPassword"
                    v-model="passwordData.confirmPassword" :class="inputClass('confirmPassword', true)"
                    @input="validate('confirmPassword', true)" placeholder="Confirm new password" required />
                  <button class="btn btn-outline-secondary" type="button" @click="showPassword.confirm = !showPassword.confirm">
                    <i :class="showPassword.confirm ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.confirmPassword.error">{{ validation.confirmPassword.error }}</div>
              </div>
              <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-warning" :disabled="loading.password || !isPasswordFormValid">
                  <span v-if="loading.password" class="spinner-border spinner-border-sm me-2"></span>
                  <i v-else class="bi bi-shield-check me-2"></i>
                  {{ loading.password ? 'Updating...' : 'Update Password' }}
                </button>
              </div>
            </form>
          </div>
        </div>

      </div>
    </div>
    <!-- Avatar Upload Modal -->
    <div class="modal fade" :class="{ show: showAvatarUpload }" :style="{ display: showAvatarUpload ? 'block' : 'none' }" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Update Avatar</h5>
            <button type="button" class="btn-close" @click="cancelAvatarUpload"></button>
          </div>
          <div class="modal-body">
            <div class="mb-3">
              <label for="avatarInput" class="form-label">Choose Image</label>
              <input type="file" class="form-control" id="avatarInput" @change="handleAvatarUpload" accept="image/*" ref="avatarInput" />
              <div class="form-text">Supported formats: JPG, PNG, GIF (Max 50MB)</div>
            </div>
            <div v-if="avatarPreview" class="text-center">
              <img :src="avatarPreview" class="img-fluid rounded" style="max-height: 200px;" />
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancelAvatarUpload">Cancel</button>
            <button type="button" class="btn btn-primary" @click="saveAvatar" :disabled="loading.avatar">
              <span v-if="loading.avatar" class="spinner-border spinner-border-sm me-2"></span>
              {{ loading.avatar ? 'Uploading...' : 'Upload' }}
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showAvatarUpload" class="modal-backdrop fade show"></div>
  </div>
</template>

<script>
import api from '../utils/api'

export default {
  name: "UserProfile",
  props: { currentUser: { type: Object, required: true } },
  data() {
    return {
      activeTab: 'personal',
      userProfile: null,
      formData: { firstName: "", lastName: "", email: "", bio: "", location: "", website: "" },
      passwordData: { currentPassword: "", newPassword: "", confirmPassword: "" },
      validation: {
        firstName: { valid: false, error: "" }, lastName: { valid: false, error: "" }, email: { valid: false, error: "" },
        currentPassword: { valid: false, error: "" }, newPassword: { valid: false, error: "" }, confirmPassword: { valid: false, error: "" }
      },
      showPassword: { current: false, new: false, confirm: false },
      showAvatarUpload: false,
      avatarPreview: null,
      avatarFile: null,
      loading: { personal: false, password: false, avatar: false },
      stats: { totalPosts: 0, comments: 0, likes: 0 },
      defaultAvatar: 'https://via.placeholder.com/150x150/0d6efd/ffffff?text=U'
    };
  },
  computed: {
    isPersonalFormValid() {
      return this.validation.firstName.valid && this.validation.lastName.valid && this.validation.email.valid;
    },
    isPasswordFormValid() {
      return this.validation.currentPassword.valid && this.validation.newPassword.valid && this.validation.confirmPassword.valid;
    },
    passwordStrength() {
      const p = this.passwordData.newPassword;
      let s = 0;
      if (!p) return 0;
      if (p.length >= 8) s += 25;
      if (p.length >= 12) s += 10;
      if (/[a-z]/.test(p)) s += 15;
      if (/[A-Z]/.test(p)) s += 15;
      if (/[0-9]/.test(p)) s += 15;
      if (/[^A-Za-z0-9]/.test(p)) s += 20;
      return Math.min(s, 100);
    },
    passwordStrengthClass() {
      if (this.passwordStrength < 40) return "bg-danger";
      if (this.passwordStrength < 70) return "bg-warning";
      return "bg-success";
    },
    passwordStrengthText() {
      if (this.passwordStrength < 40) return "Weak password";
      if (this.passwordStrength < 70) return "Medium strength password";
      return "Strong password";
    }
  },
  watch: {
    currentUser: {
      immediate: true,
      handler(newUser) { if (newUser) this.loadUserProfile(); }
    }
  },
  mounted() { if (this.currentUser) this.loadUserProfile(); },
  methods: {
    inputClass(field, isPassword = false) {
      const v = this.validation[field];
      if (isPassword && !this.passwordData[field]) return '';
      if (!isPassword && !this.formData[field]) return '';
      return v.valid ? 'is-valid' : v.error ? 'is-invalid' : '';
    },
    validate(field, isPassword = false) {
      let value = isPassword ? this.passwordData[field] : this.formData[field];
      value = value ? value.trim() : '';
      if (field === 'firstName' || field === 'lastName') {
        this.validation[field] = !value
          ? { valid: false, error: `${field === 'firstName' ? 'First' : 'Last'} name is required` }
          : value.length < 2
            ? { valid: false, error: `${field === 'firstName' ? 'First' : 'Last'} name must be at least 2 characters` }
            : { valid: true, error: "" };
      }
      if (field === 'email') {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        this.validation.email = !value
          ? { valid: false, error: "Email is required" }
          : !emailRegex.test(value)
            ? { valid: false, error: "Please enter a valid email address" }
            : { valid: true, error: "" };
      }
      if (field === 'currentPassword') {
        this.validation.currentPassword = !value
          ? { valid: false, error: "Current password is required" }
          : value !== this.currentUser.password
            ? { valid: false, error: "Current password is incorrect" }
            : { valid: true, error: "" };
      }
      if (field === 'newPassword') {
        this.validation.newPassword = !value
          ? { valid: false, error: "New password is required" }
          : value.length < 8
            ? { valid: false, error: "Password must be at least 8 characters" }
            : { valid: true, error: "" };
      }
      if (field === 'confirmPassword') {
        this.validation.confirmPassword = !value
          ? { valid: false, error: "Please confirm your password" }
          : value !== this.passwordData.newPassword
            ? { valid: false, error: "Passwords do not match" }
            : { valid: true, error: "" };
      }
    },
    async loadUserProfile() {
      if (!this.currentUser) return;
      const res = await api.getUser(this.currentUser.id);
      this.userProfile = res.data;
      this.formData = {
        firstName: res.data.firstName || "",
        lastName: res.data.lastName || "",
        email: res.data.email || "",
        bio: res.data.bio || "",
        location: res.data.location || "",
        website: res.data.website || ""
      };
      this.avatarPreview = res.data.avatar;
      await this.loadStats(); // <-- Thêm dòng này
    },
    async loadStats() {
      const res = await api.getPosts();
      const userPosts = res.data.filter(post => post.authorId === this.currentUser.id);
      const total = userPosts.length;
      const published = userPosts.filter(p => p.isPublished).length;
      const draft = total - published;
      this.stats = { totalPosts: total, publishedPosts: published, draftPosts: draft };
    },
    async updatePersonalInfo() {
      this.validate('firstName'); this.validate('lastName'); this.validate('email');
      if (!this.isPersonalFormValid) return;
      this.loading.personal = true;
      try {
        const updates = {
          firstName: this.formData.firstName,
          lastName: this.formData.lastName,
          name: `${this.formData.firstName} ${this.formData.lastName}`,
          email: this.formData.email,
          bio: this.formData.bio,
          location: this.formData.location,
          website: this.formData.website
        };
        const res = await api.updateUser(this.currentUser.id, updates);
        this.userProfile = res.data;
        this.$emit('profile-updated', res.data);
      } finally { this.loading.personal = false; }
    },
    async updatePassword() {
      this.validate('currentPassword', true); this.validate('newPassword', true); this.validate('confirmPassword', true);
      if (!this.isPasswordFormValid) return;
      this.loading.password = true;
      try {
        const updates = { password: this.passwordData.newPassword };
        const res = await api.updateUser(this.currentUser.id, updates);
        this.userProfile = res.data;
        this.$emit('profile-updated', res.data);
        this.passwordData = { currentPassword: "", newPassword: "", confirmPassword: "" };
        this.validation.currentPassword = { valid: false, error: "" };
        this.validation.newPassword = { valid: false, error: "" };
        this.validation.confirmPassword = { valid: false, error: "" };
      } finally { this.loading.password = false; }
    },
    handleAvatarUpload(event) {
      const file = event.target.files[0];
      if (!file || !file.type.startsWith('image/') || file.size > 50 * 1024 * 1024) return;
      this.avatarFile = file;
      const reader = new FileReader();
      reader.onload = (e) => { this.avatarPreview = e.target.result; };
      reader.readAsDataURL(file);
      this.showAvatarUpload = true;
    },
    async saveAvatar() {
      if (!this.avatarFile) return;
      this.loading.avatar = true;
      try {
        const updates = { avatar: this.avatarPreview };
        const res = await api.updateUser(this.currentUser.id, updates);
        this.userProfile.avatar = res.data.avatar;
        this.$emit('profile-updated', res.data);
        this.$emit('avatar-updated', this.avatarPreview);
      } finally {
        this.showAvatarUpload = false;
        this.avatarFile = null;
        this.loading.avatar = false;
      }
    },
    cancelAvatarUpload() {
      this.showAvatarUpload = false;
      this.avatarFile = null;
      this.avatarPreview = this.userProfile.avatar;
    },
    formatDate(dateString) {
      if (!dateString) return 'Unknown';
      const date = new Date(dateString);
      if (isNaN(date.getTime())) return 'Unknown';
      return date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
    }
  }
};
</script>

<style scoped>
.card { border: none; border-radius: 15px; }
.card-header { border-radius: 15px 15px 0 0 !important; }
.form-control:focus, .form-select:focus { border-color: #0d6efd; box-shadow: 0 0 0 0.2rem rgba(13,110,253,0.25);}
.btn { border-radius: 8px; font-weight: 500; }
.btn:hover:not(:disabled) { transform: translateY(-1px); box-shadow: 0 4px 8px rgba(0,0,0,0.1);}
.btn:disabled { opacity: 0.6; cursor: not-allowed; }
.progress, .progress-bar { border-radius: 10px; transition: width 0.3s ease; }
.form-check-input:checked { background-color: #0d6efd; border-color: #0d6efd; }
.modal { background-color: rgba(0,0,0,0.5);}
.input-group .btn { border-radius: 0 8px 8px 0; }
.input-group .form-control { border-radius: 8px 0 0 8px; }
.spinner-border { width: 1rem; height: 1rem; }
</style>