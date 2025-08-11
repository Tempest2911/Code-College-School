<template>
  <section class="blog-list">
    <h2 class="blog-title">📰 Latest Blog Posts</h2>
    <div class="blog-grid">
      <transition-group name="fade" tag="div" class="grid-inner">
        <div v-for="post in posts" :key="post.id" class="blog-card">
          <div class="card-header">
            <h3 class="card-title">
              <router-link :to="{ name: 'BlogPost', params: { id: post.id } }" class="card-link">
                {{ post.title }}
              </router-link>
            </h3>
            <div class="card-meta">
              <span class="author">By {{ post.author }}</span>
              <span class="date">• {{ post.date }}</span>
            </div>
          </div>
          <p class="card-excerpt">{{ post.excerpt }}</p>
          <div class="card-footer">
            <router-link
              :to="{ name: 'BlogPost', params: { id: post.id } }"
              class="read-more"
            >
              Read more →
            </router-link>
          </div>
        </div>
      </transition-group>
    </div>
  </section>
</template>

<script>
export default {
  name: "BlogListPage",
  data() {
    return {
      posts: [
        {
          id: 1,
          title: "🌟 How to Learn Vue.js",
          author: "Alice Nguyen",
          date: "2025-07-20",
          excerpt:
            "Bắt đầu với Vue.js: nắm reactive, component và router để xây dựng SPA linh hoạt.",
        },
        {
          id: 2,
          title: "💡 Vue Router Deep Dive",
          author: "Minh Tran",
          date: "2025-07-18",
          excerpt:
            "Tìm hiểu sâu về Vue Router: nested routes, guards và lazy loading.",
        },
        {
          id: 3,
          title: "🧩 Composable Architecture in Vue 3",
          author: "Lan Pham",
          date: "2025-07-25",
          excerpt:
            "Sử dụng Composition API để tái sử dụng logic, làm sạch code và tăng tính modular.",
        },
        {
          id: 4,
          title: "⚡ Performance Optimization Tips",
          author: "Huy Le",
          date: "2025-07-27",
          excerpt:
            "Tối ưu Vue app bằng lazy loading, keep-alive, v-once và giảm re-render không cần thiết.",
        },
        {
          id: 5,
          title: "🎨 Designing UI with TailwindCSS",
          author: "Trang Vo",
          date: "2025-07-28",
          excerpt:
            "Xây dựng giao diện đẹp nhanh với utility classes của Tailwind kết hợp Vue component.",
        },
        {
          id: 6,
          title: "📦 State Management with Pinia",
          author: "Quang Pham",
          date: "2025-07-30",
          excerpt:
            "Pinia thay thế Vuex: đơn giản hơn, reactive tốt và tích hợp mượt với Composition API.",
        },
        {
          id: 7,
          title: "🔐 Auth Strategies in SPAs",
          author: "Truc Nguyen",
          date: "2025-08-01",
          excerpt:
            "Giải pháp xác thực cho SPA: JWT, route guards, và bảo vệ dữ liệu người dùng.",
        },
        {
          id: 8,
          title: "📁 File Uploads in Vue",
          author: "Khanh Le",
          date: "2025-08-03",
          excerpt:
            "Upload file trong Vue: dùng FormData, axios, và hiển thị feedback khi xử lý lỗi.",
        },
      ],
    };
  },
};
</script>

<style scoped>
.blog-list {
  padding: 2rem;
  background: #f9f9f9;
}
.blog-title {
  font-size: 2rem;
  color: #2c3e50;
  margin-bottom: 1.5rem;
}
.blog-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 1.5rem;
}
.blog-card {
  background: white;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  padding: 1.5rem;
  transition: transform 0.2s;
  margin-bottom: 10px;
}
.blog-card:hover {
  transform: translateY(-5px);
}
.card-header {
  margin-bottom: 1rem;
}
.card-title {
  font-size: 1.25rem;
  color: #3498db;
  margin: 0;
}
.card-link {
  text-decoration: none;
  color: inherit;
}
.card-meta {
  font-size: 0.9rem;
  color: #6b7a93;
}
.card-excerpt {
  font-size: 1rem;
  color: #2c3e50;
  margin: 1rem 0;
}
.card-footer {
  text-align: right;
}
.read-more {
  color: #3498db;
  text-decoration: none;
  font-weight: bold;
}
.read-more:hover {
  text-decoration: underline;
}
.fade-enter-active,
.fade-leave-active {
  transition: all 0.35s ease;
}
.fade-enter-from {
  opacity: 0;
  transform: translateY(12px);
}
.fade-enter-to {
  opacity: 1;
  transform: translateY(0);
}
</style>
