<template>
  <div class="reply">
    <div class="side-bar"></div>
    <div class="content">
      <img class="reviewReplyImg" src="../assets/review-reply.png" />
      <h1 class="replyH1">Please responde to the review below:</h1>
      <form v-on:submit.prevent="addReply">
        <div class="form">
          <label for="reply"></label>
          <textarea class="review-reply" id="review-reply"  v-model="newReply.reviewReply"></textarea>
            <div class="actions">
              <button type="submit">Submit</button>
              <router-link :to="{ name: 'EmployeeReview' }" tag ="button">Cancel</router-link>
            </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import replyServices from '../services/ReplyServices'

export default {
  name: 'add-reply-form',
  props:{

  },
  data() {
    return {
      newReply: {
        reviewId: this.$route.params.id,
        userId: this.$store.state.user.id,
        reviewDate: Date.now(),
        reviewReply: ''
      }
    }
  },
  methods: {
    addReply() {
      replyServices.createAddReply(this.$route.params.id, this.newReply).then(response => {
        if (response.status === 201) {
          this.$store.commit('ADD_REPLY', response.data)
          this.$router.push({name: 'EmployeeReview'})
        }
      })
    },
  }
}
</script>

<style scoped>
.reply{
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
 .reviewReplyImg{
  width: 300px;
}
 .replyH1{
  text-align:left;
  font-size: 35px;
 }
.form{
  color: #414042;
  border-style: none;
  font-size: 22px;
  font-family: Cambria;
  font-weight: lighter;
  padding: 5px;
}
.actions{
display: flex;
justify-content: space-around;
}
.review-reply {
  text-align: left;
  padding:90px;
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
  padding:20px;
}
button:hover {
  background-color: #64b5f6;
}

</style>