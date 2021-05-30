import store from '@/store'
import axios from 'axios'

/** @method store.subscribe set authorization header for any further request  */

store.subscribe((mutation) => {
  //  console.log(mutation)
  switch (mutation.type) {
    case 'auth/SET_TOKEN':
      //  console.log(mutation.payload)
      if (mutation.payload) {
        axios.defaults.headers.common.Authorization = `Baerer ${mutation.payload}`
      } else {
        axios.defaults.headers.common.Authorization = null
      }
      break
  }
})
