import axios from "axios";

export default {
    getReplies(reviewId) {
        return axios.get(`/reviews/${reviewId}/replies`)
    },
    addReply(reviewId, replyData) {
        return axios.post(`/reviews/${reviewId}/replies`, replyData)
    }
}