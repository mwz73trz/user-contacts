import axios from "axios";

export default {
    getAppointmentsByID() {
        return axios.get('/appointmenst')
    },
    createAppointment(appointment){
        return axios.post(`/appointments`, appointment)
    }
}