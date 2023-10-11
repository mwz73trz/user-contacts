<template>
  <div>
    <h1> Please edit the information below as needed: </h1>
    <img class="personalInfoImg" src="../assets/personal-information.png" />
    <form class="updateinfo" v-on:submit.prevent="submitForm">
        <div>
         <label class="infolabel" for="firstName">First Name: </label>
         <input type="text" class="text-input" v-model="patient.firstName">
       </div>
        <div>
         <label for="lastName">Last Name: </label>
         <input type="text" class="text-input" v-model="patient.lastName">
       </div>
        <div>
         <label for="address">Address: </label>
         <input type="text" class="text-input" v-model="patient.address">
       </div>
        <div>
         <label for="city">City: </label>
         <input type="text" class="text-input" v-model="patient.city">
       </div>
       <div>
         <label for="state"> State: </label>
         <input type="text" class="text-input" v-model="patient.state">
       </div>
        <div>
         <label for="zip">Zip: </label>
         <input type="text" class="text-input" v-model="patient.zip">
       </div>
       <div>
         <label for="phoneNumber"> Phone Number: </label>
         <input type="text" class="text-input" v-model="patient.phoneNumber">
       </div>
        <div>
         <label for="email"> Email: </label>
         <input type="text" class="text-input" v-model="patient.email">
       </div>
       <button>Submit</button>
       <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
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
form.updateinfo{
  width:180px;
  line-height:30px;
  padding:126px;
  margin-left:480px;
  /* box-shadow:-2px -3px 2px 2px rgba(0,0,0,0.06); */
  border-radius:3px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size: 25px;
  margin-top:-7rem;
}

.text-input{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size:20px;
  color:rgb(19, 3, 3);
  box-shadow:5px 0 15px 19px rgba(0,0,0,0.06);
  box-sizing:25rem; 
  border-radius:6px;
 
}

.personalInfoImg{
  width: 450px;
  float:right;
}

.infolabel{
  margin-top: 22rem;
  text-align: center;
}

h1{
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
   /* font-weight: bold; */
   text-align: justify;
  text-decoration:underline dotted;
  color:rgb(100, 100, 100)
}

button {
  color: #fff;
  background: #0060f0;
  margin-right: 10px;
  margin-left:9px;
  border-radius: 4px;
  width: 100px;
  height: 30px;
  border: 0.5px solid;
  display:inline-block; 
  /* float:left; */
  margin-top: 2rem;
  padding:5px; 
	border:0 none;
	cursor:pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
	color: #fff;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size:20px;
}

</style>
