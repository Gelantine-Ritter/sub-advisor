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
import DetailPlace from './views/DetailPlacePage.vue'
import Profile from './views/ProfilePage.vue'
import Login from './views/LoginPage.vue'
import Signup from './views/SignupPage.vue'
import VenueProfile from './views/VenueProfile.vue'
import store from './store'
import auth from './store/auth'
import VueToast from 'vue-toast-notification'
import 'vue-toast-notification/dist/theme-sugar.css'

require('@/store/subscriber')

window.axios = axios
axios.defaults.baseURL = 'http://localhost:8080'

store.dispatch('auth/attempt', localStorage.getItem('token'))

Vue.use(axios)
Vue.use(VueCompositionAPI)
Vue.use(Router)

Vue.use(VueToast, {
  type: 'default',
  position: 'top',
  dismissible: true,
  duration: 2000,
})

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
      path: '/myProfile',
      name: 'myProfile',
      component: VenueProfile,
      meta: {
        requiresAuth: true,
      },
    },
  ],
})

router.beforeEach((to, from, next) => {
  if (to.matched.some((record) => record.meta.requiresAuth)) {
    if (auth.state.token !== null) {
      // console.log('isAuth')
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
