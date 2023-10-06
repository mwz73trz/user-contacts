<template>
    <div id="calendar-container">
    <DayPilotCalendar id="dp" :config="config" ref="scheduler" />
  </div>
</template>

<script>
import { DayPilot, DayPilotCalendar } from "@daypilot/daypilot-lite-vue";
import employeeServices from '../services/EmployeeServices'
import apptServices from '../services/ApptService'

export default {
  name: 'Scheduler',
  props: { },
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
      appointment:{
        appointmentId: 0,
        employeeId: "",
        patientId: "",
        appointmentDate: "", 
        appointmentStartTime: "",
        appointmentEndTime: "",
      },

      //how will the calendar display
      config: {
        viewType: "Week",
        startDate: DayPilot.Date.today(),
        events: [],
        
      //  onTimeRangeSelected: (args) => {
      //   DayPilot.Modal.prompt('Create a new event:', 'Event 1').then((modal) => {
      //     var dp = args.control;
      //     dp.clearSelection();
      //     if (modal.canceled) {
      //       return;
      //     }
      //     dp.events.add({
      //       start: args.start,
      //       end: args.end,
      //       id: DayPilot.guid(),
      //       text: modal.result,
      //     });
      //   });
      // },
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
      this.config.events = [
        {
          appointmentId: this.appointment.appointmentId,
          employeeId: this.appointment.employeeId,
          patientId: this.appointment.patientId,
          Date: this.appointment.appointmentDate,
          StartTime: this.appointment.appointmentStartTime,
          EndTime: this.appointment.appointmentEndTime,
          text: "Available",
        },
      ];
    });
    apptServices.getAppointmentsByID(this.$route.params.employeeId).then(response => {
      this.appointment = response.data;
    });
    
  },

  computed: {
    calendar() {
      return this.$refs.scheduler.control;
  }
    // calendarEvents(){
    //  return [
    //     {
    //       id: this.schedule.scheduleId,
    //       start: this.schedule.startTime,
    //       end: this.schedule.endTime,
    //       text: "Available",
    //     },
    //   ];
    // }
  },
  methods: {
    //what comes back from the database
    loadAppointmentEvents(){
    this.calendar.update({events: this.config.events}); //returns the "events" and redraws the UI
    }
  },

  //a lifecycle hook
  mounted() {
    this.loadAppointmentEvents();
  },
}
</script>

<style>
#calendar-container {
  margin: 20px;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f5f5f5;
}

.event_box {
  background-color: #d0db34;
  color: #fff;
  padding: 5px;
  border-radius: 3px;
  margin-bottom: 5px;
}
</style>