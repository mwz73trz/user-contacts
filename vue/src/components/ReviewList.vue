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
          <tr v-for="review in filteredReviews" v-bind:key="review.officeId">
            <td with="80%">{{ review.reviewDate }}</td>
            <td class="wrap-review">{{ review.review }}</td>
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
    ReviewServices.getReviews().then(response => {
        this.reviewList = response.data;
    });
  },
  computed: {
      filteredReviews(){
          return this.reviewList.filter((item) => {
              return item.officeId == this.$route.params.officeId;
          })
        }
      }
  }
</script>

<style scoped>
.reviews {
  background-color: #f7fafc;
  border-radius: 10px;
  padding: 10px 20px 20px 20px;
  align-items: center;
}
.reviews h2 {
  text-align: center;
  margin: 0 0 30px 0;
}
.reviews-list {
  margin: 20px 0;
  max-width: 800px;
}
.wrap-review {
  text-wrap: wrap;
}
.review {
  font-size: 24px;
  /* border-bottom: 1px solid #f2f2f2; */
  padding: 10px 20px;
  border-bottom: 1px solid rgb(180, 180, 180);
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
  border-bottom: 1px solid rgb(180, 180, 180);
}
/* tbody tr:nth-child(even) {
  background-color: #F2F2F2;
} */

</style>