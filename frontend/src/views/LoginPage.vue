<template>
  <v-parallax
    class="myparallax"
    style="height: 100%"
    src="../assets/tv-noise.jpeg"
    alt="Welcome to SUB"
  >
    <Login />
  </v-parallax>
</template>

<script>
import Login from '../components/auth/Login'

export default {
  name: 'LoginPage',
  props: {
    source: String,
  },
  components: { Login },
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
</style>
