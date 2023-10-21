<template>
  <div id="login" class="login-content">
    <form @submit.prevent="login">
      <div role="alert" v-if="invalidCredentials">
        Invalid username and password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label  for="username">Username</label>
        <input
          class="username-input"
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input class="password-input" type="password" id="password" v-model="user.password" required />
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

<script scoped>
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

<style>
</style>

