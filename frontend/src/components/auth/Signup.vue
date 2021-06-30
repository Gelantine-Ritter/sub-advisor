<template>
  <div class="rounded-xl mycontainer" fluid>
    <v-form
      @submit.prevent="registrateSubmit"
      ref="form"
      v-model="valid"
      lazy-validation
    >
      <h2>YOU WANT TO REGISTRER AS A</h2>

      <v-radio-group v-model="radios">
        <v-row>
          <v-col class="myCol" cols="12" sm="6" md="6">
            <v-radio
              label="VENUE"
              color="black"
              value="VENUE"
              @click.stop="handleSignUpVenue"
            ></v-radio>
          </v-col>
          <v-col cols="12" sm="6" md="6">
            <v-radio
              label="MEMBER"
              color="black"
              value="MEMBER"
              @click.stop="handleSignUpMember"
            ></v-radio>
          </v-col>
        </v-row>
      </v-radio-group>
      <br />
      <template v-if="showSignUpVenue">
        <SignupVenue v-model="showSignUpVenue" />
      </template>
      <template v-else-if="showSignUpMember">
        <SignupMember v-model="showSignUpMember" />
      </template>
    </v-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import SignupVenue from './SignupVenue.vue'
import SignupMember from './SignupMemberView.vue'
export default {
  name: 'Signup',
  data() {
    return {
      valid: true,
      radios: null,
      showSignUpVenue: false,
      showSignUpMember: false,
    }
  },
  components: {
    SignupVenue,
    SignupMember,
  },
  methods: {
    ...mapActions({
      signupVenue: 'auth/signupVenue',
    }),
    handleSignUpVenue() {
      this.showSignUpMember = false
      this.showSignUpVenue = true
    },
    handleSignUpMember() {
      this.showSignUpVenue = false
      this.showSignUpMember = true
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
