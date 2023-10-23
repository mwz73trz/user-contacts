<template>
  <div>
    <h2>Contact Groups</h2>
    <div>
      <select name="groups" v-on:change="contactRoute($event)">
        <option value="0">Group Options</option>
        <option v-for="group in groupingList" :key="group.groupingId" :value="group.groupingId">
          {{ group.title }}
        </option>
      </select>
    </div>
    <div>
      <router-link :to="{ name: 'add-grouping' }" tag="button">Add New Grouping</router-link>
          |
      <router-link :to="{ name: 'add-contact' }" tag="button">Add New Contact</router-link>
    </div>
  </div>
</template>

<script>
import GroupingServices from "@/services/GroupingServices";

export default {
  name: "grouping-list",
  data() {
    return {
      groupingList: [],
    };
  },
  created() {
    GroupingServices.getGroupingList().then((response) => {
      this.groupingList = response.data;
    });
  },
  methods: {
    contactRoute(e) {
      this.$router.push("/groupings/" + e.target.value + "/contacts")
    }
  }
};
</script>

<style></style>