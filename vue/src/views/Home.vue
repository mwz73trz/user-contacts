<template>
  <div class="home">
    <nav class="navbar">
      <h1 class="welcome"> Welcome,
        {{ this.$store.state.user.username }}
      </h1>
    </nav>
  </div>
</template>

<script>
export default {
  name: "home",
};
</script>

<style>
</style>