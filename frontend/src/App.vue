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
import auth from './store/auth'

import axios from 'axios'

export default {
  name: 'App',

  components: { Navbar, Footer },
  beforeMount() {
    console.log('APP MOUNTED')
    if (!auth.state.token) {
      if (localStorage.getItem('token')) {
        console.log('Token before: ', auth.state.token)
        console.log('Reload Token : localStorage TO state')
        store.dispatch('auth/setToken', localStorage.getItem('token'))
        console.log('Token after: ', auth.state.token)

        console.log('User before: ', auth.state.user)
        console.log('Reload User : localStorage TO state')
        store.dispatch('auth/setUser', this.sendReq())
        console.log('User after: ', auth.state.user)
      }
    }
  },
  methods: {
    async sendReq(){
      console.log('Trying to fetch user');
      const response = await axios.get('/venues/' + localStorage.getItem('userId'), {
        'headers' : {
          'Authorization': 'Bearer'+auth.state.token
        }
      })
      return response
    }
  }
}
</script>

<style scoped></style>
