import axios from "axios";

export default {
  getGroupingList() {
    return axios.get(`/groupings`);
  },
  getGroupingById(groupingId) {
    return axios.get(`/groupings/${groupingId}`);
  },
  addGrouping(grouping) {
    return axios.post(`/groupings`, grouping);
  },
  updateGrouping(groupingId, updatedGrouping) {
    return axios.put(`/groupings/${groupingId}`, updatedGrouping);
  },
  deleteGroup(groupingId) {
    return axios.delete(`/groupings/${groupingId}`);
  },
};
