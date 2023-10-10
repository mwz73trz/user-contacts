<template>
  <div id="login" class="login-content">
    <div class="intro">
      <h1>Elevate your health</h1>
      <p class="welcome-msg">
        Use Elevate your health portal where you can be in control of your
        health. Sign in with your account, you can make appointments, view
        doctor schedules, read the doctor reviews and more.
      </p>
    </div>
    <form @submit.prevent="login">
      <p class="title">
        Patient Portal/Doctor Portal Sign In To Elevate Your Health
      </p>
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
        <img src="../assets/login-photo.jpg">
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

<style scoped>
.login-content {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  font-family: Arial, sans-serif;
  font-weight: normal;
  font-size: 16px;
  min-height: 100vh;
}

#signup {
  font-family: Arial, sans-serif;
  font-size: 18px;
  text-align: left;
  margin-top: 20px;
}

.title {
  font-family: Arial, sans-serif;
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 20px;
}

.intro {
  font-family: Georgia, serif;
  font-style: italic;
  color: #333;
  text-align: left;
  margin-bottom: 20px;
}

.intro > p {
  font-family: Courier, monospace;
  font-size: 16px;
  text-align: center;
  max-width: 664.12px;
}

.form-input-group {
  margin-bottom: 10px;
  /* padding: 2px; */
}
.password-input, .username-input {
  padding-left: 2px;
  margin: 4px;
}

button {
  background-color: #0060f0;
  color: white;
  border: none;
  /* padding: 10px 20px; */
  cursor: pointer;
  border-radius: 4px;
  font-size: 16px;
  text-align: center;
}
img {
  width: 600px;
  margin-left: 700px;
  margin-top: -2080px;
}

button:hover {
  background-color: #64b5f6;
}

</style>

