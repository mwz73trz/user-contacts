<template>
<div>
  <div><h1>Patient</h1></div>
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
    <select v-model="selectedDoctor">
      <option v-for="employee in employeeList" v-bind:key="employee.employeeId">
        {{ employee.lastName }}, {{ employee.firstName }}
      </option>
    </select>
  </div>
  </div>
</template>
<script>
import patientService from '../services/patientService';

export default {
  data(){
    return{
      officeList: [],
      employeeList: [],
      selectedDoctor: '',
      id: 0
    }
  },
  methods: {
    officesRoute(e) {
      this.$router.push("/offices/" + e.target.value)
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