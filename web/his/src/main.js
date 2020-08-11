// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Vue from 'vue'
import App from './App'
import VueResource from 'vue-resource'
import router from './router'
import store from './store'

Vue.use(VueResource)
Vue.use(ElementUI)
Vue.config.productionTip = false
var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8001'

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) {
    if (store.state.user) {
      if (store.state.user.type == 1) {
        if (to.path == "/index/register" || to.path == "/index/withdraw") next()
        else if (to.path == "/index") next("/index/register")
        else next(from.path)
      } else if (store.state.user.type == 2) {
        if (to.path == "/index/diagnose" || to.path == "/index/make_presc") next()
        else if (to.path == "/index") next("/index/diagnose")
        else next(from.path)
      } else if (store.state.user.type == 3) {
        // 检验师
      } else if (store.state.user.type == 4) {
        if (to.path == "/index/drug_trans" || to.path == "/index/drug_rcv") next()
        else if (to.path == "/index") next("/index/drug_trans")
        else next(from.path)
      } else if (store.state.user.type == 5) {
        if (to.path == "/index/pay" || to.path == "/index/refund") next()
        else if (to.path == "/index") next("/index/pay")
        else next(from.path)
      } else {
        // 管理员 
      }
    } else next('/login')
  } else next()
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
