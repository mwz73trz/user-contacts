<template>
<div>
  <div id="info">
     <p>
      Thank you for choosing us for your care! If you are having an emergency, call 911 now—do not send a message through this portal.
       If you are thinking of hurting yourself, call for help right away. Don’t wait, help is available to anyone. All calls are kept private and confidential 1-740-361-4729.
       If you need to reschedule, you will be directed to call your appointment location to reschedule your appointment.  
     </p>
    </div>
     <div class="notification-content">
    <h2 class="alertHeader">My Upcoming Appointments</h2>
    <ul class="alertList">
      <li v-for="appointment in appointmentList" :key="appointment.appointmentId">
        Doctor: {{ getDoctorName(appointment) }} |
        Date: {{ appointment.appointmentDateStart }} |
        Time: {{ appointment.appointmentTimeStart }} to {{ appointment.appointmentTimeEnd }}
      </li>
    </ul>
  </div>
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
    <!-- <nav class = "nav-buttons"> -->
    <router-link class = "personal-info-button" v-bind:to="{name: 'patient', params: {id: $route.params.id}}" tag = "button"> To view your personal info </router-link>
  <!-- </nav> -->
   <img src="../assets/PatientLanding.jpg" alt = "Click me" class="image" id ="imageToClick">
  <calendar-scheduler />
  <patient-form v-if="isFormVisible"></patient-form>
    <covid-list/>
</div>
  
  
  
</template>
<script>
import patientService from '../services/PatientService';
import PatientForm from './PatientForm.vue';
import CovidList from './CovidList.vue';
import apptService from '../services/ApptService'


export default {
  components:{
    PatientForm,
    CovidList
  },
  data(){
    return{
      officeList: [],
      employeeList: [],
      appointmentList: [],
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
    getDoctorName(appointment) {
      const employee = this.employeeList.find(employee => employee.employeeId === appointment.employeeId);
      return employee ? employee.firstName + " " + employee.lastName : 'Unknown';
    }
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
    }),
    apptService.getAppointmentsByPatientUser().then(response => {
      this.appointmentList = response.data;
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
/* $banner-width: 20em;
$banner-height: 5em; */

*,
*::before,
*::after {
	position: relative;
	box-sizing: border-box;
	transition: 1s all linear;
}
.logo{
  border: 1px solid rgb(176, 165, 196);
  border-radius: 6px;
  padding: 6px;
  width: 150px;
}
h2{
   color:blueviolet;
   margin-left: 8rem;
}
.headerPatient{
  text-align: center;
  display: flex;
  flex-wrap: wrap;
  margin-top: 1rem;
 
}
.office-list, .employee-list{
  text-align: center;
  font-family:monospace;
  margin-left: 2rem;
  margin-right: 11rem;
  float:left;
  font-size: 188%;
  position: relative;
  color:rgb(32, 94, 32);       
}

.info{
  text-shadow: length;
  white-space:pre-wrap;
  margin-top: 4rem;
  
} 

p{
  font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  font-size:1rem;
  color:blueviolet;
  font-size:large;
  /* text-align:left;  */
  margin-left: 0.5%;
  font-style: oblique;
  /* font-stretch: extra-expanded; */
}


.personal-info-button{
  color: solid #000; 
  margin-left: -50rem; 
  color: #fff;
  background: #0060f0;
  border-radius: 4px;
  width: 300px;
  height: 40px;
  border: 0.5px solid;
  font-family: monospace;
  margin-top:8rem; 
  display:flexbox;
  font-size: 20px;
  /* position:relative; */
  /* flex:left;  */
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

/* #navbar a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  line-height: 25px;
  border-radius: 4px;
  margin-top: 5%
} */

.logo{
  border: 1px ;
  border-radius: 6px;
  padding: 15px;
  width: 250px;
  float: left;
}

/* .image{
  width:50rem;
  height: 35rem;
} */
#info{
  background:seashell;
}

.image{
  float:right;
  width:520px;
  position: relative;
  margin-right: 0.5px;
  height: 2in;
  margin-top:-10rem;
  display:inline-flex;
  padding-right: 10px;;
  }
  .alertHeader{
    font-size: 22px;
}
.alertList{
    color: red;
    list-style-type: none;
    font-size: 17px;
    padding-left: 530px;
}
.notification-content {
    display: flex;
    flex-direction: column;
    height: 150px;
    padding-right: 500px;;
}

</style>
