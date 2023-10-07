import axios from "axios";

export default {
    getReplies(reviewId) {
        return axios.get(`/reviews/${reviewId}/replies`)
    },

}