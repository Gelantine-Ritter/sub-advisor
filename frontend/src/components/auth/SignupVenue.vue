<template>
  <div>
    <v-form
      @submit.prevent="registrateSubmit"
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <h2>NAME</h2>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input"
        id="name"
        name="name"
        label=""
        type="text"
        required
        v-model="name"
        :rules="nameRules"
      ></v-text-field>
      <h2>E-MAIL</h2>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input"
        id="email"
        name="email"
        label=""
        type="text"
        required
        v-model="email"
        :rules="emailRules"
      ></v-text-field>

      <h2>USERNAME</h2>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input"
        id="username"
        name="username"
        label=""
        type="text"
        required
        v-model="username"
        :rules="usernameRules"
      ></v-text-field>
      <h2>PASSWORD</h2>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input centered-input-password"
        id="password"
        name="password"
        label=""
        type="password"
        required
        v-model="password"
        :rules="passwordRules"
      ></v-text-field>
      <h2>CONFIRM PASSWORD</h2>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input centered-input-password"
        id="password_confirm"
        name="password_confirm"
        label=""
        type="password"
        required
        v-model="password_confirm"
        :rules="passwordMatchRules"
      ></v-text-field>
      <div class="text-center">
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill myEnterBtn"
          style="font-size: 125%"
          type="submit"
          >REGISTER</v-btn
        >
      </div>
    </v-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'Signup',
  data() {
    return {
      valid: true,
      name: '',
      nameRules: [
        (v) => !!v || 'Name is required',
        (v) => (v && !!v.trim()) || 'Name cannot be blank',
        (v) => /^[^\s]/.test(v) || 'You can not start with a space',
      ],
      email: '',
      emailRules: [
        (v) => !!v || 'E-mail is required',
        (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid',
      ],
      username: '',
      usernameRules: [
        (v) => !!v || 'Username is required',
        (v) => (v && !!v.trim()) || 'Username cannot be blank',
        (v) => /^[^\s]/.test(v) || 'You can not start with a space',
      ],
      password: '',
      passwordRules: [
        (v) => !!v || 'Password is required',
        (v) => v.length >= 8 || 'Min 8 characters',
        (v) => (v && !!v.trim()) || 'Seriously? just a blank password?',
      ],
      password_confirm: '',
      passwordMatchRules: [
        (v) => !!v || 'Password is required',
        (v) => v === this.password || 'Password must match',
      ],
    }
  },
  computed: {
    radios: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
      },
    },
  },
  methods: {
    ...mapActions({
      signupVenue: 'auth/signupVenue',
    }),
    registrateSubmit() {
      if (this.$refs.form.validate()) {
        this.signupVenue(this.$data)
          .then(() => {
            this.$toast.open(
                'You have been successfully registered and can log in now!'
              )
              this.$router.replace({
                name: 'login',
              })
          })
          .catch(() => {
            this.$toast.open('You are already registered!')
            this.$router.go()
          })
      }
    },
  },
}
</script>

<style scoped>
h2 {
  font-size: 2vw;
  color: black;
  text-align: center;
}

.centered-input >>> input {
  text-align: center;
  font-size: 180%;
}
.centered-input-password >>> input {
  text-align: center;
  font-size: 300%;
}

::v-deep textarea {
  padding-left: 50px;
  padding-right: 50px !important;
  text-align: center;
  font-size: 180%;
}
::v-deep .v-label {
  font-size: 180% !important;
  margin-right: 0;
}
::v-deep .v-label:nth-child(1) {
  font-size: 16px !important;
}
::v-deep .v-label:nth-child(2) {
  font-size: 16px !important;
}
::v-deep label {
  margin-bottom: 0;
}
::v-deep .v-input--radio-group__input {
  width: auto;
  text-align: center;
}

::v-deep .v-input__slot {
  justify-content: center;
}

@media screen and (max-width: 600px) {
  h2 {
    font-size: 5vw;
  }
}
</style>
