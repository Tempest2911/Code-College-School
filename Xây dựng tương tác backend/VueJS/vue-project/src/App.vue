<script setup>
import { ref } from 'vue';
import TopMenu from './components/TopMenu.vue';
import BasicOneWayBinding from './components/BasicOneWayBinding.vue';
import ChildComponent from "./components/ChildComponent.vue";
import User from './components/User.vue';
import Fruit from './components/Fruit.vue';
import Student from './components/Student.vue';
import TwoWayBinding from './components/TwoWayBinding.vue';
import RefExp from './components/RefExp.vue';
import ReactiveExp from './components/ReactiveExp.vue';
import ArrReactiveExp from './components/ArrReactiveExp.vue';
import AccountExp from './components/AccountExp.vue';
import ArrClassBinding from './components/ArrClassBinding.vue';
const parentMessage = 'Thong diep tu thanh phan cha';
import ConditionalRendering from './components/ConditionalRendering.vue';
import ListRendering from './components/ListRendering.vue';
import EventHandling from './components/EventHandling.vue';
import FormBindling from './components/FormBindling.vue';
import CustomInput from './components/CustomInput.vue';
import { RouterView } from 'vue-router';

const handleEvent = (data) => {
  alert(data)
}

const inputValue = ref('');

</script>

<template>
  <TopMenu></TopMenu>
  <main>
    <!-- <EventHandling @increment="handleEvent"></EventHandling>
    <h1>===========================================</h1>
    <FormBindling></FormBindling>
    <h1>===========================================</h1>
    <CustomInput v-model="inputValue"></CustomInput>
    <p>Input value: {{ inputValue }}</p> -->

    <RouterView></RouterView>

  </main>
</template>


<style scoped>

</style>
