<template>
  <div class="employee">
    <h1>Employee</h1>
    <div>
      <nav>
        <input type="button" value="Review" />
        |
        <input type="button" value="Update Availability" /> 
        |
        <router-link 
        v-bind:to="{name: 'employee', params: {id: $route.params.id}}"
        >
          View Personal Information
          </router-link>
      </nav>
      <div class="agenda"><p>calendar placeholder</p></div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
nav {
  padding: 30px;
  margin: 30px;
}

.agenda {
  border-style: solid;
}
</style>