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

Methods:{
    updateNewPatientInfo(){
    PatientService.createPersonalInfo(this.patient.id)
    .then(response => {
        if(response.status === 201){
            this.$store.state.commit("ADD_PATIENT_INFO", this.response.data)
            this.$router.push({name:'Patient', params:{id: this.patient.id}});
        }
    });
    },


}


};
</script>

<style>
</style>