<template>
  <div>
<!--    <h2>这是头组件！</h2>-->
<!--    <div class="line"></div>-->
    <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="1" @click="addwenzhang()">文章列表</el-menu-item>
      <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">文章资料</a></el-menu-item>
      <el-menu-item index="3" @click="message()">消息中心</el-menu-item>
      <el-submenu index="2">
        <template slot="title">个人中心</template>
<!--        <el-menu-item index="2-1">个人标签</el-menu-item>-->
        <el-submenu index="2-1">
          <template slot="title">个人标签</template>
          <el-menu-item index="2-1-1">java</el-menu-item>
          <el-menu-item index="2-1-2">python</el-menu-item>
          <el-menu-item index="2-1-3">c++</el-menu-item>
        </el-submenu>
        <el-menu-item index="2-2">帐号管理</el-menu-item>
        <el-menu-item index="2-3">积分</el-menu-item>
        <el-submenu index="2-4">
          <template slot="title">文章管理</template>
          <el-menu-item index="2-4-1">文章分类</el-menu-item>
          <el-menu-item index="2-4-2">草稿</el-menu-item>
          <el-menu-item index="2-4-3">文章列表</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-menu-item index="5" @click="writeText">写博客</el-menu-item>
      <el-menu-item index="6" v-model="username">{{username}}</el-menu-item>
    </el-menu>
    <div class="wrap">
      <div v-if="a>0" class="notice" >{{a}}</div>
    </div>
  </div>
</template>

<script>
export default {
  // 不能以html元素导出
  name: 'v-header',
  data () {
    return {
      a: localStorage.getItem('a'),
      // props: ['username']
      username: localStorage.getItem('username'),
      list: [],
      activeIndex: '1',
      activeIndex2: '1'
    }
  },
  mounted () {
  },
  methods: {
    addwenzhang () {
      this.$router.push({name: 'list'})
    },
    writeText () {
      // alert('hello world!')
      this.$router.push({name: 'addWZ'})
    },
    message () {
      this.$router.push({name: 'message'})
    },
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
    }
  }
}
</script>

<style scoped>
  .wrap {
    width: 280px;
    margin-bottom: 10px;
    position: relative;
  }
  .notice {
    width: 20px;
    height: 20px;
    line-height: 20px;
    font-size: 10px;
    color: #fff;
    text-align: center;
    background-color: #f00;
    border-radius: 50%;
    position: absolute;
    right: -2px;
    top: -50px;
  }
</style>
