<template>
  <div>
    <h1>Profile Information</h1>
    <p>Name: {{ employee.firstName }} {{ employee.lastName }}</p>
    <div v-for="office in filteredOffices" v-bind:key="office.officeId">
     Office Name: {{ office.officeName }}
    </div>
    <router-link :to="{ name: 'home' }">Return Home</router-link>
  </div>
</template>

<script>
import EmployeeServices from "../services/EmployeeServices";
// import patientService from "../services/patientService"
export default {
  name: "employee-details",
  components: {},
  data() {
    return {
    officeList: [],
      employee: {
        id: 0,
        firstName: "",
        lastName: "",
      },
    };
  },
  created() {
    EmployeeServices.getEmployee(
      this.$route.params.id,
      this.$route.params.id
    ).then((response) => {
      this.employee = response.data;
    }),
     EmployeeServices.getOffices().then(response => {
    this.officeList = response.data;
  })
  },
  computed: {
      filteredOffices() {
          return this.officeList.filter((item) => {
              return item.officeId === this.employee.officeId;
          })
      }
  }
};
</script>

<style>
</style>