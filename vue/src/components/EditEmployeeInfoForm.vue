<template>
    <div>
    <h1> Please edit the information below as needed: </h1>
    <form v-on:submit.prevent="submitForm">
        <div>
         <label for="firstName">First Name: </label>
         <input type="text" v-model="employee.firstName">
       </div>
        <div>
         <label for="lastName">Last Name: </label>
         <input type="text" v-model="employee.lastName">
       </div>
        <div>
         <label for="email">Email: </label>
         <input type="text" v-model="employee.email">
       </div>
        <div>
        <label for="mobilePhone"> Cell Phone Number: </label>
        <input type="mobilePhone" v-model="employee.mobilePhone">
        </div>
       <button>Submit</button>
       <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
      </form>  
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices'

export default {
    name: 'edit-employee-info',
    data(){
        return{
            employee: {
                employeeId: this.$route.params.id,
                firstName: "",
                lastName: "",
                email: "",
                mobilePhone: "",
            }
        }
    },

    methods:{
        submitForm(){
            const updateInfo ={
                employeeId: this.employee.employeeId,
                firstName: this.employee.firstName,
                lastName: this.employee.lastName,
                email: this.employee.email,
                mobilePhone: this.employee.mobilePhone,
            }
        EmployeeServices.updateEmployeeInfo(updateInfo).then(response =>{
            if(response.status === 200){
                this.$router.push('/')
            }
        })
        }
    },

created(){
    EmployeeServices.getEmployee().then(response => {
        return this.employee = response.data;
    })
}

}
</script>

<style>

</style>