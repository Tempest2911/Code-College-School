// Authentication and User Management Utility
class AuthManager {
  constructor() {
    this.currentUser = null;
    this.users = this.loadUsers();
    this.posts = this.loadPosts();
    this.comments = this.loadComments();
  }

  // User Management
  loadUsers() {
    const users = localStorage.getItem('blog_users');
    return users ? JSON.parse(users) : this.getDefaultUsers();
  }

  saveUsers() {
    localStorage.setItem('blog_users', JSON.stringify(this.users));
  }


  // Authentication Methods
  login(username, password) {
    const user = this.users.find(u =>
      (u.username === username || u.email === username) && u.password === password
    );

    if (user) {
      this.currentUser = { ...user };
      user.lastLogin = new Date().toISOString();
      this.saveUsers();
      this.saveCurrentUser();
      return { success: true, user: this.currentUser };
    }

    return { success: false, error: 'Invalid username or password' };
  }

  register(userData) {
    // Check if username or email already exists
    const existingUser = this.users.find(u =>
      u.username === userData.username || u.email === userData.email
    );

    if (existingUser) {
      return {
        success: false,
        error: existingUser.username === userData.username
          ? 'Username already exists'
          : 'Email already exists'
      };
    }

    const newUser = {
      id: Date.now(),
      username: userData.username,
      email: userData.email,
      password: userData.password,
      name: userData.username,
      firstName: userData.username,
      lastName: '',
      avatar: null,
      bio: '',
      location: '',
      website: '',
      createdAt: new Date().toISOString(),
      lastLogin: null
    };

    this.users.push(newUser);
    this.saveUsers();

    // Auto login after registration
    this.currentUser = { ...newUser };
    this.saveCurrentUser();

    return { success: true, user: this.currentUser };
  }

  logout() {
    this.currentUser = null;
    localStorage.removeItem('blog_current_user');
  }

  getCurrentUser() {
    if (!this.currentUser) {
      const savedUser = localStorage.getItem('blog_current_user');
      if (savedUser) {
        this.currentUser = JSON.parse(savedUser);
      }
    }
    return this.currentUser;
  }

  saveCurrentUser() {
    if (this.currentUser) {
      localStorage.setItem('blog_current_user', JSON.stringify(this.currentUser));
    }
  }

  updateUserProfile(userId, updates) {
    const userIndex = this.users.findIndex(u => u.id === userId);
    if (userIndex !== -1) {
      this.users[userIndex] = { ...this.users[userIndex], ...updates };
      this.saveUsers();

      // Update current user if it's the same user
      if (this.currentUser && this.currentUser.id === userId) {
        this.currentUser = { ...this.currentUser, ...updates };
        this.saveCurrentUser();
      }

      return { success: true, user: this.users[userIndex] };
    }
    return { success: false, error: 'User not found' };
  }

  // Posts Management
  loadPosts() {
    const posts = localStorage.getItem('blog_posts');
    return posts ? JSON.parse(posts) : this.getDefaultPosts();
  }

  savePosts() {
    localStorage.setItem('blog_posts', JSON.stringify(this.posts));
  }

  getPostsByUser(userId) {
    return this.posts.filter(post => post.authorId === userId);
  }

  getAllPosts() {
    return this.posts.filter(post => post.isPublished);
  }

  getAllPublishedPosts() {
    this.loadPosts();
    return this.posts.filter(post => post.isPublished);
  }

  createPost(postData) {
    const newPost = {
      id: Date.now(),
      ...postData,
      authorId: this.currentUser.id,
      author: this.currentUser.name,
      createdAt: new Date().toISOString(),
      updatedAt: new Date().toISOString()
    };

    this.posts.unshift(newPost);
    this.savePosts();
    return newPost;
  }

  updatePost(postId, updates) {
    const postIndex = this.posts.findIndex(p => p.id === postId);
    if (postIndex !== -1) {
      this.posts[postIndex] = {
        ...this.posts[postIndex],
        ...updates,
        updatedAt: new Date().toISOString()
      };
      this.savePosts();
      return this.posts[postIndex];
    }
    return null;
  }

  deletePost(postId) {
    const postIndex = this.posts.findIndex(p => p.id === postId);
    if (postIndex !== -1) {
      const deletedPost = this.posts.splice(postIndex, 1)[0];
      this.savePosts();
      return deletedPost;
    }
    return null;
  }

  // Comments Management
  loadComments() {
    const comments = localStorage.getItem('blog_comments');
    return comments ? JSON.parse(comments) : this.getDefaultComments();
  }

  saveComments() {
    localStorage.setItem('blog_comments', JSON.stringify(this.comments));
  }

  getCommentsByPost(postId) {
    return this.comments.filter(comment => comment.postId === postId);
  }

  createComment(postId, content) {
    const newComment = {
      id: Date.now(),
      postId,
      content,
      userName: this.currentUser.name,
      userId: this.currentUser.id,
      userAvatar: this.currentUser.avatar,
      createdAt: new Date().toISOString(),
      replies: []
    };

    this.comments.unshift(newComment);
    this.saveComments();
    return newComment;
  }

  createReply(commentId, content) {
    const comment = this.comments.find(c => c.id === commentId);
    if (comment) {
      const reply = {
        id: Date.now(),
        content,
        userName: this.currentUser.name,
        userId: this.currentUser.id,
        userAvatar: this.currentUser.avatar,
        createdAt: new Date().toISOString()
      };

      comment.replies.push(reply);
      this.saveComments();
      return reply;
    }
    return null;
  }

  updateComment(commentId, content) {
    // Cập nhật comment cấp 1
    const comment = this.comments.find(c => c.id === commentId);
    if (comment) {
      comment.content = content;
      this.saveComments();
      return comment;
    }

    // Cập nhật comment cấp 2 (reply)
    for (const c of this.comments) {
      if (Array.isArray(c.replies)) {
        const reply = c.replies.find(r => r.id === commentId);
        if (reply) {
          reply.content = content;
          this.saveComments();
          return reply;
        }
      }
    }

    return null; // Không tìm thấy
  }


  deleteComment(commentId) {
    // Xoá ở cấp 1
    const idx = this.comments.findIndex(c => c.id === commentId);
    if (idx !== -1) {
      const deleted = this.comments.splice(idx, 1)[0];
      this.saveComments();
      return deleted;
    }

    // Xoá ở cấp 2 (reply trong từng comment)
    for (const c of this.comments) {
      if (Array.isArray(c.replies)) {
        const rIdx = c.replies.findIndex(r => r.id === commentId);
        if (rIdx !== -1) {
          const deleted = c.replies.splice(rIdx, 1)[0];
          this.saveComments();
          return deleted;
        }
      }
    }

    return null;
  }


  // User Statistics
  getUserStats(userId) {
    const userPosts = this.getPostsByUser(userId);
    const userComments = this.comments.filter(c => c.userId === userId);

    return {
      totalPosts: userPosts.length,
      publishedPosts: userPosts.filter(p => p.isPublished).length,
      draftPosts: userPosts.filter(p => !p.isPublished).length,
      comments: userComments.length,
      likes: 0 // Placeholder for future like system
    };
  }

  // Data Export/Import (for backup)
  exportUserData(userId) {
    const user = this.users.find(u => u.id === userId);
    const userPosts = this.getPostsByUser(userId);
    const userComments = this.comments.filter(c => c.userId === userId);

    return {
      user,
      posts: userPosts,
      comments: userComments,
      exportDate: new Date().toISOString()
    };
  }

  // Clear all data (for testing)
  clearAllData() {
    localStorage.removeItem('blog_users');
    localStorage.removeItem('blog_posts');
    localStorage.removeItem('blog_comments');
    localStorage.removeItem('blog_current_user');

    this.users = this.getDefaultUsers();
    this.posts = this.getDefaultPosts();
    this.comments = this.getDefaultComments();
    this.currentUser = null;

    this.saveUsers();
    this.savePosts();
    this.saveComments();
  }

  getUserById(userId) {
    const users = this.getAllUsers();
    return users.find(u => u.id == userId) || null;
  }

  deleteUserById(userId) {
    const users = this.getAllUsers();
    const newUsers = users.filter(u => u.id !== userId);
    this.saveAllUsers(newUsers);

    // Nếu user đang đăng nhập bị xóa, đăng xuất luôn
    const currentUser = this.getCurrentUser();
    if (currentUser && currentUser.id === userId) {
      this.logout();
    }
  }

  getAllUsers() {
    return this.users;
  }
}

// Create singleton instance
const authManager = new AuthManager();

export default authManager; 