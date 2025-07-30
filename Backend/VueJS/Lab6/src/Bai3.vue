<template>
    <h1 class="text-center mt-5">Bài 3</h1>
    <div class="container ">
        <h1 class="text-center">Kiến thức sức khỏe cộng đồng</h1>
        <div class="row">
            <div class="col-sm-4 mb-4" v-for="(item, index) in items" :key="index">
                <div class="card">
                    <img :src="item.image" class="card-img-top custom-img img-fluid" alt="Hình ảnh" />
                    <div class="card-body">
                        <h3 class="card-title">{{ item.title }}</h3>
                        <p class="card-text">{{ item.content }}</p>
                        <button class="btn btn-info">Xem chi tiết</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<!-- <style scoped>
.custom-img {
  height: 200px;
  object-fit: cover;
  width: 100%;
}
</style> -->

<script setup>
import img1 from '@/assets/images/img1.jpg';
import img2 from '@/assets/images/img2.jpg';
import img3 from '@/assets/images/img3.jpg';
const items = [
    {
        title: '8 loại rau củ quả giàu canxi',
        content: 'Canxi là khoáng chất thiết yếu đối với cơ thể người. Có nhiều cách để bổ sung canxi, trong đó bổ sung qua đường ăn uống là cách tốt nhất. Có 8 loại rau củ giàu canxi...',
        image: img1
    },
    {
        title: 'Các loại gia vị tốt cho sức khỏe',
        content: 'Một số gia vị cung cấp nhiều polyphenol chống oxy hóa cao hơn các gia vị quen thuộc khác. Với một lượng nhỏ các món ăn đã thêm hương thơm và vị hấp dẫn...',
        image: img2
    },
    {
        title: '9 loại đậu bổ dưỡng nên dùng nhiều',
        content: 'Đậu lăng, đậu phộng, đậu Hà Lan giàu chất sơ, protein cùng nhiều vitamin và khoáng chất giúp giảm lượng đường trong máu, tốt cho tim...',
        image: img3
    }   
];
</script>