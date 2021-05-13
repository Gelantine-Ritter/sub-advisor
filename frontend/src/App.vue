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
      <!-- Provides the application the proper gutter -->
      <v-container fluid>
        <!-- If using vue-router -->
        <router-view></router-view>
      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<script>
import Navbar from './components/header/AppNavbar'
import Drawer from './components/header/AppDrawer'

export default {
  name: 'App',

  components: { Navbar, Drawer },
}
</script>
