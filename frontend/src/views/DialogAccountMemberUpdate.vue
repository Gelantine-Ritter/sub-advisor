<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="text-h5">YOUR ACCOUNT</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >USERNAME</v-list-item-subtitle
                >

                <v-text-field
                  type="text"
                  v-model="userData.username"
                  :error-messages="usernameErrors"
                  @input="$v.userData.username.$touch()"
                  @blur="$v.userData.username.$touch()"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >E-MAIL</v-list-item-subtitle
                >

                <v-text-field
                  type="text"
                  v-model="userData.email"
                  :error-messages="emailErrors"
                  @input="$v.userData.email.$touch()"
                  @blur="$v.userData.email.$touch()"
                >
                </v-text-field>
            </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >FIRSTNAME</v-list-item-subtitle
                >
                <v-text-field
                  type="text"
                  v-model="userData.firstName"
                  :error-messages="firstNameErrors"
                  @input="$v.userData.firstName.$touch()"
                  @blur="$v.userData.firstName.$touch()"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="6">
                <v-list-item-subtitle class="text-h6"
                  >LASTNAME</v-list-item-subtitle
                >
                <v-text-field
                  type="text"
                  v-model="userData.lastName"
                  :error-messages="lastNameErrors"
                  @input="$v.userData.lastName.$touch()"
                  @blur="$v.userData.lastName.$touch()"
                >
                </v-text-field>
              </v-col>
              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >NEW PASSWORD</v-list-item-subtitle
                >
                <v-text-field
                  type="password"
                  v-model="userData.password"
                  :error-messages="passwordErrors"
                  @input="$v.userData.password.$touch()"
                  @blur="$v.userData.password.$touch()"
                >
                </v-text-field>
              </v-col>

              <v-col cols="12">
                <v-list-item-subtitle class="text-h6"
                  >CONFIRM PASSWORD</v-list-item-subtitle
                >
                <v-text-field
                  type="password"
                  v-model="confirm_password"
                  :error-messages="confirmPasswordErrors"
                  @input="$v.confirm_password.$touch()"
                  @blur="$v.confirm_password.$touch()"
                >
                </v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn elevation="1" outlined rounded text @click="dialog = false">
            Cancel
          </v-btn>
          <v-btn
            elevation="1"
            outlined
            rounded
            text
            @click="updateSubmit"
            :disabled="$v.$invalid"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import {
  required,
  minLength,
  sameAs,
  maxLength,
  email,
} from 'vuelidate/lib/validators'
export default {
  data() {
    return {
      userData: {
        username: null,
        password: null,
        email: null,
        firstName: null,
        lastName: null,
      },
      confirm_password: null,
    }
  },
  validations: {
    userData: {
      username: { required, minLength: minLength(4), maxLength: maxLength(20) },
      email: { required, email },
      firstName: { minLength: minLength(2) },
      lastName: { minLength: minLength(2) },
      password: { required, minLength: minLength(8) },
    },
    confirm_password: {
      required,
      sameAsPassword: sameAs(function () {
        return this.userData.password
      }),
    },
  },
  props: {
    value: Boolean,
  },
  computed: {
    ...mapGetters({
      user: 'auth/user',
    }),
    dialog: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
      },
    },
    usernameErrors() {
      const errors = []
      if (!this.$v.userData.username.$dirty) return errors
      !this.$v.userData.username.required &&
        errors.push('Username is required.')
      !this.$v.userData.username.minLength &&
        errors.push('Name must be at least 4 letters')
      !this.$v.userData.username.maxLength &&
        errors.push('Name must have at most 20 letters')
      return errors
    },
    emailErrors() {
      const errors = []
      if (!this.$v.userData.email.$dirty) return errors
      !this.$v.userData.email.required && errors.push('Email is required.')
      !this.$v.userData.email.email &&
        errors.push('Email must be a valid Email, e.g. meine.email@mail.org')
      return errors
    },
    firstNameErrors() {
      const errors = []
      !this.$v.userData.firstName.minLength &&
        errors.push('Name must have at least 2 letters ')
      return errors
    },
    lastNameErrors() {
      const errors = []
      !this.$v.userData.lastName.minLength &&
        errors.push('Name must have at least 2 letters ')
      return errors
    },
    passwordErrors() {
      const errors = []
      if (!this.$v.userData.password.$dirty) return errors
      !this.$v.userData.password.required &&
        errors.push('Password is required.')
      !this.$v.userData.password.minLength &&
        errors.push('Password must have at least 8 characters')
      return errors
    },
    confirmPasswordErrors() {
      const errors = []
      if (!this.$v.confirm_password.$dirty) return errors
      !this.$v.confirm_password.required && errors.push('Password is required.')
      !this.$v.confirm_password.sameAsPassword &&
        errors.push('Passwords must be identical ')
      return errors
    },
  },

  mounted: function () {
    this.userData.username = this.user.username
    this.userData.email = this.user.email
    this.userData.firstName = this.user.firstName
    this.userData.lastName = this.user.lastName
  },

  methods: {
    ...mapActions({
      updateMember: 'auth/updateMember',
    }),
    updateSubmit() {
      this.updateMember(this.userData)
        .then(() => {
          this.$toast.open('Your account has been updated!')
          this.dialog = false
        })
        .catch((e) => {
          console.log(e)
        })
    },
  },
}
</script>

<style scoped>
.v-card__subtitle,
.v-card__text {
  line-height: 2rem;
}
.v-application .text-h6 {
  margin-bottom: 10px;
}
.v-text-field {
  padding-top: 0;
  margin-top: 0;
}
</style>
