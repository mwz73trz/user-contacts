<template>
 <div class="notification-content"> 
    <h2 class="alertHeader"> Please see your newest appointments below: </h2>
    <ul class="alertList">
      <li v-for="appointment in recentAppointments" :key="appointment.id">
        Patient: {{ getPatientName(appointment)}} |
        Date: {{appointment.appointmentDateStart}} |
        Time: {{ appointment.appointmentTimeStart }} to {{appointment.appointmentTimeEnd}}
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

        filteredAppointments() {
        return this.allAppointments.filter((item) => {
            return item.employeeId === this.$store.state.user.id;
            })
       },
        sortedAppointments() {
            return this.filteredAppointments.slice().sort(
             (b, a) => (a.appointmentId) - (b.appointmentId)
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
        },
        getPatientName(appointment) {
        const patient = this.patientList.find(patient => patient.patientId === appointment.patientId);
        return patient ? patient.firstName + " " + patient.lastName : 'Unknown';
        },
        findEmployeeId(appointment){
            const employee = this.allAppointments.find(employee => employee.employeeId === appointment.employeeId);
            return employee ? employee.employeeId : 'Unknown';
        },
        hasNotifications() {
            if (this.recentAppointments != null) {
                alert("You have appointments! Check your notifications please.");
            }
        }
        
    },
    created(){
        PatientService.getAllPatients().then((response) => {
            return this.patientList = response.data; 
        }),
        this.loadAppointments(),
        this.hasNotifications();
    }
}
</script>

<style scoped>
.alertHeader{
    font-size: 22px;
}
.alertList{
    color: red;
    list-style-type: none;
    font-size: 17px;
}
.notification-content {
    display: flex;
    flex-direction: column;
    height: 150px;
}

</style>