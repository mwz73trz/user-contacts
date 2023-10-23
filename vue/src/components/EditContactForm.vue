<template>
  <div>
    <form v-on:submit.prevent="submitForm">
      <div v-show="errorMsg !== ''">{{ errorMsg }}</div>
      <div>
      <label for="firstName">First Name:</label>
      <input type="text" v-model="contact.firstName" autocomplete="off" />
      </div>
      <div>
      <label for="lastName">Last Name:</label>
      <input type="text" v-model="contact.lastName" autocomplete="off" />
      </div>
      <div>
      <label for="address">Address:</label>
      <input type="text" v-model="contact.address" autocomplete="off" />
      </div>
      <div>
      <label for="city">City:</label>
      <input type="text" v-model="contact.city" autocomplete="off" />
      </div>
       <div>
      <label for="state">State:</label>
      <input type="text" v-model="contact.state" autocomplete="off" />
      </div>
      <div>
      <label for="zip">Zip:</label>
      <input type="text" v-model="contact.zip" autocomplete="off" />
      </div>
      <div>
      <label for="phoneNumber">Phone Number:</label>
      <input type="text" v-model="contact.phoneNumber" autocomplete="off" />
      </div>
      <div>
      <label for="email">Email:</label>
      <input type="text" v-model="contact.email" autocomplete="off" />
      </div>
      <div>
        <label for="birthDate">Birth Date:</label>
        <input type="date" v-model="contact.birthDate" />
      </div>
      <div>
        <label for="note">Notes:</label>
        <textarea cols="30" rows="10" v-model="contact.note"></textarea>
      </div>
      <div>
        <label for="groupingId">Change Grouping:</label>
        <select name="groups" id="groupingId" v-model="contact.groupingId">
          <option value="0">Group Options</option>
          <option v-for="group in groupingList" :key="group.groupingId" :value="group.groupingId">
            {{ group.title }}
          </option>
        </select>
      </div>
      <button v-on:click="cancelForm">Cancel</button>
      <button>Save</button>
    </form>
  </div>
</template>

<script>
import contactServices from "../services/ContactServices";
import groupingServices from "../services/GroupingServices";

export default {
  name: "edit-contact-form",
  data() {
    return {
      groupingList: [],
      contact: {
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
      errorMsg: "",
    };
  },
  methods: {
    submitForm() {
      const updatedContact = {
        contactId: this.$route.params.contactId,
        firstName: this.contact.firstName,
        lastName: this.contact.lastName,
        address: this.contact.address,
        city: this.contact.city,
        state: this.contact.state,
        zip: this.contact.zip,
        phoneNumber: this.contact.phoneNumber,
        email: this.contact.email,
        birthDate: this.contact.birthDate,
        note: this.contact.note,
        groupingId: this.contact.groupingId,
      };
      contactServices
        .updateContact(this.contact.contactId, updatedContact)
        .then((response) => {
          if (response.status === 200) {
            this.$router.push(`/groupings/${this.contact.groupingId}/contacts/${this.contact.contactId}`);
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "updating");
        });
    },
    cancelForm() {
      this.$router.push(
        `/groupings/${this.contact.groupingId}/contacts/${this.contact.contactId}`
      );
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " contact. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " contact. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " contact. Request could not be created.";
      }
    },
  },
  created() {
    contactServices.getContactById(this.$route.params.contactId).then(response => {
      this.contact = response.data;
    }),
    groupingServices.getGroupingList().then(response => {
      this.groupingList = response.data;
    })
  },
};
</script>

<style></style>