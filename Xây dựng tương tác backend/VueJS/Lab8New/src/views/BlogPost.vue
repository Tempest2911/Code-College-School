<template>
  <section class="page blog-post">
    <div class="post-wrapper">
      <div v-if="post" class="post-card-full">
        <div class="header">
          <h1 class="title">{{ post.title }}</h1>
          <div class="meta">
            <span>By {{ post.author }}</span>
            <span>• {{ post.date }}</span>
          </div>
        </div>
        <div class="content" v-html="post.contentHtml"></div>
        <div class="back">
          <router-link :to="{ name: 'BlogList' }">← Back to all posts</router-link>
        </div>
      </div>
      <div v-else class="not-found">
        <h2>Bài viết không tồn tại</h2>
        <p>Không tìm thấy bài viết với ID: {{ $route.params.id }}</p>
        <router-link :to="{ name: 'BlogList' }">Quay lại danh sách</router-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "BlogPostPage",
  data() {
    return {
      posts: [
        {
          id: 1,
          title: "🌟 How to Learn Vue.js",
          author: "Alice Nguyen",
          date: "2025-07-20",
          contentHtml: `
            <p>Học Vue.js bắt đầu bằng việc nắm vững các khái niệm như <strong>reactive data</strong>, <code>computed</code>, và <code>methods</code>. Sau đó, làm quen với components và Vue Router để xây dựng SPA thực tế.</p>
            <h3>Step 1: Core Concepts</h3>
            <p>Reactive state, template syntax, and component composition are foundations.</p>
          `,
        },
        {
          id: 2,
          title: "💡 Vue Router Deep Dive",
          author: "Minh Tran",
          date: "2025-07-22",
          contentHtml: `
            <p>Vue Router cho phép tạo ứng dụng nhiều trang. Bạn nên hiểu cách dùng <strong>route params</strong>, <strong>route guards</strong>, <em>nested routes</em> và lazy loading.</p>
          `,
        },
        {
          id: 3,
          title: "🧩 Composable Architecture in Vue 3",
          author: "Lan Pham",
          date: "2025-07-25",
          contentHtml: `
            <p>Composition API giúp tái sử dụng logic dễ dàng qua các composables. Sử dụng <code>setup()</code>, <code>reactive()</code> và <code>ref()</code> một cách hiệu quả là chìa khóa.</p>
          `,
        },
        {
          id: 4,
          title: "⚡ Performance Optimization Tips",
          author: "Huy Le",
          date: "2025-07-27",
          contentHtml: `
            <p>Sử dụng <strong>lazy loading</strong>, <code>v-once</code>, <code>keep-alive</code> và tránh re-render không cần thiết là một số mẹo tối ưu Vue apps.</p>
          `,
        },
        {
          id: 5,
          title: "🎨 Designing UI with TailwindCSS",
          author: "Trang Vo",
          date: "2025-07-28",
          contentHtml: `
            <p>TailwindCSS giúp tạo UI nhanh với utility classes. Kết hợp với Vue bạn có thể xây dựng component đẹp mà không cần viết nhiều CSS.</p>
          `,
        },
        {
          id: 6,
          title: "📦 State Management with Pinia",
          author: "Quang Pham",
          date: "2025-07-30",
          contentHtml: `
            <p>Pinia là giải pháp quản lý state chính thức cho Vue. Nó đơn giản hơn Vuex và tích hợp tốt với Composition API.</p>
          `,
        },
        {
          id: 7,
          title: "🔐 Auth Strategies in SPAs",
          author: "Truc Nguyen",
          date: "2025-08-01",
          contentHtml: `
            <p>SPA cần xác thực token-based như JWT. Sử dụng route guards và localStorage để bảo vệ các routes nhạy cảm.</p>
          `,
        },
        {
          id: 8,
          title: "📁 File Uploads in Vue",
          author: "Khanh Le",
          date: "2025-08-03",
          contentHtml: `
            <p>Tạo input file và gửi <code>FormData</code> qua axios để upload file. Cần xử lý lỗi và feedback cho người dùng.</p>
          `,
        },
      ],
    };
  },
  computed: {
    post() {
      const id = parseInt(this.$route.params.id, 10);
      return this.posts.find((p) => p.id === id) || null;
    },
  },
};
</script>

<style scoped>
.post-wrapper {
  max-width: 900px;
  margin: 0 auto;
}
.post-card-full {
  background: white;
  border-radius: 14px;
  padding: 2rem 2rem 2.5rem;
  box-shadow: 0 35px 70px -15px rgba(0, 0, 0, 0.08);
}
.header {
  margin-bottom: 1rem;
}
.title {
  margin: 0;
  font-size: 2rem;
  color: #1f2f44;
}
.meta {
  font-size: 0.85rem;
  color: #6b7a93;
  margin-top: 6px;
}
.content {
  line-height: 1.65;
  margin: 1.25rem 0;
  font-size: 1rem;
}
.back {
  margin-top: 1.75rem;
}
.back a {
  text-decoration: none;
  color: #6366f1;
  font-weight: 600;
}
.not-found {
  background: #fff3f0;
  padding: 1.5rem;
  border-radius: 10px;
  border: 1px solid #f5c6cb;
  text-align: center;
}
</style>
