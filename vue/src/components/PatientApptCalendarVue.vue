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
      appointmentList: [],
      appointment:{
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
        },
      },
      config: {
        viewType: "WorkWeek",
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
    })
    // employeeServices.getScheduleByEmployeeId(this.$route.params.employeeId).then((response) => {
    //   this.schedule = response.data;
    //   if(this.schedule.startTime && this.schedule.endTime) {
    //     this.config.events.push({
    //       id: 0,
    //       start:DayPilot.Date.today().addTime(this.schedule.startTime),
    //       end: DayPilot.Date.today().addTime(this.schedule.endTime),
    //       text: "Available"
    //     })
    //   }
    // })
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
    //   employeeServices.getScheduleByEmployeeId(this.$route.params.employeeId).then((response) => {
    //   this.schedule = response.data;
    //   if(this.schedule.startTime && this.schedule.endTime) {
    //     this.config.events.push({
    //       id: 0,
    //       start:DayPilot.Date.today().addTime(this.schedule.startTime),
    //       end: DayPilot.Date.today().addTime(this.schedule.endTime),
    //       text: "Available"
    //     })
    //   }
    // })
    }, 
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