<template>
  <v-parallax
    class="myparallax"
    style="height: 100%"
    src="../assets/tv-noise.jpeg"
    alt="Welcome to SUB"
  >
    <div class="rounded-xl mycontainer" fluid>
      <h1>THIS IS HAPPENING</h1>
      <div class="text-center">
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill"
          style="font-size: 125%"
          @click="redirectToEvents()"
          >ENTER</v-btn
        >
      </div>
    </div>
  </v-parallax>
</template>

<script>
export default {
  name: 'LandingPage',
  props: {
    text: String,
  },
  methods: {
    redirectToEvents(){
      this.$router.replace({
        name: 'events',
      })
    }
  }
}
</script>

<style scoped>
h1 {
  font-size: 8vw;
  color: black;
  padding-bottom: 5vw;
}
@media screen and (max-width: 600px) {
  h1 {
    font-size: 13vw;
  }
}
</style>
