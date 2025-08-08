<template>
    <nav class="menu">
        <ul>
            <li><router-link class="nav-link" :to="{ name: 'home' }">Home</router-link></li>
            <li><router-link class="nav-link" :to="{ name: 'about' }">About</router-link></li>
            <li><router-link class="nav-link" :to="{ name: 'contact' }">Contact</router-link></li>
        </ul>
    </nav>
</template>
<script>
</script>
<style scoped>
.menu {
    background-color: transparent;
    padding: 0.5rem;
}
.menu ul {
    list-style: none;
    display: flex;
    gap: 0.75rem;
    margin: 0;
    padding: 0;
}
.menu li a{
    color: #0067B8;
    padding: 0.5rem 1rem;
    display: block;
    font-size: 1rem;
    font-weight: 300;
}
.menu li a:hover {
    background-color: transparent;
    text-decoration: underline;
}
</style>