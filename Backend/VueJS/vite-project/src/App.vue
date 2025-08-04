<script setup>

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
// const StudentData = {
//     name: 'Nguyen Van ABC',
//     age: '20',
//     isActive: true,
//     class: '10A1',
//     address: '123 Main St',
//     phone: '0123456789',
//     email: 'abc@gmail.com',
//     gpa: 3.5,
//     major: 'Computer Science',
//     graduationYear: 2023,
//     extracurricularActivities: ['Football', 'Debate Club'],
//     scholarships: ['Merit Scholarship'],
//     internships: ['Tech Internship'],
//     projects: ['AI Project'],
//     languages: ['English', 'Vietnamese'],
//     skills: ['JavaScript', 'Vue.js'],
//     achievements: ['Top of the Class'],
//     volunteerWork: ['Community Service'],
//     references: ['Teacher A', 'Employer B'],
//     notes: 'Excellent student with a passion for technology.',
//     lastUpdated: '2023-10-01',
//     profilePicture: 'https://example.com/profile.jpg',
//     socialMedia: ['Facebook', 'Twitter'],
//     linkedIn: 'https://linkedin.com/in/nguyenvana',
//     gitHub: 'abc',
//     website: 'https://nguyenvana.com',
//     blog: 'https://blog.nguyenvana.com',
//     favoriteSubjects: ['Math', 'Science'],
//     dislikedSubjects: ['History'],
//     preferredLearningStyle: 'Visual',
//     careerGoals: 'Software Engineer',
//     personalInterests: ['Reading', 'Traveling'],
//     hobbies: ['Gaming', 'Cooking'],
//     favoriteBooks: ['The Great Gatsby', '1984'],
//     favoriteMovies: ['Inception', 'The Matrix'],
//     favoriteMusic: ['Pop', 'Rock'],
//     favoriteFoods: ['Pizza', 'Sushi'],
//     favoriteSports: ['Football', 'Basketball'],
//     favoriteTravelDestinations: ['Paris', 'Tokyo'],
//     favoriteQuotes: ['"To be or not to be."', '"I think, therefore I am."'],
//     favoriteColors: ['Blue', 'Green'],
//     favoriteAnimals: ['Dog', 'Cat'],
//     favoriteGames: ['Chess', 'Football'],
//     favoriteTVShows: ['Breaking Bad', 'Game of Thrones'],
//     favoritePodcasts: ['Tech Talk', 'History Podcast'],
//     favoriteApps: ['WhatsApp', 'Instagram'],
//     favoriteWebsites: ['Google', 'YouTube'],
//     favoriteOnlineCommunities: ['Stack Overflow', 'Reddit'],
//     favoriteOnlineCourses: ['Udemy', 'Coursera', 'edX'],
//     favoriteOnlineTools: ['Figma', 'Trello']
// };
</script>

<template>
  <main>
    <ListRendering></ListRendering>
  </main>
</template>


<style scoped>

</style>
