<template>
  <div>
    <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
    <employee-review-list />  
  </div>
</template>

<script>
import EmployeeReviewList from "../components/EmployeeReviewList.vue";

export default {
  components: {
    EmployeeReviewList,
  },
};
</script>

<style>
</style>