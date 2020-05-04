<template>
  <div id="app">
<!--    <my-header></my-header>-->
    <!--<img src="./assets/logo.png">-->
    <router-view>
    </router-view>

  </div>
</template>

<script>
import login from '@/components/home/login'
// 定义全局的方法
export default {
  name: 'App',
  data () {
    return {
      websock: ''
    }
  },
  created () {
    // 页面刚进入时开启长连接
    this.initWebSocket()
  },
  destroyed: function () {
    // 页面销毁时关闭长连接
    this.websocketclose()
  },
  methods: {
    initWebSocket () { // 初始化weosocket
      const wsuri = 'ws://localhost:8081/ws'// ws地址
      this.websock = new WebSocket(wsuri)
      this.websock.onopen = this.websocketonopen
      this.websock.onerror = this.websocketonerror
      this.websock.onmessage = this.websocketonmessage
      this.websock.onclose = this.websocketclose
    },
    websocketonopen () {
      this.websock.send(JSON.stringify('connect'))
      console.log('WebSocket连接成功')
    },
    websocketonerror (e) { // 错误
      console.log('WebSocket连接发生错误')
    },
    websocketonmessage (e) { // 数据接收
      // const redata = JSON.parse(e.data)
      // 接收数据
      // console.log(redata.value)
    },
    websocketsend (agentData) { // 数据发送
      this.websock.send(agentData)
    },
    websocketclose (e) { // 关闭
      console.log('connection closed (' + e.code + ')')
    }
  },
  components: {login}
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
