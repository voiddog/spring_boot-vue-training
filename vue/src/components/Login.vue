<template>
  <div id="login">
    <div>
      <i class="fab fa-vuejs" style="font-size: 100px; color: #67C23A"></i>
      <el-input placeholder="请输入用户名" v-model="username"/>
      <el-input placeholder="请输入密码" type="password" v-model="password"/>
      <el-button round type="primary" plain style="width: 200px" @click="onLoginClick">登录</el-button>
    </div>
  </div>
</template>

<script>
  import * as types from '@/store/mutation-types'

  export default {
    name: 'Login',
    data () {
      return {
        username: '',
        password: ''
      }
    },
    methods: {
      onLoginClick () {
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
          this.$router.replace('/')
        }, 1000)
      }
    }
  }
</script>

<style scoped>
  #login {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
  }
  #login > div {
    width: 300px;
    text-align: center;
  }
  #login > div > * {
    margin-bottom: 20px;
  }
</style>
