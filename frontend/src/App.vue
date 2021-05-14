<template>
  <!-- App.vue -->

  <v-app>
    <v-navigation-drawer>
      <Drawer />
    </v-navigation-drawer>

    <v-app-bar elevation="0" app>
      <v-app-bar-nav-icon
        @click.stop="sideNav = !sideNav"
        class="d-flex d-sm-none"
      ></v-app-bar-nav-icon>
      <Navbar />
    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-main>
      
      <AppButton text="ENTER"/>
      <br>
      <AppButton text="READ MORE"/>
      <br>
      <AppButton text="UNITED WE STREAM"/>
      <br>
      <AppButton text="SEE ON MAP"/>
      <br>
      <AppButton text="ONLINE ONLY"/>
      <br>
      <AppButton text="SELECT A DATE"/>
      <br>
      <br>
      <AppButton text="TOO LONG TOO LONG TOO LONG TOO LONG TOO LONG"/>
      <br>
      <AppButton text="HIHI"/>
      <br>
      
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
import Drawer from './components/header/AppDrawer'
import AppButton from './components/ui/AppButton.vue'

export default {
  name: 'App',

  components: { Navbar, Drawer, Footer, AppButton, },

}
</script>

