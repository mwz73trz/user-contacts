<template>
  <div class="replies">
    <div>
      <h2>Replies</h2>
      </div>
    <div class="replies-list">
      <table>
        <tr>
          <th>Reply Date</th>
          <th>Reply</th>
        </tr>
        <tbody>
          <tr v-for="reply in replyList" v-bind:key="reply.replyId">
            <td with="80%">{{ reply.replyDate }}</td>
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
       replyServices.getReplies().then(response => {
           this.replyList = response.data;
       }) 
    }
}
</script>

<style>

</style>