import axios from 'axios'

export default {
  namespaced: true,
  state: {
    token: null,
    user: null,
    role: null,
  },
  getters: {
    authenticated(state) {
      return state.token && state.user && state.role
    },
    user(state) {
      return state.user
    },
    role(state) {
      return state.role
    },
  },
  mutations: {
    SET_TOKEN(state, jwt) {
      state.token = jwt
      // console.log('STATE TOKEN: ' + state.token)
    },
    SET_USER(state, data) {
      state.user = data
      // console.log('STATE USER: ' + state.user.name + ' ' + state.user.email + ' ' + state.user.info)
    },
    SET_ROLE(state, role) {
      state.role = role
      // console.log('STATE ROLE: ' + state.role)
    },
  },

  actions: {
    async login({ dispatch }, credentials) {
      const response = await axios.post('/authenticate/', credentials)
      const responseDataObject = {
        jwt: response.data.jwt,
        userId: response.data.userId,
        role: response.data.role,
      }
      dispatch('attempt', responseDataObject)
    },
    async attempt({ commit, state }, responseDataObject) {
      //  { commit } will commit a mutation
      if (responseDataObject == null) {
        console.log('userDataObject is null')
        return
      }
      const jwt = responseDataObject.jwt
      const userId = responseDataObject.userId
      const role = responseDataObject.role

      if (jwt) {
        commit('SET_TOKEN', jwt)
      }
      if (!state.token) {
        //  if there is no token do not continue with a request
        return
      }

      if (role) {
        commit('SET_ROLE', role)
      }
      if (!state.role) {
        return
      }

      try {
        const response = await axios.get('/venues/' + userId)
        // console.log(response)
        commit('SET_USER', response.data)
      } catch (e) {
        commit('SET_TOKEN', null)
        commit('SET_USER', null)
        commit('SET_ROLE', null)
      }
    },
  },
}
