<template>
  <v-parallax
    class="myparallax"
    style="height: 100%"
    src="../assets/tv-noise.jpeg"
    alt="Welcome to SUB"
  >
    <!-- <div class="rounded-xl mycontainer" fluid> -->
      <h1 class="text-center">THIS IS HAPPENING</h1> <br/>
      <div class="text-center">
        <v-btn
          large
          block
          outlined
          elevation="1"
          class="rounded-pill myBtn"
          style="font-size: 125%"
          @click="redirectToEvents()"
          >ENTER</v-btn
        >
      </div>
      <br/>
      <h1 class="text-center h1Two">THIS IS HAPPENING</h1>
      
    <!-- </div>-->
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
/* @import url('https://fonts.googleapis.com/css2?family=Fondamento&display=swap'); */
@import url('https://fonts.googleapis.com/css2?family=Fondamento&family=Orbitron:wght@500&display=swap');
h1 {
  font-size: 8vw;
  padding-bottom: 5vw;
  letter-spacing: .4rem !important;
  font-family: 'Orbitron' !important;
  padding-bottom: 0;
  color: #cafb03;
  text-shadow: 5px 5px 5px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
.h1Two{
  transform: scale(1, -1);;
}
.myBtn{
  background: white;
  width: 50% !important;
}
@media screen and (max-width: 600px) {
  h1 {
    font-size: 10vw;
  }
}
</style>
