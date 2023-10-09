<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">Home</router-link>&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>&nbsp;
      <h4 id="date">{{ new Date() }}</h4>
    </div>
    <router-view />
  </div>
</template>
<style scoped>

#nav{
  font-family:Cambria;
  font-size: 90%;
}

#date{
  color: rgb(81, 79, 85);
  font-weight: lighter;
}


</style>
