<template>
  <div class="personal-info">
      <employee-profile />
  </div>
</template>

<script>
import EmployeeProfile from "../components/EmployeeProfile.vue";

export default {
  name: "EmployeeInformation",
  components: {
    EmployeeProfile,
  },
};
</script>

<style>
</style>