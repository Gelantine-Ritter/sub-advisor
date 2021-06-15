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

import store from './store'

export default {
  name: 'App',

  components: { Navbar, Footer },
  beforeMount() {
    this.reloadStateFromLocalStorage()
  },
  methods: {
    async reloadStateFromLocalStorage() {
      await store.dispatch('auth/attempt', {
        jwt: localStorage.getItem('token'),
        userId: localStorage.getItem('userId'),
        role: localStorage.getItem('role'),
      })
    },
  },
}
</script>

<style scoped></style>
