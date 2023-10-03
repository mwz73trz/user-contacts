<template>
  <div>
    <h1>Profile Information</h1>
    <p>Name: {{ employee.firstName }} {{ employee.lastName }}</p>
    <div v-for="office in filteredOffices" v-bind:key="office.officeId">
     Office Name: {{ office.officeName }}
    </div>
    <div class=home> 
      <router-link :to="{ name: 'home' }">Return Home</router-link>
    </div>
    <div class="form">
      <input type="button" value="Update Information">
       <!-- <a
      id="show-form-button"
      href="#"
      v-if="showForm === false"
      v-on:click.prevent="showForm = true"
        >Show Form</a> -->

      <form v-on:submit.prevent="updateInformation"> 
      <div class="form-element">
          <label for="name">First Name:</label>
          <input id="name" type="text" v-model="updatedInfo.firstName" />
      </div>
      <div class="form-element">
          <label for="name">Last Name:</label>
          <input id="name" type="text" v-model="updatedInfo.lastName" />
      </div>
      <div class="form-element">
          <label for="office">Office Name:</label>
          <input id="office" type="text" v-model="updatedInfo.officeName" />
      </div>
      <input type="submit" value="Update Information">
      </form> 

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
        id: 0,
        firstName: "",
        lastName: "",
      },
       updatedInfo: {
        firstName: "",
        lastName: "",
        officeName: "",
      },
    };
  },

   methods: {
    updateInformation() {
      const information = {
        id: this.employee.id,
        firstName: this.updatedInfo.firstName, 
        lastName: this.updatedInfo.lastName,  
        officeName: this.updatedInfo.officeName, 
      };
      EmployeeServices.updateInformation(this.employee.id, information)
        .then(response => {
          if (response.status === 200) {
            this.$router.push({ name: 'Employee', params: { id: this.employee.id } });
          }
        });
    },
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
  },

};
</script>

<style>
div.home{
  padding: 10px;
}
</style>