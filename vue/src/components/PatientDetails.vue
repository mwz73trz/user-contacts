<template>
  <div>
      <h1>Patient Information</h1>
       <p>Name : {{ patient.firstName }} {{ patient.lastName }}</p>
       <p>Address : {{ patient.address}} {{patient.city}} {{patient.state}} {{patient.zip}} </p>
       <p> Phone Number : {{patient.phoneNumber}} </p>
       <p> Email Address : {{patient.email}} </p>
    <div class=home> 
      <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
      <router-link class="update-personal-info" v-bind:to="{name: 'edit-personal-info', params:{ id: patient.id }}" tag ="button"> Update Info</router-link>
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
            address: "",
            city: "",
            state: "",
            zip: "",
            phoneNumber: "",
            email: "",
            
        }
    }
},
created(){
    patientService.getPatient(
   ).then (response => {
            this.patient = response.data;
        })
},

}
</script>

<style scoped>

</style>