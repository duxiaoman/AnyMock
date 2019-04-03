import SpaceTree from '../../modal/space/tree'
import InterfaceList from '../../modal/interface_http/selectBySpaceId'
import InterfaceListAll from './../../modal/interface_http/selectAll'
import moment from 'moment'
const business = {
  state: {
    spaceId: '',
    interfaceList: [],
    spaceList: [],
    pagination: {
      total: 0
    }
  },

  mutations: {
    UPDATE_PAINATION: (state, total) => {
      state.pagination.total = total
    },
    UPDATE_INTERFACE_LIST: (state, data) => {
      data.map((v) => {
        v.configMode === 'TEXT' ? v.configMode = 0 : ''
        v.configMode === 'GROOVY' ? v.configMode = 1 : ''
        v.configMode === 'GROOVY_TEMPLATE_SWITCH_CASE' ? v.configMode = 2 : ''
        try { // 接口类型适配
          v.configModeText = ['静态文本', 'Groovy', 'Groovy(预设switch/case)'][v.configMode]
        } catch (e) {
          v.configModeText = '静态模式'
        }
        v.lastUpdateTimeCopy = moment(v.lastUpdateTime).format('YYYY-MM-DD hh:mm:ss')
        return v
      })
      state.interfaceList = data
    },
    UPDATE_SPACE_LIST: (state, data) => {
      state.spaceList = data
    },
    UPDATE_SPACE_ID: (state, data) => {
      state.spaceId = (data && data.spaceId && data.spaceId) || ''
    }
  },

  actions: {
    async getInterfaceListWithId({ commit }, param) {
      commit('UPDATE_SPACE_ID', param)
      const interface_list_result = await InterfaceList(Object.assign({ page: 1, itemsPerPage: 13 }, param))
      commit('UPDATE_PAINATION', interface_list_result.data.total)
      const list = (interface_list_result.data && interface_list_result.data.list && interface_list_result.data.list) || []
      commit('UPDATE_INTERFACE_LIST', list)
      return interface_list_result
    },
    async getInterfaceListAll({ commit }, param) {
      const interface_list_all_result = await InterfaceListAll(Object.assign({
        'page': 1,
        'itemsPerPage': 13
      }, param))
      commit('UPDATE_PAINATION', interface_list_all_result.data.total)
      const list = (interface_list_all_result.data && interface_list_all_result.data.list && interface_list_all_result.data.list) || []
      commit('UPDATE_INTERFACE_LIST', list)
      return interface_list_all_result
    },
    async getSpaceTree({ commit }, param) {
      const space_tree_result = await SpaceTree()
      commit('UPDATE_SPACE_LIST', space_tree_result.data)
      return space_tree_result
    }

  }
}

export default business
