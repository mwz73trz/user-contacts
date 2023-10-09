<template>
<div>
  <div class="covid-list"> 
    <covid-list/>
  </div>
  <div class="headerPatient"><h1>Please Select one of the below to view additional information.</h1></div>
  <div><h2>To book an appointment please select the doctores name.</h2></div>
  <div class="office-list">
    <label for="office-list">Select an office</label>
    <select name="offices" v-on:change="officesRoute($event)">
      <option value="0">Offices</option>
      <option v-for="office in officeList" :key="office.officeId" :value="office.officeId">
        {{ office.officeName }}
      </option>
    </select>
</div>
  <div class="employee-list">
    <label for="employee-list">Select a Doctor</label>
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
}
.office-list, .employee-list{
  text-align: center;
  font-family:monospace;

}
</style>
