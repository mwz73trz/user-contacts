<template>
  <div class="reviews">
    <div>
      <h2>Office Reviews</h2>
      </div>
    <div class="reviews-list">
      <table>
        <tr>
          <th>Review Date</th>
          <th>Review</th>
        </tr>
        <tbody>
          <tr v-for="review in reviewList" v-bind:key="review.officeId">
            <td with="80%">{{ review.reviewDate }}</td>
            <td>{{ review.review }}</td>
            <td><button>Reply</button></td>
          </tr>
        </tbody>
      </table>
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
    ReviewServices.getEmployeeReviews().then(response => {
        this.reviewList = response.data;
    });
  },
//   computed: {
//       filteredReviews(){
//           return this.reviewList.filter((item) => {
//               return item.officeId == this.$route.params.officeId;
//           })
//         }
//       }
  }
</script>

<style>
h2 {
  text-align: center;
}
.reviews-list {
  margin: 0 auto;
  max-width: 800px;
}
.review {
  font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px;
}
.topic:last-child {
  border: 0px;
}

table {
  text-align: left;
  width: 800px;
  border-collapse: collapse;
}
td {
  padding: 4px;
}
/* tbody tr:nth-child(even) {
  background-color: #F2F2F2;
} */

</style>