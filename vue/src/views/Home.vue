<template>
  <div class="home">
    <nav class="navbar">
      <h1 class="welcome"> Welcome,
        {{ this.$store.state.user.username }}
      </h1>
    </nav>
    <test-list />
  </div>
</template>

<script>
import TestList from '../components/TestList.vue'

export default {
  name: "home",
  components: {
    TestList
  }
};
</script>

<style>
</style>