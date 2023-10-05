<template>
  <form v-on:submit.prevent="addNewReview">
    <div class="form-element">
      <label for="review">Review:</label>
      <textarea id="review" v-model="newReview.review"></textarea>
      <button>Submit</button>
    </div>
  </form>
</template>

<script>
export default {
  name: "add-review",
  data() {
    return {
      newReview: {
        id: 0,
        userId: 0,
        date: null,
        review: "",
        officeId: 0,
      },
    };
  },
  methods: {
    addNewReview() {
        
    },
  },
};
</script>

<style>
</style>