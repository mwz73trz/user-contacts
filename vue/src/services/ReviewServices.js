import axios from "axios";

export default {
    getReviews() {
        return axios.get('/reviews')
    },
    getEmployeeReviews() {
        return axios.get('/officeReview')
    },
    createReview(review){
        return axios.post(`/reviews/`, review)
    }
}