<template>
<div>
    <office-info />
  <add-review></add-review>
  <review-list></review-list>
  </div>
</template>

<script>
import OfficeInfo from '../components/OfficeInfo.vue'
import AddReview from '../components/AddReview.vue'
import ReviewList from "../components/ReviewList.vue"


export default {
    name: 'office-details',
    components: {
        OfficeInfo,
        AddReview,
        ReviewList
        }
}
</script>

<style>

</style>