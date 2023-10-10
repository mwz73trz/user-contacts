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

<style scoped>
.personal-info{
  float: left;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
  font-style:italic;
  color:rgb(22, 82, 10);
  font-size: small;
  font-weight: bold;
  margin-right:5rem;
}

</style>