<template>
    <div>{{ state.count }}</div>
    <div>{{ state.user.name }}</div>
    <button @click="increment">Increment</button>
</template>
<script>
import { reactive } from 'vue';
export default {
    name: 'ReactiveExp',
    setup() {
        const state = reactive({
            count: 0,
            user: {
                name: 'Phong',
                age: 20,

            }
        });
        const increment = () => {
            state.count++;
        };
        return {
            state,
            increment
        };
    }
}
</script>