<template>
  <div class ="Profile">
  <employee-profile />
  </div>
</template>

<script>
import EmployeeProfile from './EmployeeProfile.vue';

export default {
  name: "employee-details",
  components: {EmployeeProfile},
}
</script>

<style>
body,
html {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 100vh;
  background-color: white;
}

</style>