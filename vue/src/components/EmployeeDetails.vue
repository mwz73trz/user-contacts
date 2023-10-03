<template>
  <div>
      <h1>Employee Information</h1>
      <p>{{employee.firstName}}</p>
  </div>
</template>

<script>
import EmployeeServices from '../services/EmployeeServices'
export default {
    name: "employee-details",
    components: {},
    data() {
        return {
            employee: {
                id: 0,
                firstName: '',
                lastName: ''
            },
        };
    },
    created(){
        EmployeeServices
        .getEmployee(this.$routeparams.id, this.$route.params.id)
        .then(response => {
            this.employee = response.data;
        })
    }
}
</script>

<style>

</style>