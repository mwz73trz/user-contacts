<template>
  <div class="home">
    <h1>
      <img src="../assets/AppLogo.png" class="logo"> Welcome, {{ this.$store.state.user.username }}</h1>
    <p>{{ this.$store.state.user.authorities[0].name }}</p>
    <!-- <test-list> </test-list> -->
    <patient-landing v-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'" />
    <employee-landing v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'" />

  </div>
</template>

<script>

import EmployeeLanding from '../components/EmployeeLanding.vue';
import PatientLanding from '../components/PatientLanding.vue';
// import TestList from "../components/TestList.vue"

export default 
  {
  name: "home",
  components:{
    // TestList,
    PatientLanding,
    EmployeeLanding
  }
};
</script>

<style scoped>
.logo{
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 100px;
}

.home{
  background: rgb(103, 147, 177);
}
</style>