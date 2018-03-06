<template>
  <div id="app">
    <div class="container">
      <el-container>
        <el-header><toolbar/></el-header>
        <el-container>
          <el-aside width="200px">
            <sidebar/>
          </el-aside>
          <el-main>
            <router-view/>
          </el-main>
        </el-container>
      </el-container>
    </div>
  </div>
</template>

<script>
  import Toolbar from '@/components/Toolbar'
  import Sidebar from '@/components/Sidebar'

  export default {
    name: 'App',
    components: {Toolbar, Sidebar}
  }
</script>

<style lang="less">
  @import "styles/base.less";

  #app {
    display: flex;
    flex-direction: row;
    justify-content: center;
    width: 100%;
    .container {
      width: 750px;
      @media (min-width: 1000px) {
        width: 1140px;
      }
    }
  }

  .el-header{
    padding: 0;
  }

  .el-main {
    padding: 10px 20px 10px 20px;
  }
</style>
