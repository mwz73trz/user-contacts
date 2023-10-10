<template>
 <div> 
    <h2 class="alertHeader"> Please see your newest appointments below: </h2>
    <ul class="alertList">
      <li v-for="appointment in recentAppointments" :key="appointment.id">
        {{appointment.appointmentDateStart}} Time: {{ appointment.appointmentTimeStart }} to {{appointment.appointmentTimeEnd}}
      </li>
    </ul>

 </div>
</template>

<script>
import ApptService from '../services/ApptService'
import PatientService from '../services/PatientService'

export default {
    name: 'notification',
    data: function(){
        return{
            patientList: [],
            allAppointments: [],
            recentAppointments: [],
        }
    },
    
    computed:{
    sortedAppointments() {
        return this.allAppointments.slice().sort(
            (a, b) => new Date(b.created_time) - new Date(a.created_time)
        );
        },
    },
    methods: {
        loadAppointments() {
        ApptService.getAllAppointments().then((response) => {
            this.allAppointments = response.data;
            this.updateRecentAppointments();
        });
        },
        updateRecentAppointments() {
        const numToShow = 5;
        this.recentAppointments = this.sortedAppointments.slice(0, numToShow);
        }
    },
    created(){
        PatientService.getAllPatients().then((response) => {
            return this.patientList = response.data; 
        }),
        this.loadAppointments();
    }
}
</script>

<style>
.alertList{
    color: red
}

</style>