<template>
  <div class="space_list">
    <el-button type="primary" @click="showDrawer">
      空间编辑
    </el-button>
    <div>
      <div>
        <a-drawer
          :width="720"
          :visible="visible"
          :wrap-style="{height: 'calc(100% - 108px)',overflow: 'auto',paddingBottom: '108px'}"
          placement="left"
          title="空间节点编辑"
          @close="onClose"
        >
          <div>
            <spaceedit/>
          </div>
        </a-drawer>
      </div>
      <el-tree
        :data="spaceList"
        :props="defaultProps"
        :default-expand-all="true"
        @node-click="handleNodeClick"/>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import spaceedit from './../../space/edit'
export default {
  components: { spaceedit },
  data() {
    return {
      visible: false,
      defaultProps: {
        children: 'children',
        label: 'label'
      }
    }
  },
  computed: {
    ...mapState({
      'spaceList': state => state.business.spaceList
    })
  },
  mounted() {
    this.$store.dispatch('getSpaceTree', {})
  },
  methods: {
    handleNodeClick(data) {
      !!data.allowCreateInterface && this.$store.dispatch('getInterfaceListWithId', { criteria: { spaceId: data.id }, page: 1, itemsPerPage: 13 })
    },
    goSpaceEdit() {
      this.$router.push({ path: '/space/edit' })
    },
    showDrawer() {
      this.$data.visible = true
    },
    onClose() {
      this.$data.visible = false
      this.$store.dispatch('getSpaceTree', {})
    }
  }
}
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
 .space_list{
   line-height: 0;
   .el-button{
     width:100%;
     top:0;
     left:0;
     border-radius: 0;
     position: absolute;
     z-index: 2;
   }
   .el-tree{
     border:1px solid #ebeeff;
     margin-top:36px;
     padding-bottom:20px;
     min-height: calc(100vh - 120px);
     overflow-y: scroll;
     height: calc( 100vh - 120px);
   }
   .editSpace{
     height:600px;
     overflow-y: scroll;
   }
 }
</style>

