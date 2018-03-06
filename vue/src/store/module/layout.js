import * as types from '@/store/mutation-types'

const state = {
  menuList: []
}

const mutations = {
  [types.SET_MENU_LIST] (state, menuList) {
    state.menuList = menuList
  }
}

export default {
  state,
  mutations
}
