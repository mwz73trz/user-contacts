<template>
  <div class="info">
    <div class="side-bar"></div>
    <div class="content">
      <img class="officeDetailEdits" src="../assets/folder.png" />
      <h1 class="editH1">Edit Office Info</h1>
      <form v-on:submit.prevent="submitForm">
        <div>
         <label for="officeName">Office Name: </label>
         <input type="text" v-model="office.officeName">
       </div>
        <div>
         <label for="phoneNumber">Phone Number: </label>
         <input type="text" v-model="office.phoneNumber">
       </div>
        <div>
         <label for="openTime">Open Time: </label>
         <input type="text" v-model="office.openTime">
       </div>
        <div>
         <label for="closeTime">Close Time: </label>
         <input type="text" v-model="office.closeTime">
       </div>
       <div>
         <label for="address">Address: </label>
         <input type="text" v-model="office.address">
       </div>
        <div>
         <label for="city">City: </label>
         <input type="text" v-model="office.city">
       </div>
        <div>
         <label for="state">State: </label>
         <input type="text" v-model="office.state">
       </div>
        <div>
         <label for="zip">Zip: </label>
         <input type="text" v-model="office.zip">
       </div>
       <div>
         <label for="serviceFee">Service Fee: </label>
         <input type="text" v-model="office.serviceFee">
       </div>
       <div class="formButtons">
       <button>Submit</button>
       <router-link :to="{ name: 'home' }" tag ="button">Return Home</router-link>
       </div>
      </form>
    </div>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: 'edit-office-info-form',
    data() {
        return {
            office: {
                officeId: this.$route.params.id,
                officeName: '',
                phoneNumber: '',
                openTime: '',
                closeTime: '',
                address: '',
                city: '',
                state: '',
                zip: '',
                serviceFee: ''
            }
        }
    },
    methods: {
        submitForm() {
            const updatedData = {
                officeId: this.office.officeId,
                officeName: this.office.officeName,
                phoneNumber: this.office.phoneNumber,
                openTime: this.office.openTime,
                closeTime: this.office.closeTime,
                address: this.office.address,
                city: this.office.city,
                state: this.office.state,
                zip: this.office.zip,
                serviceFee: this.office.serviceFee
            }
            employeeServices.updateOfficeInfo(this.office.officeId, updatedData).then(response => {
                if (response.status === 200) {
                    this.$router.push('/')
                }
            })
        }
    },
    created() {
        employeeServices.getOfficeById(this.office.officeId).then(response => {
            return this.office = response.data
        })
    }
}
</script>

<style scoped>

/* body,
html {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 100vh;
  background-color: white;
} */

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
  /* background: green; */
}
form {
  font-size: 20px;
  text-align: left;
  padding-left: 50px;
}
.officeDetailEdits{
  width: 400px;
}
.editH1{
  text-align:left
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
  text-align: center;
  border-right: solid lightgrey 1px;
}

</style>