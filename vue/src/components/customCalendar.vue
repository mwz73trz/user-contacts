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
import { DayPilot, DayPilotCalendar, DayPilotNavigator } from "@daypilot/daypilot-lite-vue";
import ApptService from '../services/ApptService'

export default {
  name: 'Calendar',
  data: function() {
    return {
      navigatorConfig: {
        showMonths: 3,
        skipMonths: 3,
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
          const modal = await DayPilot.Modal.prompt("Create a new event:", "Event 1");
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
        },
       },
      appointments:{
        appointmentId: 0,
        employeeId: "",
        patientId: "",
        date: "",
        startTime: "",
        endTime: ""
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
      start: appointment.date + "T" + appointment.startTime,
      end: appointment.date + "T" + appointment.endTime,
      text: appointment.patientId, // You may want to change this to use the actual appointment text
    }));
    this.config.events = events;
    this.calendar.update();
  });

    //   const events = [
    //     {
    //       id: 1,
    //       start: "2023-10-06T10:00:00",
    //       end: "2023-10-06T10:00:00",
    //       text: "Event 1",
    //     },
    
    //   ];

    //   // () => {
    //   //   for(let x=0; x < this.appointments.length; x++) {
    //   //   events[x].id = this.appointments[x].appointmentId;
    //   //   events[x].start = this.appointments[x].startTime;
    //   //   events[x].end = this.appointments[x].endTime;
    //   //   events[x].text = this.appointments[x].patientId;
    //   //   }
    //   // }

    //   // () => {this.appointments.forEach(appointment => {
    //   //   events.push(appointment);
    //   // });}

    // this.calendar.update({events});
     }
  },
 created() {
    ApptService.getAppointmentsByID(
      this.$route.params.employeeId).then(response => {
      this.appointments = response.data;
    });
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