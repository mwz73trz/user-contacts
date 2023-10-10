import axios from "axios";


export default {
    getAllPatients(){
        return axios.get('/patients')
    },

    getEmployees(){
        return axios.get('/employees')
    },

    getOffices(){
        return axios.get('/offices')
    },

    getPatient(user){
        return axios.get('patients/user', user)
    },
    updatePatientInfo(updatedPersonalInfo){
        return axios.put('patients/user', updatedPersonalInfo)
    },
    createPersonalInfo(patient){
        return axios.post(`/patient`, patient)
    },

    getCovidInfo(){
        return axios.get(`/covid`)
    }

}