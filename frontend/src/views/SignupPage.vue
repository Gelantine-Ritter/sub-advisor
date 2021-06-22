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

<style scoped></style>
