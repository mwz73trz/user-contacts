<template>
  <div class="form">
      <div class="employeeForm"> 
          <h2 class="updateInfo">Please update the information below: </h2>
      <form v-on:submit="updateNewEmployeeInfo" > 
      <div class="form-element">
          <label for="name">First Name:</label>
          <input id="firstName" type="text" v-model="employee.firstName" />
      </div>
      <div class="form-element">
          <label for="name">Last Name:</label>
          <input id="lastName" type="text" v-model="employee.lastName" />
      </div>
        <div class="form-element">
          <label for="email"> Email:</label>
          <input id="email" type="text" v-model="employee.email" />
      </div>
        <div class="form-element">
          <label for="mobilePhone"> Cell Phone:</label>
          <input id="mobilePhone" type="text" v-model="employee.mobilePhone" />
      </div>
          <div class="form-element">
            <label for="startTime">Start Time</label>
              <input type="text" v-model="newSchedule.startTime">
      </div>
        <div class="form-element">
              <label for="endTime">End Time</label>
              <input type="text" v-model="newSchedule.endTime">
      </div> 
      <div class="form-element" > Primary Office Name:
            <select name="offices" v-model="employee.officeId">
            <option value="0">Offices</option>
            <option v-for="office in officeList" :key="office.officeId" v-bind:value="office.officeId">
                {{ office.officeName }}
            </option> 
          </select>
      </div>
      <div class="actions">
        <button type="submit"> Update </button>
      </div>
      </form>
     </div>
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices'

export default {
name: "employee-form",
data(){
    return{
        employee:{
            id: 0,
            firstName: "",
            lastName: "",
            email: "",
            mobilePhone: "",
            officeId: 0
            },
        officeList:[],
        newSchedule: {
            employeeId: this.$store.state.user.id,
            startTime: '',
            endTime: ''
        }
    };

},

created(){
   EmployeeServices.getOffices().then(response => {
       this.officeList = response.data;
   }) 
},

methods:{
    updateNewEmployeeInfo(){
        if  (this.employee.id != 0) {
            this.updateEmployeeOfficeInfo();
        }else {
    EmployeeServices.createPersonalInfo(this.employee)
    .then(response => {
        if(response.status === 201){
            this.$store.commit("ADD_EMPLOYEE_INFO", response.data);
            this.updateEmployeeOfficeInfo();
            this.addSchedule();
        }
    });
        }
    },

    updateEmployeeOfficeInfo(){
        EmployeeServices.addOfficeIdToEmployee(this.employee).then(response => {
            if(response.status === 201){
                this.$store.commit("ADD_OFFICE_INFO", response.data)
            }
        })
    },

    addSchedule() {
        EmployeeServices.addSchedule(this.newSchedule).then(response => {
            if (response.status === 201) {
                // this.$router.push("/")
            }
        })
    }   
  }
};
</script>

<style>
h2.updateInfo{
   display: flex;
   justify-content: center;
   color: rgb(131, 11, 125);
}
.form-element{
    display: flex;
    justify-content: center;
    padding: 10px;
}
.actions{
    display: flex;
    justify-content: center;
    padding: 10px;
}
form {
    border-style: none;
    color: #414042;
}
</style>