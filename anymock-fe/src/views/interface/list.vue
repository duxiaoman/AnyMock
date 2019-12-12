<template>
  <el-container>
    <el-aside width="200px"><spacelist/></el-aside>
    <el-main><interfacelist/></el-main>
  </el-container>
</template>
<script>
import spacelist from './component/space_list'
import interfacelist from './component/interface_list'
export default {
  name: 'ListPage',
  components: { spacelist, interfacelist },
  data() {
    return {
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .el-aside {
    color: #333;
    text-align: center;
    line-height: 200px;
    margin-left: 5px;
    position: relative;
  }

  .el-main {
    color: #333;
    text-align: center;
    line-height: 160px;
    padding-top:0;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
</style>
