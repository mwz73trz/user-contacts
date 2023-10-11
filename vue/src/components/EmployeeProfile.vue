<template>
  <div class ="info">
    <div class="side-bar"></div>
    <div class="content">
    <img class="personalInfoImg" src="../assets/personal-information.png" />
    <h1 class="profileH1">Profile Information</h1>
    <div class="mainInfo"> 
      <p>Name: {{ employee.firstName }} {{ employee.lastName }}</p>
      <p> Email: {{employee.email}} </p>
      <p> Cell Phone: {{employee.mobilePhone}} </p>
    </div>
    <div class="schedule"> 
      <p>Your current schedule is: {{schedule.startTime}} - {{schedule.endTime}} </p>
    </div>
    <div class="officeInfo" v-for="office in officeList" v-bind:key="office.officeId">
      <p> Office Name: {{ office.officeName }} </p>
      <p> Office Address: {{ office.address}} {{office.city}} {{office.state}}  </p>
      <p> Office Phone Number: {{office.phoneNumber}}  </p> 
      <p> Service Fee: ${{ office.serviceFee }}</p>
    <div class='profileButtons'> 
       
    <router-link
      class="update-office-info"
      :to="{ name: 'edit-office-info', params: { id: office.officeId } }"
      tag="button">
      Edit Office Info
    </router-link>

    <router-link 
      v-if="schedule.startTime" 
      v-bind:to="{name: 'EditSchedule', params: { id: schedule.scheduleId }}" 
      tag="button">
      Edit Schedule
    </router-link>
    <router-link  
      v-bind:to="{name: 'update-personal-info', params: { id: employee.employeeId }}" 
      tag="button">
      Edit Peronsal info
    </router-link>
      <router-link 
      :to="{ name: 'home' }" 
      tag ="button">
      Return Home
      </router-link>
    </div>
    </div>
    </div>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices";

export default {
  name: "employee-details",
  data() {
    return {
    officeList: [],
      employee: {
        employeeId: 0,
        firstName: "",
        lastName: "",
        email: "",
        mobilePhone: "",
      },
      schedule: {
        startTime: '',
        endTime: ''
      },
    };
  },
  created() {
    EmployeeServices.getEmployee().then((response) => {
      this.employee = response.data;
    }),
   EmployeeServices.getOfficesByEmployeeId(this.$store.state.user.id).then(response => {
    this.officeList = response.data;
  }),
    EmployeeServices.getScheduleByEmployeeId(this.$store.state.user.id).then(response => {
      this.schedule = response.data;
    })
  },

};
</script>

<style scoped>
.info {
  display: flex;
}
.side-bar {
  width: 20%; 
  background-color: darkblue
}
.content {
  flex: 1;
  text-align: center;
  padding-left: 50px;
}
.personalInfoImg{
  width: 350px;
}
.profileH1{
  text-align:left;
}
.mainInfo, .schedule, .officeInfo{
  font-size: 22px;
  text-align: left;
  color: #414042;
  border-style: none;
  font-family: Cambria;
  margin-left: 40px;
}
button {
  color: #fff;
  background: #0060f0;
  margin-right: 30px;
  border-radius: 4px;
  width: 150px;
  height: 70px;
  border: 0.5px solid;
}
.homeBtn{
  padding-top: 30px;
}
.profileButtons{
  text-align: center;
}
button:hover {
  background-color: #64b5f6;
}
</style>