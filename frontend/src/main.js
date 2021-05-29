import '@babel/polyfill'
import 'mutationobserver-shim'
import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import Vue from 'vue'
import './plugins/bootstrap-vue'

import App from './App.vue'
import vuetify from './plugins/vuetify'
import VueCompositionAPI from '@vue/composition-api'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Router from 'vue-router'
import axios from 'axios'

import Events from './views/EventsPage.vue'
import About from './views/AboutPage.vue'
import Landing from './views/LandingPage.vue'
import Contact from './views/ContactPage.vue'
import Places from './views/PlacesPage.vue'
import Profile from './views/ProfilePage.vue'
import Login from './views/LoginPage.vue'
import Signup from './views/SignupPage.vue'
import store from './store'

window.axios = axios
axios.defaults.baseURL = 'http://localhost:8080'

Vue.use(axios)
Vue.use(VueCompositionAPI)
Vue.use(Router)

Vue.config.productionTip = false

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/profile',
      name: 'profile',
      component: Profile,
    },
    {
      path: '/contact',
      name: 'contact',
      component: Contact,
    },
    {
      path: '/places',
      name: 'places',
      component: Places,
    },
    {
      path: '/events',
      name: 'events',
      component: Events,
    },
    {
      path: '/about',
      name: 'about',
      component: About,
    },
    {
      path: '/',
      name: 'home',
      component: Landing,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup,
    },
  ],
})

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')
