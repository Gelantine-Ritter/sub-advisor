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

    <v-footer app> <Footer /> </v-footer>
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

<style scoped>

</style>
