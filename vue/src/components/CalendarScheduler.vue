<template>
<div>
  <div>
      <datepicker v-bind:format="customFormatter" :inline="true" :value="this.date"></datepicker>
  </div>
  <div><p>Selected Date: {{ this.date }}</p></div>
  </div>
</template>

<script>
import Datepicker from 'vuejs-datepicker'
import moment from 'moment'

export default {
    date: '2016/09/16',
    components: {
        Datepicker
    },
    methods: {
        customFormatter(date) {
            return moment(date).format('MMMM Do YYYY, h:mm:ss a')
        }
    }
}
</script>

<style>

</style>