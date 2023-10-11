<template>
  <div class="info">
    <div class="side-bar"></div>
    <div class="content">
      <img class="officeDetailEdits" src="../assets/folder.png" />
      <h1 class="editH1">Edit Office Info</h1>
      <form v-on:submit.prevent="submitForm">
        <div>
         <label for="officeName">Office Name: </label>
         <input class="name" type="text" v-model="office.officeName">
       </div>
        <div>
         <label for="phoneNumber">Phone Number: </label>
         <input class="phone" type="text" v-model="office.phoneNumber">
       </div>
        <div>
         <label for="openTime">Open Time: </label>
         <input class="time" type="text" v-model="office.openTime">
       </div>
        <div>
         <label for="closeTime">Close Time: </label>
         <input class="closeTime" type="text" v-model="office.closeTime">
       </div>
       <div>
         <label for="address">Address: </label>
         <input class="address" type="text" v-model="office.address">
       </div>
        <div>
         <label for="city">City: </label>
         <input class="city" type="text" v-model="office.city">
       </div>
        <div>
         <label for="state">State: </label>
         <input class="state" type="text" v-model="office.state">
       </div>
        <div>
         <label for="zip">Zip: </label>
         <input class="zip" type="text" v-model="office.zip">
       </div>
       <div>
         <label for="serviceFee">Service Fee: </label>
         <input class="fee" type="text" v-model="office.serviceFee">
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
.officeDetailEdits{
  width: 300px;
}
.editH1{
  text-align:left;
  font-size: 40px;
}
form {
  color: #414042;
  border-style: none;
  font-size: 22px;
  font-family: Cambria;
  font-weight: lighter;
  padding: 5px;
  margin-left: -400px;
}
.name, .phone, .time, .closeTime, .address, .city, .state, .zip, .fee{
  margin-bottom: 6px;
  border: 0.5px solid;
  border-radius: 5px;
  font-size: 15px;
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
  padding:50px;
  margin-left: 330px;
}
</style>