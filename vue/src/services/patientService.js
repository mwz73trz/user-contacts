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
<<<<<<< HEAD
        return axios.post(`/patient/form`, patient)
=======
        return axios.post(`/patient`, patient)
>>>>>>> 0621a1be6a593ee6b55c0ddaea92e12b2c8115b6
    },

}