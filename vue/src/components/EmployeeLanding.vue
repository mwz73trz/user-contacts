<template>
  <div class="employee">
    <div class="imgHeader">
      <img src="../assets/doc.png" class="doc" />
      <div class="notification"><employee-notification /></div>
    </div>
    <!-- <div class="schedule-container">
      <schedule />
    </div> -->
    <div>
      <nav class="nav-buttons">
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
        v-bind:to="{name: 'Update-Personal-Info', params: { id: employee.employeeId }}" 
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
        v-for="office in officeList"
        :key="office.officeId"
        :to="{ name: 'edit-office-info', params: { id: office.officeId } }"
        tag="button">
        Update Office Info
      </router-link>   
     
      </nav>

      <employee-info-form v-if="isFormVisible"></employee-info-form>
      <div class="agenda">
        <EmployeeApptCalendar />
      </div>
    </div>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices";
import EmployeeInfoForm from "./EmployeeInfoForm.vue";
// import Schedule from "../components/Schedule.vue";
import EmployeeApptCalendar from "./EmployeeApptCalendar.vue";
import EmployeeNotification from "./EmployeeNotification.vue";

export default {
  components: {
    EmployeeInfoForm,
    // Schedule,
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

<style>
body,
html {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 100vh;
  background-color: white;
}
.agenda {
  border-style: solid;
}
.employee {
  background-color: white;
  margin-left: 6px;
  /* background: rgb(103, 147, 177); */
}
.nav-buttons {
  display: flex;
  justify-content: center;
  justify-content: space-evenly;
  padding: 15px;
  color: purple;
}
button {
  /* color: solid #000; */
  color: #fff;
  background: #0060f0;
  margin-right: 5px;
  border-radius: 4px;
  width: 110px;
  height: 30px;
  border: 0.5px solid;
}
button:hover {
  background-color: #64b5f6;
}
.doc {
  /* border: 1px solid #ddd; */
  border-radius: 70%;
  margin-top: 15px;
  /* margin-left: -120px; */
  /* padding: 5px; */
  width: 150px;
}
/* h1.employee{
  display: flex;
  justify-content: center;
  justify-content: space-around;
} */
/* .schedule-container {
  justify-content: center;
}
.schedule-header {
  margin-left: 20px;
  padding-right: 6px;
  margin-bottom: 2px;
} */
.nav-buttons {
  display: flex;
  flex-direction: column;
  column-gap: 5px;
  /* justify-content: space-around; */
  padding: 15px;
  padding-left: 0px;
  padding-bottom: 2px;
  margin: 0px;
  margin-top: -6px;
}
.view-profile, .update-personal-info, .update-schedule, .update-office-info {
  margin-top: 6px;
}
nav {
  margin-top: -5px;
  max-width: -5px;
}
.notification {
  display: flex;
  justify-content: right;
}
/* .nav-buttons {
  display: inline-block;
  padding: 10px 20px;
  margin: 0 10px;
  text-decoration: none;
  color: #fff;
  background-color: #4a90e2;
  border: 1px solid #357abd;
  border-radius: 4px;
  transition: background-color 03.s ease;
}
.nav-buttons a:hoover {
  background-color: #357abd;
}
.nav-buttons {
  outline: none;
  background-color: #2b6bb0;
} */
</style>
