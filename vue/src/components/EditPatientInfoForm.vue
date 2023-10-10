<template>
  <div>
    <h1> Please edit the information below as needed: </h1>
    <form v-on:submit.prevent="submitForm">
        <div>
         <label for="firstName">First Name: </label>
         <input type="text" v-model="patient.firstName">
       </div>
        <div>
         <label for="lastName">Last Name: </label>
         <input type="text" v-model="patient.lastName">
       </div>
        <div>
         <label for="address">Address: </label>
         <input type="text" v-model="patient.address">
       </div>
        <div>
         <label for="city">City: </label>
         <input type="text" v-model="patient.city">
       </div>
       <div>
         <label for="state"> State: </label>
         <input type="text" v-model="patient.state">
       </div>
        <div>
         <label for="zip">Zip: </label>
         <input type="text" v-model="patient.zip">
       </div>
       <div>
         <label for="phoneNumber"> Phone Number: </label>
         <input type="text" v-model="patient.phoneNumber">
       </div>
        <div>
         <label for="email"> Email: </label>
         <input type="text" v-model="patient.email">
       </div>
       <button>Submit</button>
      </form>
  
  </div>
</template>

<script>
import PatientService from "../services/PatientService";

export default {
    name: 'edit-personal-info',
    data(){
        return{
            patient: {
                patient: {
                    patientId:this.$route.params.id,
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
        }
    },
    methods: {
        submitForm(){
            const updateInfo = {
            patientId: this.patient.patientId,
            firstName: this.patient.firstName,
            lastName: this.patient.lastName,
            address: this.patient.address,
            city: this.patient.city,
            state: this.patient.state,
            zip: this.patient.zip,
            phoneNumber: this.patient.phoneNumber,
            email:this.patient.email,
            }
        PatientService.updatePatientInfo(updateInfo).then(response => {
            if (response.status === 200) {
                    this.$router.push('/')
                }
        })
        }
    },
    created(){
        PatientService.getPatient().then(response => {
            return this.patient = response.data;
        });
    }
}
</script>

<style scoped>

</style>