<template>
    <div class="container">
        <h3>{{ studet.name }}</h3>
        <p>Age: {{ studet.age }}</p>
        <p>Status: {{ studet.isActive ? 'Active' : 'nonActive' }}</p>
        <p>Class: {{ studet.class }}</p>
        <p>Address: {{ studet.address }}</p>
        <p>Phone: {{ studet.phone }}</p>
        <p>Email: {{ studet.email }}</p>
        <p>GPA: {{ studet.gpa }}</p>
        <p>Major: {{ studet.major }}</p>
        <p>Graduation Year: {{ studet.graduationYear }}</p>
        <p>Extracurricular Activities: {{ studet.extracurricularActivities.join(', ') }}</p>
        <p>Scholarships: {{ studet.scholarships.join(', ') }}</p>
        <p>Internships: {{ studet.internships.join(', ') }}</p>
        <p>Projects: {{ studet.projects.join(', ') }}</p>
        <p>Languages: {{ studet.languages.join(', ') }}</p>
        <p>Skills: {{ studet.skills.join(', ') }}</p>
        <p>Achievements: {{ studet.achievements.join(', ') }}</p>
        <p>Volunteer Work: {{ studet.volunteerWork.join(', ') }}</p>
        <p>References: {{ studet.references.join(', ') }}</p>
        <p>Notes: {{ studet.notes }}</p>
        <p>Last Updated: {{ studet.lastUpdated }}</p>
        <p>Profile Picture: <img :src="studet.profilePicture" alt="Profile Picture" /></p>
        <p>Social Media: {{ studet.socialMedia.join(', ') }}</p>
        <p>LinkedIn: {{ studet.linkedIn }}</p>
        <p>GitHub: {{ studet.gitHub }}</p>
        <p>Website: {{ studet.website }}</p>
        <p>Blog: {{ studet.blog }}</p>
        <p>Favorite Subjects: {{ studet.favoriteSubjects.join(', ') }}</p>
        <p>Disliked Subjects: {{ studet.dislikedSubjects.join(', ') }}</p>
        <p>Preferred Learning Style: {{ studet.preferredLearningStyle }}</p>
        <p>Career Goals: {{ studet.careerGoals }}</p>
        <p>Personal Interests: {{ studet.personalInterests.join(', ') }}</p>
        <p>Hobbies: {{ studet.hobbies.join(', ') }}</p>
        <p>Favorite Books: {{ studet.favoriteBooks.join(', ') }}</p>
        <p>Favorite Movies: {{ studet.favoriteMovies.join(', ') }}</p>
        <p>Favorite Music: {{ studet.favoriteMusic.join(', ') }}</p>
        <p>Favorite Foods: {{ studet.favoriteFoods.join(', ') }}</p>
        <p>Favorite Sports: {{ studet.favoriteSports.join(', ') }}</p>
        <p>Favorite Travel Destinations: {{ studet.favoriteTravelDestinations.join(', ') }}</p>
        <p>Favorite Quotes: {{ studet.favoriteQuotes.join(', ') }}</p>
        <p>Favorite Colors: {{ studet.favoriteColors.join(', ') }}</p>
        <p>Favorite Animals: {{ studet.favoriteAnimals.join(', ') }}</p>
        <p>Favorite Games: {{ studet.favoriteGames.join(', ') }}</p>
        <p>Favorite TV Shows: {{ studet.favoriteTVShows.join(', ') }}</p>
        <p>Favorite Podcasts: {{ studet.favoritePodcasts.join(', ') }}</p>
        <p>Favorite Apps: {{ studet.favoriteApps.join(', ') }}</p>
        <p>Favorite Websites: {{ studet.favoriteWebsites.join(', ') }}</p>
        <p>Favorite Online Communities: {{ studet.favoriteOnlineCommunities.join(', ') }}</p>
        <p>Favorite Online Courses: {{ studet.favoriteOnlineCourses.join(', ') }}</p>
        <p>Favorite Online Tools: {{ studet.favoriteOnlineTools.join(', ') }}</p>
    </div>
</template>

<script setup>
defineProps({
    studet: {
        type: Object,
        required: true
    }
})
</script>