<template>
<div>
    Schedule ID: {{ this.$route.params.scheduleId }}
    Employee ID: {{ this.schedule.employeeId }}
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
        scheduleId: {
            type: Number,
            default: 0
        }
    },
    data() {
        return {
            schedule: {
                startTime: '',
                endTime: ''
            }
        }
    },
    methods: {
        submitForm() {
            const newSchedule = {
                employeeId: this.$store.state.user.id,
                startTime: this.schedule.startTime,
                endTime: this.schedule.endTime
            };
            if (this.scheduleId === 0) {
                employeeServices.addSchedule(newSchedule).then(response => {
                    if (response.status === 201) {
                        this.$router.push("/")
                    }
                })
            }else {
                newSchedule.scheduleId = this.$route.scheduleId;
                newSchedule.startTime = this.schedule.startTime;
                newSchedule.endTime = this.schedule.endTime;
                employeeServices.updateSchedule(newSchedule).then(response => {
                    if (response.status === 200) {
                        this.$router.push("/");
                    }
                })
            }
        },
            created() {
                if (this.scheduleId != 0) {
                    employeeServices.getScheduleByEmployeeId(this.$store.state.user.id).then(response => {
                        this.schedule = response.data;
                    })
                }
            }
        }
    }
</script>

<style>

</style>