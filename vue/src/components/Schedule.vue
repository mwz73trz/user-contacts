<template>
  <div>
      <p>Start Time: {{ this.schedule.startTime }}</p>
      <p>End Time: {{ this.schedule.endTime }}</p>
      EmployeeId: {{ this.schedule.employeeId }}
      Schedule Id: {{ this.schedule.scheduleId }}
      <router-link v-bind:to="{name: 'AddSchedule', params: { id: schedule.employeeId }}" tag="button">Add Schedule</router-link>
      |
      <router-link v-bind:to="{name: 'EditSchedule', params: { id: schedule.scheduleId }}" tag="button">Edit Schedule</router-link>
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