<template>
  <div class="rounded-xl mycontainer" fluid>
    <v-form @submit.prevent="handleSubmit">
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
          style="font-size: 125%"
          type="submit"
          >ENTER</v-btn
        >
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
          this.$toast.open('Wrong password or username')
          this.form.username = ''
          this.form.password = ''
        })
    },
  },
}
</script>

<style scoped>
h1 {
  font-size: 5.5vw;
  color: black;
  text-align: center;
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
