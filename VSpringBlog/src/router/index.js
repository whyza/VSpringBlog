import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '@/views/admin/layout/Layout'
import index from '@/views/front/index'
import Article from '@/views/front/Article'
import ArtCategory from '@/views/front/ArtCategory'
// import UserManager from '@/views/admin/userManager/UserManager'


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

  { path: '/', redirect: '/index', hidden: true }
  ,
  {
    path: '/index',
    component: index
  },
  {
    path: '/Category/:id',
    name: '分类',
    component: ArtCategory
  }, {
    path: '/ArticleDetail/:id',
    name: '文章详情',
    component: Article
  }
  ,
  { path: '/login', component: () => import('@/views/admin/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/admin/404'), hidden: true },
  { path: '*', component: () => import('@/views/admin/404'), hidden: true },

]
//实例化vue的时候只挂载constantRouterMap
export default new Router({
  mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
});


export const asyncRouterMap  = [


  {
    path: '/admin',
    component: Layout,
    redirect: '/admin/index',
    name: 'index',
    meta: { title: '首页', icon: 'index', roles: ['admin'] },
    children: [{
      path: 'index',
      name: 'homeindex',
      component: () => import('@/views/admin/dashboard/index'),
      meta: { title: '首页', icon: 'index' }
    }]
  },
  {
    path: '/article',
    component: Layout,
    name: '文章管理',
    redirect: '/article/articleList',
    meta: { title: '文章管理', icon: 'index', roles: ['admin'] },
    children: [{
      path: 'articleList',
      name: '/article/articleList',
      component: () => import('@/views/admin/articlemanager/articleLists'),
      meta: { title: '文章列表', icon: 'index' }
    }, {
      path: 'addArticle',
      name: '/article/addArticle',
      component: () => import('@/views/admin/articlemanager/addArticle'),
      meta: { title: '添加文章', icon: 'index' }
    }]
  }
  , {
    path: '/userManager',
    component: Layout,
    redirect: '/userManager',
    name: '用户管理',
    meta: { title: '首页', icon: 'index', roles: ['admin'] },

    children: [{
      path: 'userManager',
      name: 'userManager',
      component: () => import('@/views/admin/userManager/UserManager'),
      meta: { title: '用户管理', icon: 'index' }
    }]
  }, {
    path: '/commentManager',
    component: Layout,
    redirect: '/commentManager',
    name: '评论管理',
    meta: { title: '首页', icon: 'index', roles: ['admin'] },

    children: [{
      path: 'commentManager',
      name: 'commentManager',
      component: () => import('@/views/admin/commentmanager/commentManager'),
      meta: { title: '评论管理', icon: 'index' }
    }]
  }, {
    path: '/categoryManager',
    component: Layout,
    redirect: '/categoryManager',
    name: '分类管理',
    meta: { title: '首页', icon: 'index', roles: ['admin'] },

    children: [{
      path: 'categoryManager',
      name: 'categoryManager',
      component: () => import('@/views/admin/dashboard/index'),
      meta: { title: '分类管理', icon: 'index' }
    }]
  }, {
    path: '/dataStatistics',
    component: Layout,
    redirect: '/dataStatistics',
    name: '数据统计',
    meta: { title: '首页', icon: 'index', roles: ['admin'] },

    children: [{
      path: 'dataStatistics',
      name: 'dataStatistics',
      component: () => import('@/views/admin/dashboard/index'),
      meta: { title: '数据统计', icon: 'index' }
    }]
  }
]