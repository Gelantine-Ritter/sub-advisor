import axios from 'axios'

export default {
  namespaced: true,
  state: {
    token: null,
    // user: null,
  },
  getters: {
    authenticated(state) {
      return state.token //  && state.user
    },
    /* 
    user (state){
      return state.user
    } 
    */
  },
  mutations: {
    SET_TOKEN(state, jwt) {
      state.token = jwt
    },
    /* 
    SET_USER (state, data){
      state.user = data
    } 
    */
  },
  actions: {
    async login({ dispatch }, credentials) {
      const response = await axios.post('/authenticate/', credentials)
      dispatch('attempt', response.data.jwt)
      console.log(response)
    },
    async attempt({ commit }, jwt) {
      //  { commit } will commit a mutation
      //  console.log(jwt)
      commit('SET_TOKEN', jwt)
      /* 
      var base64Url = jwt.split('.')[1]
      var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
      var jsonPayload = decodeURIComponent(
        atob(base64)
          .split('')
          .map(function (c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
          })
          .join('')
      )
      const userData = JSON.parse(jsonPayload)
      console.log(userData) 
      */
      try {
        //  Change the '/'route to a route that gives the user object with the data. example: '/venue/venueId'
        const response = await axios.get('/', {
          // headers: {
          //   Authorization: 'Bearer ' + jwt,
          // },
        })
        console.log(response)
        //   commit('SET_USER', response.data)
      } catch (e) {
        commit('SET_TOKEN', null)
        //   commit('SET_USER', null)
      }
    },
  },
}
