import axios from "axios";


export default {
    getEmployees(){
        return axios.get('/employees')
    },

    getOffices(){
        return axios.get('/offices')
    },

    getPatient(user){
        return axios.get('patients/user', user)
    },
    createPersonalInfo(patient){
        return axios.post(`/patient`, patient)
    },

    getCovidInfo(){
        return axios.get(`/covid`)
    }

}