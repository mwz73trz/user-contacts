<template>
  <div class="home">
    <nav class="navbar">
       <img src="../assets/AppLogo.png" class="logo" />
      <h1 class="welcome"> Welcome,
        {{ this.$store.state.user.username }}
      </h1>
    </nav>
    <div class="page-content">
      <patient-landing
        v-if="this.$store.state.user.authorities[0].name === 'ROLE_USER'"
      />
      <employee-landing
        v-else-if="this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'"
      />
    </div>
  </div>
</template>

<script>
import EmployeeLanding from "../components/EmployeeLanding.vue";
import PatientLanding from "../components/PatientLanding.vue";

export default {
  name: "home",
  components: {
    PatientLanding,
    EmployeeLanding,
  },
};
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
/* .navbar{
  background-color: rgb(103, 147, 177);
} */

.logo{
  /* border: 1px solid #ddd; */
  border-radius: 4px;
  padding: 5px;
  width: 8rem;
  display: flex;
  float: left;
  height: 8rem;
}

.home {
  /* background: rgb(103, 147, 177); */
  font-family: monospace;
  font-size: 12px;
  display: list-item;
}

.welcome{
  margin-left:2rem;
  float:left;
  margin-top:0.5rem;
  color:darkolivegreen
}
</style>