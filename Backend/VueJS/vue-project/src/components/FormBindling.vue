<script setup>
import { ref } from 'vue';

const name = ref('Liên');
const message = ref('');
const agree = ref(false); // Biến để theo dõi checkbox
// Danh sách các mục để chọn
const interests = ['Âm nhạc', 'Thể thao', 'Du lịch', 'Công nghệ', 'Nấu ăn'];
const selectedInterests = ref([]); // Lưu các lựa chọn được chọn
const selectedCity = ref('');
const citiess = ['Hà Nội', 'Đà Nẵng', 'Hồ Chí Minh', 'Huế'];
// Mỗi thành phố có id riêng (value), nhưng hiển thị tên (label)
const cities = ref([
  { id: 1, name: 'Hà Nội' },
  { id: 2, name: 'Đà Nẵng' },
  { id: 3, name: 'Hồ Chí Minh' },
  { id: 4, name: 'Huế' }
]);

// Biến để lưu id được chọn
const selectedCityId = ref('');
</script>

<template>
  <div class="container mb-3">
    <h4>1. Input text</h4>
    <label for="name">Tên: </label>
    <input id="name" v-model="name" type="text" class="form-control" placeholder="Nhập tên của bạn" />
    <p class="mt-2">Tên của bạn là: <strong>{{ name }}</strong></p>
  </div>

  <div class="container mb-3">
    <h4>2. Multi-line text</h4>
    <span>Để lại góp ý của bạn tại đây:</span>
    <textarea v-model="message" placeholder="Nội dung góp ý..." rows="4" class="form-control mt-2"></textarea>
    <p v-if="message" class="mt-2 text-success">{{ message }}</p>
  </div>

  <div class="container mb-3">
    <h4>3. Đồng ý điều khoản</h4>
    <div class="form-check">
      <input type="checkbox" id="agree" v-model="agree" class="form-check-input" />
      <label class="form-check-label" for="agree">
        Tôi đồng ý với các điều khoản
      </label>
    </div>
    <p class="mt-2">
      Trạng thái đồng ý:
      <strong>{{ agree ? '✅ Đã đồng ý' : '❌ Chưa đồng ý' }}</strong>
    </p>
  </div>

  <div class="container mb-3">
    <h4>4. Chọn sở thích (multi checkbox)</h4>
    <div v-for="(interest, index) in interests" :key="index" class="form-check">
      <input type="checkbox" class="form-check-input" :id="'interest-' + index" :value="interest"
        v-model="selectedInterests" />
      <label class="form-check-label" :for="'interest-' + index">
        {{ interest }}
      </label>
    </div>
    <p class="mt-2">
      Bạn đã chọn:
      <strong v-if="selectedInterests.length > 0">
        {{ selectedInterests.join(', ') }}
      </strong>
      <span v-else>Chưa chọn gì</span>
    </p>
  </div>
  <div class="container mb-3">
    <h4>5. Chọn thành phố (Select/Option)</h4>

    <select v-model="selectedCity" class="form-select">
      <option disabled value="">-- Vui lòng chọn thành phố --</option>
      <option v-for="(city, index) in citiess" :key="index" :value="city">
        {{ city }}
      </option>
    </select>

    <p class="mt-2">
      Thành phố đã chọn:
      <strong>{{ selectedCity || 'Chưa chọn' }}</strong>
    </p>
  </div>

  <div class="container mb-3">
    <h4>6. Select binding theo giá trị (ID)</h4>

    <select v-model="selectedCityId" class="form-select">
      <option disabled value="">-- Chọn thành phố --</option>
      <option v-for="city in cities" :key="city.id" :value="city.id">
        {{ city.name }}
      </option>
    </select>

    <p class="mt-2">
      ID thành phố đã chọn: <strong>{{ selectedCityId || 'Chưa chọn' }}</strong>
    </p>

    <p v-if="selectedCityId">
      Bạn đã chọn:
      <strong>
        {{cities.find(c => c.id === Number(selectedCityId))?.name}}
      </strong>
    </p>
  </div>
</template>