<template>
  <!-- App.vue -->

  <v-app>
    <v-app-bar elevation="0" app>
      <Navbar />
    </v-app-bar>
    <!-- Sizes your content based upon application components -->

    <v-main>
      <router-view></router-view>
    </v-main>

    <footer><Footer /></footer>
  </v-app>
</template>

<script>
import Footer from './components/AppFooter'
import Navbar from './components/header/AppNavbar'

export default {
  name: 'App',
  components: { Navbar, Footer },
}
</script>

<style>
/* @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap'); */
@import url('https://fonts.googleapis.com/css2?family=Fondamento&family=Orbitron:wght@500&display=swap');
.mycontainer {
  border: solid 2px black;
  width: 70vw;
  margin-top: 5vw;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 5vw;
  background: white;
  padding: 3vw;
}
.h1Style {
  color: #cafb03;
  text-shadow: 1px 1px 1px black, 1px -1px 1px black, -1px 1px 1px black,
    -1px -1px 1px black;
}
.v-application .display-3{
  letter-spacing: .4rem !important;
}
.v-application .display-4{
  letter-spacing: .4rem !important;
}
.myEnterBtn {
  background: black;
  color: white !important;
  font-size: 125%;
}
 
.v-parallax {
  height: 100%;
} 
.v-parallax__image-container {
  position: absolute;
  top: -calc(100vh - 64px) !important;
}

.lastPage {
  text-decoration: none;
  font-size: 1.5em;
  color: black;
}

.lastPage:visited {
  color: black;
  text-decoration: none;
}

.lastPage:hover {
  color: #cafb03;
  text-decoration: none;
}

.myDeleteButton {
  margin: 30px;
}

.v-toolbar__content,
.v-toolbar__extension {
  padding: 0;
}
.infoTruncate {
  overflow: hidden;
  position: relative;
  max-height: 6em;
  text-align: left;
}
p.infoTruncate {
    margin-bottom: 0!important;
}

@media screen and (max-width: 600px) {
  .mycontainer {
    width: 80vw;
  }
  .v-main {
    background-color: black;
  }
  .h1Style {
    color: white;
  }
}
</style>
