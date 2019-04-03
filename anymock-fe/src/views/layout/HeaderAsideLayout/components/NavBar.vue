<template>
  <div class="navbar">
    <a class="footer-brand" href="#">
      <img src="./../../../../assets/favicon.png" class="footer-logo" alt="logo">
      ANYMOCK
    </a>
    <a-menu
      v-model="active"
      mode="horizontal"
      @select="selectHandle"
    >
      <a-menu-item key="/home">首页</a-menu-item>
      <a-menu-item key="/interface/list">接口列表</a-menu-item>
      <a-menu-item key="/interface/create/0">创建接口</a-menu-item>
      <a-menu-item key="/doc">文档</a-menu-item>
    </a-menu>
  </div>
</template>

<script>
import { mapState } from 'vuex'
export default {
  name: 'NavBar',
  data() {
    return {
      active: []
    }
  },
  computed: {
    ...mapState({
      'navBarActive': state => state.navBar.navBarActive
    })
  },
  watch: {
    'navBarActive': function(v) {
      // store 不能够被setter
      this.$data.active = v
    }
  },
  mounted() {
    this.$data.active = this.navBarActive
  },
  methods: {
    selectHandle({ item, key, selectedKeys }) {
      this.$router.push({ path: key })
      this.$store.commit('UPDATE_NAR_BAR_ACTIVE', selectedKeys)
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  z-index: 100;
  position: absolute;
  top:0;
  left:0;
  height: 64px;
  box-shadow: 0 1px 4px rgba(0,21,41,.08);
  display: flex;
  flex:1;
  width: 100%;
  padding-left: 15%;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
  background-color: white;
  .footer-brand{
    display: flex;
    flex-direction:row;
    justify-content: center;
    align-items: center;
    margin-right:50px;
    font-size:24px;
    img{
      height:40px;
      margin-right:5px;
    }
  }
  .ant-menu-horizontal {
    border-bottom: 0 !important;
  }
}
</style>

