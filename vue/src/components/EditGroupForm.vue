<template>
  <div>
    <form v-on:submit.prevent="submitForm">
      <div v-show="errorMsg !== ''">{{ errorMsg }}</div>
      <label for="title">Title:</label>
      <input type="text" v-model="grouping.title" autocomplete="off">
      <button>Submit</button>
      <button v-on:click="cancelForm" type="button">Cancel</button>
    </form>
  </div>
</template>

<script>
import groupingServices from '../services/GroupingServices'

export default {
  name: 'edit-group-form',
  data() {
    return {
      grouping: {
        title: ''
      },
      errorMsg: ''
    }
  },
  methods: {
    submitForm() {
      const updatedGroup = {
        groupingId: this.$route.params.groupingId,
        title: this.grouping.title
      }
      groupingServices.updateGrouping(this.$route.params.groupingId, updatedGroup).then(response => {
        if (response.status === 200) {
          this.$router.push(`/groupings/${updatedGroup.groupingId}/contacts`);
        }
      })
      .catch(error => {
        this.handleErrorResponse(error, "updating")
      })
    },
    cancelForm() {
      this.$router.push(`/groupings/${this.$route.params.groupingId}/contacts`)
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " group. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " group. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " group. Request could not be created.";
      }
    }
    },
    created() {
      groupingServices.getGroupingById(this.$route.params.groupingId).then(response => {
        this.grouping = response.data;
      })
    }
  }
</script>

<style>

</style>