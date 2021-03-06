import store from '../store'
export async function reloadStateFromLocalStorage() {
    store.dispatch('auth/attempt', {
        jwt: localStorage.getItem('token'),
        userId: localStorage.getItem('userId'),
        role: localStorage.getItem('role'),
    })
}
