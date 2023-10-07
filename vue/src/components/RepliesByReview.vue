<template>
  <div class="replies">
    <div>
      <h2>Replies</h2>
        <div class="navButtons">
          <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
          |
          <router-link :to="{ name: 'EmployeeReview' }" tag ="button">Return To Reviews</router-link>
        </div>
      </div>
    <div class="replies-list">
      <table>
        <tr>
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

<style>
h2 {
  text-align: center;
}
.navButtons{
 text-align: center;
}
.replies-list {
  padding: 40px;
  margin: 0 auto;
  max-width: 800px;
}
.reply {
  font-size: 24px;
  border-bottom: 1px solid #f2f2f2;
  padding: 10px 20px;
}
.reply:last-child {
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
</style>