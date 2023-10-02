<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <test-list> </test-list>
  </div>
</template>

<script>
import TestList from "../components/TestList.vue"
export default 
  {
  name: "home",
  components:{
    TestList
  }
};
</script>
