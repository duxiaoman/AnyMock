<template>
  <div class="interface_list">
    <a-table
      :columns="columns"
      :data-source="interfaceList"
      :pagination="defalutPagination"
      :loading="loading"
      bordered
    >
      <template slot="protocol">
        <a-dropdown :trigger="['click']">
          <a
            :style="{color: 'rgba(0, 0, 0, 0.85)'}"
            class="ant-dropdown-link"
            href="#"
          > 协议（{{ protocol }}）
            <a-icon type="down" />
          </a>
          <a-menu slot="overlay">
            <a-menu-item
              key="HTTP"
              @click="visibleChange"
            >
              HTTP
            </a-menu-item>
            <a-menu-item
              key="TCP"
              @click="visibleChange"
            >
              TCP
            </a-menu-item>
          </a-menu>
        </a-dropdown>
      </template>
      <template slot="scopedProtocol">
        {{ protocol === 'HTTP' && 'HTTP' ||protocol === 'TCP' && 'TCP' || '-' }}
      </template>

      <template
        slot="requestMethod"
        slot-scope="text"
      >
        {{ protocol === 'HTTP' && text || '-' }}
      </template>

      <template
        slot="operation"
        slot-scope="text, record, index"
      >
        <div
          v-if="record.variable"
          class="editable-row-operations"
        >
          <span style="margin-right:15px;">
            <el-button
              size="mini"
              icon="el-icon-edit"
              circle
              @click="() => Edit(record)"
            />
          </span>
          <span>
            <a-popconfirm
              title="Sure to Delete?"
              @confirm="() => Delete(record)"
            >
              <el-button
                size="mini"
                icon="el-icon-minus"
                circle
              />
            </a-popconfirm>
          </span>
        </div>
        <div
          v-else
          class="editable-row-operations"
        >
          <el-button
            type="text"
            size="mini"
            @click="() => Edit(record)"
          >查看示例</el-button>
        </div>
      </template>
    </a-table>
  </div>
</template>
<script>
import { mapState } from 'vuex'
import Delete from './../../../modal/interface_http/delete'
import TcpDelete from './../../../modal/interface_tcp/delete'
export default {
  data() {
    return {
      defalutPagination: {
      },
      columns: [
        { title: '接口名称', dataIndex: 'name', key: 'name' },
        { title: '接口URL', dataIndex: 'requestUri', key: 'requestUri' },
        {
          dataIndex: 'protocol',
          slots: { title: 'protocol' },
          scopedSlots: { customRender: 'scopedProtocol' },
          width: 150
        },
        { title: '请求方式', width: 100, dataIndex: 'requestMethod', key: 'requestMethod', scopedSlots: { customRender: 'requestMethod' }},
        { title: '配置模式', width: 110, dataIndex: 'configModeText', key: 'configModeText' },
        { title: '最近更新时间', dataIndex: 'gmtModified', key: 'gmtModified', width: '120px' },
        {
          title: '操作',
          key: 'operation',
          scopedSlots: { customRender: 'operation' },
          width: 130
        }
      ]
    }
  },
  computed: {
    ...mapState({
      'interfaceList': state => state.business.interfaceList,
      'spaceId': state => state.business.spaceId,
      'pagination': state => state.business.pagination,
      'protocol': state => state.business.protocol,
      'loading': state => state.business.loading
    })
  },
  watch: {
    'pagination.total': function(total) {
      this.$data.defalutPagination.total = total
    },
    'protocol': function(total) {
      if (total === 'TCP') {
        this.columns = [
          { title: '接口名称', dataIndex: 'name', key: 'name' },
          { title: '接口URL', dataIndex: 'requestUri', key: 'requestUri' },
          {
            dataIndex: 'protocol',
            slots: { title: 'protocol' },
            scopedSlots: { customRender: 'scopedProtocol' },
            width: 150
          },
          { title: '配置模式', width: 110, dataIndex: 'configModeText', key: 'configModeText' },
          { title: '最近更新时间', dataIndex: 'gmtModified', key: 'gmtModified', width: '120px' },
          {
            title: '操作',
            key: 'operation',
            scopedSlots: { customRender: 'operation' },
            width: 130
          }
        ]
      }
      if (total === 'HTTP') {
        this.columns = [
          { title: '接口名称', dataIndex: 'name', key: 'name' },
          { title: '接口URL', dataIndex: 'requestUri', key: 'requestUri' },
          {
            dataIndex: 'protocol',
            slots: { title: 'protocol' },
            scopedSlots: { customRender: 'scopedProtocol' },
            width: 150
          },
          { title: '请求方式', width: 100, dataIndex: 'requestMethod', key: 'requestMethod', scopedSlots: { customRender: 'requestMethod' }},
          { title: '配置模式', width: 110, dataIndex: 'configModeText', key: 'configModeText' },
          { title: '最近更新时间', dataIndex: 'gmtModified', key: 'gmtModified', width: '120px' },
          {
            title: '操作',
            key: 'operation',
            scopedSlots: { customRender: 'operation' },
            width: 130
          }
        ]
      }
    }
  },
  async mounted() {
    if (this.protocol === 'HTTP') {
      this.columns = [
        { title: '接口名称', dataIndex: 'name', key: 'name' },
        { title: '接口URL', dataIndex: 'requestUri', key: 'requestUri' },
        {
          dataIndex: 'protocol',
          slots: { title: 'protocol' },
          scopedSlots: { customRender: 'scopedProtocol' },
          width: 150
        },
        { title: '请求方式', width: 100, dataIndex: 'requestMethod', key: 'requestMethod', scopedSlots: { customRender: 'requestMethod' }},
        { title: '配置模式', width: 110, dataIndex: 'configModeText', key: 'configModeText' },
        { title: '最近更新时间', dataIndex: 'gmtModified', key: 'gmtModified', width: '120px' },
        {
          title: '操作',
          key: 'operation',
          scopedSlots: { customRender: 'operation' },
          width: 130
        }
      ]
    }

    if (this.protocol === 'TCP') {
      this.columns = [
        { title: '接口名称', dataIndex: 'name', key: 'name' },
        { title: '接口URL', dataIndex: 'requestUri', key: 'requestUri' },
        {
          dataIndex: 'protocol',
          slots: { title: 'protocol' },
          scopedSlots: { customRender: 'scopedProtocol' },
          width: 150
        },
        { title: '配置模式', width: 110, dataIndex: 'configModeText', key: 'configModeText' },
        { title: '最近更新时间', dataIndex: 'gmtModified', key: 'gmtModified', width: '120px' },
        {
          title: '操作',
          key: 'operation',
          scopedSlots: { customRender: 'operation' },
          width: 130
        }
      ]
    }

    var interface_list_result = {}
    if (parseInt(this.spaceId) > 0) {
      interface_list_result = await this.$store.dispatch(
        'getInterfaceListWithId',
        {
          'criteria': { 'spaceId': this.spaceId }
        })
    } else {
      interface_list_result = await this.$store.dispatch('getInterfaceListAll', {})
    }
    if (interface_list_result.data && interface_list_result.data.total && interface_list_result.data.total > 0) {
      this.$data.defalutPagination = {
        pageSize: 13,
        current: 1,
        total: interface_list_result.data.total,
        onChange: page => {
          this.defalutPagination.current = page
          this.nextPage(page)
        }
      }
    }
  },
  beforeDestroy() {
    // this.$store.commit('UPDATE_PROTOCOL', 'HTTP')
  },
  methods: {
    headerRowClass() { // 设置列表头样式
      return 'background-color:rgb(246, 246, 246);'
    },
    Edit(item) {
      if (item.id) {
        // this.$store.commit('UPDATE_SPACE_ID', { spaceId: item.spaceId })
        if (this.protocol === 'HTTP') {
          this.$router.push({ name: 'interface_update', params: { id: item.id }})
        }
        if (this.protocol === 'TCP') {
          this.$router.push({ name: 'interface_tcpUpdate', params: { id: item.id }})
        }

        this.$store.commit('UPDATE_NAR_BAR_ACTIVE', [])
      }
    },
    async Delete(item) {
      let delete_result
      await this.$store.commit('UPDATE_LOADING', true)
      if (this.protocol === 'TCP') {
        delete_result = await TcpDelete({ id: item.id })
      } else {
        delete_result = await Delete({ id: item.id })
      }
      await this.$store.commit('UPDATE_LOADING', false)
      this.$message(delete_result.resultMsg)
      delete_result.resultCode === '000000' &&
        this.$store.dispatch('getInterfaceListWithId', {
          criteria: { spaceId: item.spaceId }
        })
    },
    async nextPage(index) {
      var interface_list_result = {}
      if (parseInt(this.spaceId) > 0) {
        interface_list_result = await this.$store.dispatch('getInterfaceListWithId', {
          spaceId: this.spaceId,
          itemsPerPage: this.$data.defalutPagination.pageSize,
          page: index
        })
      } else {
        interface_list_result = await this.$store.dispatch('getInterfaceListAll', {
          itemsPerPage: this.$data.defalutPagination.pageSize,
          page: index
        })
      }
      // 修正总数
      if (interface_list_result.data && interface_list_result.data.total && interface_list_result.data.total > 0) {
        this.$data.defalutPagination.total = interface_list_result.data.total
      }
    },
    copyUrlSuc(e) { // 复制url
      this.$message({ message: '复制成功', type: 'success' })
    },
    copyUrlErr() {
      this.$message('复制失败')
    },
    async visibleChange(value) { // 协议切换
      this.$store.commit('UPDATE_PROTOCOL', value.key)

      if (this.spaceId) {
        await this.$store.dispatch('getInterfaceListWithId', {
          itemsPerPage: this.$data.defalutPagination.pageSize || 13,
          page: this.$data.defalutPagination.current || 1
        })
      } else {
        await this.$store.dispatch('getInterfaceListAll', {
          itemsPerPage: this.$data.defalutPagination.pageSize || 13,
          page: this.$data.defalutPagination.current || 1
        })
      }
    }
  }
}
</script>
<style>
.interface_list {
  min-height: calc(100vh - 80px);
  overflow-y: scroll;
  height: calc(100vh - 80px);
}
</style>

