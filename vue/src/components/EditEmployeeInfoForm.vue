<template>
    <div class="info">
    <div class="side-bar"></div>
    <div class="content"> 
    <img class="editPersonalInfoImg" src="../assets/edit-personal-info.png" />
     <div class= editH1> <h1> Please edit the information below as needed: </h1>
        <form v-on:submit.prevent="submitForm">
            <div>
            <label for="firstName">First Name: </label>
            <input class="name" type="text" v-model="employee.firstName">
        </div>
            <div>
            <label for="lastName">Last Name: </label>
            <input class="lastName" type="text" v-model="employee.lastName">
        </div>
            <div>
            <label for="email">Email: </label>
            <input class="email" type="text" v-model="employee.email">
        </div>
            <div>
            <label for="mobilePhone"> Cell Phone Number: </label>
            <input class="phone" type="mobilePhone" v-model="employee.mobilePhone">
            </div>
          <div class="formButtons">
            <button>Submit</button>
            <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
          </div>
      </form> 
     </div>
    </div> 
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
.editPersonalInfoImg{
  width: 350px;
}
.editH1{
  text-align:left
}
form {
  color: #414042;
  border-style: none;
  font-size: 22px;
  font-family: Cambria;
  font-weight: lighter;
  padding: 5px;
  margin-left: 40px;
}
.name, .lastName, .email, .phone {
  margin-bottom: 6px;
  border: 0.5px solid;
  border-radius: 5px;
   font-size: 18px;
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
.formButtons{
  text-align: left;
  padding: 50px;
}
</style>>

