import router from './router'
import store from './store'
import { constantRouterMap } from './router'
import _ from 'lodash'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css'// progress bar style
NProgress.configure({ showSpinner: false })// NProgress Configuration

router.beforeEach((to, from, next) => {
  NProgress.start() // start progress bar
  if (to.path === '/') {
    next({ path: '/home' })
    NProgress.done()
    return
  }
  const pathIndex = '/' + to.path.split('/')[1]
  if (_.find(constantRouterMap, { path: pathIndex })) {
    store.commit('UPDATE_NAR_BAR_ACTIVE', [to.fullPath])
    next()
  } else {
    next({ path: '/404', replace: true, query: { noGoBack: true }})
  }
  NProgress.done()
})

