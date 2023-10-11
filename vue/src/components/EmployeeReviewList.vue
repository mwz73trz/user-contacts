<template>
  <div class="reviews">
    <div class="side-bar"></div>
    <div class="content">
      <img class="reviewsImg" src="../assets/reviews.png" />
    <div>
      <h2 class="officeH2">Office Reviews</h2>
      <h3 class="reviewText"> To view all replys for a review click on the review </h3>
    </div>
    <div class="reviews-list">
      <table>
        <tr class="headers">
          <th> Review Date</th>
          <th> Review</th>
          <th> Submit Reply </th>
        </tr>
        <tbody>
          <tr v-for="review in reviewList" v-bind:key="review.officeId">
            <td>{{ review.reviewDate }}</td>
              <router-link v-bind:to="{ name: 'RepliesList', params: { id: review.reviewId } }" v-bind:key="review.reviewId"><td>{{ review.review }}</td></router-link>
            <td><router-link class="replyBtn" v-bind:to="{ name: 'AddReplyForm', params: { id: review.reviewId } }" v-bind:key="review.reviewId" tag="button">Reply</router-link></td>
          </tr>
        </tbody>
      </table>
      <router-link class="homeBtn" :to="{ name: 'home' }" tag ="button">Return Home</router-link>
    </div>
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
}
</script>

<style scoped>
.reviews{
  display: flex;
}
 .side-bar {
  width: 20%; 
  background-color: darkblue
}
 .content {
  flex: 1;
  text-align: center;
  padding-left: 50px;
} 
.reviewsImg{
  width: 450px;
  text-align: left;
}
.officeH2 {
  text-align: left;
  color: #414042;
  font-size: 40px;
}
.reviewText{
  text-align: left;
  color: #414042;
  font-size: 18px;
}
table {
  text-align: left;
  width: 800px;
  border-collapse: collapse;
}
.headers {
  background-color: lightgrey;
  font-size: 25px; 
}
tbody{
  font-size: 18px;
}
.homeBtn{
  color: #fff;
  background: #0060f0;
  margin: 30px;
  border-radius: 4px;
  width: 150px;
  height: 70px;
  border: 0.5px solid; 
}

.topic:last-child {
  border: 0px;
}
a.router-link-exact-active {
  text-decoration: none;
  color: blue;
  }
tbody tr:nth-child(even) {
  background-color: #F2F2F2;
} 
button:hover {
  background-color: #64b5f6;
}
</style>