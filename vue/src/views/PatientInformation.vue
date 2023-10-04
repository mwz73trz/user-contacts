<template>
   <div class="personal-info">
      <patient-details />
  </div>
</template>

<script>
import PatientDetails from "../components/PatientDetails.vue";
export default {
    name: "PatientInformation",
  components: {
    PatientDetails,
  },
}
</script>

<style>

</style>