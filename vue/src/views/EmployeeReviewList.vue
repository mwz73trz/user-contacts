<template>
  <div>
    <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
    <review-list />  
  </div>
</template>

<script>
import ReviewList from "../components/ReviewList.vue";

export default {
  components: {
    ReviewList,
  },
};
</script>

<style>
</style>