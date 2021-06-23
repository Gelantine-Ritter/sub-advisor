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

<style scoped></style>
