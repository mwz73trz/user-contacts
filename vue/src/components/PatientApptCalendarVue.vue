<template>
    <div id="calendar-container">
    <DayPilotCalendar id="dp" :config="config" ref="calendar" />
  </div>
</template>

<script>
import { DayPilot, DayPilotCalendar } from "@daypilot/daypilot-lite-vue";
import employeeServices from '../services/EmployeeServices'
import ApptService from '../services/ApptService'

export default {
  name: 'calendar',
  props: { },
  data: function() {
    return {
      employee:{
        id: 0,
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
        appointmentDateStart: "",
        appointmentTimeStart: "",
        appointmentDateEnd: "",
        appointmentTimeEnd: ""
      },

      //how will the calendar display
      config: {
        viewType: "Week",
        startDate: DayPilot.Date.today(),
        events: [],
        // onTimeRangeSelected: async (args) => {

        //   var form = [
        //     {name: "Enter First Name", id: "first"},
        //     {name: "Enter Last Name", id: "last"},
        //     {option: "doc ", id:"doc"}
        //   ];

        //   var data = {
        //     first: "Jane",
        //     last: "Doe",
        //     id: 1204
        //   };
        //   const modal = await DayPilot.Modal.form(form,data).then(function(args){
        //     if(!args.canceled){
        //       console.log("data", args.result)
        //     }
        //   });

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
          id: this.schedule.scheduleId,
          start: this.schedule.startTime,
          end: this.schedule.endTime,
          text: "Available",
        },
      ];
    });
    // apptServices.getAppointmentsByID(this.$route.params.employeeId).then(response => {
    //   this.appointment = response.data
    // })
    
  },

  computed: {
    calendar() {
      return this.$refs.calendar.control;
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
    loadEvents(){
      ApptService.getAppointmentsByID(this.$route.params.patientId).then((response) => {
      const events = response.data.map((appointment) => ({
        id: appointment.appointmentId,
        start: appointment.appointmentDateStart + "T" + appointment.appointmentTimeStart,
        end: appointment.appointmentDateEnd + "T" + appointment.appointmentTimeEnd,
        text: "Booked", 
        }));
      this.config.events = events;
      this.calendar.update();
      });
    }, 
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