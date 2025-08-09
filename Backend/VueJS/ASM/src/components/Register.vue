<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-5">
        <div class="card shadow-lg border-0 rounded-3">
          <div class="card-body p-4">
            <h3 class="text-center mb-4 text-primary fw-bold">Register</h3>

            <form @submit.prevent="handleRegister">
              <!-- Username Field -->
              <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" :class="{
                  'is-valid': validation.username.valid && formData.username,
                  'is-invalid': validation.username.error && formData.username
                }" id="username" v-model="formData.username" @blur="validateUsername" @input="validateUsername"
                  placeholder="Enter username" required />
                <div class="invalid-feedback" v-if="validation.username.error">
                  {{ validation.username.error }}
                </div>
                <div class="valid-feedback" v-if="validation.username.valid">
                  Username is available!
                </div>
              </div>

              <!-- Email Field -->
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" :class="{
                  'is-valid': validation.email.valid && formData.email,
                  'is-invalid': validation.email.error && formData.email
                }" id="email" v-model="formData.email" @blur="validateEmail" @input="validateEmail"
                  placeholder="Enter email address" required />
                <div class="invalid-feedback" v-if="validation.email.error">
                  {{ validation.email.error }}
                </div>
                <div class="valid-feedback" v-if="validation.email.valid">
                  Email format is valid!
                </div>
              </div>

              <!-- Password Field -->
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                  <input :type="showPassword ? 'text' : 'password'" class="form-control" :class="{
                    'is-valid': validation.password.valid && formData.password,
                    'is-invalid': validation.password.error && formData.password
                  }" id="password" v-model="formData.password" @blur="validatePassword" @input="validatePassword"
                    placeholder="Enter password" required />
                  <button class="btn btn-outline-secondary" type="button" @click="showPassword = !showPassword">
                    <i :class="showPassword ? 'bi bi-eye-slash' : 'bi bi-eye'"></i>
                  </button>
                </div>
                <div class="invalid-feedback" v-if="validation.password.error">
                  {{ validation.password.error }}
                </div>
                <div class="valid-feedback" v-if="validation.password.valid">
                  Password meets requirements!
                </div>
              </div>

              <!-- Password Strength Indicator -->
              <div class="mb-3" v-if="formData.password">
                <label class="form-label">Password Strength:</label>
                <div class="progress" style="height: 8px;">
                  <div class="progress-bar" :class="passwordStrengthClass" :style="{ width: passwordStrength + '%' }">
                  </div>
                </div>
                <small class="text-muted">
                  {{ passwordStrengthText }}
                </small>
              </div>

              <!-- Confirm Password Field -->
              <div class="mb-3">
                <label for="confirmPassword" class="form-label">Confirm Password</label>
                <input type="password" class="form-control" :class="{
                  'is-valid': validation.confirmPassword.valid && formData.confirmPassword,
                  'is-invalid': validation.confirmPassword.error && formData.confirmPassword
                }" id="confirmPassword" v-model="formData.confirmPassword" @blur="validateConfirmPassword"
                  @input="validateConfirmPassword" placeholder="Confirm your password" required />
                <div class="invalid-feedback" v-if="validation.confirmPassword.error">
                  {{ validation.confirmPassword.error }}
                </div>
                <div class="valid-feedback" v-if="validation.confirmPassword.valid">
                  Passwords match!
                </div>
              </div>

              <!-- Terms and Conditions -->
              <div class="form-check mb-3">
                <input type="checkbox" class="form-check-input" :class="{
                  'is-valid': validation.terms.valid,
                  'is-invalid': validation.terms.error
                }" id="terms" v-model="formData.terms" @change="validateTerms" />
                <label class="form-check-label" for="terms">
                  I agree to the
                  <a href="#" class="text-decoration-none">Terms and Conditions</a>
                </label>
                <div class="invalid-feedback" v-if="validation.terms.error">
                  You must agree to the terms and conditions
                </div>
              </div>

              <!-- Register Button -->
              <div class="d-grid">
                <button type="submit" class="btn btn-primary" :disabled="loading || !isFormValid">
                  <span v-if="loading" class="spinner-border spinner-border-sm me-2" role="status"></span>
                  {{ loading ? "Creating account..." : "Register" }}
                </button>
              </div>
            </form>

            <hr class="my-4" />

            <div class="text-center mt-4">
              <p class="mb-0">Already have an account?
                <router-link to="/login" class="text-decoration-none">Login here</router-link>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../utils/api';

export default {
  name: "Register",
  data() {
    return {
      formData: {
        username: "",
        email: "",
        password: "",
        confirmPassword: "",
        terms: false,
      },
      validation: {
        username: { valid: false, error: "" },
        email: { valid: false, error: "" },
        password: { valid: false, error: "" },
        confirmPassword: { valid: false, error: "" },
        terms: { valid: false, error: "" },
      },
      showPassword: false,
      loading: false,
    };
  },
  computed: {
    passwordStrength() {
      if (!this.formData.password) return 0;

      let strength = 0;
      const password = this.formData.password;

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
    },
    isFormValid() {
      return (
        this.validation.username.valid &&
        this.validation.email.valid &&
        this.validation.password.valid &&
        this.validation.confirmPassword.valid &&
        this.validation.terms.valid
      );
    },
  },
  methods: {
    validateUsername() {
      const username = this.formData.username;

      if (!username) {
        this.validation.username = { valid: false, error: "" };
        return;
      }

      if (username.length < 3) {
        this.validation.username = {
          valid: false,
          error: "Username must be at least 3 characters long",
        };
        return;
      }

      if (username.length > 20) {
        this.validation.username = {
          valid: false,
          error: "Username must be less than 20 characters",
        };
        return;
      }

      if (!/^[a-zA-Z0-9_]+$/.test(username)) {
        this.validation.username = {
          valid: false,
          error: "Username can only contain letters, numbers, and underscores",
        };
        return;
      }

      this.validation.username = { valid: true, error: "" };
    },

    validateEmail() {
      const email = this.formData.email;

      if (!email) {
        this.validation.email = { valid: false, error: "" };
        return;
      }

      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (!emailRegex.test(email)) {
        this.validation.email = {
          valid: false,
          error: "Please enter a valid email address",
        };
        return;
      }

      this.validation.email = { valid: true, error: "" };
    },

    validatePassword() {
      const password = this.formData.password;

      if (!password) {
        this.validation.password = { valid: false, error: "" };
        return;
      }

      if (password.length < 8) {
        this.validation.password = {
          valid: false,
          error: "Password must be at least 8 characters long",
        };
        return;
      }

      if (!/[a-z]/.test(password)) {
        this.validation.password = {
          valid: false,
          error: "Password must contain at least one lowercase letter",
        };
        return;
      }

      if (!/[A-Z]/.test(password)) {
        this.validation.password = {
          valid: false,
          error: "Password must contain at least one uppercase letter",
        };
        return;
      }

      if (!/[0-9]/.test(password)) {
        this.validation.password = {
          valid: false,
          error: "Password must contain at least one number",
        };
        return;
      }

      if (!/[^A-Za-z0-9]/.test(password)) {
        this.validation.password = {
          valid: false,
          error: "Password must contain at least one special character",
        };
        return;
      }

      this.validation.password = { valid: true, error: "" };

      // Re-validate confirm password if it exists
      if (this.formData.confirmPassword) {
        this.validateConfirmPassword();
      }
    },

    validateConfirmPassword() {
      const confirmPassword = this.formData.confirmPassword;

      if (!confirmPassword) {
        this.validation.confirmPassword = { valid: false, error: "" };
        return;
      }

      if (confirmPassword !== this.formData.password) {
        this.validation.confirmPassword = {
          valid: false,
          error: "Passwords do not match",
        };
        return;
      }

      this.validation.confirmPassword = { valid: true, error: "" };
    },

    validateTerms() {
      this.validation.terms = {
        valid: this.formData.terms,
        error: this.formData.terms ? "" : "You must agree to the terms and conditions",
      };
    },

    async handleRegister() {
      if (!this.isFormValid) {
        alert("Please fix all validation errors before submitting");
        return;
      }

      this.loading = true;

      try {
        const res = await api.createUser({
          username: this.formData.username,
          email: this.formData.email,
          password: this.formData.password,
          createdAt: new Date().toLocaleDateString('en-GB') // "dd/mm/yyyy"
        });

        if (res.status === 201 || res.status === 200) {
          alert("Account created successfully!");
          this.$router.push('/login');
        } else {
          alert("Registration failed");
        }

      } catch (error) {
        console.error("Registration error:", error);
        alert("An error occurred during registration");
      } finally {
        this.loading = false;
      }
    }
  },
};
</script>


<style scoped>
.card {
  border: none;
  border-radius: 15px;
}

.card-body {
  padding: 2rem;
}

.form-control {
  border-radius: 8px;
  border: 1px solid #e0e0e0;
  padding: 12px 15px;
}

.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}

.btn-primary {
  border-radius: 8px;
  padding: 12px;
  font-weight: 500;
}

.btn-primary:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.btn-primary:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.form-check-input:checked {
  background-color: #0d6efd;
  border-color: #0d6efd;
}

.progress {
  border-radius: 10px;
}

.progress-bar {
  border-radius: 10px;
  transition: width 0.3s ease;
}

a {
  color: #0d6efd;
}

a:hover {
  color: #0a58ca;
}

.input-group .btn {
  border-radius: 0 8px 8px 0;
}

.input-group .form-control {
  border-radius: 8px 0 0 8px;
}
</style>
