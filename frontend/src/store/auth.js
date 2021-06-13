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
    },
    SET_USER(state, data) {
      state.user = data
    },
    SET_ROLE(state, role) {
      state.role = role
    },
  },

  actions: {
    async logout({ commit, state }) {
      commit('SET_TOKEN', null)
      commit('SET_USER', null)
      commit('SET_ROLE', null)
    },
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
      if (responseDataObject == null) {
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
        const response = await axios.get('/venues/' + userId, {
          headers: { Authorization: 'Bearer ' + jwt },
        })
        console.log(response.data)
        commit('SET_USER', response.data)
      } catch (e) {
        commit('SET_TOKEN', null)
        commit('SET_USER', null)
        commit('SET_ROLE', null)
      }
    },

    // REGISTRATE
    async signupVenue({ commit }, username, name, password, email) {
      const response = await axios.post(
        '/authenticate/registrate/',
        username,
        email,
        password,
        name
      )
      console.log(response)
    },

    // DELETE ACCOUNT
    async deleteProfile({ commit, state }) {
      const response = await axios.delete('/venues/' + state.user.id, {
        headers: { Authorization: 'Bearer ' + state.token },
      })
      console.log(response)
      commit('SET_TOKEN', null)
      commit('SET_USER', null)
      commit('SET_ROLE', null)
    },

    // UPDATE ACCOUNT
    async updateVenue({ commit, state }, userData) {
      var newData = userData
      var token = state.token
      const response = await axios.put('/venues/' + state.user.id, newData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      })
      if (response.status === 200) {
        commit('SET_USER', newData)
      }
      console.log(response)
    },
  },
}
