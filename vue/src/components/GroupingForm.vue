<template>
  <div>
    <h1>Add New Grouping</h1>
    <form v-on:submit.prevent="submitForm">
      <label for="grouping">Grouping Title:</label>
      <input type="text" v-model="newGrouping.title">
      <button>Cancel</button>
      <button>Save</button>
    </form>
  </div>
</template>

<script>
  import groupingServices from "../services/GroupingServices"

export default {
  name: 'grouping-form',
  data() {
    return {
      newGrouping: {
        title: ''
      }
    }
  },
  methods: {
    submitForm() {
      groupingServices.addGrouping(this.newGrouping).then(response => {
        if (response.status === 201) {
          this.$router.push("/");
        }
      })
    },
    cancel() {
      this.$router.push('/');
    }
  }
}
</script>

<style>

</style>