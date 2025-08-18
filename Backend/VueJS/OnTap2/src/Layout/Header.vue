<template>
    <header class="bg-primary text-white p-3">
        <div class="container d-flex justify-content-between align-items-center">
            <h2>Home</h2>
            <nav class="nav p-2">
                <router-link to="/phone" class="nav-link text-white">Điện thoại</router-link>
                <router-link to="/page" class="nav-link text-white">Page</router-link>
            </nav>
        </div>
    </header>

    <main class="container mt-3">
        <router-view />
    </main>
</template>
