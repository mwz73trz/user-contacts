<template>
  <div>
    <h2>{{ contact.firstName }} {{ contact.lastName }}</h2>
    <p>{{ contact.address }}</p>
    <p>{{ contact.city }}, {{ contact.state }} {{ contact.zip }}</p>
    <p>{{ contact.phoneNumber }}</p>
    <p>{{ contact.email }}</p>
    <p>Birth Date: {{ contact.birthDate }}</p>
    <p>Notes: {{ contact.note }}</p>
    <div>
      <router-link class="btn editContact" :to="{ name: 'edit-contact', params: { contactId: $route.params.contactId } }">Edit Contact</router-link>
          |    
      <button class="btn deleteContact" v-on:click="deleteContact">Delete Contact</button>
    </div>
  </div>
</template>

<script>
import contactServices from "../services/ContactServices";

export default {
  name: "contact-details",
  data() {
    return {
      contact: {
        contactId: 0,
        firstName: "",
        lastName: "",
        address: "",
        city: "",
        state: "",
        zip: "",
        phoneNumber: "",
        email: "",
        birthDate: "",
        note: "",
        groupingId: 0,
      },
    };
  },
  methods: {
    deleteContact() {
      if (confirm("Are you sure you want to delete this Contact?")) {
        contactServices.deleteContact(this.contact.contactId).then(response => {
          if (response.status === 200) {
            alert("Contact deleted successfully")
            this.$router.push(`/groupings/${this.$route.params.groupingId}/contacts`)
          }
        })
        .catch(error => {
          if (error.response) {
            this.errorMsg = "Error deleting contact. Response received was " + error.response.statusText + ".";
          }else if (error.request) {
            this.errorMsg = "Error deleting contact. Server could not be reached";
          }else {
            "Error deleting contact. Request could not be created."
          }
        })
      }
    }
  },
  created() {
    contactServices
      .getContactById(this.$route.params.contactId)
      .then((response) => {
        this.contact = response.data;
      });
  },
};
</script>

<style>
.btn.editContact {
  color: #fff;
  text-decoration: none;
  background-color: #508ca8;
  border-color: #508ca8;
  margin-bottom: 10px;
}
.btn.deleteContact {
  color: #fff;
  background-color: #ef031a;
  border-color: #ef031a;
  margin-bottom: 10px;
}
</style>