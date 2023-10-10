<template>
    <div>
    <div id="calendar-container">
      <h1>Your schedule for today: {{ currentDate }} </h1>
      <div class="calendar-wrapper">
        <DayPilotCalendar id="dp" :config="config" ref="calendar" />
        <DayPilotNavigator id="calNav" :config="navigatorConfig" />
      </div>
    </div>
  </div>
</template>

<script>
import { DayPilot, DayPilotCalendar, DayPilotNavigator } from "@daypilot/daypilot-lite-vue";
import ApptService from '../services/ApptService'
import PatientService from '../services/PatientService'

export default {
  name: 'Calendar',
  data: function() {
    return {
      currentDate: DayPilot.Date.today().toString("MM-dd-yyyy"),
      newAppointment:{
        appointmentId: 0,
        employeeId: "",
        patientId: "",
        appointmentDateStart: "",
        appointmentTimeStart: "",
        appointmentDateEnd: "",
        appointmentTimeEnd: ""
      },
      patientList: [],
      // allAppointments: [],

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
        viewType: "day",
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
              appointmentId: 0,
              employeeId: this.$store.state.user.id,
              patientId: 1, 
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

   created(){
         PatientService.getAllPatients().then((response) => {
         return this.patientList = response.data; 
      }),
        ApptService.getAllAppointments().then((response)=> {
          return this.allAppointments = response.data;
        })
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
        text: this.findPatientId(appointment), 
        }));        
      this.config.events = events;
      this.calendar.update();
      });
    }, 

    findPatientId(appointment) {
      const patient = this.patientList.find(patient => patient.patientId === appointment.patientId);
       return patient ? patient.firstName + " " + patient.lastName : 'Unknown';
    },
    saveAppointmentToDatabase(newAppointment){
      ApptService.addEmployeeAppointment(newAppointment).then(response => {
        if (response.status === 201) {
          this.$store.commit("ADD_APPOINTMENT_EMPLOYEE", response.data);
          this.loadEvents();
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
  flex-direction: row; 
  align-items: center; 
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