<template>
<div>
  <h1>Add Schedule</h1>
  <form v-on:submit.prevent="submitForm">
      <div>
      <label for="startTime">Start Time:</label>
      <input type="text" v-model="schedule.startTime">
      </div>
        <div>
      <label for="endTime">Start Time:</label>
      <input type="text" v-model="schedule.endTime">
        </div>
        <div>
            <button>Submit</button>
        </div>
  </form>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: "schedule-form",
    props: {
        sceduleId: {
            type: Number,
            default: 0
        }
    },
    data() {
        return {
            schedule: {
                employeeId: '',
                startTime: '',
                endTime: ''
            }
        }
    },
    methods: {
        submitForm() {
            const newSchedule = {
                employeeId: Number(this.$store.state.user.id),
                startTime: this.schedule.startTime,
                endTime: this.schedule.endTime
            };
            if (this.scheduleId === 0) {
                employeeServices.addSchedule(newSchedule).then(response => {
                    if (response.status === 201) {
                        this.router.push("/")
                    }
                })
            }else {
                newSchedule.sceduleId = this.scheduleId;
                newSchedule.startTime = this.schedule.startTime;
                newSchedule.endTime = this.schedule.endTime;
                employeeServices.updateSchedule(newSchedule).then(response => {
                    if (response.status === 200) {
                        this.router.push(`/schedules/${newSchedule.scheduleId}`);
                    }
                })
            }
        },
            created() {
                if (this.scheduleId != 0) {
                    employeeServices.getScheduleByEmployeeId(this.employeeId).then(response => {
                        this.schedule = response.data;
                    })
                }
            }
        }
    }
</script>

<style>

</style>