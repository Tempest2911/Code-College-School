<template>
  <div class="container mt-4">
    <!-- Loading State -->
    <div v-if="!userProfile" class="text-center py-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Loading...</span>
      </div>
      <p class="mt-3 text-muted">Loading profile...</p>
    </div>

    <!-- Profile Content -->
    <div v-else class="row">
      <!-- Profile Sidebar -->
      <div class="col-lg-4 mb-4">
        <div class="card shadow-lg border-0 rounded-3">
          <div class="card-body text-center p-4">
            <!-- Avatar Section -->
            <div class="position-relative d-inline-block mb-4">
              <img
                :src="userProfile.avatar || 'https://via.placeholder.com/150x150/0d6efd/ffffff?text=U'"
                class="rounded-circle"
                width="150"
                height="150"
                alt="Profile"
              />
              <button
                class="btn btn-primary position-absolute bottom-0 end-0"
                @click="showAvatarUpload = true"
              >
                <i class="bi bi-camera"></i>
              </button>
            </div>

            <!-- User Info -->
            <h4 class="mb-2">{{ userProfile.name || 'User' }}</h4>
            <p class="text-muted mb-3">{{ userProfile.email || 'user@example.com' }}</p>
            
            <!-- Stats -->
            <div class="row text-center mb-3">
              <div class="col-4">
                <h5 class="text-primary mb-1">{{ stats.totalPosts }}</h5>
                <small class="text-muted">Posts</small>
              </div>
              <div class="col-4">
                <h5 class="text-success mb-1">{{ stats.comments }}</h5>
                <small class="text-muted">Comments</small>
              </div>
              <div class="col-4">
                <h5 class="text-info mb-1">{{ stats.likes || 0 }}</h5>
                <small class="text-muted">Likes</small>
              </div>
            </div>

            <!-- Member Since -->
            <div class="text-muted">
              <i class="bi bi-calendar me-1"></i>
              Member since {{ formatDate(userProfile.createdAt) }}
            </div>
          </div>
        </div>

        <!-- Quick Actions -->
        <div class="card shadow-lg border-0 rounded-3 mt-3">
          <div class="card-body">
            <h6 class="card-title mb-3">Quick Actions</h6>
            <div class="d-grid gap-2">
              <button
                class="btn btn-outline-primary"
                @click="activeTab = 'personal'"
              >
                <i class="bi bi-person me-2"></i>
                Edit Profile
              </button>
              <button
                class="btn btn-outline-secondary"
                @click="activeTab = 'security'"
              >
                <i class="bi bi-shield-lock me-2"></i>
                Security Settings
              </button>
              <button
                class="btn btn-outline-info"
                @click="activeTab = 'notifications'"
              >
                <i class="bi bi-bell me-2"></i>
                Notifications
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- Main Content -->
      <div class="col-lg-8">
        <!-- Personal Information Tab -->
        <div v-if="activeTab === 'personal'" class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-primary text-white">
            <h5 class="mb-0">
              <i class="bi bi-person me-2"></i>
              Personal Information
            </h5>
          </div>
          <div class="card-body p-4">
            <form @submit.prevent="updatePersonalInfo">
              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="firstName" class="form-label">First Name</label>
                  <input
                    type="text"
                    class="form-control"
                    :class="{
                      'is-valid': validation.firstName.valid && formData.firstName,
                      'is-invalid': validation.firstName.error && formData.firstName
                    }"
                    id="firstName"
                    v-model="formData.firstName"
                    @blur="validateFirstName"
                    @input="validateFirstName"
                    placeholder="Enter first name"
                    required
                  />
                  <div class="invalid-feedback" v-if="validation.firstName.error">
                    {{ validation.firstName.error }}
                  </div>
                </div>
                <div class="col-md-6 mb-3">
                  <label for="lastName" class="form-label">Last Name</label>
                  <input
                    type="text"
                    class="form-control"
                    :class="{
                      'is-valid': validation.lastName.valid && formData.lastName,
                      'is-invalid': validation.lastName.error && formData.lastName
                    }"
                    id="lastName"
                    v-model="formData.lastName"
                    @blur="validateLastName"
                    @input="validateLastName"
                    placeholder="Enter last name"
                    required
                  />
                  <div class="invalid-feedback" v-if="validation.lastName.error">
                    {{ validation.lastName.error }}
                  </div>
                </div>
              </div>

              <div class="mb-3">
                <label for="email" class="form-label">Email Address</label>
                <input
                  type="email"
                  class="form-control"
                  :class="{
                    'is-valid': validation.email.valid && formData.email,
                    'is-invalid': validation.email.error && formData.email
                  }"
                  id="email"
                  v-model="formData.email"
                  @blur="validateEmail"
                  @input="validateEmail"
                  placeholder="Enter email address"
                  required
                />
                <div class="invalid-feedback" v-if="validation.email.error">
                  {{ validation.email.error }}
                </div>
              </div>

              <div class="mb-3">
                <label for="bio" class="form-label">Bio</label>
                <textarea
                  class="form-control"
                  id="bio"
                  v-model="formData.bio"
                  rows="3"
                  placeholder="Tell us about yourself..."
                  maxlength="500"
                ></textarea>
                <div class="form-text">
                  {{ formData.bio.length }}/500 characters
                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3">
                  <label for="location" class="form-label">Location</label>
                  <input
                    type="text"
                    class="form-control"
                    id="location"
                    v-model="formData.location"
                    placeholder="Enter your location"
                  />
                </div>
                <div class="col-md-6 mb-3">
                  <label for="website" class="form-label">Website</label>
                  <input
                    type="url"
                    class="form-control"
                    id="website"
                    v-model="formData.website"
                    placeholder="https://yourwebsite.com"
                  />
                </div>
              </div>

              <div class="d-flex justify-content-end">
                <button
                  type="submit"
                  class="btn btn-primary"
                  :disabled="loading.personal || !isPersonalFormValid"
                >
                  <span
                    v-if="loading.personal"
                    class="spinner-border spinner-border-sm me-2"
                    role="status"
                  ></span>
                  <i v-else class="bi bi-check-circle me-2"></i>
                  {{ loading.personal ? 'Saving...' : 'Save Changes' }}
                </button>
              </div>
            </form>
          </div>
        </div>

        <!-- Security Settings Tab -->
        <div v-if="activeTab === 'security'" class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-warning text-dark">
            <h5 class="mb-0">
              <i class="bi bi-shield-lock me-2"></i>
              Security Settings
            </h5>
          </div>
          <div class="card-body p-4">
            <form @submit.prevent="updatePassword">
              <div class="mb-3">
                <label for="currentPassword" class="form-label">Current Password</label>
                <div class="input-group">
                  <input
                    :type="showPassword.current ? 'text' : 'password'"
                    class="form-control"
                    :class="{
                      'is-valid': validation.currentPassword.valid && passwordData.currentPassword,
                      'is-invalid': validation.currentPassword.error && passwordData.currentPassword
                    }"
                    id="currentPassword"
                    v-model="passwordData.currentPassword"
                    @blur="validateCurrentPassword"
                    @input="validateCurrentPassword"
                    placeholder="Enter current password"
                    required
                  />
                  <button
                    class="btn btn-outline-secondary"
                    type="button"
                    @click="showPassword.current = !showPassword.current"
                  >
                    <i :class="showPassword.current ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.currentPassword.error">
                  {{ validation.currentPassword.error }}
                </div>
              </div>

              <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <div class="input-group">
                  <input
                    :type="showPassword.new ? 'text' : 'password'"
                    class="form-control"
                    :class="{
                      'is-valid': validation.newPassword.valid && passwordData.newPassword,
                      'is-invalid': validation.newPassword.error && passwordData.newPassword
                    }"
                    id="newPassword"
                    v-model="passwordData.newPassword"
                    @blur="validateNewPassword"
                    @input="validateNewPassword"
                    placeholder="Enter new password"
                    required
                  />
                  <button
                    class="btn btn-outline-secondary"
                    type="button"
                    @click="showPassword.new = !showPassword.new"
                  >
                    <i :class="showPassword.new ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.newPassword.error">
                  {{ validation.newPassword.error }}
                </div>
                
                <!-- Password Strength Indicator -->
                <div class="mt-2" v-if="passwordData.newPassword">
                  <div class="progress" style="height: 8px;">
                    <div
                      class="progress-bar"
                      :class="passwordStrengthClass"
                      :style="{ width: passwordStrength + '%' }"
                    ></div>
                  </div>
                  <small class="text-muted">{{ passwordStrengthText }}</small>
                </div>
              </div>

              <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm New Password</label>
                <div class="input-group">
                  <input
                    :type="showPassword.confirm ? 'text' : 'password'"
                    class="form-control"
                    :class="{
                      'is-valid': validation.confirmPassword.valid && passwordData.confirmPassword,
                      'is-invalid': validation.confirmPassword.error && passwordData.confirmPassword
                    }"
                    id="confirmPassword"
                    v-model="passwordData.confirmPassword"
                    @blur="validateConfirmPassword"
                    @input="validateConfirmPassword"
                    placeholder="Confirm new password"
                    required
                  />
                  <button
                    class="btn btn-outline-secondary"
                    type="button"
                    @click="showPassword.confirm = !showPassword.confirm"
                  >
                    <i :class="showPassword.confirm ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.confirmPassword.error">
                  {{ validation.confirmPassword.error }}
                </div>
              </div>

              <div class="d-flex justify-content-end">
                <button
                  type="submit"
                  class="btn btn-warning"
                  :disabled="loading.password || !isPasswordFormValid"
                >
                  <span
                    v-if="loading.password"
                    class="spinner-border spinner-border-sm me-2"
                    role="status"
                  ></span>
                  <i v-else class="bi bi-shield-check me-2"></i>
                  {{ loading.password ? 'Updating...' : 'Update Password' }}
                </button>
              </div>
            </form>
          </div>
        </div>

        <!-- Notification Preferences Tab -->
        <div v-if="activeTab === 'notifications'" class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-info text-white">
            <h5 class="mb-0">
              <i class="bi bi-bell me-2"></i>
              Notification Preferences
            </h5>
          </div>
          <div class="card-body p-4">
            <form @submit.prevent="updateNotifications">
              <div class="mb-3">
                <h6>Email Notifications</h6>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="emailNotifications"
                    v-model="notificationSettings.emailNotifications"
                  />
                  <label class="form-check-label" for="emailNotifications">
                    Receive email notifications
                  </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="commentNotifications"
                    v-model="notificationSettings.commentNotifications"
                  />
                  <label class="form-check-label" for="commentNotifications">
                    Notify me when someone comments on my posts
                  </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="postNotifications"
                    v-model="notificationSettings.postNotifications"
                  />
                  <label class="form-check-label" for="postNotifications">
                    Notify me about new posts from people I follow
                  </label>
                </div>
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="marketingEmails"
                    v-model="notificationSettings.marketingEmails"
                  />
                  <label class="form-check-label" for="marketingEmails">
                    Receive marketing emails and updates
                  </label>
                </div>
              </div>

              <div class="d-flex justify-content-end">
                <button
                  type="submit"
                  class="btn btn-info"
                  :disabled="loading.notifications"
                >
                  <span
                    v-if="loading.notifications"
                    class="spinner-border spinner-border-sm me-2"
                    role="status"
                  ></span>
                  <i v-else class="bi bi-check-circle me-2"></i>
                  {{ loading.notifications ? 'Saving...' : 'Save Preferences' }}
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
              <input
                type="file"
                class="form-control"
                id="avatarInput"
                @change="handleAvatarUpload"
                accept="image/*"
                ref="avatarInput"
              />
              <div class="form-text">
                Supported formats: JPG, PNG, GIF (Max 50MB)
              </div>
            </div>
            
            <div v-if="avatarPreview" class="text-center">
              <img
                :src="avatarPreview"
                class="img-fluid rounded"
                style="max-height: 200px;"
                alt="Avatar Preview"
              />
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancelAvatarUpload">
              Cancel
            </button>
            <button
              type="button"
              class="btn btn-primary"
              @click="saveAvatar"
              :disabled="loading.avatar"
            >
              <span
                v-if="loading.avatar"
                class="spinner-border spinner-border-sm me-2"
                role="status"
              ></span>
              {{ loading.avatar ? 'Uploading...' : 'Upload' }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Backdrop -->
    <div v-if="showAvatarUpload" class="modal-backdrop fade show"></div>
  </div>
</template>

<script>
import authManager from '../utils/auth.js'

export default {
  name: "UserProfile",
  props: {
    currentUser: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      activeTab: 'personal',
      userProfile: null,
      formData: {
        firstName: "",
        lastName: "",
        email: "",
        bio: "",
        location: "",
        website: ""
      },
      passwordData: {
        currentPassword: "",
        newPassword: "",
        confirmPassword: ""
      },
      notificationSettings: {
        emailNotifications: true,
        commentNotifications: true,
        postNotifications: true,
        marketingEmails: false
      },
      validation: {
        firstName: { valid: false, error: "" },
        lastName: { valid: false, error: "" },
        email: { valid: false, error: "" },
        currentPassword: { valid: false, error: "" },
        newPassword: { valid: false, error: "" },
        confirmPassword: { valid: false, error: "" }
      },
      showPassword: {
        current: false,
        new: false,
        confirm: false
      },
      showAvatarUpload: false,
      avatarPreview: null,
      avatarFile: null,
      loading: {
        personal: false,
        password: false,
        notifications: false,
        avatar: false
      },
      stats: {
        totalPosts: 0,
        publishedPosts: 0,
        draftPosts: 0,
        comments: 0,
        likes: 0
      }
    };
  },
  computed: {
    isPersonalFormValid() {
      return (
        this.validation.firstName.valid &&
        this.validation.lastName.valid &&
        this.validation.email.valid
      );
    },
    isPasswordFormValid() {
      return (
        this.validation.currentPassword.valid &&
        this.validation.newPassword.valid &&
        this.validation.confirmPassword.valid
      );
    },
    passwordStrength() {
      if (!this.passwordData.newPassword) return 0;
      
      let strength = 0;
      const password = this.passwordData.newPassword;
      
      // Length check
      if (password.length >= 8) strength += 25;
      if (password.length >= 12) strength += 10;
      
      // Character variety checks
      if (/[a-z]/.test(password)) strength += 15;
      if (/[A-Z]/.test(password)) strength += 15;
      if (/[0-9]/.test(password)) strength += 15;
      if (/[^A-Za-z0-9]/.test(password)) strength += 20;
      
      return Math.min(strength, 100);
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
      handler(newUser) {
        if (newUser) {
          this.loadUserProfile();
        }
      }
    }
  },
  mounted() {
    if (this.currentUser) {
      this.loadUserProfile();
    }
  },
  methods: {
    loadUserProfile() {
      console.log('Loading user profile for:', this.currentUser);
      
      if (!this.currentUser) {
        console.error('No current user provided to UserProfile component');
        return;
      }
      
      this.userProfile = { ...this.currentUser };
      this.formData = {
        firstName: this.currentUser.firstName || "",
        lastName: this.currentUser.lastName || "",
        email: this.currentUser.email || "",
        bio: this.currentUser.bio || "",
        location: this.currentUser.location || "",
        website: this.currentUser.website || ""
      };
      this.avatarPreview = this.currentUser.avatar;
      
      // Load user stats
      this.stats = authManager.getUserStats(this.currentUser.id);
      
      console.log('User profile loaded:', this.userProfile);
      console.log('Form data loaded:', this.formData);
      console.log('Stats loaded:', this.stats);
    },
    
    validateFirstName() {
      const firstName = this.formData.firstName.trim();
      
      if (!firstName) {
        this.validation.firstName = { valid: false, error: "First name is required" };
        return;
      }
      
      if (firstName.length < 2) {
        this.validation.firstName = { valid: false, error: "First name must be at least 2 characters" };
        return;
      }
      
      this.validation.firstName = { valid: true, error: "" };
    },
    
    validateLastName() {
      const lastName = this.formData.lastName.trim();
      
      if (!lastName) {
        this.validation.lastName = { valid: false, error: "Last name is required" };
        return;
      }
      
      if (lastName.length < 2) {
        this.validation.lastName = { valid: false, error: "Last name must be at least 2 characters" };
        return;
      }
      
      this.validation.lastName = { valid: true, error: "" };
    },
    
    validateEmail() {
      const email = this.formData.email.trim();
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      
      if (!email) {
        this.validation.email = { valid: false, error: "Email is required" };
        return;
      }
      
      if (!emailRegex.test(email)) {
        this.validation.email = { valid: false, error: "Please enter a valid email address" };
        return;
      }
      
      this.validation.email = { valid: true, error: "" };
    },
    
    validateCurrentPassword() {
      const password = this.passwordData.currentPassword;
      
      if (!password) {
        this.validation.currentPassword = { valid: false, error: "Current password is required" };
        return;
      }
      
      if (password !== this.currentUser.password) {
        this.validation.currentPassword = { valid: false, error: "Current password is incorrect" };
        return;
      }
      
      this.validation.currentPassword = { valid: true, error: "" };
    },
    
    validateNewPassword() {
      const password = this.passwordData.newPassword;
      
      if (!password) {
        this.validation.newPassword = { valid: false, error: "New password is required" };
        return;
      }
      
      if (password.length < 8) {
        this.validation.newPassword = { valid: false, error: "Password must be at least 8 characters" };
        return;
      }
      
      this.validation.newPassword = { valid: true, error: "" };
    },
    
    validateConfirmPassword() {
      const confirmPassword = this.passwordData.confirmPassword;
      const newPassword = this.passwordData.newPassword;
      
      if (!confirmPassword) {
        this.validation.confirmPassword = { valid: false, error: "Please confirm your password" };
        return;
      }
      
      if (confirmPassword !== newPassword) {
        this.validation.confirmPassword = { valid: false, error: "Passwords do not match" };
        return;
      }
      
      this.validation.confirmPassword = { valid: true, error: "" };
    },
    
    validateAllPersonalFields() {
      this.validateFirstName();
      this.validateLastName();
      this.validateEmail();
    },
    
    async updatePersonalInfo() {
      this.validateAllPersonalFields();
      
      if (!this.isPersonalFormValid) {
        alert('Please fix all validation errors before saving');
        return;
      }
      
      this.loading.personal = true;
      
      try {
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        const updates = {
          firstName: this.formData.firstName,
          lastName: this.formData.lastName,
          name: `${this.formData.firstName} ${this.formData.lastName}`,
          email: this.formData.email,
          bio: this.formData.bio,
          location: this.formData.location,
          website: this.formData.website
        };
        
        console.log('Updating user profile with:', updates);
        
        const result = authManager.updateUserProfile(this.currentUser.id, updates);
        
        if (result.success) {
          this.userProfile = { ...this.userProfile, ...updates };
          this.$emit('profile-updated', result.user);
          alert('Personal information updated successfully!');
        } else {
          alert(result.error || 'Failed to update personal information');
        }
        
      } catch (error) {
        console.error('Error updating personal info:', error);
        alert('An error occurred while updating personal information');
      } finally {
        this.loading.personal = false;
      }
    },
    
    async updatePassword() {
      this.validateCurrentPassword();
      this.validateNewPassword();
      this.validateConfirmPassword();
      
      if (!this.isPasswordFormValid) {
        alert('Please fix all validation errors before saving');
        return;
      }
      
      this.loading.password = true;
      
      try {
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        const updates = {
          password: this.passwordData.newPassword
        };
        
        const result = authManager.updateUserProfile(this.currentUser.id, updates);
        
        if (result.success) {
          this.userProfile = { ...this.userProfile, ...updates };
          this.$emit('profile-updated', result.user);
          
          // Reset password form
          this.passwordData = {
            currentPassword: "",
            newPassword: "",
            confirmPassword: ""
          };
          this.validation.currentPassword = { valid: false, error: "" };
          this.validation.newPassword = { valid: false, error: "" };
          this.validation.confirmPassword = { valid: false, error: "" };
          
          alert('Password updated successfully!');
        } else {
          alert(result.error || 'Failed to update password');
        }
        
      } catch (error) {
        console.error('Error updating password:', error);
        alert('An error occurred while updating password');
      } finally {
        this.loading.password = false;
      }
    },
    
    async updateNotifications() {
      this.loading.notifications = true;
      
      try {
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        const updates = {
          notificationSettings: this.notificationSettings
        };
        
        const result = authManager.updateUserProfile(this.currentUser.id, updates);
        
        if (result.success) {
          this.userProfile = { ...this.userProfile, ...updates };
          this.$emit('profile-updated', result.user);
          alert('Notification settings updated successfully!');
        } else {
          alert(result.error || 'Failed to update notification settings');
        }
        
      } catch (error) {
        console.error('Error updating notifications:', error);
        alert('An error occurred while updating notification settings');
      } finally {
        this.loading.notifications = false;
      }
    },
    
    handleAvatarUpload(event) {
      const file = event.target.files[0];
      if (!file) return;
      // Validate file type
      if (!file.type.startsWith('image/')) {
        alert('Please select an image file (JPG, PNG, GIF)');
        return;
      }
      // Validate file size (50MB limit)
      if (file.size > 50 * 1024 * 1024) {
        alert('Image size must be less than 50MB');
        return;
      }
      this.avatarFile = file;
      // Create preview
      const reader = new FileReader();
      reader.onload = (e) => {
        this.avatarPreview = e.target.result;
      };
      reader.readAsDataURL(file);
      this.showAvatarUpload = true;
    },
    
    async saveAvatar() {
      if (!this.avatarFile) {
        alert('Please select an image first');
        return;
      }
      
      this.loading.avatar = true;
      
      try {
        await new Promise(resolve => setTimeout(resolve, 1000));
        
        // In a real app, you would upload the file to a server
        // For now, we'll use the data URL as the avatar
        const updates = {
          avatar: this.avatarPreview
        };
        
        const result = authManager.updateUserProfile(this.currentUser.id, updates);
        
        if (result.success) {
          this.userProfile = { ...this.userProfile, ...updates };
          this.$emit('profile-updated', result.user);
          this.$emit('avatar-updated', this.avatarPreview);
          
          this.showAvatarUpload = false;
          this.avatarFile = null;
          
          alert('Avatar updated successfully!');
        } else {
          alert(result.error || 'Failed to update avatar');
        }
        
      } catch (error) {
        console.error('Error updating avatar:', error);
        alert('An error occurred while updating avatar');
      } finally {
        this.loading.avatar = false;
      }
    },
    
    cancelAvatarUpload() {
      this.showAvatarUpload = false;
      this.avatarFile = null;
      this.avatarPreview = this.currentUser.avatar;
    },
    
    formatDate(dateString) {
      if (!dateString) return 'Unknown';
      
      const date = new Date(dateString);
      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
      });
    }
  }
};
</script>

<style scoped>
.card {
  border: none;
  border-radius: 15px;
}

.card-header {
  border-radius: 15px 15px 0 0 !important;
}

.form-control:focus,
.form-select:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}

.btn {
  border-radius: 8px;
  font-weight: 500;
}

.btn:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.progress {
  border-radius: 10px;
}

.progress-bar {
  border-radius: 10px;
  transition: width 0.3s ease;
}

.form-check-input:checked {
  background-color: #0d6efd;
  border-color: #0d6efd;
}

.modal {
  background-color: rgba(0, 0, 0, 0.5);
}

.input-group .btn {
  border-radius: 0 8px 8px 0;
}

.input-group .form-control {
  border-radius: 8px 0 0 8px;
}

.spinner-border {
  width: 1rem;
  height: 1rem;
}
</style> 