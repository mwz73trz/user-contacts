<template>
  <div>
      <form v-on:submit.prevent="addReply">
        <div class="form">
          <label for="reply">Reply Response:</label>
          <textarea class="review-reply" id="review-reply" cols="30" rows="10" v-model="newReply.reviewReply"></textarea>
            <div class="actions">
              <button type="submit">Submit</button>
              <router-link :to="{ name: 'EmployeeReview' }" tag ="button">Cancel</router-link>
            </div>
        </div>
      </form>
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

<style>
.form{
display: flex;
flex-direction: column;
justify-content: center;
}
.actions{
display: flex;
justify-content: space-around;
}

.review-reply {
  margin-top: 4px;
}

</style>