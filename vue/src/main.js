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
import netio from './netio'

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
    store.commit(types.SET_USER_ID, cookie.getCookie('user_id'))
  },
  mounted: function () {
    // if has token and no user info, getUserInfo from server
    let token = store.state.token
    if (token && token.length > 0 && !store.state.isLogin) {
      const loading = this.$loading({
        lock: true,
        text: 'Loading'
      })
      let request = {
        'id': store.state.user.userId,
        'token': token
      }
      netio.post('/user/refresh_token', request)
        .then(value => {
          loading.close()
          this.$store.commit(types.SET_LOGIN, true)
          this.$store.commit(types.SET_TOKEN, value.token)
          let userInfo = {userName: value.name, userId: value.id, userAvatar: value.avatar}
          this.$store.commit(types.SET_USER_INFO, userInfo)
          this.$router.replace('/')
        })
        .catch(reason => {
          this.$store.commit(types.SET_USER_INFO, undefined)
          this.$store.commit(types.SET_LOGIN, false)
          this.$store.commit(types.SET_TOKEN, undefined)
          this.$router.replace('/')
          loading.close()
        });
    }
  }
})

window.alert = function (message) {
  window.vm.$message(message)
}
