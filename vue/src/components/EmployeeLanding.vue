<template>
  <div class="employee">
    <div class="imgHeader">
      <img class='doc' src="../assets/doc.png" />
      <div class="notification"><employee-notification /></div>
    </div>
    <div class="profileNav">
      <nav class="profile-buttons">
        <router-link
        class="reviews"
          v-bind:to="{ name: 'EmployeeReview',params: { id: $route.params.officeId }}"
          tag="button" >
          View Review
        </router-link>

        <router-link
          class="view-profile"
          v-bind:to="{ name: 'employee', params: { id: employee.employeeId } }"
          tag="button" >
          View Profile
        </router-link>

        <router-link
        class="update-personal-info"
        v-bind:to="{name: 'update-personal-info', params: { id: employee.employeeId }}" 
        tag="button">
        Edit Peronsal info
        </router-link>

        <router-link
        class="update-schedule"  
        v-bind:to="{name: 'EditSchedule', params: { id: schedule.scheduleId }}" 
        tag="button">
        Edit Schedule
        </router-link>

      <router-link
        class="update-office-info"
        :to="{ name: 'edit-office-info', params: { id: officeId } }"
        tag="button">
        Update Office Info
      </router-link>
          
      </nav>

      <employee-info-form v-if="isFormVisible"></employee-info-form>

      <div class="agenda"> <h1 class="schedeuleH1">Your schedule for today: {{ currentDate }} </h1>  <EmployeeApptCalendar />   </div>

    </div>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices";
import EmployeeInfoForm from "./EmployeeInfoForm.vue";
import EmployeeApptCalendar from "./EmployeeApptCalendar.vue";
import EmployeeNotification from "./EmployeeNotification.vue";

export default {
  components: {
    EmployeeInfoForm,
    EmployeeApptCalendar,
    EmployeeNotification,
  },
  data() {
    return {
      employee: {
        employeeId: 0,
        firstName: "",
        lastName: "",
      },
      officeList: [],
      schedule: {
        startTime: '',
        endTime: ''
      }
    };
  },

  created() {
    EmployeeServices.getEmployee().then((response) => {
      this.employee = response.data;
    }),
    EmployeeServices.getOffices().then((response) => {
      this.officeList = response.data;
    });
    EmployeeServices.getScheduleByEmployeeId(this.$store.state.user.id).then(response => {
        this.schedule = response.data;
    })
  },

  computed: {
    isFormVisible() {
      return !!(
        this.employee.firstName === "" && this.employee.lastName === ""
      );
    },
  },
};
</script>

<style scoped>
body,
html {
  padding: 0;
  margin: 0;
  /* width: 100%;
  height: 100vh; */
  background-color: white;
}
.schedeuleH1{
  display: grid;
  justify-content: center;
  padding-right: 550px;
  padding-left: 220px;
}
.employee {
  background-color: white;
}
button {
  color: #fff;
  background: #0060f0;
  margin-right: 5px;
  border-radius: 4px;
  width: 200px;
  height: 100px;
  border: 0.5px solid;
}
button:hover {
  background-color: #64b5f6;
}
.doc {
  border: 1px solid #ddd;
  border-radius: 70%;
  width: 150px;

}
.imgHeader{
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;  
}
/* .profileNav{
  height: 100%;
  width: 12%;
  position: fixed;
  z-index: 1;
  left: 0%;
  top: 0;
  padding-top: 18%;
  padding-bottom: 0px;
  border-right: solid lightgrey 1px;
  background-color: darkblue;
} */
.profile-buttons {
  height: 100%;
  width: 12%;
  position: fixed;
  z-index: 2;
  left: 0%;
  top: 0;
  padding-top: 18%;
  padding-bottom: 0px;
  border-right: solid lightgrey 1px;

 }
.agenda{
  display: grid;
  grid-template-columns: 1;
  gap:20px;
  padding-bottom:200px;
  
}
.view-profile, .update-personal-info, .update-schedule, .update-office-info {
  margin-top: 20px;
}
.notification {
  display: grid;
  justify-content: right;  
}
</style>
