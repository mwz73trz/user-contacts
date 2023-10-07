<template>
  <form v-on:submit="addNewReview">
    <div class="form-element">
      <label for="review">Review:</label>
      <textarea id="review" v-model="newReview.review"></textarea>
      <button type="submit">Submit Review</button>
    </div>
  </form>
</template>

<script>
import ReviewServices from "../services/ReviewServices";
import employeeServices from "../services/EmployeeServices"

export default {
  name: "add-review",
  data() {
    return {
      newReview: {
        userId: this.$store.state.user.id,
        reviewDate: Date.now(),
        review: "",
        officeId: this.$route.params.officeId
      },
    };
  },
  methods: {
    addNewReview() {
      ReviewServices.createReview(this.newReview).then( response => {
          if(response.status === 201){
             this.$store.commit('ADD_REVIEW', response.data)
          }
      })
    },
     created() {
        employeeServices.getOfficeById(this.$route.params.officeId).then(response => {
            this.office = response.data;
        })
    }
  },
};
</script>

<style>
</style>