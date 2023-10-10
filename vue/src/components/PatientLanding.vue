<template>
<div>
  <div id="info">
     <p>
      Thank you for choosing us for your care! If you are having an emergency, call 911 now—do not send a message through this portal.
       If you are thinking of hurting yourself, call for help right away. Don’t wait, help is available to anyone. All calls are kept private and confidential 1-740-361-4729.
       If you need to reschedule, you will be directed to call your appointment location to reschedule your appointment.  
      </p>
  <div class="covid-list"> 
    <covid-list/>
  </div>
  <!-- <div class="info">
    </div> -->
  <div class="headerPatient"><h2>Please select from Offices/Doctors to view additional information and to book
    an appointment.</h2></div>
  <div class="office-list">
    <label for="office-list">Select an Office:  </label>
    <select name="offices" v-on:change="officesRoute($event)">
      <option value="0">Offices</option>
      <option v-for="office in officeList" :key="office.officeId" :value="office.officeId">
        {{ office.officeName }}
      </option>
    </select>
</div>
  <div class="employee-list">
    <label for="employee-list">Select a Doctor:  </label>
    <select name="employees" v-on:change="employeesRoute($event)">
      <option value="0">Doctors</option>
      <option v-for="employee in employeeList" :key="employee.employeeId" :value="employee.employeeId">
        {{ employee.lastName }}, {{ employee.firstName }}
      </option>
    </select>
  </div>
    <nav class = "nav-buttons">
    <router-link v-bind:to="{name: 'patient', params: {id: $route.params.id}}" tag = "button"> To view your personal info </router-link>
  </nav>
  
  <calendar-scheduler />
  <patient-form v-if="isFormVisible"></patient-form>
  </div>
   <!-- <img src="../assets/PatientLanding.jpg" alt = "Click me" class="image" id ="imageToClick"> -->
  </div>
  
</template>
<script>
import patientService from '../services/PatientService';
import PatientForm from './PatientForm.vue';
import CovidList from './CovidList.vue';


export default {
  components:{
    PatientForm,
    CovidList
  },
  data(){
    return{
      officeList: [],
      employeeList: [],
      id: 0,
      patient: {
        id: 0,
        firstName: "",
        lastName: "",
      },
    }
  },
  
  methods: {
    officesRoute(e) {
      this.$router.push("/offices/" + e.target.value)
    },
    employeesRoute(e) {
      this.$router.push("/employees/" + e.target.value)
    },
  },

  created() {
  patientService.getOffices().then(response => {
    this.officeList = response.data;
  }),
  patientService.getEmployees().then(response => {
    this.employeeList = response.data;
  }),
  patientService.getPatient(this.$route.params.id, this.$route.params.id).then(response => {
      this.patient = response.data;
    })
  },

computed: {
    isFormVisible() {
      return !!(this.patient.firstName === "" && this.patient.lastName === "");
    }
  },
  

}
</script>

<style scoped>

.logo{
  border: 1px solid rgb(176, 165, 196);
  border-radius: 6px;
  padding: 6px;
  width: 150px;
}
.headerPatient{
  text-align: center;
  display: flex;
  flex-wrap: wrap;
  margin-top: 3rem;
}
.office-list, .employee-list{
  text-align: center;
  font-family:monospace;
  margin-left: .1rem;
  margin-right: 2rem;
  float:left;
}

.info{
  text-shadow: length;
  white-space:pre-wrap;
    margin-top: 3rem;
}

p{

  font-family:Raleway;
  /* width: 400rem; */
  font-size:1rem;
  margin-top: 0.5rem;
  margin-right: 2rem;
  color:blueviolet;
  font-size:large;
  text-align: justify;
  font-stretch: extra-expanded;
  
  
}
button{
  /* color: solid #000; */
  
  margin-right: 15rem;;
  color: #fff;
  background: #0060f0;
  border-radius: 4px;
  width: 210px;
  height: 30px;
  border: 0.5px solid;
  font-family: monospace;
  
}


/* .nav-buttons{
  margin-left: auto;
  width:10px;
  cursor: pointer;
  float:right;
  cursor: pointer;
}  */


select {

  background-color: transparent;
  padding: justify;
  margin: 0;
  width: 50%;
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
  font-family:monospace;
   z-index: 1;
  cursor: pointer;
  line-height: 1.1;
  color:gray;
  font-weight:bold;
}

#navbar a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  line-height: 25px;
  border-radius: 4px;
  margin-top: 5%
}

.logo{
  border: 1px ;
  border-radius: 6px;
  padding: 15px;
  width: 250px;
  float: left;
}

.image{
  width:50rem;
  height: 35rem;
}

</style>
