import * as types from '@/store/mutation-types.js'

const state = {
  userId: '',
  userName: ''
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
    }
  }
}
