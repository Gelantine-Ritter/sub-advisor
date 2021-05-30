import Vue from 'vue'
import Vuex from 'vuex'
import auth from './auth'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //  stored everything
  },
  mutations: {
    //  updated state
  },
  actions: {
    //  make requests and commit mutations
  },
  modules: {
    auth,
  },
})
