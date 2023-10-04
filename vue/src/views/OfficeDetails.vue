<template>
<div>
  <h1>{{ office.officeName }}</h1>
  <p>Our office opens at {{ office.openTime }} and closes at {{ office.closeTime }}</p>
  <p>For appointments call {{ office.phoneNumber }}</p>
  <p>If this is a medical emergency call 911</p>
  <p>Mailing address is: {{ office.address }}, {{ office.city }}, {{ office.state }} {{ office.zip }}</p>
  <p>Office fee: ${{ office.serviceFee }}</p>
  <div>
      <h4>Select a Doctor:</h4>
      <ul v-for="employee in office.employees" v-bind:key="employee.employeeId">
          <li><router-link :to="{ name: 'doctor-details', params: { employeeId: employee.employeeId } }">{{ employee.lastName }}, {{ employee.firstName }}</router-link></li>
      </ul>
  </div>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: 'office-details',
    data() {
        return {
            office: {
                officeName: '',
                openTime: '',
                closeTime: '',
                address: '',
                city: '',
                state: '',
                zip: '',
                serviceFee: '',
                phoneNumber: '',
                employees: []
            }
        }
    },
    created() {
        employeeServices.getOfficeById(this.$route.params.officeId).then(response => {
            this.office = response.data;
        })
    }
}
</script>

<style>

</style>