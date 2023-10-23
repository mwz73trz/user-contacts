<template>
  <div>
    <h1>{{ grouping.title }} Contacts</h1>
    <div>
      <ul v-for="contact in filteredContacts" :key="contact.contactId">
        <li v-bind:key="contact.contactId" v-on:click="viewContactDetails(contact.contactId)">
            {{ contact.firstName }} {{ contact.lastName }}
        </li>
      </ul>
    </div>
    <div>
      <router-link class="btn editGrouping" :to="{ name: 'edit-grouping', params: { groupingId: $route.params.groupingId } }">Edit Grouping</router-link>
          |    
      <button class="btn deleteGrouping" v-on:click="deleteGrouping">Delete Grouping</button>
    </div>
  </div>
</template>

<script>
import groupingServices from '../services/GroupingServices'
import contactsServices from '../services/ContactServices'

export default {
  name: 'contact-list',
  data() {
    return {
      grouping: {
        groupingId: 0,
        title: ''
      },
      contacts: [],
      errorMsg: ''
    }
  },
  methods: {
    viewContactDetails(contactId) {
      this.$router.push(`/groupings/${this.grouping.groupingId}/contacts/${contactId}`);
    },
    deleteGrouping() {
      if (confirm("Are you sure you want to delete this grouping?")) {
        groupingServices.deleteGroup(this.grouping.groupingId).then(response => {
          if (response.status === 200) {
            alert("Group deleted successfully")
            this.$router.push("/")
          }
        })
        .catch(error => {
          if (error.response) {
            this.errorMsg = "Error deleting group. Response received was " + error.response.statusText + ".";
          }else if (error.request) {
            this.errorMsg = "Error deleting group. Server could not be reached";
          }else {
            "Error deleting group. Request could not be created."
          }
        })
      }
    }
  },
  computed: {
    filteredContacts() {
      return this.contacts.filter(item => {
        return item.groupingId === this.grouping.groupingId;
      })
    }
  },
  created() {
    groupingServices.getGroupingById(this.$route.params.groupingId).then(response => {
      this.grouping = response.data;
    }),
    contactsServices.getContactList().then(response => {
      this.contacts = response.data;
    })
  }
}
</script>

<style>
.btn.editGrouping {
  color: #fff;
  text-decoration: none;
  background-color: #508ca8;
  border-color: #508ca8;
  margin-bottom: 10px;
}
.btn.deleteGrouping {
  color: #fff;
  background-color: #ef031a;
  border-color: #ef031a;
  margin-bottom: 10px;
}

</style>