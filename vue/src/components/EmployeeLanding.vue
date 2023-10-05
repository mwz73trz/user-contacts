<template>
  <div class="employee">
    <div class="imgHeader">
      <img src="../assets/doc.png" class="doc">
    <h1 class="employee">Employee</h1>
    </div>
    <div>
      <nav class = "nav-buttons">
        <input type="button" value="View Review" />
        |
        <input type="button" value="Update Availability" /> 
        |
        <router-link v-bind:to="{name: 'employee', params: {id: $route.params.id}}" tag = "button"> View Profile </router-link>
      </nav>
      <employee-info-form v-if="isFormVisible"></employee-info-form>
      <div class="agenda"><p>calendar placeholder</p></div>
    </div>
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices';
import EmployeeInfoForm from './EmployeeInfoForm.vue';

export default {
  components: { 
    EmployeeInfoForm
    },

  data(){
    return{
    employee:{
      id:0,
      firstName: "",
      lastName: ""
      },
      officeList: []
    }
  },

  created() {
    EmployeeServices.getEmployee(this.$route.params.id, this.$route.params.id).then(response => {
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
.agenda {
  border-style: solid;
}
.employee{
  background: rgb(103, 147, 177);
  }
.nav-buttons{
  display: flex;
  justify-content: center;
  justify-content: space-evenly;
  padding: 15px;
  color: purple;
}
button{
  color: solid #000;
  background: rgb(158, 157, 157);
}
.doc{
  border: 1px solid #ddd;
  border-radius: 70%;
  /* padding: 5px; */
  width: 150px;
}
h1.employee{
  display: flex;
  justify-content: center;
  justify-content: space-around;
}
</style>
