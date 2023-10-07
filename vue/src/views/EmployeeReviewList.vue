<template>
  <div>
    <add-review></add-review>
    <p>{{ this.$route.params.officeId }}</p> 
    <review-list />  
  </div>
</template>

<script>
import ReviewList from "../components/ReviewList.vue";
import AddReview from "../components/AddReview.vue";
export default {
  components: {
    ReviewList,
    AddReview,
  },
};
</script>

<style>
</style>