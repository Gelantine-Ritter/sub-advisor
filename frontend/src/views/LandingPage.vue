<template>
  <v-parallax
    class="myparallax"
    height="100%"
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
  padding-bottom: 5vw;
}
</style>
