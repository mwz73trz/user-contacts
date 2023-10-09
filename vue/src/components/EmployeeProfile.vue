<template>
  <div class ="Info">
    <h1>Profile Information</h1>
    <p>Name: {{ employee.firstName }} {{ employee.lastName }}</p>
    <div class="officeName" v-for="office in officeList" v-bind:key="office.officeId">
     Office Name: {{ office.officeName }}
    </div>
    <div class=home> 
      <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
    </div>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices";

export default {
  name: "employee-details",
  components: {},
  data() {
    return {
    officeList: [],
      employee: {
        employeeId: 0,
        firstName: "",
        lastName: "",
      }
    };
  },
  created() {
    EmployeeServices.getEmployee(
    ).then((response) => {
      this.employee = response.data;
    }),
  //    EmployeeServices.getOffices().then(response => {
  //   this.officeList = response.data;
  // })
   EmployeeServices.getOfficesByEmployeeId(this.$store.state.user.id).then(response => {
    this.officeList = response.data;
  })
  },
  // computed: {
  //     filteredOffices() {
  //         return this.officeList.filter((item) => {
  //             return item.officeId === this.employee.officeId;
  //         })
  //     }
  // },

};
</script>

<style>
div.home{
  padding: 10px;
}

.personal-info{
   display: flex;
  justify-content: center;
}
p, .officeName{
  font-family: "Times New Roman", Times, serif;
  font-size: 20px;
}
button{
  color: solid #000;
  /* background: rgb(158, 157, 157); */
}
form{
  border-style: solid;
  padding: 50px;
}
body{
   background: rgb(103, 147, 177);
}
</style>