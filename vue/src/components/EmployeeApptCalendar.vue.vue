<template>
    <div>
    <div id="calendar-container">
      <h1>Your Booked Appointments</h1>
      <div class="calendar-wrapper">
        <DayPilotCalendar id="dp" :config="config" ref="calendar" />
        <DayPilotNavigator id="calNav" :config="navigatorConfig" />
      </div>
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
        showMonths: 3,
        skipMonths: 2,
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
          // Create a new appointment object to send to DB
            const newAppointment = {
              appointmentId: DayPilot.guid(),
              employeeId: this.$store.state.user.id,
              patientId: this.$store.state.user.id, 
              appointmentDateStart: args.start.toDate(),
              appointmentTimeStart: args.start.toString("HH:mm:ss"),
              appointmentDateEnd: args.end.toDate(),
              appointmentTimeEnd: args.end.toString("HH:mm:ss"),
            };

          // Save the new appointment to the database
          this.saveAppointmentToDatabase(newAppointment);

          // Add the new appointment to the DayPilot calendar
          dp.events.add({
            start: args.start,
            end: args.end,
            id: DayPilot.guid(),
            text: modal.result,
          });  
        },
      }
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
    saveAppointmentToDatabase(newAppointment){
      // Call your ApptService or another method to save the appointment to the database
      ApptService.addEmployeeAppointment(this.$route.params.employeeId, newAppointment).then(response => {
        if (response.status === 201) {
          this.$store.commit("ADD_APPOINTMENT_EMPLOYEE", response.data);
          // this.loadEvents();
          }
      });
    },
  },
  //a lifecycle hook
  mounted() {
    this.loadEvents();
  }
}
</script>

<style scooped>
#calendar-container {
  display: flex;
  flex-direction: column; 
  align-items: center; 
}

.calendar-wrapper {
  display: flex;
  flex-direction: row; /* Stack calendar and navigator vertically */
  align-items: center; /* Center calendar and navigator horizontally */
}
#dp{
  flex:1
}
/* .event_box {
  background-color: #d0db34;
  color: #fff;
  padding: 5px;
  border-radius: 3px;
  margin-bottom: 5px;
} */
</style>