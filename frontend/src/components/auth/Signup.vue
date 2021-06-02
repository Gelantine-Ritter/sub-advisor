<template>
  <div class="rounded-xl mycontainer" fluid>
    <v-form ref="form" v-model="form.valid" lazy-validation>
      <h2>YOU WANT TO REGISTRER AS A</h2>

      <v-radio-group v-model="form.radios" mandatory>
        <v-row>
          <v-col class="myCol" cols="12" sm="6" md="6">
            <v-radio label="VENUE" color="black" value="VENUE"></v-radio>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <v-radio label="MEMBER" color="black" value="MEMBER"></v-radio>
          </v-col>
        </v-row>
      </v-radio-group>
      <br />
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
        v-model="form.name"
        :rules="form.nameRules"
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
        v-model="form.email"
        :rules="form.emailRules"
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
        v-model="form.username"
        :rules="form.usernameRules"
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
        v-model="form.password"
        :rules="form.passwordRules"
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
        v-model="form.password_confirm"
        :rules="form.passwordMatchRules"
      ></v-text-field>
      <div class="text-center">
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill myEnterBtn"
          @click="registrateSubmit"
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
      form: {
        valid: true,
        radios: null,
        name: '',
        nameRules: [(v) => !!v || 'Name is required'],
        email: '',
        emailRules: [
          (v) => !!v || 'E-mail is required',
          (v) => /.+@.+\..+/.test(v) || 'E-mail must be valid',
        ],
        username: '',
        usernameRules: [(v) => !!v || 'Name is required'],
        password: '',
        passwordRules: [
          (v) => !!v || 'E-mail is required',
          (v) => v.length >= 8 || 'Min 8 characters',
        ],
        password_confirm: '',
        passwordMatchRules: [
          (v) => !!v || 'E-mail is required',
          (v) => v === this.password || 'Password must match',
        ],
      },
    }
  },
  methods: {
    ...mapActions({
      signupVenue: 'auth/signupVenue',
    }),
    registrateSubmit() {
      if (this.form.radios === 'VENUE') {
        this.signupVenue(this.form)
          .then(() => {
            alert('Successfully registrated')
            this.$router.replace({
              name: 'home',
            })
          })
          .catch(() => {
            alert('Failed')
            this.$router.go()
          })
      } else {
        //  TODO MEMBER
        alert('You can only register as a venue right now')
        this.$router.go()
      }
    },
  },
}
</script>

<style scoped>
.mycontainer {
  border: solid 2px black;
  width: auto;
  margin-top: 5vw;
  margin-left: 15vw;
  margin-right: 15vw;
  margin-bottom: 5vw;
  background: white;
  padding: 5vw;
}
h2 {
  font-size: 2vw;
  color: black;
  text-align: center;
}
.myEnterBtn {
  background: black;
  color: white;
  font-size: 125%;
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
</style>
