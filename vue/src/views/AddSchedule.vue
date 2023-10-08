<template>
  <div>
      <form v-on:submit.prevent="addSchedule">
          <div>
              <label for="startTime">Start Time</label>
              <input type="text" v-model="newSchedule.startTime">
          </div>
          <div>
              <label for="endTime">End Time</label>
              <input type="text" v-model="newSchedule.endTime">
          </div>
          <div>
              <button>Submit</button>
          </div>
      </form>
  </div>
</template>

<script>
import employeeServicees from '../services/EmployeeServices'

export default {
    name: 'add-schedule',
    data() {
        return {
        newSchedule: {
            employeeId: this.$store.state.user.id,
            startTime: '',
            endTime: ''
        }
        }
    },
    methods: {
        addSchedule() {
            employeeServicees.addSchedule(this.newSchedule).then(response => {
                if (response.status === 201) {
                    this.$router.push("/")
                }
            })
        }
    }
}
</script>

<style>

</style>