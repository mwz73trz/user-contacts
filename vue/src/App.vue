<template>
  <div id="app">
    <div id="nav">
      <div class="nav-content">
        <div class="home-logout">
             <router-link
          v-bind:to="{ name: 'home' }"
          v-if="$store.state.token != ''"
          >Home</router-link
        >&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >&nbsp;
        </div>
        <img src="./assets/AppLogo.png" class="logo">
        <h4 id="date">{{ new Date() }}</h4>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#nav {
  font-family: Cambria;
  font-size: 90%;
  /* background-color: rgb(103, 147, 177); */
  background-color: #B9D9EB;
  height: 80px;
}

#date {
  color: rgb(81, 79, 85);
  font-weight: lighter;
  float: right;
  margin-top: 35px;
}
.nav-content {
  margin-left: 6px;
}

.logo{
   float: center;
   width: 60.3px;
   height: 60.3px;
   border-style: none;
   margin-top: -20px;
   /* margin-top: 70px; */
   /* margin-right: 350px; */
}

.home-logout {
  display: flex;
  justify-content: right;
  padding: 5px;
}
.home-logout {
    text-decoration: none;
}
</style>
