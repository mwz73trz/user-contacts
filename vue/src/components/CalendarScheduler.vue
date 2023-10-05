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
      schedule: {
        id: '',
        startTime: '',
        endTime: ''
      },

      //how will the calendar display
      config: {
      viewType: "Week",
      startDate: DayPilot.Date.today(),
    //    onTimeRangeSelected: (args) => {
    //     DayPilot.Modal.prompt('Create a new event:', 'Event 1').then((modal) => {
    //       var dp = args.control;
    //       dp.clearSelection();
    //       if (modal.canceled) {
    //         return;
    //       }
    //       dp.events.add({
    //         start: args.start,
    //         end: args.end,
    //         id: DayPilot.guid(),
    //         text: modal.result,
    //       });
    //     });
    //   },
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
    employeeServices.getScheduleByEmployeeId(this.$route.params.employeeId).then((response) => {
      this.schedule = response.data;
    });
  },

  computed: {
    calendar() {
      return this.$refs.scheduler.control;
  },
    calendarEvents(){
     return [
        {
          id: this.schedule.scheduleId,
          start: this.schedule.startTime,
          end: this.schedule.endTime,
          text: "Available",
        },
      ];
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