<template>
  <div class="reviews">
    <div
      class="review"
      v-for="review in this.$store.state.reviews"
      v-bind:key="review.id"
    >
      <h2>Reviews</h2>
      <p class="review-date">{{review.date}}</p>
      <p>Hi{{review.review}}</p>
    </div>
  </div>
</template>

<script>
import ReviewServices from "../services/ReviewServices";
export default {
  name: "review-list",
  data() {
    return {
      reviewList: [],
      review: {
        id: 0,
        userId: 0,
        date: null,
        review: "",
        officeId: 0,
      },
    };
  },
  created() {
    ReviewServices.getReviews().then(response => {
        this.reviewList = response.data;
    });
  },
};
</script>

<style>
</style>