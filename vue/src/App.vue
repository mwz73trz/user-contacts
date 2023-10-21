<template>
  <div id="app" class="app-content">
    <div id="nav">
      <div class="nav-content">
        <div class="home-logout">
          <router-link
            class="router-link-exact"
            v-bind:to="{ name: 'home' }"
            v-if="$store.state.token != ''"
            >Home</router-link
          >&nbsp;
          <router-link
            class="router-link-exact"
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >&nbsp;
        </div>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style>
</style>
