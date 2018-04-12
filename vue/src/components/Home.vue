<template>
  <div id="home">
    <img src="@/assets/ic_zjsfgw_logo.jpg" alt="rem">
  </div>
</template>

<style lang="less" scoped>
  @import "../styles/base.less";

  #home {
    display: flex;
    flex-direction: column;
    align-items: center;
    img {
      width: 90%;
    }
    h1 {
      font-size: 2em;
    }
  }
</style>

<script>
  export default {
    name: 'Home'
  }
</script>
