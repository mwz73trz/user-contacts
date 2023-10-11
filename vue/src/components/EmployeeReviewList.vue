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
            <router-link v-bind:to="{ name: 'RepliesList', params: { id: review.reviewId } }" v-bind:key="review.reviewId"><td>{{ review.review }}</td></router-link>
            <td><router-link v-bind:to="{ name: 'AddReplyForm', params: { id: review.reviewId } }" v-bind:key="review.reviewId" tag="button">Reply</router-link></td>
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
        reviewId: 0,
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
  color: #414042;
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
  color: #414042;
}
td {
  padding: 4px;
  text-decoration-style: none;
}
a.router-link-exact-active {
  text-decoration: none;
  color: blue;
}
/* tbody tr:nth-child(even) {
  background-color: #F2F2F2;
} */

</style>