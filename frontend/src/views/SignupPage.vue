<template>
  <v-parallax
    class="myparallax"
    height="100%"
    src="../assets/tv-noise.jpeg"
    alt="Welcome to SUB"
  >
    <Signup />
  </v-parallax>
</template>

<script>
import Signup from '../components/auth/Signup'

export default {
  name: 'SignupPage',
  components: { Signup },
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
  font-size: 8vw;
  color: black;
}
</style>
