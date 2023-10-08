<template>
   <div>
      <h1>Edit Schedule</h1>
     <form v-on:submit="submitForm">
       <div>
         <label for="startTime">Start Time</label>
         <input type="text" v-model="schedule.startTime">
       </div>
        <div>
         <label for="endTime">endTime Time</label>
         <input type="text" v-model="schedule.endTime">
       </div>
       <button>Submit</button>
     </form>   
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: 'edit-schedule-form',
    data() {
    return {
      schedule: {
        scheduleId: 0,
        employeeId: this.$store.state.user.id,
        startTime: '',
        endTime: ''
      }
    }
  },
  methods: {
    submitForm() {
      const updatedForm = {
        newId: this.schedule.scheduleId,
        newUser: this.schedule.employeeId,
        newStart: this.schedule.startTime,
        newEnd: this.schedule.endTime
      }
      employeeServices.updateSchedule(this.schedule.scheduleId, updatedForm).then(response => {
        if (response.status === 200) {
          this.$router.push("/")
        }
      })
    }
  },
  created() {
    employeeServices.getScheduleByEmployeeId(this.schedule.employeeId).then(response => {
      this.schedule = response.data;
    })
  }

}
</script>

<style>

</style>