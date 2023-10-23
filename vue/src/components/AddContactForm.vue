<template>
  <div>
    <form v-on:submit.prevent="submitForm">
      <div>
      <label for="firstName">First Name:</label>
      <input type="text" v-model="newContact.firstName" autocomplete="off" />
      </div>
      <div>
      <label for="lastName">Last Name:</label>
      <input type="text" v-model="newContact.lastName" autocomplete="off" />
      </div>
      <div>
      <label for="address">Address:</label>
      <input type="text" v-model="newContact.address" autocomplete="off" />
      </div>
      <div>
      <label for="city">City:</label>
      <input type="text" v-model="newContact.city" autocomplete="off" />
      </div>
       <div>
      <label for="state">State:</label>
      <input type="text" v-model="newContact.state" autocomplete="off" />
      </div>
      <div>
      <label for="zip">Zip:</label>
      <input type="text" v-model="newContact.zip" autocomplete="off" />
      </div>
      <div>
      <label for="phoneNumber">Phone Number:</label>
      <input type="text" v-model="newContact.phoneNumber" autocomplete="off" />
      </div>
      <div>
      <label for="email">Email:</label>
      <input type="text" v-model="newContact.email" autocomplete="off" />
      </div>
      <div>
        <label for="birthDate">Birth Date:</label>
        <input type="date" v-model="newContact.birthDate" />
      </div>
      <div>
        <label for="note">Notes:</label>
        <textarea cols="30" rows="10" v-model="newContact.note"></textarea>
      </div>
      <div>
        <label for="groupingId">Grouping Selector:</label>
        <select name="groups" id="groupingId" v-on:change="groupSelect($event)">
          <option value="0">Group Options</option>
          <option v-for="group in groupList" :key="group.groupingId" :value="group.groupingId">
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
import contactServices from "../services/ContactServices"
import groupingServices from "../services/GroupingServices"

export default {
name: 'add-contact-form',
data() {
  return {
    groupList: [],
    newContact: {
      firstName: '',
      lastName: '',
      address: '',
      city: '',
      state: '',
      zip: '',
      phoneNumber: '',
      email: '',
      birthDate: '',
      note: '',
      groupingId: 0
    }
  }
},
methods: {
  groupSelect(e) {
    this.newContact.groupingId = e.target.value;
  },
  submitForm() {
    contactServices.createContact(this.newContact).then(response => {
      if (response.status === 201) {
        this.$router.push(`/groupings/${this.newContact.groupingId}/contacts`);
      }
    })
  },
  cancelForm() {
    this.$router.push("/");
  }
},
created() {
  groupingServices.getGroupingList().then(response => {
    this.groupList = response.data;
  })
}
}
</script>

<style>

</style>