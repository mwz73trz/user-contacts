<template>
  <div id="login">
    <div class="intro">
      <h1>Elevate your health</h1>
      <p>
        Use Elevate your health portal where you can be in control of your
        health. Sign in with your account, you can make appointments, view
        doctor schedules, read the doctor reviews and more.
      </p>
    </div>
    <form @submit.prevent="login">
      <p id="app">
        Patient Portal/Doctor Portal Sign In To Elevate Your Health
      </p>
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div>
        <button class="submit-btn" id="signin" type="submit">Sign in</button>
      </div>
      <div>
        <p id="signup">
          <router-link :to="{ name: 'register' }"
            >Need an account? Sign up.</router-link
          >
        </p>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
      invalidCredentials: false,
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
  },
};
</script>

<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: center;
  border-style: none;
  /* margin-left: 450px; */
}

.form-input-group {
  margin-bottom: 0.5rem;
  margin-left: 250px;
}
label {
  margin-right: 0.5rem;
}

#login {
  display: flex;
  flex-direction: column;
  align-content: center;
  align-items: center;
  justify-content: space-evenly;
  flex-wrap: wrap;
  font-family: Cambria;
  font-weight: lighter;
  font-size: smaller;
  /* min-height: 100vh; */
}

#signup {
  font-family: Cambria;
  font-size: medium;
  /* float: left; */
  /* margin-left: 63px; */
  text-align: center;
  margin-top: 0.5rem;
}

#signin {
  font-family: Cambria;
  font-size: small;
  position: middle;
  cursor: pointer;
  margin-left: 63px;
  width: 169px;
  border-radius: 4px;
  border: 0.5px solid;
  margin-bottom: 0.5rem;
}

.logo {
  float: center;
  width: 140px;
  height: 150px;
  margin-top: 70px;
  margin-right: 350px;
}

#app {
  font-family: Cambria;
  font-size: medium;
  font-weight: bold;
}

.intro {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: right;
  font-family: sans-serif;
  font-style: italic;
  text-align: center;
  color: #414042;
}

.intro > p {
  font-family: monospace;
  font-size: medium;
  /* float:center; */
}

.submit-btn{
  margin-right: -14px;
}

</style>