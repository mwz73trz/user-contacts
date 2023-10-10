<template>
   <div class="edit-schedule">
      <h1>Edit Schedule</h1>
     <form v-on:submit.prevent="submitForm">
       <div>
         <label for="startTime">Start Time: </label>
         <input class="start-time" type="text" v-model="schedule.startTime">
       </div>
        <div>
         <label for="endTime">End Time: </label>
         <input class="end-time" type="text" v-model="schedule.endTime">
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
        scheduleId: this.schedule.scheduleId,
        employeeId: this.schedule.employeeId,
        startTime: this.schedule.startTime,
        endTime: this.schedule.endTime
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
form {
  color: #414042;
  border-style: none;
  font-family: Cambria;
  font-weight: lighter;
  font-size: medium;
  padding: 5px;
  margin-left: 40px;
}

.start-time {
  margin-bottom: 6px;
  border: 0.5px solid;
  border-radius: 5px;
}

.end-time {
  border: 0.5px solid;
  border-radius: 5px;
  margin-bottom: 6px;
}
h1 {
   font-family: Cambria;
   font-weight:bold;
   margin-left: 40px;
}

.edit-schedule {
  /* display: flex; */
  float: center;
  justify-content: center;
}

</style>