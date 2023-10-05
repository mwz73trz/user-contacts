<template>
  <div>
      <h1>Edit Schedule</h1>
      <schedule-form v-bind:scheduleId="parseInt($route.params.scheduleId)" />
  </div>
</template>

<script>
import ScheduleForm from '../components/ScheduleForm.vue'

export default {
    components: {
        ScheduleForm
    }
}
</script>

<style>

</style>