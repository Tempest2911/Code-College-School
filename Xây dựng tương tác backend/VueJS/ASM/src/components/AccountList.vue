<template>
  <div class="account-list">
    <div class="card shadow-lg border-0 rounded-3">
      <div class="card-header bg-info text-white">
        <h5 class="mb-0">
          <i class="bi bi-people me-2"></i>
          Available Accounts for Testing
        </h5>
      </div>
      <div class="card-body">
        <div class="row">
          <div 
            class="col-md-4 mb-3" 
            v-for="account in accounts" 
            :key="account.id"
          >
            <div class="card h-100 border-0 shadow-sm">
              <div class="card-body text-center">
                <div class="mb-3">
                  <img
                    :src="account.avatar || 'https://via.placeholder.com/80x80/0d6efd/ffffff?text=' + account.name.charAt(0)"
                    class="rounded-circle"
                    width="80"
                    height="80"
                    :alt="account.name"
                  />
                </div>
                <h6 class="card-title mb-1">{{ account.name }}</h6>
                <p class="text-muted small mb-2">{{ account.email }}</p>
                <div class="mb-3">
                  <span class="badge bg-primary me-1">{{ account.username }}</span>
                  <span class="badge bg-secondary">ID: {{ account.id }}</span>
                </div>
                <div class="d-grid">
                  <button
                    class="btn btn-outline-primary btn-sm"
                    @click="useAccount(account)"
                  >
                    <i class="bi bi-box-arrow-in-right me-1"></i>
                    Use This Account
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="text-center mt-4">
          <p class="text-muted mb-3">
            <strong>Password for all accounts:</strong> Password123!
          </p>
          <div class="alert alert-info">
            <i class="bi bi-info-circle me-2"></i>
            <strong>Tip:</strong> You can also register a new account or use any of these existing accounts to test the multi-user functionality.
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authManager from '../utils/auth.js';

export default {
  name: "AccountList",
  data() {
    return {
      accounts: []
    };
  },
  mounted() {
    this.loadAccounts();
  },
  methods: {
    loadAccounts() {
      this.accounts = authManager.users;
    },
    
    useAccount(account) {
      // Pre-fill the login form with account details
      this.$emit('use-account', {
        username: account.username,
        password: 'Password123!'
      });
    }
  }
};
</script>

<style scoped>
.account-list {
  margin-top: 2rem;
}

.card {
  border-radius: 15px;
}

.card-header {
  border-radius: 15px 15px 0 0 !important;
}

.btn {
  border-radius: 8px;
  font-weight: 500;
}

.btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.badge {
  font-size: 0.75rem;
}
</style> 