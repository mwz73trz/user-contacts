import axios from "axios";

export default {
  getContactList() {
    return axios.get(`/contacts`);
  },
  getContactById(contactId) {
    return axios.get(`/contacts/${contactId}`);
  },
  createContact(newContact) {
    return axios.post(`/contacts`, newContact);
  },
  updateContact(contactId, updatedContact) {
    return axios.put(`/contacts/${contactId}`, updatedContact);
  },
  deleteContact(contactId) {
    return axios.delete(`/contacts/${contactId}`);
  },
};
