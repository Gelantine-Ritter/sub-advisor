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
        v-model="username"
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
        v-model="password"
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
          >REGISTER</v-btn
        >
      </div>
    </v-form>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  name: 'Login',
  props: {
    source: String,
  },
  data() {
    return {
      username: '',
      password: '',
    }
  },
  methods: {
    handleSubmit(){
      console.log("Enter has been clicked");
    }
    /* 
    async handleSubmit(){
        console.log("Enter has been clicked"));
        const response = await axios.post('/authentication/login', {
          username: this.username,
          password: this.password
        });
        console.log(response);
    }
     */ 
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
  background: white;
  padding: 5vw;
}
h1 {
  font-size: 8vw;
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
