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

Vue.use(VueCompositionAPI)

Vue.config.productionTip = false

new Vue({
  vuetify,
  render: (h) => h(App),
}).$mount('#app')
