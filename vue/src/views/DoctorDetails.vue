<template>
  <div>
      <doctor-info />
      <div class="appointmentCalendar"> <calendar-scheduler></calendar-scheduler> </div>
  </div>
</template>

<script>
import DoctorInfo from '../components/DoctorInfo.vue'
import CalendarScheduler from '../components/PatientApptCalendarVue.vue'

export default {
  name: 'doctor-details',
  components: {
    DoctorInfo,
    CalendarScheduler
  }
}
</script>

<style>
.appointmentCalendar{
  border-style: solid;
}

</style>