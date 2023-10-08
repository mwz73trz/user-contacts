<template>
  <div>
      <h1>Doctor: {{ employee.firstName }} {{ employee.lastName }}</h1>
      <calendar-scheduler></calendar-scheduler>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'
import CalendarScheduler from '../components/PatientApptCalendarVue.vue'

export default {
  name: 'doctor-details',
  components: {
    CalendarScheduler
  },
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