import * as types from '@/store/mutation-types.js'
import cookie from '@/cookie'

const state = {
  userId: 0,
  userName: '',
  userAvatar: ''
}

export default {
  state,
  mutations: {
    [types.SET_USER_INFO] (state, user) {
      if (!user) {
        return
      }
      state.userId = user.userId
      state.userName = user.userName
      state.userAvatar = user.userAvatar
      cookie.setCookie('user_id', user.userId)
    },
    [types.SET_USER_ID] (state, id) {
      state.userId = id
      cookie.setCookie('user_id', id)
    }
  }
}
