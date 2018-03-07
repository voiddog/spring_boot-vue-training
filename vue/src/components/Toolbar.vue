<template>
  <div id="toolbar">
    <div class="vertical-flex-center">
      <template v-if="$store.state.isLogin">
        <img :src="$store.state.user.userAvatar" alt="header" class="img-header">
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link">
            {{$store.state.user.userName}}
            <i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="logout" ><i class="fas fa-sign-out-alt"></i> 退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </template>
      <template v-else>
        <el-button type="primary" plain round @click="onLoginClick">登录</el-button>
      </template>
    </div>
    <img src="@/assets/test-icon.svg">
  </div>
</template>

<style lang="less" scoped>
  #toolbar {
    display: flex;
    flex-direction: row-reverse;
    justify-content: space-between;
    align-items: center;
    padding: 0 20px 0 20px;
    span {
      font-size: 1.2em;
    }
  }
  .img-header {
    width: 40px;
    height: 40px;
    border-radius: 40px;
    margin-right: 10px;
    box-shadow: 0 0 5px #aaa;
  }
  .vertical-flex-center {
    display: flex;
    flex-direction: row;
    align-items: center;
  }
</style>

<script>
  import * as types from '@/store/mutation-types'

  export default {
    name: 'toolbar',
    methods: {
      onLoginClick () {
        this.$router.push('login')
      },
      handleCommand (command) {
        switch (command) {
          case 'logout': {
            this.$store.commit(types.SET_USER_INFO, undefined)
            this.$store.commit(types.SET_LOGIN, false)
            this.$store.commit(types.SET_TOKEN, undefined)
            this.$router.replace('home')
          }
        }
      }
    }
  }
</script>
