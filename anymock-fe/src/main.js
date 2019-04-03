import Vue from 'vue'
import Cookies from 'js-cookie'
import App from './App'
import i18n from './lang' // Internationalization
import 'normalize.css/normalize.css' // A modern alternative to CSS resets
import router from './router'
import store from './store'
import './errorLog' // error log

import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
Vue.use(Antd)

import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/iconfont/iconfont.css'
Vue.use(
  Element,
  {
    size: Cookies.get('size') || 'medium', // set element-ui default size
    i18n: (key, value) => i18n.t(key, value)
  }
)

import VueClipboards from 'vue-clipboard2'
Vue.use(VueClipboards)

// 全局注册echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
import './permission' // permission control

