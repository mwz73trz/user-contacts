import axios from "axios";

export default {
    getReviews() {
        return axios.get('/reviews')
    },
    createReview(review){
        return axios.post(`/reviews/`, review)
    }
}