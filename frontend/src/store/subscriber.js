import store from '@/store'
import axios from 'axios'

/** @method store.subscribe
 * - set authorization header for any further request
 * - manage localStorage and reauthenticating  */

store.subscribe((mutation) => {
  switch (mutation.type) {
    case 'auth/SET_TOKEN':

      if (mutation.payload) {
        axios.defaults.headers.common.Authorization = `Baerer ${mutation.payload}`
        localStorage.setItem('token', mutation.payload)
      } else {
        delete axios.defaults.headers.common.Authorization
        localStorage.removeItem('token')
      }
      break
    case 'auth/SET_USER_ID':
      if (mutation.payload) {
        localStorage.setItem('userId', mutation.payload)
      } else {
        localStorage.removeItem('userId')
      }
      break
    case 'auth/SET_ROLE':
      if (mutation.payload) {
        localStorage.setItem('role', mutation.payload)
      } else {
        localStorage.removeItem('role')
      }
      break
    case 'auth/SET_USER':
      if (!mutation.payload) {
        localStorage.removeItem('userId')
      }
      break
  }
})
