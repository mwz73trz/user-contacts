<template>
  <div class="replies">
    <div class="side-bar"></div>
    <div class="content">
    <img class="reviewsImg" src="../assets/reviews.png" />
    <h1 class="repliesH1">Replies</h1>
      <div class="formButtons">
        <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
         <router-link :to="{ name: 'EmployeeReview' }" tag ="button">All Reviews</router-link>
      </div>
    <div class="replies-list">
      <table>
        <tr class="headers">
          <th>Reply Date</th>
          <th>Reply</th>
        </tr>
        <tbody>
          <tr v-for="reply in replyList" v-bind:key="reply.replyId">
            <td with="80%">{{ reply.reviewDate }}</td>
            <td>{{ reply.reviewReply }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    </div>
  </div>
</template>

<script>
import replyServices from '../services/ReplyServices'

export default {
 name: 'replies-by-review',
 data() {
        return {
            replyList: [],
            reply: {
                replyId: 0,
                reviewId: 0,
                userId: 0,
                reviewDate: '',
                reviewReply: ''
            }
        }
    },
    created() {
       replyServices.getReplies(this.$route.params.id).then(response => {
           this.replyList = response.data;
       }) 
    }
}
</script>

<style scoped>
.replies {
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
.officeDetailEdits{
  width: 300px;
}
.replyH1{
  text-align:left;
  font-size: 40px;
}
.reviews-list{
  max-width: 800px;
  margin: 0;
  align-items: center;
}
th{
  width: 100px;
  height: 10px; 
}
.headers {
  background-color: lightgrey;
  font-size: 25px; 
}
td {
  padding: 10px;
  font-size: 20px;
  border-bottom: 1px solid grey; 
  text-decoration-style: none;
}
button {
  color: #fff;
  background: #0060f0;
  margin-right: 30px;
  border-radius: 4px;
  width: 150px;
  height: 70px;
  border: 0.5px solid;
}
.formButtons{
  text-align: center;
  padding:50px;
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

</style>