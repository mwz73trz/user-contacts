<template>
  <div class=patient>
    <img src="../assets/patientintake.jpg" alt = "Click me" class="logo">
      <h1>My Information</h1>
      <div>
       Name : {{ patient.firstName }} {{ patient.lastName }}
       </div>
       <div>
       Address :  {{ patient.address}}
       </div>
       <div>
       {{patient.city}} {{patient.state}} {{patient.zip}} .
       </div>
       <div>
        Phone Number : {{patient.phoneNumber}}
        </div>
       Email Address : {{patient.email}}
    <div class=home> 
      <router-link  class="return-home-btn" :to="{ name: 'home' }" tag ="button">Return Home</router-link>
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

.patient{
     display:list-item;
     justify-content:center;
     flex-direction: row;
     margin-top:1%;
     
}
.return-home-btn .update-personal-info{
   justify-content:center;
   font-family: monospace;
   font-size: large;
   width:20%;
   margin-right:11%;
   margin-top: 50%;
}

.logo{
  float:left;
  width: 40%;
  height: 4in;
}
</style>