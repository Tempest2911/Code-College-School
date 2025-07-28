<template>
  <div class="container mt-4">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow-lg border-0 rounded-3">
          <div class="card-header bg-primary text-white">
            <h3 class="mb-0">
              <i class="bi bi-pencil-square me-2"></i>
              {{ isEditing ? 'Edit Post' : 'Create New Post' }}
            </h3>
          </div>
          <div class="card-body p-4">
            <form @submit.prevent="handleSubmit">
              <!-- Title Field -->
              <div class="mb-3">
                <label for="title" class="form-label fw-bold">Title</label>
                <input
                  type="text"
                  class="form-control form-control-lg"
                  :class="{
                    'is-valid': validation.title.valid && formData.title,
                    'is-invalid': validation.title.error && formData.title
                  }"
                  id="title"
                  v-model="formData.title"
                  @blur="validateTitle"
                  @input="validateTitle"
                  placeholder="Enter your post title"
                  maxlength="100"
                  required
                />
                <div class="invalid-feedback" v-if="validation.title.error">
                  {{ validation.title.error }}
                </div>
                <div class="valid-feedback" v-if="validation.title.valid">
                  Great title!
                </div>
                <div class="form-text">
                  {{ formData.title.length }}/100 characters
                </div>
              </div>

              <!-- Content Field -->
              <div class="mb-3">
                <label for="content" class="form-label fw-bold">Content</label>
                <textarea
                  class="form-control"
                  :class="{
                    'is-valid': validation.content.valid && formData.content,
                    'is-invalid': validation.content.error && formData.content
                  }"
                  id="content"
                  v-model="formData.content"
                  @blur="validateContent"
                  @input="validateContent"
                  rows="8"
                  placeholder="Write your post content here..."
                  maxlength="5000"
                  required
                ></textarea>
                <div class="invalid-feedback" v-if="validation.content.error">
                  {{ validation.content.error }}
                </div>
                <div class="valid-feedback" v-if="validation.content.valid">
                  Content looks good!
                </div>
                <div class="form-text">
                  {{ formData.content.length }}/5000 characters
                </div>
              </div>

              <!-- Image Upload -->
              <div class="mb-3">
                <label for="image" class="form-label fw-bold">Featured Image</label>
                <div class="input-group">
                  <input
                    type="file"
                    class="form-control"
                    id="image"
                    @change="handleImageUpload"
                    accept="image/*"
                    ref="fileInput"
                  />
                  <button
                    class="btn btn-outline-secondary"
                    type="button"
                    @click="$refs.fileInput.click()"
                  >
                    <i class="bi bi-upload me-1"></i>
                    Choose File
                  </button>
                </div>
                <div class="form-text">
                  Supported formats: JPG, PNG, GIF (Max 50MB)
                </div>
              </div>

              <!-- Image Preview -->
              <div class="mb-3" v-if="imagePreview">
                <label class="form-label">Image Preview:</label>
                <div class="position-relative d-inline-block">
                  <img
                    :src="imagePreview"
                    class="img-fluid rounded"
                    style="max-height: 200px; max-width: 100%;"
                    alt="Preview"
                  />
                  <button
                    type="button"
                    class="btn btn-sm btn-danger position-absolute top-0 end-0 m-1"
                    @click="removeImage"
                  >
                    <i class="bi bi-x"></i>
                  </button>
                </div>
              </div>

              <!-- Tags Field -->
              <div class="mb-3">
                <label for="tags" class="form-label fw-bold">Tags</label>
                <input
                  type="text"
                  class="form-control"
                  id="tags"
                  v-model="formData.tags"
                  placeholder="Enter tags separated by commas (e.g., technology, vue, tutorial)"
                />
                <div class="form-text">
                  Add relevant tags to help others find your post
                </div>
              </div>

              <!-- Category Selection -->
              <div class="mb-3">
                <label for="category" class="form-label fw-bold">Category</label>
                <select
                  class="form-select"
                  id="category"
                  v-model="formData.category"
                  required
                >
                  <option value="">Select a category</option>
                  <option value="technology">Technology</option>
                  <option value="lifestyle">Lifestyle</option>
                  <option value="travel">Travel</option>
                  <option value="food">Food</option>
                  <option value="health">Health</option>
                  <option value="education">Education</option>
                  <option value="other">Other</option>
                </select>
              </div>

              <!-- Publish Options -->
              <div class="mb-3">
                <div class="form-check">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    id="isPublished"
                    v-model="formData.isPublished"
                  />
                  <label class="form-check-label" for="isPublished">
                    Publish immediately
                  </label>
                </div>
                <div class="form-text">
                  Uncheck to save as draft
                </div>
              </div>

              <!-- Action Buttons -->
              <div class="d-flex gap-2 justify-content-end">
                <button
                  type="button"
                  class="btn btn-secondary"
                  @click="handleCancel"
                >
                  <i class="bi bi-x-circle me-1"></i>
                  Cancel
                </button>
                <button
                  type="button"
                  class="btn btn-outline-primary"
                  @click="saveDraft"
                  :disabled="loading"
                >
                  <i class="bi bi-save me-1"></i>
                  Save Draft
                </button>
                <button
                  type="submit"
                  class="btn btn-primary"
                  :disabled="loading || !isFormValid"
                >
                  <span
                    v-if="loading"
                    class="spinner-border spinner-border-sm me-2"
                    role="status"
                  ></span>
                  <i v-else class="bi bi-check-circle me-1"></i>
                  {{ loading ? 'Publishing...' : (isEditing ? 'Update Post' : 'Publish Post') }}
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Upload",
  props: {
    post: {
      type: Object,
      default: null
    }
  },
  data() {
    return {
      formData: {
        title: "",
        content: "",
        tags: "",
        category: "",
        isPublished: true,
        image: null
      },
      validation: {
        title: { valid: false, error: "" },
        content: { valid: false, error: "" }
      },
      imagePreview: null,
      loading: false,
      isEditing: false
    };
  },
  computed: {
    isFormValid() {
      return (
        this.validation.title.valid &&
        this.validation.content.valid &&
        this.formData.category
      );
    }
  },
  watch: {
    post: {
      immediate: true,
      handler(newPost) {
        if (newPost) {
          this.isEditing = true;
          this.formData = {
            title: newPost.title || "",
            content: newPost.content || "",
            tags: newPost.tags || "",
            category: newPost.category || "",
            isPublished: newPost.isPublished !== false,
            image: newPost.image || null
          };
          this.imagePreview = newPost.imageUrl || null;
          this.validateTitle();
          this.validateContent();
        }
      }
    }
  },
  methods: {
    validateTitle() {
      const title = this.formData.title;
      
      if (!title) {
        this.validation.title = { valid: false, error: "" };
        return;
      }
      
      if (title.length < 5) {
        this.validation.title = {
          valid: false,
          error: "Title must be at least 5 characters long"
        };
        return;
      }
      
      if (title.length > 100) {
        this.validation.title = {
          valid: false,
          error: "Title must be less than 100 characters"
        };
        return;
      }
      
      this.validation.title = { valid: true, error: "" };
    },
    
    validateContent() {
      const content = this.formData.content;
      
      if (!content) {
        this.validation.content = { valid: false, error: "" };
        return;
      }
      
      if (content.length < 50) {
        this.validation.content = {
          valid: false,
          error: "Content must be at least 50 characters long"
        };
        return;
      }
      
      if (content.length > 5000) {
        this.validation.content = {
          valid: false,
          error: "Content must be less than 5000 characters"
        };
        return;
      }
      
      this.validation.content = { valid: true, error: "" };
    },
    
    handleImageUpload(event) {
      const file = event.target.files[0];
      
      if (!file) {
        this.formData.image = null;
        this.imagePreview = null;
        return;
      }
      
      // Validate file type
      if (!file.type.startsWith('image/')) {
        alert('Please select a valid image file');
        this.$refs.fileInput.value = '';
        return;
      }
      
      // Validate file size (50MB)
      if (file.size > 50 * 1024 * 1024) {
        alert('Image size must be less than 50MB');
        this.$refs.fileInput.value = '';
        return;
      }
      
      this.formData.image = file;
      
      // Create preview
      const reader = new FileReader();
      reader.onload = (e) => {
        this.imagePreview = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    
    removeImage() {
      this.formData.image = null;
      this.imagePreview = null;
      this.$refs.fileInput.value = '';
    },
    
    async handleSubmit() {
      if (!this.isFormValid) {
        alert('Please fix all validation errors before submitting');
        return;
      }
      
      this.loading = true;
      
      try {
        // Simulate API call
        await new Promise(resolve => setTimeout(resolve, 2000));
        
        const postData = {
          ...this.formData,
          
          id: this.isEditing ? this.post.id : Date.now(),
          createdAt: this.isEditing ? this.post.createdAt : new Date().toISOString(),
          updatedAt: new Date().toISOString(),
          author: 'Current User', // In real app, get from auth context
          imageUrl: this.imagePreview,
          userId: this.$root.currentUser?.id || 0 // <-- gán userId từ current user

        };
        
        console.log('Post data:', postData);
        
        // Emit event to parent component
        this.$emit('post-saved', postData);
        
        // Show success message
        const action = this.isEditing ? 'updated' : 'published';
        alert(`Post ${action} successfully!`);
        
        // Reset form if not editing
        if (!this.isEditing) {
          this.resetForm();
        }
        
      } catch (error) {
        console.error('Error saving post:', error);
        alert('An error occurred while saving the post');
      } finally {
        this.loading = false;
      }
    },
    
    async saveDraft() {
      this.formData.isPublished = false;
      await this.handleSubmit();
    },
    
    handleCancel() {
      if (this.isEditing) {
        this.$emit('cancel-edit');
      } else {
        this.resetForm();
      }
    },
    
    resetForm() {
      this.formData = {
        title: "",
        content: "",
        tags: "",
        category: "",
        isPublished: true,
        image: null
      };
      this.validation = {
        title: { valid: false, error: "" },
        content: { valid: false, error: "" }
      };
      this.imagePreview = null;
      this.isEditing = false;
      this.$refs.fileInput.value = '';
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
  padding: 8px 16px;
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

textarea {
  resize: vertical;
  min-height: 200px;
}

.form-control-lg {
  font-size: 1.1rem;
}

.input-group .btn {
  border-radius: 0 8px 8px 0;
}

.input-group .form-control {
  border-radius: 8px 0 0 8px;
}
</style>
