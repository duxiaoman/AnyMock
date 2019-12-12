<template>
  <div class="app-wrapper" >
    <div class="main-container">
      <nav-bar/>
      <app-main/>
    </div>
  </div>
</template>

<script>
import { NavBar, SideBar, AppMain } from './components'

export default {
  name: 'Layout',
  components: {
    NavBar,
    SideBar,
    AppMain
  }
}
</script>

<style lang="scss" scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  &:after {
    content: "";
    display: table;
    clear: both;
  }
  overflow: hidden;
}

.main-container {
  min-height: 100vh;
  transition: margin-left 0.28s;
  margin-left: 0 !important;
  background-color: white;
  overflow: hidden;
}
</style>
