<template>
  <div class="rounded-xl mycontainer" fluid>
    <v-form>
      <h1>USERNAME</h1>
      <v-text-field
        large
        block
        outlined
        class="rounded-pill centered-input centered-input-username"
        id="username"
        name="username"
        label=""
        type="text"
        required
        v-model="form.username"
      ></v-text-field>
      <h1>PASSWORD</h1>
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
      ></v-text-field>

      <div class="text-center">
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill myEnterBtn"
          @click="handleSubmit"
          >ENTER</v-btn
        >
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill"
          style="font-size: 125%"
          :to="{ path: '/signup' }"
          >REGISTER</v-btn
        >
      </div>
    </v-form>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: 'Login',
  // props: {
  //   source: String,
  // },
  data() {
    return {
      form: {
        username: '',
        password: '',
      },
    }
  },
  methods: {
    ...mapActions({
      login: 'auth/login',
    }),
    handleSubmit() {
      this.login(this.form)
        .then(() => {
          this.$router.replace({
            name: 'home',
          })
        })
        .catch(() => {
          this.form.username = ''
          this.form.password = ''
          alert('It seems like you entered wrong data... Try again!')
          console.log('failed')
          // ADD A ERROR MESSAGE
        })
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
h1 {
  font-size: 5.5vw;
  color: black;
  text-align: center;
}
.myEnterBtn {
  background: black;
  color: white;
  font-size: 125%;
}
.centered-input-username >>> input {
  text-align: center;
  font-size: 180%;
}
.centered-input-password >>> input {
  text-align: center;
  font-size: 300%;
}
</style>
