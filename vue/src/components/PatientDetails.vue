<template>
  <div class=patient>
    <img src="../assets/patientintake.jpg" alt = "Click me" class="logo">
    <div class="my-info">
      <h1>My Information</h1>
      <div>
       Name : {{ patient.firstName }} {{ patient.lastName }}
       </div>
       <div>
       Address :  {{ patient.address}} {{patient.city}} {{patient.state}} {{patient.zip}}
       </div>
       <div>
        Phone Number : {{patient.phoneNumber}}
        </div>
       Email Address : {{patient.email}}
       </div>
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
     line-height: 3.8rem;
     font-size: 1.5rem;
     float:right;
     margin-left: -25px;
     
}


/* .return-home-btn .update-personal-info{
   justify-content:center;
   font-family: monospace;
   font-size: larger;
   width:20%; 
   margin-right:11%;
   margin-top: 80%; 
   color: #fff;
  background: #0060f0;
     margin-top: -12rem; 
  margin-bottom: -12rem;
} */

.return-home-btn.router-link-active {
  color: #fff;
  background: #0060f0;
  border-radius: 4px;
  width: 160px;
  height: 30px;
  border: 0.5px solid;
  float:right;
   font-size:20px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  margin-top: 63px;
}  

button.update-personal-info {
  color: #fff;
  background: #0060f0;
  border-radius: 4px;
  width: 150px;
  height: 30px;
  border: 0.5px solid;
  float:right;
  margin-right: -600px;
  font-size:20px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
   margin-top: 63px;
  /* margin-left: -56%; */
}

.logo{
  float:left;
  width: 40%;
  height: 5in;
}

h1{
  text-align: center;
  color:dimgrey
}
.my-info{
 text-align:center;
 margin-right: -110%;
 color:dimgrey
}
</style>