<template>
  <div class="personal-info">
      <employee-details />
  </div>
</template>

<script>
import EmployeeDetails from "./EmployeeDetails.vue";

export default {
  name: "EmployeeInformation",
  components: {
    EmployeeDetails,
  },
};
</script>

<style>
</style>