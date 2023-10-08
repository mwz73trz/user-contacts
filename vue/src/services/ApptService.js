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
    // createAppointment(appointment){
    //     return axios.post(`/appointments`, appointment)
    // },
    addEmployeeAppointment(appointment){
        return axios.post(`/appointments/create`, appointment)
    },
}