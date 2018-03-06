import Vue from 'vue'
import Vuex from 'vuex'
import user from './module/user'
import layout from './module/layout'
import {SET_LOGIN, SET_TOKEN} from './mutation-types'
import cookie from '@/cookie'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isLogin: false,
    token: undefined
  },
  modules: {
    user,
    layout
  },
  mutations: {
    [SET_TOKEN] (state, token) {
      cookie.saveToken(token)
      state.token = token
    },
    [SET_LOGIN] (state, isLogin) {
      state.isLogin = isLogin
    }
  }
})
