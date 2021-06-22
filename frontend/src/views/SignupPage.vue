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
@media screen and (min-width: 1400px) {
  .v-parallax {
    height: calc(140vh - 64px) !important;
  }
  .v-parallax__image-container {
    position: absolute;
    top: -calc(140vh - 64px) !important;
  }
}
</style>
