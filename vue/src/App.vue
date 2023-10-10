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
        <img src="./assets/AppLogo.png" class="logo" />
        <h4 id="date">{{ new Date() }}</h4>
      </div>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
/* .app-content {
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
} */
#nav {
  font-family: Cambria;
  font-size: 90%;
  /* background-color: rgb(103, 147, 177); */
  background-color: #b9d9eb;
  height: 80px;
}

#date {
  color: rgb(81, 79, 85);
  font-weight: lighter;
  float: right;
  margin-top: 30px;
}
.nav-content {
  margin-left: 6px;
}

.logo {
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
  padding: 4px;
}
a.router-link-exact-active:hoover {
  background-color: yellow;
}
a.router-link-exact-active {
  text-decoration: none;
  color: #42b983;
}
a.router-link-exact {
  text-decoration: none;
}
a:-webkit-link {
  text-decoration: none;
}
</style>
