import axios from "axios";


export default {
    getEmployees(){
        return axios.get('/employees')
    },

    getOffices(){
        return axios.get('/offices')
    }

}