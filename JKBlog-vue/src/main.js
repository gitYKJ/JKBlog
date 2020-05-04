// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import header from '@/components/common/header'
import message from '@/components/home/message'
import axios from 'axios'
import apiConfig from '../config/api.config'
import qs from 'qs'
// 定义全局变量
Vue.prototype.$qs = qs
axios.defaults.baseURL = apiConfig.baseUrl
Vue.prototype.axios = axios
// 全局使用ElementUI
// vue组件之间传值三种或vuex工具
Vue.prototype.bus = new Vue()
Vue.use(ElementUI)
Vue.component('my-header', header)
Vue.component('message', message)
Vue.config.productionTip = false
// 配置axios支持cookie
axios.defaults.withCredentials = true
/* eslint-disable no-new */
router.beforeEach(function (to, from, next) {
  if (to.meta.needLogin) {
    // 页面是否登录
    if (localStorage.getItem('token')) {
      // 本地存储中是否有token(uid)数据
      next() // 表示已经登录
    } else {
      // next可以传递一个路由对象作为参数 表示需要跳转到的页面
      next({
        name: 'login'
      })
    }
  } else {
    // 表示不需要登录
    next() // 继续往后走
  }
})
new Vue({
  el: '#app',
  router,
  components: { App
  },
  template: '<App/>'
})
