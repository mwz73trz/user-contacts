<template>
  <div>
      <h1>Patient Information</h1>
       <p>Name : {{ patient.firstName }} {{ patient.lastName }}</p>
    <div class=home> 
      <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
    </div>
  </div>
</template>

<script>
import patientService from '../services/PatientService';


export default {
name:"patient-details",
data(){
    return{
        patient: {
            id:0,
            firstName: "",
            lastName:"",
        }
    }
},
created(){
    patientService.getPatient(
        this.$route.params.id,
        this.$route.params.id).then (response => {
            this.patient = response.data;
        })
},

}
</script>

<style>

</style>