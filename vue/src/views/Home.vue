<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <p>{{ this.$store.state.user.authorities[0].name }}</p>
    <test-list> </test-list>
    <employee-details></employee-details>
    <patient-landing v-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'" />
    <employee-landing v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'" />
  </div>
</template>

<script>
import EmployeeDetails from '../components/EmployeeDetails.vue';
import EmployeeLanding from '../components/EmployeeLanding.vue';
import PatientLanding from '../components/PatientLanding.vue';
import TestList from "../components/TestList.vue"


export default 
  {
  name: "home",
  components:{
    TestList,
    EmployeeDetails,
    PatientLanding,
    EmployeeLanding
  }
};
</script>
