<template>
  <div class="reviews">
      <h2>Office Reviews</h2>
    <div
      class="review"
      v-for="review in filteredReviews"
      v-bind:key="review.officeId"
    >
      <p class="review-date">{{review.reviewDate}}</p>
      <h4>{{review.review}}</h4>
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
        userId: "",
        reviewDate: "",
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
  computed: {
      filteredReviews(){
        if (this.$store.state.user.authorities[0].name === 'ROLE_ADMIN') {
          return this.reviewList.filter((item) => {
              return item.userId == this.$store.state.user.id;
          })
        }else {
          return this.reviewList.filter((item) => {
              return item.officeId == this.$route.params.officeId;
          })
        }
      }
  }
};
</script>

<style>
</style>