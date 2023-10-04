import axios from "axios";

export default {
    getEmployee(user) {
        return axios.get('/employee/user', user)
    },

    getOffices(){
        return axios.get('/offices')
    },

    getReviews(review) {
        return axios.get('/reviews', review)
    },

    updateAvailability(){
        },

    updatePersonalInfo(id, information){
        return axios.put(`/employee/${id}`, information)
    },
}