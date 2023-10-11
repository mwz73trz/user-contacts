<template>
   <div class="info">
      <div class="side-bar"></div>
      <div class="content"> 
      <img class="scheduleImg" src="../assets/schedule-update.png" />
     <div class="editH1"> <h1>Edit Schedule</h1>
     <form v-on:submit.prevent="submitForm">
       <div>
         <label for="startTime">Start Time: </label>
         <input class="start-time" type="text" v-model="schedule.startTime">
       </div>
        <div>
         <label for="endTime">End Time: </label>
         <input class="end-time" type="text" v-model="schedule.endTime">
       </div>
       <div class="formButtons">
       <button>Submit</button>
       <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
       </div>
     </form>
      </div> 
      </div>  
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

<style scoped>
.info {
  display: flex;
}

.side-bar {
  width: 20%; 
  background-color: darkblue
}
.content {
  flex: 1;
  text-align: center;
  padding-left: 50px;
 
}
.scheduleImg{
  width: 350px;
}
.editH1{
  text-align:left
}
form {
  color: #414042;
  border-style: none;
  font-size: 22px;
  font-family: Cambria;
  font-weight: lighter;
  padding: 5px;
  margin-left: 40px;
}
.start-time, .end-time {
  margin-bottom: 6px;
  border: 0.5px solid;
  border-radius: 5px;
   font-size: 18px;
}
button {
  color: #fff;
  background: #0060f0;
  margin-right: 30px;
  border-radius: 4px;
  width: 150px;
  height: 70px;
  border: 0.5px solid;
}
.formButtons{
  text-align: left;
  padding: 50px;
}

</style>