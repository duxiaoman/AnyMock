import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

/* Layout */
import Layout from './../views/layout/HeaderAsideLayout/index'

export const constantRouterMap = [
  {
    path: '/redirect',
    component: Layout,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404')
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401')
  },
  {
    path: '/resource',
    component: Layout,
    children: [
      {
        name: 'resource_tmp',
        path: '/resource/tmp/:item',
        component: () => import('@/views/resource')
      }
    ]
  },
  {
    path: '/home',
    component: Layout,
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('@/views/home/index')
      }
    ]
  },
  {
    path: '/interface',
    component: Layout,
    children: [
      {
        name: 'interface_list',
        path: '/interface/list',
        component: () => import('@/views/interface/list')
      }
    ]
  },
  {
    path: '/interface',
    component: Layout,
    children: [
      {
        name: 'interface_create',
        path: '/interface/create/:id(\\d+)',
        component: () => import('@/views/interface/create_interface')
      },
      {
        name: 'tcpInterface_create',
        path: '/interface/tcpCreate/:id(\\d+)',
        component: () => import('@/views/interface/tcpCreate_interface')
      }
    ]
  },
  {
    path: '/interface',
    component: Layout,
    children: [
      {
        name: 'interface_update',
        path: '/interface/update/:id(\\d+)',
        component: () => import('@/views/interface/update_interface')
      },
      {
        name: 'interface_tcpUpdate',
        path: '/interface/tcpUpdate/:id(\\d+)',
        component: () => import('@/views/interface/tcpUpdate_interface')
      }
    ]
  },
  {
    path: '/space',
    component: Layout,
    children: [
      {
        path: '/space/edit',
        name: 'space_edit',
        component: () => import('@/views/space/edit')
      }
    ]
  },
  {
    path: '/doc',
    component: Layout,
    children: [
      {
        name: 'doc',
        path: '/doc',
        component: () => import('@/views/doc/index')
      }
    ]
  }
]

// eslint-disable-next-line no-undef
export default new VueRouter({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

