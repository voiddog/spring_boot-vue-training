// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import ElementUI from 'element-ui'
import store from './store'
import cookie from './cookie'
import * as types from './store/mutation-types'

import 'element-ui/lib/theme-chalk/index.css'

Vue.config.productionTip = false

Vue.use(Vuex)
Vue.use(ElementUI)

/* eslint-disable no-new */
window.vm = new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>',
  computed: {
    isLogin () {
      return store.state.isLogin
    }
  },
  created: function () {
    // read token from cookie
    console.log(cookie.getToken())
    store.commit(types.SET_TOKEN, cookie.getToken())
  },
  mounted: function () {
    // if has token and no user info, getUserInfo from server
    let token = store.state.token
    if (token && token.length > 0 && !store.state.isLogin) {
      const loading = this.$loading({
        lock: true,
        text: 'Loading'
      })
      setTimeout(() => {
        loading.close()
        this.$store.commit(types.SET_LOGIN, true)
        this.$store.commit(types.SET_TOKEN, 'asdfghjklqwertyuiopzxcvbnmasdfg')
        let userInfo = {userName: '戚耿鑫', userId: '123456'}
        this.$store.commit(types.SET_USER_INFO, userInfo)
        this.$router.push('/')
      }, 1000)
    }
  }
})

window.alert = function (message) {
  window.vm.$message(message)
}
