<template>
  <section class="page dashboard">
    <h2>📊 Dashboard</h2>
    <p>Welcome to your dashboard. Manage content, settings, and view analytics below.</p>
    <div class="dashboard-summary">
      <div class="dashboard-card">
        <h4>📝 Total Posts</h4>
        <p>12 blog posts published</p>
      </div>
      <div class="dashboard-card">
        <h4>👥 Followers</h4>
        <p>358 followers</p>
      </div>
      <div class="dashboard-card">
        <h4>💬 Comments</h4>
        <p>1,243 comments received</p>
      </div>
      <div class="dashboard-card">
        <h4>📈 Views</h4>
        <p>28,531 total views</p>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "DashboardPage"
};
</script>