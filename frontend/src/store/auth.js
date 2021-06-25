import { requestProvider } from '../util/requestProvider'

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
    SET_USER_ID(state, userId) {},
  },

  actions: {
    async logout({ commit, state }) {
      commit('SET_TOKEN', null)
      commit('SET_USER', null)
      commit('SET_ROLE', null)
    },
    async login({ dispatch }, credentials) {
      requestProvider.loginUser(credentials).then((response) => {
        const responseDataObject = {
          jwt: response.data.jwt,
          userId: response.data.userId,
          role: response.data.role,
        }
        dispatch('attempt', responseDataObject)
      })
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
        requestProvider
          .getVenue(userId, {
            headers: { Authorization: 'Bearer ' + jwt },
          })
          .then((response) => {
            commit('SET_USER', response.data)
          })
      } catch (e) {
        commit('SET_TOKEN', null)
        commit('SET_USER', null)
        commit('SET_ROLE', null)
      }
    },
    async setToken({ commit, state }, token) {
      if (token) {
        commit('SET_TOKEN', token)
      }
    },
    async setUser({ commit, state }, user) {
      if (user) {
        commit('SET_USER', user)
      }
    },
    async setRole({ commit, state }, role) {
      if (role) {
        commit('SET_ROLE', role)
      }
    },

    // REGISTRATE
    async signupVenue({ commit }, username, name, password, email) {
      requestProvider.postVenue(username, name, password, email)
    },

    // DELETE ACCOUNT
    async deleteProfile({ commit, state }) {
      requestProvider.deleteVenue(state.user.id, {
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
      requestProvider
        .updateVenue(state.user.id, newData, {
          headers: {
            Authorization: `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        })
        .then((response) => {
          if (response.status === 200) {
            commit('SET_USER', response.data)
          }
        })
    },

    // DELETE EVENT
    async deleteEvent({ state }, eventId) {
      requestProvider.deleteEvent(eventId, {
        headers: { Authorization: 'Bearer ' + state.token },
      })
    },
  },
}
