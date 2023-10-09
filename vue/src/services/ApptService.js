import axios from "axios";

export default {
    getAppointmentsByID(user) {
        return axios.get('/appointments', user)
    },
    getAppointmentsByEmployeeId(employeeId) {
        return axios.get(`/appointments/${employeeId}` )
    }, 
    getAllAppointments(){
        return axios.get('/appointments/doctor')
    },
    addEmployeeAppointment(appointment){
        return axios.post(`/appointments/create`, appointment)
    },
    addPatientAppointment(appointment){
        return axios.post(`/appointments/new`, appointment)
    },
}