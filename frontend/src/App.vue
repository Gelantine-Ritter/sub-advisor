<template>
  <!-- App.vue -->

  <v-app>
    <v-app-bar elevation="0" app>
      <Navbar />
    </v-app-bar>
    <!-- Sizes your content based upon application components -->

    <v-main style="padding:0;">

      <LandingPage text="THIS IS HAPPENING"/>
      
      <v-container fluid>
        <!-- If using vue-router -->
        <router-view></router-view>
      </v-container>
    </v-main>

    <v-footer app> <Footer /> </v-footer>
  </v-app>
</template>

<script>
import Footer from './components/AppFooter'
import Navbar from './components/header/AppNavbar'
import LandingPage from './views/LandingPage'

export default {
  name: 'App',

  components: { Navbar, Footer, LandingPage},


}
</script>

<style scoped>
.v-main{
  padding-top:0;
}

</style>

