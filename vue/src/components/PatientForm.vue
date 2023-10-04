<template>
  <div class="form">
      <div class="patientForm"> 
          <h2 class="updateInfo">Please update the information below: </h2>
      <form v-on:submit.prevent="updateNewPatientInfo" > 
      <div class="form-element">
          <label for="name">First Name:</label>
          <input id="firstName" type="text" v-model="patient.firstName" />
      </div>
      <div class="form-element">
          <label for="name">Last Name:</label>
          <input id="lastName" type="text" v-model="patient.lastName" />
      </div>
      <div class="actions">
        <button type="submit" >Update</button>
      </div>
      </form>
  </div>
  </div>
</template>

<script>
import PatientService from '../services/PatientService'

export default {
name: "patient-form",
data(){
    return{
        patient:{
            id: 0,
            firstName: "",
            lastName: "",
        },
    };

},
<<<<<<< HEAD
methods:{
    updateNewPatientInfo(){
        const newPatient = {
            firstName: this.patient.firstName,
            lastName: this.patient.lastName
        }
    PatientService.createPersonalInfo(newPatient)
    .then(response => {
        if(response.status === 201){
            this.$router.push("/");
=======

methods:{
    updateNewPatientInfo(){
    PatientService.createPersonalInfo(this.patient)
    .then(response => {
        if(response.status === 201){
            this.$store.commit("ADD_PATIENT_INFO", response.data)
            this.$router.push({name:'Patient', params:{id: this.patient.id}});
>>>>>>> 0621a1be6a593ee6b55c0ddaea92e12b2c8115b6
        }
    });
    },

}


};
</script>

<style>
h2.updateInfo{
   display: flex;
   justify-content: center;
   color: rgb(131, 11, 125);
}
.form-element{
    display: flex;
    justify-content: center;
    padding: 10px;
}
.actions{
    display: flex;
    justify-content: center;
    padding: 10px;
}
</style>