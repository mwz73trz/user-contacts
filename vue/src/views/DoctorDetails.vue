<template>
  <div>
      <h1>Doctor: {{ employee.firstName }} {{ employee.lastName }}</h1>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
  name: 'doctor-details',
  data() {
    return {
      employee: {
        firstName: '',
        lastName: ''
      }
    }
  },
  created() {
    employeeServices.getEmployeeById(this.$route.params.employeeId).then(response => {
      this.employee = response.data;
    })
  }
}
</script>

<style>

</style>