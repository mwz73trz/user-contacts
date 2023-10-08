<template>
  <div>
  <div id="calendar-container">
      <h1> Title </h1>
      <DayPilotNavigator id="nav" :config="navigatorConfig" />
      <DayPilotCalendar id="dp" :config="config" ref="calendar" />
    </div>
  </div>
</template>

<script>
import { DayPilot, DayPilotCalendar, DayPilotNavigator } from "@daypilot/daypilot-lite-vue"; // addback DayPilotNavigator
import ApptService from '../services/ApptService'

export default {
  name: 'Calendar',
  data: function() {
    return {
      newAppointment:{
        appointmentId: 0,
        employeeId: "",
        patientId: "",
        appointmentDateStart: "",
        appointmentTimeStart: "",
        appointmentDateEnd: "",
        appointmentTimeEnd: ""
      },
      navigatorConfig: {
        showMonths: 1,
        skipMonths: 1,
        selectMode: "Week",
        startDate: DayPilot.Date.today(),
        selectionDay: DayPilot.Date.today(),
        onTimeRangeSelected: args => {
          this.config.startDate = args.day;
        }
      },
      config: {
        viewType: "WorkWeek",
        startDate: DayPilot.Date.today(),
        events: [],           
        onTimeRangeSelected: async (args) => {
          const modal = await DayPilot.Modal.prompt("Create a new event:", "Blocked");
          const dp = args.control;
          dp.clearSelection();
          if (modal.canceled) {
            return;
          }
          dp.events.add({            
            start: args.start,
            end: args.end,
            id: DayPilot.guid(),
            text: modal.result
          });
        const startDateTime = args.start.split("T");
        const endDateTime = args.start.split("T");
        this.newAppointment.appointmentDateStart = startDateTime[0];
        this.newAppointment.appointmentTimeStart = startDateTime[1];
        this.newAppointment.appointmentDateEnd = endDateTime[0];
        this.newAppointment.appointmentTimeEnd = endDateTime[1];
                
        },
       },
      
    }
  },
  components: {
   DayPilotCalendar,
   DayPilotNavigator
  },
  computed: {
    calendar() {
      return this.$refs.calendar.control;
    }
  },
  methods: {
    loadEvents(){
    ApptService.getAppointmentsByID(this.$route.params.employeeId).then((response) => {
    const events = response.data.map((appointment) => ({
      id: appointment.appointmentId,
      start: appointment.appointmentDateStart + "T" + appointment.appointmentTimeStart,
      end: appointment.appointmentDateEnd + "T" + appointment.appointmentTimeEnd,
      text: appointment.patientId, 
      }));
    this.config.events = events;
    this.calendar.update();
    });
   }, 
   addNewAppointment(){
     ApptService.addEmployeeAppointment(this.$route.params.employeeId, this.newAppointment)
      .then(response => {
      if(response.status === 201){
      this.$store.commit("ADD_APPOINTMENT_EMPLOYEE", response.data)
      }
    })
   }
  },

  //a lifecycle hook
  mounted() {
    this.loadEvents();
  }
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