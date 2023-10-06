<template>
  <form v-on:submit.prevent="addNewReview">
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
        // id: 0,
        userId: this.$store.state.user.id, //this.$store.state.user.id
        reviewDate: null,
        review: "",
        officeId: this.$route.params.officeId//this.$store.state.office.id,
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