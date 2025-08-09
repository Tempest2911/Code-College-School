import axios from 'axios';
import api from '../utils/api.js';

const API_URL = 'http://localhost:3000';

export default {
  // Posts
  getPosts() {
    return axios.get(`${API_URL}/posts`);
  },
  getPost(id) {
    return axios.get(`${API_URL}/posts/${id}`);
  },
  createPost(data) {
    // Chỉ gửi các trường chuẩn
    const post = {
      title: data.title,
      content: data.content,
      tags: data.tags,
      authorId: data.authorId,
      category: data.category,
      isPublished: data.isPublished,
      createdAt: data.createdAt,
      updatedAt: data.updatedAt,
      imageUrl: data.imageUrl || "",
    };
    return axios.post(`${API_URL}/posts`, post);
  },
  updatePost(id, data) {
    // Đảm bảo id là chuỗi
    const post = {
      ...data,
      id: id, // giữ nguyên kiểu chuỗi
    };
    return axios.put(`${API_URL}/posts/${id}`, post);
  },
  deletePost(id) {
    return axios.delete(`${API_URL}/posts/${id}`);
  },

  // Users
  getUsers() {
    return axios.get(`${API_URL}/users`);
  },
  getUser(id) {
    return axios.get(`${API_URL}/users/${id}`);
  },
  createUser(data) {
    return axios.post(`${API_URL}/users`, data);
  },
  updateUser(id, data) {
    return axios.patch(`${API_URL}/users/${id}`, data);
  },

  // Comments
  getCommentsByPost(postId) {
    return axios.get(`${API_URL}/comments?postId=${postId}`);
  },
  createComment(data) {
    return axios.post(`${API_URL}/comments`, data);
  },
  updateComment(id, data) {
    return axios.put(`${API_URL}/comments/${id}`, data);
  },
  deleteComment(id) {
    return axios.delete(`${API_URL}/comments/${id}`);
  },

  data() {
    return {
      posts: [],
      comments: [],
      // ...các biến khác...
    };
  },
  async mounted() {
    await this.loadPosts();
    await this.loadComments();
  },
  watch: {
    reload() {
      this.loadComments();
    }
  },
  methods: {
    async loadPosts() {
      const res = await api.getPosts();
      // Nếu muốn lọc theo user:
      this.posts = res.data
        .filter(post => post.authorId === this.currentUser.id);
    },
    async handlePostSaved(postData) {
      if (this.editingPost) {
        await api.updatePost(postData.id, postData);
      } else {
        await api.createPost(postData);
      }
      await this.loadPosts();
      this.closePostModal();
    },
    async deletePost(post) {
      await api.deletePost(post.id);
      await this.loadPosts();
    },
    async loadComments() {
      const res = await api.getCommentsByPost(this.postId);
      const allComments = res.data;
      // Comment cha
      this.comments = allComments.filter(c => !c.parentId).map(parent => ({
        ...parent,
        replies: allComments.filter(r => r.parentId === parent.id)
      }));
    },
    async submitComment() {
      // ...validate...
      await api.createComment({
        postId: this.postId,
        userId: this.currentUser.id,
        content: this.newComment.content,
        parentId: this.parentId, // id của comment cha
        createdAt: new Date().toISOString()
      });
      this.newComment.content = '';
      await this.loadComments();
    },
    async saveEdit(comment) {
      await api.updateComment(comment.id, { ...comment, content: comment.editContent });
      await this.loadComments();
    },
    async deleteComment(comment) {
      await api.deleteComment(comment.id);
      await this.loadComments();
    },
    async register() {
      // ...validate...
      await api.createUser({
        username: this.username,
        password: this.password,
        // ...các trường khác...
      });
      // ...chuyển hướng hoặc thông báo...
    },
    formatDate(dateString) {
      if (!dateString) return 'Unknown';
      const date = new Date(dateString);
      if (isNaN(date.getTime())) return dateString; // Nếu không phải ISO, trả về nguyên chuỗi
      return date.toLocaleDateString('en-US', { year: 'numeric', month: 'long', day: 'numeric' });
    }
  }
};