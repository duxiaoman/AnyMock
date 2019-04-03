<template>
  <div class="space_edit">
    <div class="custom-tree-container">
      <el-button
        style="margin:10px;"
        size="mini"
        type="primary"
        @click="() => add({id:'-1',copyId:0,parentId:0,canEdit:true,label:'',variable:true})">添加空间节点</el-button>
      <el-tree
        :data="spaceList"
        :expand-on-click-node="false"
        :props="defaultProps"
        node-key="id"
        default-expand-all>
        <span slot-scope="{ node, data }" class="custom-tree-node">
          <a-input
            v-if="data.canEdit"
            :key="data.id"
            :value="data.label"
            style="padding:0;margin:0;height:20px;width:50%;"
            @change="e => handleChange(data,e.target.value)"
          />
          <span v-else>{{ node.label }}</span>
          <span>
            <el-button v-if="!data.canEdit&&!data.allowCreateInterface" slot="reference" size="mini" type="text" icon="el-icon-plus" circle @click="() => add(data)"/>
            <span v-if="data.canEdit">
              <a @click="() => save(data)">保存</a>
              <a-popconfirm title="确认取消?" ok-text="确定" cancel-text="取消" @confirm="() => cancel(data)">
                <a>取消</a>
              </a-popconfirm>
            </span>
            <el-button v-else size="mini" type="text" icon="el-icon-edit" circle @click="() => edit(data)"/>

            <a-popconfirm v-if="!data.canEdit" title="确认删除?" ok-text="确定" cancel-text="取消" @confirm="() => deleteData(data)">
              <el-button size="mini" type="text" icon="el-icon-delete"/>
            </a-popconfirm>
          </span>
        </span>
      </el-tree>
    </div>
  </div>

</template>

<script>
import { mapState } from 'vuex'
import UpdateTree from './../../modal/space/update'
import InsertTree from './../../modal/space/insert'
import DeleteTree from './../../modal/space/delete'
export default {
  data() {
    return {
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      cacheData: []
    }
  },
  computed: {
    ...mapState({
      'spaceList': (state) => {
        console.log('----', state.business.spaceList)
        return state.business.spaceList.filter((v) => {
          if (v.variable) { return v }
        })
      }
    })
  },
  created() {

  },
  mounted() {
    this.$store.dispatch('getSpaceTree', {})
  },
  methods: {
    edit(item) {
      // 同步缓存数据
      this.$data.cacheData = JSON.parse(JSON.stringify(this.spaceList))

      const newData = [...this.spaceList]
      const target = this.getTargetData(newData, item.id)
      if (target) {
        target['canEdit'] = true
        this.$store.commit('UPDATE_SPACE_LIST', newData)
      }
    },
    async save(item) {
      if (!item.label) {
        this.$message('节点不能为空')
        return
      }
      const newData = [...this.spaceList]
      const target = this.getTargetData(newData, item.id)
      if (target) {
        const result = parseInt(item.id) > 0
          ? await UpdateTree({ 'id': item.id, 'label': item.label })
          : await InsertTree({ 'label': item.label, 'parentId': item.copyId })// 父节点的id都被赋值0 所有可以全部使用id

        if (result.resultCode !== '000000') {
          Object.assign(target, this.getTargetData(this.$data.cacheData, item.id))
          this.$message(result.resultMsg)
        }
        delete (target.canEdit)
        this.$store.commit('UPDATE_SPACE_LIST', newData)
        this.$store.dispatch('getSpaceTree', {})
      }
    },
    async deleteData(item) {
      const delete_tree_result = await DeleteTree({ id: item.id })
      delete_tree_result.resultCode === '000000' ? this.$store.dispatch('getSpaceTree', {}) : ''
    },
    add(item) {
      console.log('++', item)
      try {
        const newData = [...this.spaceList]
        debugger
        // 添加缓存
        this.$data.cacheData = JSON.parse(JSON.stringify(newData))
        Object.keys(this.getTargetData(newData, '-1')).length === 0
          ? this.$store.commit('UPDATE_SPACE_LIST', this.setTargetData(newData, item))
          : this.$message('请完成新增项')
      } catch (e) {
        //
      }
    },
    cancel(item) {
      const newData = [...this.spaceList]
      const target = this.getTargetData(newData, item.id)
      if (target) {
        Object.assign(target, this.getTargetData(this.$data.cacheData, item.id))
        delete (target.canEdit)
        var recursion = function(list) {
          list.map((v, k) => {
            v.id === '-1' ? list.splice(k, 1) : ''
            v.children && v.children.length > 0 ? recursion(v.children) : ''
          })
        }

        recursion(newData)
        this.$store.commit('UPDATE_SPACE_LIST', newData)
      }
    },
    getTargetData(data = [], id = '') {
      var result = {}

      // 递归寻找目标节点
      var recursion = function(list) {
        list.map((v) => {
          v.id === id ? result = v : ''
          v.children && v.children.length > 0 ? recursion(v.children) : ''
        })
      }

      recursion(data)
      return result
    },
    setTargetData(data = [], item = '') {
      // 递归寻找目标节点
      var recursion = function(list) {
        list.map((v, k) => {
          if (v.id === item.id) {
            if (!v.children) { v.children = [] }
            v.children.push({
              id: '-1',
              label: '',
              canEdit: true,
              copyId: item.id,
              variable: true,
              parentId: (v && v.parentId && v.parentId) || '0'// 插入父节点
            })
          }
          v.children && v.children.length > 0 ? recursion(v.children) : ''
        })
      }
      recursion(data)
      // 父节点添加
      if (parseInt(item.copyId) === 0) {
        data.splice(0, 0, {
          id: '-1',
          label: '',
          canEdit: true,
          copyId: 0,
          parentId: 0,
          variable: true
        })
      }
      this.$store.commit('UPDATE_SPACE_LIST', data)
      return data
    },
    handleChange(item, e) {
      const newData = [...this.spaceList]
      const target = this.getTargetData(newData, item.id)
      if (target) {
        Object.assign(target, this.getTargetData(this.$data.cacheData, item.id))
        target['label'] = e
        this.$store.commit('UPDATE_SPACE_LIST', newData)
      }
    }
  }
}
</script>

<style>
  .space_edit{
    margin-left:10px;
  }
  .space_edit .el-breadcrumb{
    margin-left:10px;
    height:28px;
  }
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 8px;
  }
  .custom-tree-container{
    border: 1px solid #e6e6e6;
    width:100%;
    min-width:400px;
  }
</style>
