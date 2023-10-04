<template>
<div>
  <h1>Office Details</h1>
  <h1></h1>
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
            this.office = response;
        })
    }
}
</script>

<style>

</style>