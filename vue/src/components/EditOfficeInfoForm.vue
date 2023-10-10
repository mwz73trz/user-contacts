<template>
  <div>
      <h1>Edit Office Info-------------------------</h1>
  </div>
</template>

<script>
import employeeServices from '../services/EmployeeServices'

export default {
    name: 'edit-office-info-form',
    data() {
        return {
            office: {
                officeId: 0,
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
    created() {
        employeeServices.getOfficeById(this.office.officeId).then(response => {
            return this.office = response.data
        })
    }
}
</script>

<style>

</style>