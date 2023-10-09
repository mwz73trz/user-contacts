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
      <div class="form-element" > Primary Office Name:
          <!-- <select name="offices" v-model="employee.officeId">
            <option value="0">Offices</option>
            <option v-for="office in officeList" :key="office.officeId" :value="office.officeId">
                {{ office.officeName }}
            </option> 
          </select> -->
                    <select name="offices" v-model="employee.officeId">
            <option value="0">Offices</option>
            <option v-for="office in officeList" :key="office.officeId" v-bind:value="office.officeId">
                {{ office.officeName }}
            </option> 
          </select>
          {{ employee.officeId }}
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
            officeId: 0
            },
        officeList:[],
    };

},

created(){
   EmployeeServices.getOffices().then(response => {
       this.officeList = response.data;
   }) 
},

methods:{
    updateNewEmployeeInfo(){
        if  (this.employee.employeeId != 0) {
            this.updateEmployeeOfficeInfo();
        }else {
    EmployeeServices.createPersonalInfo(this.employee)
    .then(response => {
        if(response.status === 201){
            this.$store.commit("ADD_EMPLOYEE_INFO", response.data)
            // this.$router.push({name:'Employee', params:{id: this.employee.id}});
        }
    });
        }
    },

    updateEmployeeOfficeInfo(){
        // for (const office of)
        EmployeeServices.addOfficeIdToEmployee(this.employee).then(response => {
            if(response.status === 201){
                this.$store.commit("ADD_OFFICE_INFO", response.data)
            }
        })
    },

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
</style>