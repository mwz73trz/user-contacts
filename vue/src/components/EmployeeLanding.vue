<template>
  <div class="employee">
    <div class="imgHeader">
      <img src="../assets/doc.png" class="doc">
    <h1 class="employee">Employee</h1>
    <div class="notification"> <employee-notification/> </div>
    </div>
    <div class="schedule-container">
      <h1>Schedule</h1>
      <schedule />
      </div>
    <div>
      <nav class = "nav-buttons">
        <router-link v-bind:to="{name: 'EmployeeReview', params: {id: $route.params.officeId}}" tag = "button"> View Review </router-link>
         
        <router-link class="view-profile" v-bind:to="{name: 'employee', params: {id: employee.employeeId}}" tag = "button"> View Profile </router-link> 
      </nav>
      
      <employee-info-form v-if="isFormVisible"></employee-info-form>
      <div class="agenda"><p>calendar placeholder</p>
        <EmployeeApptCalendar/>
      </div>
    </div>
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices';
import EmployeeInfoForm from './EmployeeInfoForm.vue';
import Schedule from '../components/Schedule.vue'
import EmployeeApptCalendar from './EmployeeApptCalendar.vue';
import EmployeeNotification from './EmployeeNotification.vue';

export default {
  components: { 
    EmployeeInfoForm,
    Schedule,
    EmployeeApptCalendar,
    EmployeeNotification
    },
  data(){
    return{
    employee:{
      employeeId:0,
      firstName: "",
      lastName: ""
      },
      officeList: []
    }
  },

  created() {
    EmployeeServices.getEmployee().then(response => {
      this.employee = response.data;
    }),
    EmployeeServices.getOffices().then(response => {
       this.officeList = response.data;
   }) 
  },

  computed: {
    isFormVisible() {
      return !!(this.employee.firstName === "" && this.employee.lastName === "" );
    }
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
.employee{
  background-color: white;
  /* background: rgb(103, 147, 177); */
  }
.nav-buttons{
  display: flex;
  justify-content: center;
  justify-content: space-evenly;
  padding: 15px;
  color: purple;
}
button{
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
  background-color: #64B5F6;
}
.doc{
  /* border: 1px solid #ddd; */
  border-radius: 70%;
  margin-top: 15px;
  margin-left: -120px;
  /* padding: 5px; */
  width: 100px;
}
h1.employee{
  display: flex;
  justify-content: center;
  justify-content: space-around;
}
.schedule-container {
  justify-content: center;
}
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
.view-profile {
  margin-top: 6px;
}
nav{
  margin-top: -5px;
  max-width: -5px;
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
