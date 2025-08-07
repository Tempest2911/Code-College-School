<template>
  <nav class="top-menu">
    <div class="logo">Tempest</div>
    <button class="menu-toggle" @click="toggleMenu">
      <span :class="{'open': menuOpen}"></span>
      <span :class="{'open': menuOpen}"></span>
      <span :class="{'open': menuOpen}"></span>
    </button>
    <ul :class="['nav-links', { 'open': menuOpen }]">
      <li v-for="item in menuItems" :key="item.text">
        <a :href="item.href">{{ item.text }}</a>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'TopMenu',
  data() {
    return {
      menuOpen: false,
      menuItems: [
        { text: 'Home', href: '#' },
        { text: 'About', href: '#' },
        { text: 'Features', href: '#' },
        { text: 'Pricing', href: '#' },
        { text: 'Blog', href: '#' },
        { text: 'Contact', href: '#' }
      ]
    }
  },
  methods: {
    toggleMenu() {
      this.menuOpen = !this.menuOpen;
    }
  }
}
</script>

<style scoped>
.top-menu {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: linear-gradient(90deg, #42b983 0%, #2c3e50 100%);
  padding: 12px 28px;
  color: white;
  position: relative;
}

.logo {
  font-size: 1.7em;
  font-weight: bold;
  letter-spacing: 1px;
}

.menu-toggle {
  display: none;
  flex-direction: column;
  justify-content: center;
  width: 32px;
  height: 32px;
  background: none;
  border: none;
  cursor: pointer;
  z-index: 2;
}
.menu-toggle span {
  display: block;
  height: 4px;
  width: 100%;
  background: white;
  margin: 5px 0;
  border-radius: 2px;
  transition: 0.4s;
}
.menu-toggle span.open:nth-child(1) {
  transform: translateY(9px) rotate(45deg);
}
.menu-toggle span.open:nth-child(2) {
  opacity: 0;
}
.menu-toggle span.open:nth-child(3) {
  transform: translateY(-9px) rotate(-45deg);
}

.nav-links {
  list-style: none;
  display: flex;
  gap: 28px;
  padding: 0;
  margin: 0;
  transition: right 0.3s ease;
}
.nav-links li a {
  text-decoration: none;
  color: white;
  font-weight: 500;
  font-size: 1.08em;
  padding: 6px 12px;
  border-radius: 4px;
  transition: background 0.2s, color 0.2s;
}
.nav-links li a:hover,
.nav-links li a:focus {
  background: rgba(255,255,255,0.15);
  color: #dfffe8;
}

@media (max-width: 800px) {
  .menu-toggle {
    display: flex;
  }
  .nav-links {
    flex-direction: column;
    background: #2c3e50;
    position: absolute;
    top: 100%;
    right: -220px;
    width: 200px;
    gap: 0;
    box-shadow: 0 8px 24px rgba(0,0,0,0.12);
    border-radius: 0 0 8px 8px;
    overflow: hidden;
    z-index: 1;
  }
  .nav-links.open {
    right: 0;
  }
  .nav-links li a {
    display: block;
    padding: 14px 20px;
    border-radius: 0;
    border-bottom: 1px solid rgba(255,255,255,0.08);
  }
}
</style>
