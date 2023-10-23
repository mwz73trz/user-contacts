<template>
  <div class="home">
    <nav class="navbar">
      <h1 class="welcome"> Welcome,
        {{ this.$store.state.user.username }}
      </h1>
    </nav>
    <grouping-list />
  </div>
</template>

<script>
import GroupingList from '../components/GroupingList'

export default {
  name: "home",
  components: {
    GroupingList
  }
};
</script>

<style>
</style>