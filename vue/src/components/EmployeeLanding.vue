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

      <!-- <router-link
        class="update-office-info"
        :to="{ name: 'edit-office-info', params: { id: office.officeId } }"
        tag="button">
        Update Office Info
      </router-link> -->
          
      </nav>
    </div>
    <div class="content">
      <employee-info-form v-if="isFormVisible"></employee-info-form>
      <div class="agenda"> <EmployeeApptCalendar />   </div>
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
  display: grid;
  border: 1px solid #ddd;
  border-radius: 70%;
  width: 150px;
  z-index: 3;
}
.imgHeader{
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}

.profile-buttons {
   display: flex;
  flex-direction: column;
  position: absolute;
  column-gap: 5px;
  padding: 15px;
  padding-left: 0px;
  padding-bottom: 2px;
  margin: 0px;
  margin-top: -6px;
}
.content{
  display: grid;  
  grid-template-columns: 2;
  z-index: 2;
  gap:20px;
  margin-left: 200px;
}
.agenda{
  display: grid;  
  grid-template-columns: 2;
  z-index: 2;
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
