<template>
    <DayPilotCalendar id="dp" :config="config" ref="scheduler" />
</template>

<script>
import {DayPilot, DayPilotCalendar} from '@daypilot/daypilot-lite-vue'
// import {onMounted, ref} from "vue";
import employeeServices from '../services/EmployeeServices'

export default {
  name: 'Scheduler',
  props: {
  },
  data: function() {
    return {
      employee:{
        id: 0,
        startTime: "",
        endTime: "",
        },
      schedule: [],

      //how will the calendar display
      config: {
      viewType: "Week",
      startDate: DayPilot.Date.today(),
      },
    }
  },
  components: {
    DayPilotCalendar
  },

 created() {
    employeeServices.getEmployeeById(this.$route.params.employeeId).then(response => {
      this.employee = response.data;
    }),
    employeeServices.getScheduleByEmployeeId(this.$store.state.user.id).then((response) => {
      this.schedule = response.data;
    });
  },

  computed: {
    calendar() {
      return this.$refs.scheduler.control;
  },
    calendarEvents(){
      return this.schedule.map((event) => ({
        id: event.schedule.id,
        start: event.startTime,
        end: event.endTime,
        text: "Available"
      }))
    }
  },

  methods: {
    loadAppointmentEvents(){
    this.calendar.update({events: this.calendarEvents}); //returns the "events" and redraws the UI
    }
  },

  mounted() {
    this.loadAppointmentEvents();
  },
  
}


</script>

<style>

</style>