import '@babel/polyfill'
import 'mutationobserver-shim'
import '@fortawesome/fontawesome-free/css/all.min.css'
import 'bootstrap-css-only/css/bootstrap.min.css'
import 'mdbvue/lib/css/mdb.min.css'
import './plugins/bootstrap-vue'
import vuetify from './plugins/vuetify'
import VueCompositionAPI from '@vue/composition-api'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import * as VueGoogleMaps from 'vue2-google-maps'

import Vue from 'vue'
import App from './App.vue'

import axios from 'axios'

import Router from 'vue-router'

import Events from './views/EventsPage.vue'
import DetailEvent from './views/DetailEventPage.vue'
import About from './views/AboutPage.vue'
import Landing from './views/LandingPage.vue'
import Contact from './views/ContactPage.vue'
import Places from './views/PlacesPage.vue'
import DetailPlace from './views/DetailPlacePage.vue'
import Login from './views/LoginPage.vue'
import Signup from './views/SignupPage.vue'
import VenueProfile from './views/VenueProfile.vue'
import VenueCreateEvent from './views/VenueCreateEvent.vue'
import MemberProfile from './views/MemberProfile.vue'
import EventsFor1Venue from './views/EventsFor1Venue.vue'
import PicOfTheWeek from './views/PicOfTheWeekPage.vue'
import PopularEvents from './views/PopularEventsPage.vue'
import Calendar from './views/CalendarPage.vue'
import Impressum from './views/Impressum.vue'

import store from './store'
import auth from './store/auth'
import VueToast from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-sugar.css'

import { reloadStateFromLocalStorage } from './util/StoreReloader'
import Vuelidate from 'vuelidate'

require('@/store/subscriber')

window.axios = axios
axios.defaults.baseURL =
  process.env.VUE_APP_BACKEND_URL || 'http://localhost:8080/'

store.dispatch('auth/attempt', localStorage.getItem('token'))

Vue.use(axios)
Vue.use(VueCompositionAPI)
Vue.use(Router)
Vue.use(Vuelidate)

// Display Toast
Vue.use(VueToast, {
  type: 'default',
  position: 'top',
  dismissible: true,
  duration: 2000,
})

// Maps Integration
Vue.use(VueGoogleMaps, {
  load: {
    key: process.env.VUE_APP_API_KEY,
  },
})

Vue.config.productionTip = false

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/contact',
      name: 'contact',
      component: Contact,
    },
    {
      path: '/impressum',
      name: 'impressum',
      component: Impressum,
    },
    {
      path: '/places',
      name: 'places',
      component: Places,
    },
    {
      path: '/places/:id',
      name: 'place',
      component: DetailPlace,
    },
    {
      path: '/events',
      name: 'events',
      component: Events,
    },
    {
      path: '/events/:id',
      name: 'event',
      component: DetailEvent,
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
    {
      path: '/myVenueProfile',
      name: 'myVenueProfile',
      component: VenueProfile,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/myMemberProfile',
      name: 'myMemberProfile',
      component: MemberProfile,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/createEvents',
      name: 'createEvent',
      component: VenueCreateEvent,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/myEvents',
      name: 'myEevents',
      component: EventsFor1Venue,
      meta: {
        requiresAuth: true,
      },
    },
    {
      path: '/picOfTheWeek',
      name: 'picOfTheWeek',
      component: PicOfTheWeek,
    },
    {
      path: '/popularEvents',
      name: 'popularEvents',
      component: PopularEvents,
    },
    {
      path: '/calendar',
      name: 'calendar',
      component: Calendar,
    },
  ],
})

let reloadedState = false

router.beforeEach((to, from, next) => {
  if (!reloadedState && !auth.state.token) {
    reloadStateFromLocalStorage()
    reloadedState = true
  }

  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (auth.state.token) {
      next()
      return
    }
    next('/login')
  } else {
    next()
  }
})

new Vue({
  vuetify,
  router,
  store,
  render: (h) => h(App),
}).$mount('#app')
