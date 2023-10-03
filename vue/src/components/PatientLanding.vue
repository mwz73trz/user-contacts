<template>
<div>
  <div><h1>Patient</h1></div>
  <div class="office"
  v-for ="office in officeList"
  v-bind:key ="office.officeId">{{office.officeName}}</div>
  <div class="employee"
  v-for ="employee in employeeList"
  v-bind:key ="employee.employeeId">{{employee.firstName}} {{employee.lastName}}</div>
  </div>
</template>
<script>
import patientService from '../services/patientService';

export default {
  data(){
    return{
      officeList: [],
      employeeList: []
    }
  },
  created() {
  patientService.getOffices().then(response => {
    this.officeList = response.data;
  }),
  patientService.getEmployees().then(response => {
    this.employeeList = response.data;
  })
  }

}
</script>

<style>

</style>