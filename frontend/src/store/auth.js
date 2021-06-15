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
    SET_USER_ID(state, userId){

    }
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

      if (userId) {
        commit('SET_USER_ID', userId)
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
    async setToken({ commit, state}, token) {
      if (token) {
        commit('SET_TOKEN', token)
      }
    },
    async setUser({commit, state}, user) {
      if(user) {
        commit('SET_USER', user)
      }
    },
    async setRole({commit, state}, role) {
      if(role) {
        commit('SET_ROLE', role)
      }
    },


    // REGISTRATE
    async signupVenue({ commit }, username, name, password, email) {
      await axios.post(
        '/authenticate/registrate/',
        username,
        email,
        password,
        name
      )
    },

    // DELETE ACCOUNT
    async deleteProfile({ commit, state }) {
      await axios.delete('/venues/' + state.user.id, {
        headers: { Authorization: 'Bearer ' + state.token },
      })
      commit('SET_TOKEN', null)
      commit('SET_USER', null)
      commit('SET_ROLE', null)
    },

    // UPDATE ACCOUNT
    async updateVenue({ commit, state }, userData) {
      var newData = userData
      var token = state.token
      console.log("REQUEST")
      console.log(axios.put('/venues/' + state.user.id, newData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      }))
      const response = await axios.put('/venues/' + state.user.id, newData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      })
      if (response.status === 200) {
        commit('SET_USER', response.data)
      }
      console.log("RESPONSE")
      console.log(response)
    },
  },
}
