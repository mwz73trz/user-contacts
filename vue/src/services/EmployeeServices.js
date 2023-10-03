import axios from "axios";

export default {
    getEmployee(user) {
        return axios.get('/employee/user', user)
    },

    getReviews(review) {
        return axios.get('/reviews', review)
    },

    updateAvailability(){},

    updatePersonalInfo(){},
}