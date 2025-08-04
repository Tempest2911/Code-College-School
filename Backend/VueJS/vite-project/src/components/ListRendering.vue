<script setup>
import { ref } from 'vue';
const students = ref([
       'Queyt',
       'Nguyen Van A',
       'Nguyen Van B', 
        'Nguyen Van C'
]);
const items = ref([
  { id: 1, title: 'Tiêu đề 1', desctiption: 'Mô tả 1....' },
  { id: 2, title: 'Tiêu đề 2', desctiption: 'Mô tả 2....' }
]);

const fruits = [
  { id: 1, name: 'Táo', visible: true },
  { id: 2, name: 'Chuối', visible: false },
  { id: 3, name: 'Cam', visible: true },
  { id: 4, name: 'Nho', visible: false }
];
const newcustomers = ref([
  { id: 1, name: 'Nguyen Van A', age: 20 },
  { id: 2, name: 'Nguyen Van B', age: 22 },
  { id: 3, name: 'Nguyen Van C', age: 21 }
]);
const newCustomer = ref({ name: '', age: null});
const addCustomer = () => {
  if (!newCustomer.value.name || !newCustomer.value.age) {
    alert('Please fill in all fields');
    return;
  }
    if (newCustomer.value.name && newCustomer.value.age > 0) {
    newcustomers.value.push({
      id: newcustomers.value.length + 1,
      name: newCustomer.value.name,
      age: newCustomer.value.age
    })
}};
</script>
<template>
    <div class="container mb-3">
        <h4>v-for voi mang</h4>
        <ul>
            <li v-for="st in students">{{ st }}</li>
        </ul>
    </div>

    <div class="container mb-3">
  <h4>3. v-for với template</h4>
  <template v-for="item in items" :key="item.id">
    <h5>{{ item.title }}</h5>
    <p>{{ item.desctiption }}</p>
  </template>
</div>

<div class="container mb-3">
  <h4>4.Ket hop v-for voi v-if</h4>
  <ul>
    <template v-for="fruit in fruits" :key="fruit.id">
      <li v-if="fruit.visible">{{ fruit.name }}</li>
    </template>
  </ul>
</div>

<div class="container mb-3">
  <h4>5. v-for voi doi tuong/ Quan ly khach hang</h4>
  <form action="" class="col-md-6" @submit.prevent="addCustomer">
    <div class="mb-3">
      <label for="name" class="form-label">Name</label>
      <input type="text" id="name" v-model="newCustomer.name" class="form-control">
    </div>
    <div class="mb-3">
      <label for="age" class="form-label">Age</label>
      <input type="number" id="age" v-model.number="newCustomer.age" class="form-control">
    </div>
    <button type="submit" class="btn btn-primary">Add Customer</button>
  </form>

  <div class="col-md-6">
    <h5>Danh sách khách hàng</h5>
    <ul class="list-group">
        <li class="list-group-item" v-for="customer in newcustomers" :key="customer.id">
            {{ customer.name }} - {{ customer.age }} tuổi
        </li>
    </ul>

  </div>

</div>
</template>