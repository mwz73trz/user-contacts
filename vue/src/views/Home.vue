<template>
  <div class="home">
     <nav class="navbar">
    <h1>
      <img src="../assets/AppLogo.png" class="logo">Welcome, {{ this.$store.state.user.username }}</h1>
     </nav>
     <div class="page-content">
    <patient-landing v-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'" />
    <employee-landing v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'" />    
    </div>
  </div>
</template>

<script>
import EmployeeLanding from '../components/EmployeeLanding.vue'
import PatientLanding from '../components/PatientLanding.vue';

export default 
  {
  name: "home",
  components:{
    PatientLanding,
    EmployeeLanding,
   
  }
};
</script>

<style scoped>


.navbar {
  background: rgb(103, 147, 177); 
  font-family: monospace;
  font-size: 12px;
  padding: 10px; 
}

.logo{
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 100px;
  display: felx;
  float: left;

}

.home{
  background: rgb(103, 147, 177);
  font-family: monospace;
  font-size: 12px;
  display: list-item;
}



</style>