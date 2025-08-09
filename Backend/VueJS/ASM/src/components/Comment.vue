<template>
  <div class="comment-section">
    <!-- Comment Form -->
    <div class="card shadow-sm border-0 rounded-3 mb-4" v-if="isAuthenticated">
      <div class="card-body">
        <h5 class="card-title mb-3">
          <i class="bi bi-chat-dots me-2"></i>
          Add a Comment
        </h5>
        <form @submit.prevent="submitComment">
          <div class="mb-3">
            <textarea class="form-control" :class="{
              'is-valid': validation.content.valid && newComment.content,
              'is-invalid': validation.content.error && newComment.content
            }" v-model="newComment.content" @input="validateComment" rows="3"
              placeholder="Write your comment here..." maxlength="500" required></textarea>
            <div class="invalid-feedback" v-if="validation.content.error">
              {{ validation.content.error }}
            </div>
            <div class="form-text">
              {{ newComment.content.length }}/500 characters
            </div>
          </div>
          <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary" :disabled="loading || !isCommentValid">
              <span v-if="loading" class="spinner-border spinner-border-sm me-2" role="status"></span>
              <i v-else class="bi bi-send me-2"></i>
              {{ loading ? 'Posting...' : 'Post Comment' }}
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- Comments List -->
    <div class="comments-list">
      <h5 class="mb-3">
        <i class="bi bi-chat-square-text me-2"></i>
        Comments ({{ comments.length }})
      </h5>

      <div v-if="comments.length === 0" class="text-center py-4">
        <i class="bi bi-chat-dots display-4 text-muted mb-3"></i>
        <p class="text-muted">No comments yet. Be the first to comment!</p>
      </div>

      <div class="comment-item mb-3" v-for="comment in sortedComments" :key="comment.id">
        <div class="card shadow-sm border-0 rounded-3">
          <div class="card-body">
            <!-- Comment Header -->
            <div class="d-flex justify-content-between align-items-start mb-2">
              <div class="d-flex align-items-center">
                <img :src="getUserAvatar(comment.userId)" class="rounded-circle me-3" width="40" height="40"
                  :alt="getUserName(comment.userId)" />
                <div>
                  <h6 class="mb-0 fw-bold">{{ comment.userName }}</h6>
                  <small class="text-muted">
                    {{ formatDate(comment.createdAt) }}
                  </small>
                </div>
              </div>
              <!-- Comment Actions -->
              <div class="dropdown" v-if="canEditComment(comment)">
                <button class="btn btn-sm btn-outline-secondary" type="button" data-bs-toggle="dropdown">
                  <i class="bi bi-three-dots"></i>
                </button>
                <ul class="dropdown-menu">
                  <li>
                    <a class="dropdown-item" href="#" @click.prevent="editComment(comment)">
                      <i class="bi bi-pencil me-2"></i>Edit
                    </a>
                  </li>
                  <li>
                    <a class="dropdown-item text-danger" href="#" @click.prevent="$emit('request-delete', comment)">
                      <i class="bi bi-trash me-2"></i>Delete
                    </a>
                  </li>
                </ul>
              </div>
            </div>

            <!-- Comment Content -->
            <div v-if="!comment.isEditing">
              <p class="mb-2">{{ comment.content }}</p>
              <button class="btn btn-sm btn-outline-primary" @click="showReplyForm(comment)"
                v-if="isAuthenticated && !comment.showReplyForm">
                <i class="bi bi-reply me-1"></i>
                Reply
              </button>
            </div>
            <!-- Edit Comment Form -->
            <div v-else>
              <input v-model="comment.editContent" class="form-control mb-2" />
              <div class="d-flex gap-2">
                <button class="btn btn-sm btn-primary" @click="saveEdit(comment)"
                  :disabled="!comment.editContent.trim()">
                  Save
                </button>
                <button class="btn btn-sm btn-secondary" @click="cancelEdit(comment)">
                  Cancel
                </button>
              </div>
            </div>

            <!-- Reply Form -->
            <div v-if="comment.showReplyForm" class="mt-3">
              <div class="card bg-light">
                <div class="card-body">
                  <h6 class="card-title">Reply to {{ comment.userName }}</h6>
                  <textarea class="form-control mb-2" v-model="comment.replyContent" rows="2"
                    placeholder="Write your reply..." maxlength="500"></textarea>
                  <div class="d-flex gap-2">
                    <button class="btn btn-sm btn-primary" @click="submitReply(comment)"
                      :disabled="!comment.replyContent.trim()">
                      Reply
                    </button>
                    <button class="btn btn-sm btn-secondary" @click="cancelReply(comment)">
                      Cancel
                    </button>
                  </div>
                </div>
              </div>
            </div>

            <!-- Replies -->
            <div v-if="comment.replies && comment.replies.length > 0" class="mt-3">
              <div class="reply-item ms-4" v-for="reply in comment.replies" :key="reply.id">
                <div class="card bg-light border-0">
                  <div class="card-body py-2">
                    <div class="d-flex justify-content-between align-items-start">
                      <div class="d-flex align-items-center">
                        <img :src="getUserAvatar(reply.userId)" class="rounded-circle me-2" width="32" height="32"
                          :alt="getUserName(reply.userId)" />
                        <div>
                          <h6 class="mb-0 fw-bold small">{{ reply.userName }}</h6>
                          <small class="text-muted">{{ formatDate(reply.createdAt) }}</small>
                        </div>
                      </div>
                      <div class="dropdown position-relative" v-if="canEditComment(reply)">
                        <button class="btn btn-sm btn-outline-secondary" type="button" data-bs-toggle="dropdown">
                          <i class="bi bi-three-dots"></i>
                        </button>
                        <ul class="dropdown-menu custom-dropdown">
                          <li>
                            <a class="dropdown-item" href="#" @click.prevent="editComment(reply)">
                              <i class="bi bi-pencil me-2"></i>Edit
                            </a>
                          </li>
                          <li>
                            <a class="dropdown-item text-danger" href="#"
                              @click.prevent="$emit('request-delete', reply)">
                              <i class="bi bi-trash me-2"></i>Delete
                            </a>
                          </li>
                        </ul>
                      </div>
                    </div>
                    <div v-if="!reply.isEditing">
                      <p class="mb-0 mt-2 small">{{ reply.content }}</p>
                    </div>
                    <div v-else class="mt-2">
                      <input v-model="reply.editContent" class="form-control mb-2" />
                      <div class="d-flex gap-2">
                        <button class="btn btn-sm btn-primary" @click="saveEdit(reply)"
                          :disabled="!reply.editContent.trim()">
                          Save
                        </button>
                        <button class="btn btn-sm btn-secondary" @click="cancelEdit(reply)">Cancel</button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '../utils/api'

export default {
  name: "Comment",
  props: {
    postId: { type: [Number, String], required: true }, // sửa lại cho nhận cả số và chuỗi
    currentUser: { type: Object, required: true },
    reload: { type: Number, default: 0 }
  },
  data() {
    return {
      comments: [],
      newComment: { content: "" },
      validation: { content: { valid: false, error: "" } },
      loading: false
    };
  },
  computed: {
    sortedComments() {
      return this.comments.slice().sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    },
    isCommentValid() {
      return this.validation.content.valid && this.newComment.content.trim().length > 0;
    },
    isAuthenticated() {
      return !!this.currentUser;
    }
  },
  mounted() {
    this.loadComments();
  },
  watch: {
    reload() {
      this.loadComments();
    }
  },
  methods: {
    async loadComments() {
      // Lấy tất cả comments của post
      const res = await api.getCommentsByPost(this.postId);
      const allComments = res.data;

      // Lấy tất cả users (hoặc chỉ các user liên quan)
      const userRes = await api.getUsers();
      const users = userRes.data;

      // Hàm lấy user theo id
      const getUser = (id) => users.find(u => u.id == id);

      // Gán avatar, tên, và trạng thái edit cho từng comment/reply
      this.comments = allComments
        .filter(c => !c.parentId)
        .map(parent => {
          const user = getUser(parent.userId);
          return {
            ...parent,
            userName: user ? (user.name || user.username || 'User') : 'User',
            avatar: user ? user.avatar : 'https://via.placeholder.com/40x40',
            isEditing: false,
            editContent: '',
            showReplyForm: false,
            replyContent: '',
            replies: allComments
              .filter(r => r.parentId === parent.id)
              .map(reply => {
                const replyUser = getUser(reply.userId);
                return {
                  ...reply,
                  userName: replyUser ? (replyUser.name || replyUser.username || 'User') : 'User',
                  avatar: replyUser ? replyUser.avatar : 'https://via.placeholder.com/40x40',
                  isEditing: false,
                  editContent: ''
                };
              })
          };
        });
    },
    validateComment() {
      const content = this.newComment.content.trim();
      if (!content) {
        this.validation.content = { valid: false, error: "" };
        return;
      }
      if (content.length > 500) {
        this.validation.content = { valid: false, error: "Comment must be less than 500 characters" };
        return;
      }
      this.validation.content = { valid: true, error: "" };
    },
    async submitComment() {
      await api.createComment({
        postId: String(this.postId), // ép kiểu về chuỗi
        userId: this.currentUser.id,
        content: this.newComment.content,
        createdAt: new Date().toISOString()
      });
      this.newComment.content = '';
      await this.loadComments();
    },
    showReplyForm(comment) {
      this.comments.forEach(c => c.showReplyForm = false);
      comment.showReplyForm = true;
      comment.replyContent = '';
    },
    cancelReply(comment) {
      comment.showReplyForm = false;
      comment.replyContent = '';
    },
    async submitReply(comment) {
      if (!comment.replyContent || !comment.replyContent.trim()) return;
      try {
        await api.createComment({
          postId: this.postId,
          userId: this.currentUser.id,
          userName: this.currentUser.name,
          content: comment.replyContent,
          parentId: comment.id,
          createdAt: new Date().toISOString()
        });
        await this.loadComments();
        this.cancelReply(comment);
      } catch (error) {
        console.error('Error posting reply:', error);
      }
    },
    editComment(comment) {
      comment.isEditing = true;
      comment.editContent = comment.content;
    },
    cancelEdit(comment) {
      comment.isEditing = false;
      comment.editContent = '';
    },
    async saveEdit(comment) {
      await api.updateComment(comment.id, { ...comment, content: comment.editContent });
      comment.isEditing = false;
      this.loadComments(); // reload lại comment sau khi sửa
    },
    canEditComment(comment) {
      return this.currentUser && comment.userId === this.currentUser.id;
    },
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString('en-US', {
        year: 'numeric',
        month: 'short',
        day: 'numeric',
        hour: '2-digit',
        minute: '2-digit'
      });
    },
    getUserAvatar(userId) {
      // Ưu tiên lấy avatar từ comment đã gán
      const comment = this.comments.find(c => c.userId === userId);
      return comment && comment.avatar ? comment.avatar : 'https://via.placeholder.com/40x40';
    },
    getUserName(userId) {
      const comment = this.comments.find(c => c.userId === userId);
      return comment && comment.userName ? comment.userName : 'User';
    }
  }
};
</script>

<style scoped>
.comment-section {
  margin-top: 2rem;
}
.comment-item {
  transition: transform 0.2s ease;
}
.comment-item:hover {
  transform: translateY(-2px);
}
.card {
  border: none;
  border-radius: 12px;
}
.reply-item {
  border-left: 3px solid #e9ecef;
}
.btn {
  border-radius: 8px;
  font-weight: 500;
}
.btn:hover:not(:disabled) {
  transform: translateY(-1px);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.form-control:focus {
  border-color: #0d6efd;
  box-shadow: 0 0 0 0.2rem rgba(13, 110, 253, 0.25);
}
.dropdown-menu {
  border: none;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  border-radius: 8px;
  z-index: 9999 !important;
  position: absolute !important;
}
.custom-dropdown {
  z-index: 9999 !important;
  position: absolute !important;
}
.dropdown-item {
  border-radius: 4px;
  margin: 2px 8px;
  padding: 6px 12px;
}
.dropdown-item:hover {
  background-color: #f8f9fa;
}
.modal {
  background-color: rgba(0, 0, 0, 0.5);
}
.spinner-border {
  width: 1rem;
  height: 1rem;
}
.modal-backdrop.fade.show {
  position: fixed;
  z-index: 1050;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 1050;
  display: flex;
  align-items: center;
  justify-content: center;
}
.modal-dialog-centered {
  background: white;
  border-radius: 12px;
  max-width: 500px;
  width: 90%;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
}
</style>