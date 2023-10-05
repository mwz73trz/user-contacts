<template>
  <div>
      <p>Start Time: {{ this.schedule.startTime }}</p>
      <p>End Time: {{ this.schedule.endTime }}</p>
      <router-link tag="button">Add Schedule</router-link>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: "schedule",
    data() {
        return {
            schedule: {
                startTime: '',
                endTime: ''
            }
        }
    },
    created() {
        employeeServices.getScheduleByEmployeeId(this.$store.state.user.id).then(response => {
            this.schedule = response.data;
        })
    }
}
</script>

<style>

</style>