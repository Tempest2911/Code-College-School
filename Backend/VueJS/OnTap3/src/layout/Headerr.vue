<template>
    <header class="bg-primary text-white p-3">
        <div class="container d-flex justify-content-between align-items-center">
            <h2>Home</h2>
            <nav class="nav p-2">
                <router-link to="/khoa-hoc" class="nav-link text-white">Quản lý khoa học</router-link>
                <router-link to="/about" class="nav-link text-white">Giới thiệu</router-link>
            </nav>
        </div>
    </header>

    <main class="container mt-3">
        <router-view />
    </main>
</template>