import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: 'Teacher',
    meta: { title: '讲师管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/edu/teacher/list'),
        meta: { title: '讲师列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Add',
        component: () => import('@/views/edu/teacher/add'),
        meta: { title: '添加讲师', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: 'Edit',
        component: () => import('@/views/edu/teacher/add'),
        meta: { title: '修改讲师', icon: 'tree' },
        hidden: true
      }
    ]
  },
  {
    path: '/subject',
    component: Layout,
    redirect: '/subject/add',
    name: 'Subject',
    meta: { title: '课程分类', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/edu/subject/list'),
        meta: { title: '分类管理', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Add',
        component: () => import('@/views/edu/subject/add'),
        meta: { title: '添加分类', icon: 'table' }
      }
    ]
  },
  {
    path: '/course',
    component: Layout,
    redirect: '/course/list',
    name: 'Course',
    meta: { title: '课程管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/edu/course/list'),
        meta: { title: '课程列表', icon: 'table' }
      },
      {
        path: 'Information',
        name: 'Add',
        component: () => import('@/views/edu/course/Information'),
        meta: { title: '课程添加', icon: 'table' }
      },
      {
        path: 'Chapter/:id',
        name: 'Chapter',
        component: () => import('@/views/edu/course/Chapter'),
        meta: { title: '章节添加', icon: 'table' },
        hidden: true
      },
      {
        path: 'editInformation/:id',
        name: 'Edit',
        component: () => import('@/views/edu/course/Information'),
        meta: { title: '课程修改', icon: 'table' },
        hidden: true
      },
      {
        path: 'publish/:id',
        name: 'Publish',
        component: () => import('@/views/edu/course/publish'),
        meta: { title: '课程发布', icon: 'table' },
        hidden: true
      }
    ]
  },
  {
    path: '/alc',
    component: Layout,
    redirect: '/alc/list',
    name: 'Alc',
    meta: { title: '权限管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/edu/alc/list'),
        meta: { title: '人员列表', icon: 'table' }
      },
      {
        path: 'add',
        name: 'Add',
        component: () => import('@/views/edu/alc/add'),
        meta: { title: '人员添加', icon: 'table' }
      },
      {
        path: 'edit/:id',
        name: 'Edit',
        component: () => import('@/views/edu/alc/add'),
        meta: { title: '人员修改', icon: 'table' },
        hidden: true
      }
    ]
  },
  {
    path: '/sta',
    component: Layout,
    redirect: '/sta/list',
    name: 'Sta',
    meta: { title: '统计管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: 'List',
        component: () => import('@/views/edu/sta/list'),
        meta: { title: '销售统计', icon: 'table' }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  //mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap,
})
