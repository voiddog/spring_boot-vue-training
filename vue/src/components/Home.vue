<template>
  <div id="home">
    <img src="@/assets/bg_sakura.jpg" alt="rem">
    <p>Sakura</p>
  </div>
</template>

<style lang="less" scoped>
  @import "../styles/base.less";

  #home {
    display: flex;
    flex-direction: column;
    align-items: center;
    img {
      width: 80%;
    }
    p {
      font-size: 4em;
      color: @txt-secondary;
    }
  }
</style>

<script>
  export default {
    name: 'Home'
  }
</script>
