<template>
  <form v-on:submit.prevent="addNewReview">
    <div class="form-element">
      <label for="review">Review:</label>
      <textarea id="review" v-model="newReview.review"></textarea>

      <button >Submit Review</button>
    </div>
  </form>
</template>

<script>
import ReviewServices from "../services/ReviewServices";

export default {
  name: "add-review",
  data() {
    return {
      newReview: {
        id: "",
        userId: "",
        date: null,
        review: "",
        officeId: "",
      },
    };
  },
  methods: {
    addNewReview() {
      ReviewServices.createReview(this.newReview).then( response => {
          if(response.status === 201){
              this.$router.push('/')
            //   this.$store.commit('ADD_REVIEW', response.data)
          }
      })
    },
  },
};
</script>

<style>
</style>