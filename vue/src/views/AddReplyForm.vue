<template>
  <div>
      <form v-on:submit.prevent="addReply">
        <div>
          <label for="reply">Reply Response:</label>
          <textarea id="review-reply" cols="30" rows="10" v-model="newReply.reviewReply"></textarea>
          <button type="submit">Submit Reply</button>
        </div>
      </form>
  </div>
</template>

<script>
import replyServices from '../services/ReplyServices'

export default {
  name: 'add-reply-form',
  data() {
    return {
      newReply: {
        reviewId: this.$route.params.id,
        userId: this.$store.state.user.id,
        reviewDate: null,
        reviewReply: ''
      }
    }
  },
  methods: {
    addReply() {
      replyServices.addReply(this.$route.params.id, this.newReply).then(response => {
        if (response.status === 201) {
          this.$store.commit('ADD_REPLY', response.data)
        }
      })
    }
  }
}
</script>

<style>

</style>