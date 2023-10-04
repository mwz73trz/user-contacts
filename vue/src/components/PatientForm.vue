<template>
  <div class="Info">
      <div class="form">
      <!-- <input type="button" value="Update Information"> -->
      <form v-on:submit.prevent="updateNewPatientInfo" > 
      <div class="form-element">
          <label for="name">First Name:</label>
          <input id="name" type="text" v-model="patient.firstName" />
      </div>
      <div class="form-element">
          <label for="name">Last Name:</label>
          <input id="name" type="text" v-model="patient.lastName" />
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
        createInfo:{
            id: 0,
            firstName: "",
            lastName: "",
        }

    };

},
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
        }
    });
    },


}


};
</script>

<style>
</style>