<template>
  <div class="container mt-4">
    <!-- Navigation Tabs -->
    <div class="row mb-4">
      <div class="col-12">
        <div class="card shadow-lg border-0 rounded-3">
          <div class="card-body p-0">
            <ul class="nav nav-tabs nav-fill" role="tablist">
              <li class="nav-item" role="presentation">
                <button
                  class="nav-link"
                  :class="{ active: activeTab === 'posts' }"
                  @click="activeTab = 'posts'"
                  type="button"
                  role="tab"
                >
                  <i class="bi bi-file-text me-2"></i>
                  My Posts
                </button>
              </li>
              <li class="nav-item" role="presentation">
                <button
                  class="nav-link"
                  :class="{ active: activeTab === 'all' }"
                  @click="activeTab = 'all'"
                  type="button"
                  role="tab"
                >
                  <i class="bi bi-globe me-2"></i>
                  All Posts
                </button>
              </li>
              <li class="nav-item" role="presentation">
                <button
                  class="nav-link"
                  :class="{ active: activeTab === 'profile' }"
                  @click="activeTab = 'profile'"
                  type="button"
                  role="tab"
                >
                  <i class="bi bi-person me-2"></i>
                  Profile Settings
                </button>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!-- Posts Tab Content -->
    <div v-if="activeTab === 'posts'">
      <!-- User Profile Header -->
      <div class="row mb-4">
        <div class="col-12">
          <div class="card shadow-lg border-0 rounded-3">
            <div class="card-body p-4">
              <div class="row align-items-center">
                <div class="col-md-3 text-center">
                  <div class="position-relative d-inline-block">
                    <img
                      :src="userProfile.avatar || defaultAvatar"
                      class="rounded-circle"
                      width="150"
                      height="150"
                      alt="Profile"
                    />
                  </div>
                </div>
                <div class="col-md-9">
                  <h2 class="mb-2">{{ userProfile?.name || 'User' }}</h2>
                  <p class="text-muted mb-3">{{ userProfile?.email || 'user@example.com' }}</p>
                  <div class="row text-center">
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
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Action Buttons -->
      <div class="row mb-4">
        <div class="col-12">
          <div class="d-flex justify-content-between align-items-center">
            <div class="d-flex gap-2">
              <button
                class="btn btn-primary"
                @click="showCreatePostModal"
              >
                <i class="bi bi-plus-circle me-2"></i>
                Create New Post
              </button>
              <button
                class="btn btn-outline-secondary"
                @click="refreshPosts"
                :disabled="loading"
              >
                <i class="bi bi-arrow-clockwise me-2"></i>
                {{ loading ? 'Loading...' : 'Refresh' }}
              </button>
            </div>
            
            <!-- Filter and Sort -->
            <div class="d-flex gap-2">
              <select class="form-select" v-model="filterStatus" style="width: auto;">
                <option value="all">All Posts</option>
                <option value="published">Published</option>
                <option value="draft">Drafts</option>
              </select>
              <select class="form-select" v-model="sortBy" style="width: auto;">
                <option value="newest">Newest First</option>
                <option value="oldest">Oldest First</option>
                <option value="title">By Title</option>
              </select>
            </div>
          </div>
        </div>
      </div>

      <!-- Posts List -->
      <div class="row">
        <div class="col-12">
          <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
              <span class="visually-hidden">Loading...</span>
            </div>
            <p class="mt-3 text-muted">Loading your posts...</p>
          </div>
          
          <div v-else-if="filteredPosts.length === 0" class="text-center py-5">
            <i class="bi bi-file-text display-1 text-muted"></i>
            <h4 class="mt-3 text-muted">No posts found</h4>
            <p class="text-muted">Create your first post to get started!</p>
            <button class="btn btn-primary" @click="showCreatePost = true">
              <i class="bi bi-plus-circle me-2"></i>
              Create Your First Post
            </button>
          </div>
          
          <div v-else class="row">
            <div 
              class="col-md-6 col-lg-4 mb-4" 
              v-for="post in filteredPosts" 
              :key="post.id"
            >
              <div class="card h-100 shadow-sm border-0 rounded-3">
                <img
                  v-if="post.imageUrl"
                  :src="post.imageUrl"
                  class="card-img-top"
                  style="height: 200px; object-fit: cover;"
                  :alt="post.title"
                />
                <div class="card-body d-flex flex-column">
                  <div class="mb-2">
                    <span 
                      class="badge"
                      :class="post.isPublished ? 'bg-success' : 'bg-warning'"
                    >
                      {{ post.isPublished ? 'Published' : 'Draft' }}
                    </span>
                    <span class="badge bg-secondary ms-1">{{ post.category }}</span>
                  </div>
                  <h5 class="card-title">{{ post.title }}</h5>
                  <p class="card-text text-muted flex-grow-1">
                    {{ truncateText(post.content, 100) }}
                  </p>
                  <div class="mt-auto">
                    <small class="text-muted">
                      <i class="bi bi-calendar me-1"></i>
                      {{ formatDate(post.createdAt) }}
                    </small>
                    <p class="text-muted mb-1">By {{ getUserName(post.authorId) }}</p>
                    <div class="mt-2 d-flex gap-1">
                      <button
                        class="btn btn-sm btn-outline-primary"
                        @click="viewPost(post)"
                      >
                        <i class="bi bi-eye"></i>
                      </button>
                      <button
                        class="btn btn-sm btn-outline-secondary"
                        @click="editPost(post)"
                      >
                        <i class="bi bi-pencil"></i>
                      </button>
                      <button
                        class="btn btn-sm btn-outline-danger"
                        @click="deletePost(post)"
                      >
                        <i class="bi bi-trash"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- All Posts Tab Content -->
    <div v-if="activeTab === 'all'">
      <div class="row mb-4">
        <div class="col-12">
          <div class="card shadow-lg border-0 rounded-3">
            <div class="card-body p-4">
              <h3 class="mb-4"><i class="bi bi-globe me-2"></i>All Published Posts</h3>
              <div v-if="allPublishedPosts.length === 0" class="text-center text-muted py-5">
                <i class="bi bi-emoji-frown display-4"></i>
                <p class="mt-3">No published posts yet.</p>
              </div>
              <div v-else>
                <div v-for="post in allPublishedPosts" :key="post.id" class="mb-4">
                  <div class="card mb-2">
                    <div class="card-body">
                      <h5 class="card-title">{{ post.title }}</h5>
                      <p class="card-text">{{ post.content }}</p>
                      <div v-if="(typeof post.image === 'string' && post.image.startsWith('data:')) || post.imageUrl" class="mb-2">
                        <img
                          :src="typeof post.image === 'string' && post.image.startsWith('data:') ? post.image : post.imageUrl"
                          class="img-fluid rounded"
                          style="max-height:500px;"
                        />
                      </div>
                      <div class="d-flex justify-content-between align-items-center">
                        <div>
                          <span class="badge bg-primary me-2">By {{ getUserName(post.authorId) }}</span>
                          <span class="text-muted small"><i class="bi bi-clock me-1"></i>{{ formatDate(post.createdAt) }}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- Comment Section -->
                  <Comment 
                    :postId="post.id"
                    :currentUser="currentUser"
                    :reload="reloadCommentKey[post.id] || 0"
                    @request-delete="(comment) => handleRequestDelete(comment, post.id)"
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Profile Settings Tab Content -->
    <div v-if="activeTab === 'profile'">
      <UserProfile 
        :currentUser="userProfile"
        @profile-updated="handleProfileUpdated"
        @avatar-updated="handleAvatarUpdated"
      />
    </div>

    <!-- Create/Edit Post Modal -->
    <div class="modal fade" :class="{ show: showCreatePost }" :style="{ display: showCreatePost ? 'block' : 'none' }" tabindex="-1">
      <div class="modal-dialog modal-xl">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              {{ editingPost ? 'Edit Post' : 'Create New Post' }}
            </h5>
            <button type="button" class="btn-close" @click="closePostModal"></button>
          </div>
          <div class="modal-body">
            <Upload 
              :post="editingPost"
              :currentUser="currentUser"
              @post-saved="handlePostSaved"
              @cancel-edit="closePostModal"
            />
          </div>
        </div>
      </div>
    </div>

    <!-- Delete Confirmation Modal -->
    <div class="modal fade" :class="{ show: showDeleteModal }" :style="{ display: showDeleteModal ? 'block' : 'none' }" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Confirm Delete</h5>
            <button type="button" class="btn-close" @click="showDeleteModal = false"></button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete "<strong>{{ postToDelete?.title }}</strong>"?</p>
            <p class="text-danger">This action cannot be undone.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showDeleteModal = false">
              Cancel
            </button>
            <button 
              type="button" 
              class="btn btn-danger" 
              @click="confirmDelete"
              :disabled="deleting"
            >
              <span v-if="deleting" class="spinner-border spinner-border-sm me-2"></span>
              {{ deleting ? 'Deleting...' : 'Delete Post' }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal Backdrop -->
    <div v-if="showCreatePost || showDeleteModal" class="modal-backdrop fade show"></div>

    <!-- Modal xác nhận xóa comment -->
    <div class="modal fade" :class="{ show: showDeleteCommentModal }" :style="{ display: showDeleteCommentModal ? 'block' : 'none' }" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Delete Comment</h5>
            <button type="button" class="btn-close" @click="showDeleteCommentModal = false"></button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete this comment?</p>
            <p class="text-danger">This action cannot be undone.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showDeleteCommentModal = false">Cancel</button>
            <button type="button" class="btn btn-danger" @click="confirmDeleteComment" :disabled="deleting">
              <span v-if="deleting" class="spinner-border spinner-border-sm me-2"></span>
              {{ deleting ? 'Deleting...' : 'Delete' }}
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showDeleteCommentModal" class="modal-backdrop fade show"></div>

    <!-- Modal xác nhận xóa post -->
    <div class="modal fade" :class="{ show: showDeletePostModal }" :style="{ display: showDeletePostModal ? 'block' : 'none' }" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Delete Post</h5>
            <button type="button" class="btn-close" @click="showDeletePostModal = false"></button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete "<strong>{{ postToDelete?.title }}</strong>"?</p>
            <p class="text-danger">This action cannot be undone.</p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="showDeletePostModal = false">Cancel</button>
            <button type="button" class="btn btn-danger" @click="confirmDeletePost" :disabled="deleting">
              <span v-if="deleting" class="spinner-border spinner-border-sm me-2"></span>
              {{ deleting ? 'Deleting...' : 'Delete Post' }}
            </button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="showDeletePostModal" class="modal-backdrop fade show"></div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import Upload from './Upload.vue'
import UserProfile from './UserProfile.vue'
import Comment from './Comment.vue'
import api from '../utils/api'

export default {
  name: "Profile",
  components: { Upload, UserProfile, Comment },
  props: { currentUser: { type: Object, required: true } },
  setup() {
    const router = useRouter()
    return { router }
  },
  data() {
    return {
      activeTab: 'posts',
      userProfile: null,
      posts: [],
      loading: false,
      deleting: false,
      showCreatePost: false,
      showDeleteModal: false,
      editingPost: null,
      filterStatus: 'all',
      sortBy: 'newest',
      allPublishedPosts: [],
      commentToDelete: null,
      showDeleteCommentModal: false,
      showDeletePostModal: false,
      postToDelete: null,
      reloadCommentKey: {},
      commentToDeletePostId: null,
      users: [],
    };
  },
  watch: {
    currentUser: {
      immediate: true,
      handler(newUser) {
        if (newUser) {
          this.loadUserProfile();
          this.loadPosts();
          this.loadAllPublishedPosts();
        }
      }
    }
  },
  mounted() {
    if (this.currentUser) {
      this.loadUserProfile();
      this.loadPosts();
      this.loadAllPublishedPosts();
    }
    this.loadUsers();
  },
  computed: {
    filteredPosts() {
      let filtered = [...this.posts];
      if (this.filterStatus === 'published') filtered = filtered.filter(post => post.isPublished);
      else if (this.filterStatus === 'draft') filtered = filtered.filter(post => !post.isPublished);
      filtered.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
      return filtered;
    },
    stats() {
      const total = this.posts.length;
      const published = this.posts.filter(p => p.isPublished).length;
      const draft = total - published;
      return { totalPosts: total, publishedPosts: published, draftPosts: draft };
    }
  },
  methods: {
    loadUserProfile() {
      if (this.currentUser) this.userProfile = { ...this.currentUser };
    },
    async loadPosts() {
      this.loading = true;
      try {
        const res = await api.getPosts();
        this.posts = res.data.filter(post => String(post.authorId) === String(this.currentUser.id));
      } catch (error) {
        console.error("Error loading posts:", error);
      } finally {
        this.loading = false;
      }
    },
    refreshPosts() { this.loadPosts(); },
    // Xem chi tiết post (ví dụ: mở modal hoặc chuyển trang)
    viewPost(post) {
      // Ví dụ: mở modal chi tiết hoặc chuyển route
      alert(`Title: ${post.title}\nContent: ${post.content}`);
      // Hoặc: this.$router.push({ name: 'PostDetail', params: { id: post.id } });
    },
    // Sửa post: mở modal và truyền dữ liệu post vào form
    editPost(post) {
      this.editingPost = {
        id: post.id, // giữ nguyên kiểu chuỗi
        title: post.title,
        content: post.content,
        tags: post.tags,
        authorId: post.authorId, // giữ nguyên
        category: post.category,
        isPublished: post.isPublished,
        createdAt: post.createdAt,
        updatedAt: post.updatedAt,
        imageUrl: post.imageUrl || "",
      };
      this.showCreatePost = true;
    },
    // Xóa post: mở modal xác nhận xóa
    deletePost(post) {
      this.postToDelete = post;
      this.showDeleteModal = true;
    },
    // Xác nhận xóa post
    async confirmDelete() {
      this.deleting = true;
      try {
        await api.deletePost(this.postToDelete.id);
        await this.loadPosts();
        await this.loadAllPublishedPosts();
        this.showDeleteModal = false;
        this.postToDelete = null;
      } catch (error) {
        console.error("Error deleting post:", error);
      } finally {
        this.deleting = false;
      }
    },
    async handlePostSaved(postData) {
      if (this.editingPost) {
        await api.updatePost(postData.id, postData); // truyền đầy đủ postData
      } else {
        const { id, ...data } = postData;
        await api.createPost(data);
      }
      this.editingPost = null;
      this.closePostModal();
      await this.loadPosts();
      await this.loadAllPublishedPosts();
    },
    closePostModal() {
      this.showCreatePost = false;
      this.editingPost = null;
    },
    async confirmDelete() {
      this.deleting = true;
      try {
        await api.deletePost(this.postToDelete.id);
        this.posts = this.posts.filter(post => post.id !== this.postToDelete.id);
        await this.loadAllPublishedPosts();
      } catch (error) {
        console.error("Error deleting post:", error);
      } finally {
        this.deleting = false;
        this.showDeleteModal = false;
        this.postToDelete = null;
      }
    },
    handleProfileUpdated(updatedProfile) {
      // Gọi API cập nhật user nếu cần
      this.userProfile = { ...this.userProfile, ...updatedProfile };
      this.$emit('profile-updated', this.userProfile);
    },
    handleAvatarUpdated(avatarUrl) {
      this.userProfile.avatar = avatarUrl;
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString('en-US', { year: 'numeric', month: 'short', day: 'numeric' });
    },
    truncateText(text, maxLength) {
      if (!text) return '';
      if (text.length <= maxLength) return text;
      return text.substring(0, maxLength) + '...';
    },
    async loadAllPublishedPosts() {
      const res = await api.getPosts();
      // Sắp xếp theo createdAt mới nhất lên đầu
      this.allPublishedPosts = res.data
        .filter(post => post.isPublished)
        .sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    },
    loadUsers() {
      api.getUsers().then(res => {
        this.users = res.data;
      }).catch(err => {
        console.error('Failed to load users:', err);
      });
    },
    getUserName(userId) {
      const user = this.users.find(u => u.id === userId);
      // Ưu tiên lấy trường 'name', nếu không có thì lấy 'username'
      return user ? (user.name || user.username || 'Unknown User') : 'Unknown User';
    },
    handleRequestDelete(comment, postId) {
      this.commentToDelete = comment;
      this.commentToDeletePostId = postId;
      this.showDeleteCommentModal = true;
    },
    async confirmDeleteComment() {
      this.deleting = true;
      try {
        await api.deleteComment(this.commentToDelete.id);
        this.showDeleteCommentModal = false;
        // Reload comment section
        if (!this.reloadCommentKey[this.commentToDeletePostId]) {
          this.reloadCommentKey[this.commentToDeletePostId] = 1;
        } else {
          this.reloadCommentKey[this.commentToDeletePostId]++;
        }
      } catch (e) {
        console.error('Failed to delete comment');
      }
      this.deleting = false;
      this.commentToDelete = null;
    },
    async confirmDeletePost() {
      this.deleting = true;
      try {
        await api.deletePost(this.postToDelete.id);
        await this.loadPosts();
        await this.loadAllPublishedPosts();
        this.showDeletePostModal = false;
        this.postToDelete = null;
      } catch (e) {
        console.error('Failed to delete post');
      }
      this.deleting = false;
    },
    // Khi bấm nút "Create New Post"
    showCreatePostModal() {
      this.editingPost = null; // Đảm bảo prop post là null
      this.showCreatePost = true;
    },
  },
};
</script>

<style scoped>
.card {
  border: none;
  border-radius: 15px;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.card:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1) !important;
}

.nav-tabs {
  border-bottom: none;
}

.nav-tabs .nav-link {
  border: none;
  border-radius: 0;
  color: #6c757d;
  font-weight: 500;
  padding: 1rem 1.5rem;
}

.nav-tabs .nav-link.active {
  color: #0d6efd;
  background-color: transparent;
  border-bottom: 3px solid #0d6efd;
}

.nav-tabs .nav-link:hover {
  border-color: transparent;
  color: #0d6efd;
}

.btn {
  border-radius: 8px;
  font-weight: 500;
}

.btn:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.modal {
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  border: none;
  border-radius: 15px;
}

.modal-header {
  border-bottom: 1px solid #e9ecef;
  border-radius: 15px 15px 0 0;
}

.modal-footer {
  border-top: 1px solid #e9ecef;
  border-radius: 0 0 15px 15px;
}

.badge {
  font-size: 0.75rem;
  padding: 0.375rem 0.75rem;
}

.form-select {
  border-radius: 8px;
  border: 2px solid #e9ecef;
}

.form-select:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}
</style>
