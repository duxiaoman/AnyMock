<template>
  <div class="interface_list">
    <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="0px" class="ruleForm">
      <a-collapse v-model="activeKey">
        <a-collapse-panel key="1" header="基础设置">
          <div class="area">
            <el-form-item label-width="100px" label="接口名称" prop="name">
              <el-input v-model="ruleForm.name" type="input" placeholder="请输入接口名称"/>
            </el-form-item>
            <el-form-item>
              <el-col :span="11">
                <el-form-item label-width="100px" label="请求类型" prop="request_method_type.activeIndex">
                  <el-select v-model="ruleForm.request_method_type.activeIndex" placeholder="请选择请求方法">
                    <el-option
                      v-for="item in ruleForm.request_method_type.list"
                      :label="item.text"
                      :value="item.type"
                      :key="item.type"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item label-width="100px" label="空间" prop="spaceData.path">
                  <el-cascader
                    :props="ruleForm.spaceData.props"
                    :options="ruleForm.spaceData.data"
                    v-model="ruleForm.spaceData.path"
                    expand-trigger="hover"
                    placeholder="空间选择"
                    filterable
                  />
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item label-width="100px" label="接口URL" prop="interface_url">
              <el-input v-model="ruleForm.interface_url" placeholder="请补全接口URL">
                <template slot="prepend">{{ ip_info }}</template>
                <el-button
                  v-clipboard:copy="ip_info+ruleForm.interface_url"
                  v-clipboard:success="copyUrlSuc"
                  v-clipboard:error="copyUrlErr"
                  slot="append"
                  icon="el-icon-dxm-charulianjie"/>
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-col :span="11">
                <el-form-item label-width="100px" label="配置模式" prop="interface_type.activeIndex">
                  <el-select v-model="ruleForm.interface_type.activeIndex" placeholder="请选择请求方法">
                    <el-option
                      v-for="item in ruleForm.interface_type.list"
                      :label="item.text"
                      :value="item.type"
                      :key="item.type"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label-width="100px" label="异步请求">
                  <el-switch
                    v-model="ruleForm.start_async_callback"/>
                </el-form-item>
              </el-col>
              <el-col :span="5">
                <el-form-item label-width="100px" label="是否启用">
                  <el-switch
                    v-model="ruleForm.start_interface"/>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item v-if="ruleForm.interface_type.activeIndex==='2'" label-width="100px" label="switch" prop="branchJumpScript">
              <editor v-model="ruleForm.branchJumpScript" lang="groovy" theme="eclipse" style="border:1px solid #eceef1;" width="100%" height="100" @init="editorInit"/>
            </el-form-item>
          </div>
        </a-collapse-panel>
        <a-collapse-panel key="2" :disabled="false" header="同步响应">
          <div class="area">
            <el-form-item label-width="100px" label="延时" prop="sync_delay_time">
              <el-input v-model="ruleForm.sync_delay_time" placeholder="请输入同步延时时间">
                <template slot="append">ms(毫秒)</template>
              </el-input>
            </el-form-item>
            <el-form-item label-width="100px" label="响应头">
              <div style="width:100%;display:flex;">
                <a-table :columns="header.columns" :data-source="header.data" :pagination="false" bordered>
                  <template v-for="col in ['name','value']" slot-scope="text, record, index" :slot="col">
                    <div :key="col">
                      <template v-if="record.editable">{{ text }}</template>
                      <a-input
                        v-else
                        :key="index"
                        :value="text"
                        style="margin: -5px 0"
                        @change="e => HeaderHandleChange(e.target.value, record.key, col)"
                      />
                    </div>
                  </template>
                  <template slot="operation" slot-scope="text, record, index">
                    <div class="editable-row-operations">
                      <span v-if="record.editable">
                        <el-button size="mini" icon="el-icon-edit" circle @click="() => HeaderEdit(record.key)"/>
                      </span>
                      <span v-else>
                        <a @click="() => HeaderSave(record.key)">保存</a>
                        <a-popconfirm title="确认取消?" ok-text="确定" cancel-text="取消" @confirm="() => HeaderCancel(record.key)">
                          <a>取消</a>
                        </a-popconfirm>
                      </span>
                      <span>
                        <el-button size="mini" icon="el-icon-plus" circle @click="() => HeaderAdd(record.key)"/>
                      </span>
                      <span v-if="header.data.length>1">
                        <a-popconfirm title="确认删除?" ok-text="确定" cancel-text="取消" @confirm="() => HeaderDelete(record.key)">
                          <el-button size="mini" icon="el-icon-minus" circle/>
                        </a-popconfirm>
                      </span>
                    </div>
                  </template>
                </a-table>
              </div>
            </el-form-item>
            <el-form-item v-if="ruleForm.interface_type.activeIndex==='0'" label-width="100px" label="响应内容" prop="responseBody">
              <el-input
                v-model="ruleForm.responseBody"
                :rows="4"
                type="textarea"
                placeholder="请输入响应内容"/>
            </el-form-item>
            <el-form-item v-if="ruleForm.interface_type.activeIndex==='1'" label-width="100px" label="脚本" prop="syncScript">
              <editor v-model="ruleForm.syncScript" lang="groovy" theme="eclipse" style="border:1px solid #eceef1;" width="100%" height="100" @init="editorInit"/>
            </el-form-item>
          </div>
        </a-collapse-panel>
        <a-collapse-panel v-if="ruleForm.start_async_callback" key="3" header="异步响应">
          <div class="area">
            <el-form-item>
              <el-col :span="11">
                <el-form-item label-width="100px" label="请求类型" prop="request_method_type.asyncActiveIndex">
                  <el-select v-model="ruleForm.request_method_type.asyncActiveIndex" placeholder="请选择请求方法类型">
                    <el-option
                      v-for="item in ruleForm.request_method_type.list"
                      :label="item.text"
                      :value="item.type"
                      :key="item.type"
                    />
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="11">
                <el-form-item label-width="100px" label="延时" prop="async_delay_time">
                  <el-input v-model="ruleForm.async_delay_time" placeholder="请输入异步延时时间">
                    <template slot="append">ms(毫秒)</template>
                  </el-input>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item label-width="100px" label="异步URL" prop="async_interface_url">
              <el-input v-model="ruleForm.async_interface_url" placeholder="请输入接口URL"/>
            </el-form-item>
            <el-form-item label-width="100px" label="响应头">
              <div style="width:100%;display:flex;">
                <a-table :columns="header.columns" :data-source="header.asyncData" :pagination="false" bordered>
                  <template v-for="col in ['name','value']" slot-scope="text, record, index" :slot="col">
                    <div :key="col">
                      <template v-if="record.editable">{{ text }}</template>
                      <a-input
                        v-else
                        :key="index"
                        :value="text"
                        style="margin: -5px 0"
                        @change="e => asyncHeaderHandleChange(e.target.value, record.key, col)"
                      />
                    </div>
                  </template>
                  <template slot="operation" slot-scope="text, record, index">
                    <div class="editable-row-operations">
                      <span v-if="record.editable">
                        <el-button size="mini" icon="el-icon-edit" circle @click="() => asyncHeaderEdit(record.key)"/>
                      </span>
                      <span v-else>
                        <a @click="() => asyncHeaderSave(record.key)">保存</a>
                        <a-popconfirm title="确认取消?" ok-text="确定" cancel-text="取消" @confirm="() => asyncHeaderCancel(record.key)">
                          <a>取消</a>
                        </a-popconfirm>
                      </span>
                      <span>
                        <el-button size="mini" icon="el-icon-plus" circle @click="() => asyncHeaderAdd(record.key)"/>
                      </span>
                      <span v-if="header.asyncData.length>1">
                        <a-popconfirm title="确认删除?" ok-text="确定" cancel-text="取消" @confirm="() => asyncHeaderDelete(record.key)">
                          <el-button size="mini" icon="el-icon-minus" circle/>
                        </a-popconfirm>
                      </span>
                    </div>
                  </template>
                </a-table>
              </div>
            </el-form-item>
            <el-form-item v-if="ruleForm.interface_type.activeIndex==='0'" label-width="100px" label="请求内容" prop="callbackRequestBody">
              <el-input
                v-model="ruleForm.callbackRequestBody"
                :rows="4"
                type="textarea"
                placeholder="接口返回体"/>
            </el-form-item>
            <el-form-item v-if="ruleForm.interface_type.activeIndex==='1'" label-width="100px" label="脚本" prop="asyncScript">
              <editor v-model="ruleForm.asyncScript" lang="groovy" theme="eclipse" style="border:1px solid #eceef1;" width="100%" height="100" @init="editorInit"/>
            </el-form-item>
          </div>
        </a-collapse-panel>
        <a-collapse-panel v-if="ruleForm.interface_type.activeIndex==='2'" key="4" header="case-list">
          <a-table :columns="branchScriptList.columns" :data-source="branchScriptList.data" :pagination="false" bordered>
            <template v-for="col in ['name','syncScript','asyncScript']" slot-scope="text, record, index" :slot="col">
              <div :key="col">
                <template v-if="record.editable">{{ text }}</template>
                <a-input
                  v-if="!record.editable&&col==='name'"
                  :key="index"
                  :value="text"
                  :rows="4"
                  style="margin: -5px 0"
                  type="textarea"
                  @change="e => branchHandleChange({key:record.key, column:col,value:e.target.value})"
                />
                <editor
                  v-if="!record.editable&&col!=='name'"
                  :key="index"
                  :value="text"
                  :v-model="text"
                  :param="{key:record.key,column:col}"
                  :change="branchHandleChange"
                  lang="groovy"
                  theme="eclipse"
                  style="border:1px solid #eceef1;"
                  width="100%"
                  height="100"
                  @init="editorInit"/>
              </div>
            </template>
            <template slot="operation" slot-scope="text, record, index">
              <div class="editable-row-operations">
                <span v-if="record.editable">
                  <el-button size="mini" icon="el-icon-edit" circle @click="() => branchEdit(record.key)"/>
                </span>
                <span v-else>
                  <a @click="() => branchSave(record.key)">保存</a>
                  <a-popconfirm title="确认取消?" ok-text="确定" cancel-text="取消" @confirm="() => branchCancel(record.key)">
                    <a>取消</a>
                  </a-popconfirm>
                </span>
                <span>
                  <el-button size="mini" icon="el-icon-plus" circle @click="() => branchAdd(record.key)"/>
                </span>
                <span v-if="branchScriptList.data.lenght>1">
                  <a-popconfirm title="确认删除?" ok-text="确定" cancel-text="取消" @confirm="() => branchDelete(record.key)">
                    <el-button size="mini" icon="el-icon-minus" circle/>
                  </a-popconfirm>
                </span>
              </div>
            </template>
          </a-table>
        </a-collapse-panel>
      </a-collapse>
      <el-form-item style="margin-top:20px;" >
        <el-button :disabled="submitDisabled" :loading="submitLoading" type="primary" @click="submitForm('ruleForm')">{{ submitText }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import CreateInterfae from './../../modal/interface_http/insert'
import InterfaceDetail from './../../modal/interface_http/selectById'
import UpdateInterface from './../../modal/interface_http/update'
import HostInfo from './../../modal/host_info/core'
import ConflictDetection from './../../modal/interface_http/conflictDetection'
export default {
  components: {
    editor: require('./../components/vue3-ace-editor')
    // editor: require('vue2-ace-editor')
  },
  data() {
    return {
      activeKey: '1',
      submitDisabled: false,
      submitLoading: false,
      submitText: '立即创建',
      isUpdate: parseInt(this.$route.params.id) > 0,
      ip_info: 'http://hostname',
      ruleForm: {
        spaceData: {
          data: [],
          path: [],
          props: { value: 'id', label: 'label', children: 'children' }
        },
        // 基础设置模块
        request_method_type: {// 请求方法类型
          activeIndex: '',
          asyncActiveIndex: '',
          list: [
            { type: 'GET', text: 'GET' },
            { type: 'POST', text: 'POST' }
          ]
        },
        interface_url: '',
        async_interface_url: '', // 异步回调url
        interface_type: {// 接口配置模式
          activeIndex: '0',
          list: [
            { type: '0', text: '静态文本' },
            { type: '1', text: 'Groovy' },
            { type: '2', text: 'Groovy(预设switch/case)' }
          ]
        },
        start_async_callback: false, // 是否开启异步请求
        start_interface: true, // 是否开启接口
        name: '', // 接口名称
        // 同步返回模块
        sync_delay_time: '', // 同步延时
        async_delay_time: '', // 异步延时
        responseBody: '',
        callbackRequestBody: '',
        syncScript: '',
        asyncScript: '',
        branchJumpScript: ''// 分支调整脚本
      },
      header: {// header 头部
        columns: [{
          title: 'Key',
          dataIndex: 'name',
          scopedSlots: { customRender: 'name' }
        }, {
          title: 'Value',
          dataIndex: 'value',
          scopedSlots: { customRender: 'value' }
        },
        {
          title: '操作',
          dataIndex: 'operation',
          width: 150,
          scopedSlots: { customRender: 'operation' }
        }],
        data: [
          {
            key: new Date().getTime().toString(),
            name: '',
            value: ''
          }
        ],
        asyncData: [
          {
            key: new Date().getTime().toString(),
            name: '',
            value: ''
          }
        ],
        cacheData: [],
        asyncCacheData: []
      },
      branchScriptList: {
        columns: [],
        tmpColumns: [{
          title: 'case',
          dataIndex: 'name',
          width: 150,
          scopedSlots: { customRender: 'name' }
        }, {
          title: '同步脚本',
          dataIndex: 'syncScript',
          scopedSlots: { customRender: 'syncScript' }
        },
        {
          title: '操作',
          dataIndex: 'operation',
          width: 150,
          scopedSlots: { customRender: 'operation' }
        }],
        asyncTmpColumns: [{
          title: 'case',
          dataIndex: 'name',
          width: 150,
          scopedSlots: { customRender: 'name' }
        }, {
          title: '同步脚本',
          dataIndex: 'syncScript',
          scopedSlots: { customRender: 'syncScript' }
        },
        {
          title: '异步脚本',
          dataIndex: 'asyncScript',
          scopedSlots: { customRender: 'asyncScript' }
        },
        {
          title: '操作',
          dataIndex: 'operation',
          width: 150,
          scopedSlots: { customRender: 'operation' }
        }],
        data: [{
          id: new Date().getTime().toString(),
          name: '',
          syncScript: '',
          asyncScript: ''
        }],
        cacheData: []
      },
      rules: {
        'name': [
          {
            required: true,
            validator: (a, b, c) => {
              if (b.length === 0) { c(new Error('请输入接口名称')) } else { c() }
            }
          }
        ],
        'spaceData.path': [
          {
            required: true,
            validator: (a, b, c) => {
              if (b.length === 0) { c(new Error('请选择空间')) } else { c() }
            }
          }
        ],
        'request_method_type.activeIndex': [
          {
            required: true,
            validator: (a, b, c) => {
              if (!b) { c(new Error('请选择请求方法类型')) } else { c() }
            }
          }
        ],
        'interface_url': [
          {
            required: true,
            validator: async(a, b, c) => {
              if (!b) {
                c(new Error('请补全URL'))
              } else if (!!b && b.length > 0 && (b[0] !== '/' || b.length < 4)) {
                c(new Error('URL首字符位 ‘/’开始,长度为大于3位'))
              } else {
                const { data } = await ConflictDetection({
                  id: this.$route.params.id,
                  uri: b,
                  method: this.$data.ruleForm.request_method_type.activeIndex
                })
                !!data.detectable && !!data.conflict ? c('url 已存在，请重新输入') : c()
              }
            }
          }
        ],
        'async_interface_url': [
          {
            required: true,
            validator: (a, b, c) => {
              if (!b) {
                c(new Error('请输异步URL'))
              } else if (!!b && !/[a-zA-z]+:\/[^\s]*/.test(b)) {
                c(new Error('请输入正确URL格式:http://ip:port/interface_url'))
              } else {
                c()
              }
            }
          }
        ],
        'request_method_type.asyncActiveIndex': [
          {
            required: true,
            validator: (a, b, c) => {
              if (!b) {
                c(new Error('请求方法类型'))
              } else {
                c()
              }
            }
          }
        ],
        'interface_type.activeIndex': [
          {
            required: true,
            validator: (a, b, c) => {
              if (!b) { c(new Error('请选择配置模式')) } else { c() }
            }
          }
        ],
        'branchJumpScript': [
          {
            required: true,
            validator: (a, b, c) => {
              if (!!b && this.$data.ruleForm.request_method_type.activeIndex === '2' && b.length > 0) { c(new Error('请输入switch脚本')) } else { c() }
            }
          }
        ]
      }
    }
  },
  computed: {

  },
  watch: {
    'ruleForm.start_async_callback': function(v, o) {
      v// 动态修改大规模分支分支路由脚本表单显示
        ? this.$data.branchScriptList.columns = this.$data.branchScriptList.asyncTmpColumns
        : this.$data.branchScriptList.columns = this.$data.branchScriptList.tmpColumns
    }
  },
  async mounted() {
    const _this = this
    // 数据备份,注意对象深层嵌套无法使用slice深拷贝
    this.$data.header.cacheData = this.$data.header.data.map(item => ({ ...item }))
    this.$data.header.asyncCacheData = this.$data.header.asyncData.map(item => ({ ...item }))

    const [
      host_info_result,
      space_tree_result
    ] = await Promise.all([
      HostInfo(),
      this.$store.dispatch('getSpaceTree', {})
    ])
    // 初始化ip
    this.$data.ip_info = (function() {
      const { data } = host_info_result
      if (data.urlPrefix) {
        return [data.urlPrefix].join('')
      }
    }())
    // 初始化空间
    this.$data.ruleForm.spaceData.data = (function() {
      var tmp_data = []
      try {
        tmp_data = JSON.parse(JSON.stringify(space_tree_result.data))
      } catch (e) {
        tmp_data = []
      }

      var recursion = function(list) {
        list.map((v, k) => {
          if (v.children && v.children.length === 0) {
            delete (v.children)
          }
          if (!v.allowCreateInterface && (!v.children || v.children.length === 0)) {
            delete (list[k])
          }
          v.children && v.children.length > 0 ? recursion(v.children) : ''
        })
      }

      recursion(tmp_data)
      // 创建时候保留空间不显示
      if (!_this.$data.isUpdate) {
        return tmp_data.filter((v) => {
          if (v.variable) { return v }
        })
      } else {
        return tmp_data
      }
    }())
    // 更新接口
    if (this.$data.isUpdate) {
      const interface_detail_result = await InterfaceDetail({ 'id': this.$route.params.id })
      const interface_detail_data = (interface_detail_result.data && interface_detail_result.data) || []

      this.$data.ruleForm.interface_url = interface_detail_data.requestUri
      this.$data.ruleForm.request_method_type.activeIndex = interface_detail_data.requestMethod
      this.$data.ruleForm.name = interface_detail_data.name
      this.$data.ruleForm.start_async_callback = interface_detail_data.needAsyncCallback
      this.$data.ruleForm.interface_type.activeIndex = (function() {
        var type = 0
        interface_detail_data.configMode === 'TEXT' ? type = '0' : ''
        interface_detail_data.configMode === 'GROOVY' ? type = '1' : ''
        interface_detail_data.configMode === 'GROOVY_TEMPLATE_SWITCH_CASE' ? type = '2' : ''
        return type
      }())
      if (interface_detail_data.responseHeaderList.length > 0) {
        interface_detail_data.responseHeaderList.map((v, k) => {
          v.key = k
          v.editable = true
        })
        this.$data.header.data = interface_detail_data.responseHeaderList
      }
      this.$data.ruleForm.responseBody = interface_detail_data.responseBody
      this.$data.ruleForm.async_interface_url = interface_detail_data.callbackRequestUrl
      this.$data.ruleForm.request_method_type.asyncActiveIndex = interface_detail_data.callbackRequestMethod
      if (interface_detail_data.callbackRequestHeaderList.length > 0) {
        interface_detail_data.callbackRequestHeaderList.map((v, k) => {
          v.key = k
          v.editable = true
        })
        this.$data.header.asyncData = interface_detail_data.callbackRequestHeaderList
      }
      this.$data.ruleForm.callbackRequestBody = interface_detail_data.callbackRequestBody
      this.$data.ruleForm.branchJumpScript = interface_detail_data.branchJumpScript
      this.$data.ruleForm.syncScript = interface_detail_data.syncScript
      this.$data.ruleForm.asyncScript = interface_detail_data.asyncScript
      this.$data.ruleForm.sync_delay_time = interface_detail_data.syncDelay
      this.$data.ruleForm.async_delay_time = interface_detail_data.asyncDelay
      this.$data.ruleForm.start_interface = interface_detail_data.start
      this.$data.ruleForm.spaceData.path = interface_detail_data.path
      if (interface_detail_data.branchScriptList.length > 0) {
        interface_detail_data.branchScriptList.map((v, k) => {
          v.key = k
          v.editable = true
        })
        this.$data.branchScriptList.data = interface_detail_data.branchScriptList
      }
      // 更新按钮初始化
      if (interface_detail_data.variable) {
        this.$data.submitText = '立即更新'
      } else {
        this.$data.submitText = '示例禁止修改'
        this.$data.submitDisabled = true
      }
    }

    // 大规模分支数据初始化
    if (this.$data.ruleForm.start_async_callback) {
      this.$data.branchScriptList.columns = this.$data.branchScriptList.asyncTmpColumns
    } else {
      this.$data.branchScriptList.columns = this.$data.branchScriptList.tmpColumns
    }

    this.$data.branchScriptList.cacheData = this.$data.branchScriptList.data.map(item => ({ ...item }))
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate(async(valid) => {
        if (valid) {
          const request = {
            'requestUri': this.$data.ruleForm.interface_url,
            'requestMethod': this.$data.ruleForm.request_method_type.activeIndex,
            'name': this.$data.ruleForm.name,
            'needAsyncCallback': this.$data.ruleForm.start_async_callback,
            'configMode': (function() {
              return ['TEXT', 'GROOVY', 'GROOVY_TEMPLATE_SWITCH_CASE'][_this.$data.ruleForm.interface_type.activeIndex]
            }()),
            'responseHeaderList': (function() {
              var tmp = []
              const data = JSON.parse(JSON.stringify(_this.$data.header.data))
              data.map((v) => {
                if (v.name) {
                  delete (v.editable)
                  delete (v.key)
                  !!v && tmp.push(v)
                }
              })
              return tmp
            }()),
            'responseBody': this.$data.ruleForm.responseBody,
            'callbackRequestUrl': this.$data.ruleForm.async_interface_url,
            'callbackRequestMethod': this.$data.ruleForm.request_method_type.asyncActiveIndex,
            'callbackRequestHeaderList': (function() {
              var tmp = []
              const data = JSON.parse(JSON.stringify(_this.$data.header.asyncData))
              data.filter((v) => {
                if (v.name) {
                  delete (v.editable)
                  delete (v.key)
                  !!v && tmp.push(v)
                }
              })
              return tmp
            }()),
            description: '',
            'callbackRequestBody': this.$data.ruleForm.callbackRequestBody,
            'branchJumpScript': this.$data.ruleForm.branchJumpScript,
            'syncScript': this.$data.ruleForm.syncScript,
            'asyncScript': this.$data.ruleForm.asyncScript,
            'syncDelay': this.$data.ruleForm.sync_delay_time || 0,
            'asyncDelay': this.$data.ruleForm.async_delay_time || 0,
            'start': this.$data.ruleForm.start_interface,
            'spaceId': (function() { try { return _this.$data.ruleForm.spaceData.path.slice(-1)[0] } catch (e) { return '' } }()),
            'branchScriptList': (function() {
              var tmp = []
              const data = JSON.parse(JSON.stringify(_this.$data.branchScriptList.data))
              data.map((v) => {
                if (!!v.name && !!v.syncScript) {
                  delete (v.editable)
                  delete (v.key)
                  tmp.push(v)
                }
              })
              return tmp
            }())
          }

          this.$data.submitLoading = true
          var tmp_result = {}
          if (this.$data.isUpdate) {
            request.id = this.$route.params.id
            tmp_result = await UpdateInterface(request)
          } else {
            tmp_result = await CreateInterfae(request)
          }
          if (tmp_result.resultCode === '000000') {
            this.$store.commit('UPDATE_SPACE_ID', { spaceId: request.spaceId })
            this.$message({ message: tmp_result.resultMsg, type: 'success' })
          } else {
            this.$message(tmp_result.resultMsg)
          }
          this.$data.submitLoading = false
        }
      })
    },
    copyUrlSuc(e) { // 复制url
      this.$message({ message: '复制成功', type: 'success' })
    },
    copyUrlErr() {
      this.$message('复制失败')
    },
    editorInit: function() {
      require('brace/mode/groovy')
      require('brace/theme/eclipse')
    },
    HeaderHandleChange(value, key, column) {
      const newData = [...this.$data.header.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.$data.header.data = newData
      }
    },
    HeaderEdit(key) {
      const newData = [...this.$data.header.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target.editable = false
        this.$data.header.data = newData
      }
    },
    HeaderSave(key) {
      const newData = [...this.$data.header.data]
      const target = newData.filter(item => key === item.key)[0]
      if (!target.name || !target.value) {
        this.$message('字段不能为空！')
        return
      }
      if (target) {
        target.editable = true
        this.$data.header.data = newData
      }
      this.$data.header.cacheData = JSON.parse(JSON.stringify(this.$data.header.data))
    },
    HeaderCancel(key) {
      const newData = [...this.$data.header.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        Object.assign(target, this.$data.header.cacheData.filter(item => key === item.key)[0])
        target.editable = true
        this.$data.header.data = newData
      }
    },
    HeaderAdd(key) {
      const newData = [...this.$data.header.data]
      const target = newData.filter(item => key === item.key)[0]
      const tmp = this.$data.header.data.slice(-1)
      if (!tmp[0].name || !tmp[0].value) {
        this.$message('请完成新增项填写')
        return
      }
      target.editable = true
      this.$data.header.data = [...this.$data.header.data, {
        key: new Date().getTime().toString(),
        name: '',
        value: ''
      }]
      this.$data.header.cacheData = JSON.parse(JSON.stringify(this.$data.header.data))
    },
    HeaderDelete(key) {
      var newData = [...this.$data.header.data]
      this.$data.header.data = newData.filter((v) => {
        if (v.key !== key) return v
      })
      if (this.$data.header.data.length === 0) {
        this.$data.header.data = [{
          key: new Date().getTime().toString(),
          name: '',
          value: ''
        }]
      }
    },
    asyncHeaderHandleChange(value, key, column) {
      const newData = [...this.$data.header.asyncData]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.$data.header.asyncData = newData
      }
    },
    asyncHeaderEdit(key) {
      const newData = [...this.$data.header.asyncData]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target.editable = false
        this.$data.header.asyncData = newData
      }
    },
    asyncHeaderSave(key) {
      const newData = [...this.$data.header.asyncData]
      const target = newData.filter(item => key === item.key)[0]
      if (!target.name || !target.value) {
        this.$message('字段不能为空！')
        return
      }
      if (target) {
        target.editable = true
        this.$data.header.asyncData = newData
      }
      this.$data.header.asyncCacheData = JSON.parse(JSON.stringify(this.$data.header.asyncData))
    },
    asyncHeaderCancel(key) {
      const newData = [...this.$data.header.asyncData]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        Object.assign(target, this.$data.header.asyncCacheData.filter(item => key === item.key)[0])
        target.editable = true
        this.$data.header.asyncData = newData
      }
    },
    asyncHeaderAdd(key) {
      const newData = [...this.$data.header.asyncData]
      const target = newData.filter(item => key === item.key)[0]
      const tmp = this.$data.header.asyncData.slice(-1)
      if (!tmp[0].name || !tmp[0].value) {
        this.$message('请完成新增项填写')
        return
      }
      target.editable = true
      this.$data.header.asyncData = [...this.$data.header.asyncData, {
        key: new Date().getTime().toString(),
        name: '',
        value: ''
      }]
      this.$data.header.asyncCacheData = JSON.parse(JSON.stringify(this.$data.header.asyncData))
    },
    asyncHeaderDelete(key) {
      var newData = [...this.$data.header.asyncData]
      this.$data.header.asyncData = newData.filter((v) => {
        if (v.key !== key) return v
      })
      if (this.$data.header.asyncData.length === 0) {
        this.$data.header.asyncData = [{
          key: new Date().getTime().toString(),
          name: '',
          value: ''
        }]
      }
    },
    branchHandleChange(param) {
      // value, key, column
      var value = param.value
      var key = param.key
      var column = param.column
      const newData = [...this.$data.branchScriptList.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target[column] = value
        this.$data.branchScriptList.data = newData
      }
    },
    branchEdit(key) {
      const newData = [...this.$data.branchScriptList.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        target.editable = false
        this.$data.branchScriptList.data = newData
      }
    },
    branchSave(key) {
      const newData = [...this.$data.branchScriptList.data]
      const target = newData.filter(item => key === item.key)[0]
      if (!target.name || !target.syncScript) {
        this.$message('字段不能为空！')
        return
      }
      if (target) {
        target.editable = true
        this.$data.branchScriptList.data = newData
      }
      this.$data.branchScriptList.cacheData = JSON.parse(JSON.stringify(this.$data.branchScriptList.data))
    },
    branchCancel(key) {
      const newData = [...this.$data.branchScriptList.data]
      const target = newData.filter(item => key === item.key)[0]
      if (target) {
        Object.assign(target, this.$data.branchScriptList.cacheData.filter(item => key === item.key)[0])
        target.editable = true
        this.$data.branchScriptList.data = newData
      }
    },
    branchAdd(key) {
      const newData = [...this.$data.branchScriptList.data]
      const target = newData.filter(item => key === item.key)[0]
      const tmp = this.$data.branchScriptList.data.slice(-1)
      if (!tmp[0].name || !tmp[0].syncScript) {
        this.$message('请完成新增项填写')
        return
      }
      target.editable = true
      this.$data.branchScriptList.data = [...this.$data.branchScriptList.data, {
        key: new Date().getTime().toString(),
        name: '',
        syncScript: '',
        asyncScript: ''
      }]
      this.$data.branchScriptList.cacheData = JSON.parse(JSON.stringify(this.$data.branchScriptList.data))
    },
    branchDelete(key) {
      var newData = [...this.$data.branchScriptList.data]
      this.$data.branchScriptList.data = newData.filter((v) => {
        if (v.key !== key) return v
      })
      if (this.$data.branchScriptList.data.length === 0) {
        this.$data.branchScriptList.data = [{
          key: new Date().getTime().toString(),
          name: '',
          syncScript: '',
          asyncScript: ''
        }]
      }
    },
    editChange(e) {
      console.log('change---', e)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .interface_list{
    width:100%;
    position: relative;
    padding:0 0 0 15%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: flex-start;
    height:calc(100vh - 64px);
    overflow-y: scroll;
    .tips{
      width:100%;
      height:29px;
      margin-bottom:12px;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      border-left: 3px solid #2395f1;
      padding-left: 10px;
      text-align: center;
      font-weight: 400;
    }
    .el-form{
      width:800px;
    }
    .area{

    }
  }
</style>
<style>
.interface_list .ruleForm{
  width:1000px !important;
}
.area .ant-table-content{
  width:866px;
  margin-right:16px;
}
.interface_list .editable-row-operations span{
  padding-right:10px;
}
</style>

