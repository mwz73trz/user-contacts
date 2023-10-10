<template>
     <div>
    <div id="calendar-container">
      <h1>Appointments for Dr. {{ employee.lastName}}</h1>
      <h2>Select a available appointment</h2>
      <h2> Dr. {{employee.lastName}} is available {{schedule.startTime}} to {{ schedule.endTime}} Monday through Friday </h2>
      <div class="calendar-wrapper">
        <DayPilotCalendar id="dp" :config="config" ref="calendar" />
        <DayPilotNavigator id="calNav" :config="navigatorConfig" />
      </div>
    </div>
  </div>
</template>

<script>
import { DayPilot, DayPilotCalendar, DayPilotNavigator } from "@daypilot/daypilot-lite-vue";
import employeeServices from '../services/EmployeeServices'
import ApptService from '../services/ApptService'

export default {
  name: 'Calendar',
  props: { },
  data: function() {
    return {
      employee:{
        id: 0,
        firstName: '',
        lastName: '',
        },
      schedule: {
        id: '',
        startTime: '',
        endTime: ''
      },
      navigatorConfig: {
        showMonths: 3,
        skipMonths: 2,
        selectMode: "Week",
        startDate: DayPilot.Date.today(),
        selectionDay: DayPilot.Date.today(),
        onTimeRangeSelected: args => {
          this.config.startDate = args.day;
        },
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
              appointmentId: 0,
              employeeId: this.$route.params.employeeId,
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

 created() {
    employeeServices.getEmployeeById(this.$route.params.employeeId).then(response => {
      this.employee = response.data;
    }),
    employeeServices.getScheduleByEmployeeId(this.$route.params.employeeId).then((response) => {
      this.schedule = response.data;
     })
 },    
  methods: {
    loadEvents(){
      ApptService.getAppointmentsByEmployeeId(this.$route.params.employeeId).then((response) => {
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
    saveAppointmentToDatabase(newAppointment){
      // Call your ApptService or another method to save the appointment to the database
      ApptService.addPatientAppointment(newAppointment).then(response => {
        if (response.status === 201) {
          this.$store.commit("ADD_APPOINTMENT_PATIENT", response.data);
          }
      });
    }
    }, 


  //a lifecycle hook
  mounted() {
    this.loadEvents();
  },
}
</script>

<style>
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
.event_box {
  background-color: #d0db34;
  color: #fff;
  padding: 5px;
  border-radius: 3px;
  margin-bottom: 5px;
}
</style>