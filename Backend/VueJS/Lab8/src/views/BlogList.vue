<template>
  <section class="page blog-list">
    <h2 class="section-title">📰 Latest Blog Posts</h2>
    <div class="posts-grid">
      <transition-group name="fade" tag="div" class="grid-inner">
        <div v-for="post in posts" :key="post.id" class="post-card">
          <div class="post-header">
            <h3 class="post-title">
              <router-link :to="{ name: 'BlogPost', params: { id: post.id } }">
                {{ post.title }}
              </router-link>
            </h3>
            <div class="meta">
              <span class="author">By {{ post.author }}</span>
              <span class="date">• {{ post.date }}</span>
            </div>
          </div>
          <p class="excerpt">{{ post.excerpt }}</p>
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
          date: "2025-07-22",
          excerpt:
            "Tìm hiểu kỹ các tính năng của Vue Router: params, guards, nested routes và lazy loading.",
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
.section-title {
  font-size: 1.9rem;
  margin-bottom: 1rem;
  color: #1f2f44;
}
.posts-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
  gap: 1.25rem;
}
.grid-inner {
  display: contents;
}
.post-card {
  background: #ffffff;
  border-radius: 12px;
  padding: 1.25rem 1rem 1.25rem;
  position: relative;
  overflow: hidden;
  box-shadow: 0 25px 50px -10px rgba(0, 0, 0, 0.07);
  display: flex;
  flex-direction: column;
  transition: transform .2s ease, box-shadow .2s ease;
}
.post-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 35px 60px -5px rgba(0, 0, 0, 0.1);
}
.post-title {
  margin: 0;
  font-size: 1.2rem;
}
.post-title a {
  text-decoration: none;
  color: #2c3e50;
}
.meta {
  font-size: 0.65rem;
  color: #6b7a93;
  margin: 4px 0 8px;
}
.excerpt {
  flex: 1;
  margin: 0.5rem 0 1rem;
  color: #444;
  font-size: 0.95rem;
}
.card-footer {
  margin-top: auto;
  display: flex;
  justify-content: flex-end;
}
.read-more {
  font-weight: 600;
  text-decoration: none;
  color: #6366f1;
  position: relative;
}
.read-more::after {
  content: "";
  display: inline-block;
  width: 6px;
  height: 6px;
  background: #6366f1;
  border-radius: 50%;
  margin-left: 6px;
  transform: translateX(0);
  transition: transform .2s;
}
.read-more:hover::after {
  transform: translateX(3px);
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
