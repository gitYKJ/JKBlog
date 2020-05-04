import Vue from 'vue'
import Router from 'vue-router'
// import HelloWorld from '@/components/HelloWorld'
import login from '@/components/home/login.vue'
import index from '@/components/index/index.vue'
import addWZ from '@//components/home/addwenzhang.vue'
import message from '@//components/home/message.vue'
import list from '@//components/home/list.vue'
import register from '@//components/home/register.vue'
Vue.use(Router)
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
export default new Router({
  routes: [
    {
      path: '/login',
      name: 'login',
      component: login
    },
    {
      path: '/',
      name: 'index',
      component: index
    },
    {
      path: '/add',
      name: 'addWZ',
      component: addWZ
    },
    {
      path: '/message',
      name: 'message',
      component: message,
      meta: {
        needLogin: true // 需要加校检判断的路由
      }
    },
    {
      path: '/list',
      name: 'list',
      component: list
    },
    {
      path: '/register',
      name: 'register',
      component: register
    }
  ]
})
