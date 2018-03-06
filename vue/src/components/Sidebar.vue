<template>
  <div id="sidebar">
    <el-menu
      default-active="1"
      text-color="#606266"
      @select="handleSelect"
      class="el-menu-vertical-demo">
      <template v-for="(menu, key) in menuList">
        <el-submenu
          v-if="menu.children?menu.children.length>0:false"
          :key="key"
          :index="menu.path">
          <template slot="title">
            <i :class="menu.icon"></i>
            <span> {{ menu.title }} </span>
          </template>
          <el-menu-item
            v-for="childMenu in menu.children"
            :key="menu.path + '/' + childMenu.path"
            :index="menu.path + '/' + childMenu.path">
            <i :class="childMenu.icon"></i>
            <span>{{childMenu.title}}</span>
          </el-menu-item>
        </el-submenu>
        <el-menu-item
          v-else
          :key="key"
          :index="menu.path">
          <i :class="menu.icon"></i>
          <span slot="title"> {{ menu.title }} </span>
        </el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<style lang="less" scoped>
  #sidebar {
    .el-menu-item {
      font-size: 1.1em;
    }
  }
</style>

<script>
  import {mapState} from 'vuex'

  export default {
    name: 'sidebar',
    computed: {
      ...mapState({
        menuList: state => state.layout.menuList
      })
    },
    methods: {
      handleSelect (keyPath) {
        this.$router.push({
          path: keyPath
        })
      }
    }
  }
</script>
