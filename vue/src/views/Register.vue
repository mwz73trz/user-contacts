<template>
  <div id="register" class="register-content">
    <form @submit.prevent="register">
      <h1>Elevate your health</h1>
      <p class="title">Create Account</p>
      <div role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" v-model="user.confirmPassword" required />
      </div>
      <div>
        <label for="role">Select a Role</label>
        <select v-model="user.role" id="role">
          <option value="user">Patient</option>
          <option value="admin">Doctor</option>
        </select>
      </div>
      <button type="submit">Create Account</button>
      <p><router-link :to="{ name: 'login' }">Already have an account? Log in.</router-link></p>
      
    </form>
    <img src="../assets/register-icon.png">
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: '',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.register-content {
  display: flex;
  flex-direction: column;
  align-items: left;
  justify-content: left;
  font-family: Arial, sans-serif;
  font-weight: normal;
  font-size: 16px;
  min-height: 100vh;
}
button {
  margin-top: 4px;
  background-color: #0060f0;
  color: white;
  border: none;
  /* padding: 10px 20px; */
  cursor: pointer;
  border-radius: 4px;
  font-size: 16px;
  text-align: center;
}
button:hover {
  background-color: #64b5f6;
}
.form-input-group {
  margin-bottom: 1rem;
}
label {
  margin-right: 0.5rem;
}

form {
  border-style: none;
  color: #414042;
  font-size: medium;
}
#register {
  margin-left: 180px;
}
h1 {
   font-family: Cambria;
   font-size: 32px;
   font-weight:bold;
   font-style: italic;
}
.form-input-group {
  font-family: Cambria;
  font-weight: lighter;
  font-size: smaller;
}
.title {
  font-family: Arial, sans-serif;
  font-size: 24px;
  font-weight: bold;
}
img {
  width: 250px;
  margin-left: 600px;
  margin-top: -250px;
}

</style>
