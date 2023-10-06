import axios from "axios";

export default {
    getAppointmentsByID(user) {
        return axios.get('/appointments', user)
    },
    createAppointment(appointment){
        return axios.post(`/appointments`, appointment)
    }
}