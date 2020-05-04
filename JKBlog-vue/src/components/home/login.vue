<template>

  <div id="loginPage">
    <el-form :model="loginForm" status-icon :rules="rules" ref="loginForm" label-width="100px" class="demo-loginForm">
      <el-form-item label="用户名" prop="user">
<!--
        <el-input type="text" v-model="loginForm.username" @change="setUser()" autocomplete="off"></el-input>
-->
        <el-input type="text" v-model="loginForm.username" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密  码" prop="pass">
        <el-input type="password" v-model="loginForm.password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('loginForm')">提交</el-button>
        <el-button @click="resetForm('loginForm')">注册</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
import login from '@/components/home/login'
export default {
  name: 'login',
  data () {
    var validateUser = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入用户名'))
      } else {
        callback()
      }
    }
    // console.log(validateUser())
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''

      },
      rules: {
        username: [
          { validator: validateUser, trigger: 'blur' }
        ],
        password: [
          { validator: validatePass, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onClickLeft () {
      // 表示返回上一级操作
      this.$router.go('-1')
    },
    submitForm (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // this.$qs.stringify(this.loginForm),{emulateJSON: true}
          this.$nextTick(function () {
            // DOM 现在更新了
            this.bus.$emit('tansferUser', this.loginForm.username)
          })
          this.axios.post('/login', this.loginForm).then(res => {
            const {status, data} = res
            if (status === 200) {
              this.$notify.success({// 弹窗
                title: '登录',
                message: '登录成功！',
                position: 'bottom-right'
              })
              // 登录成功后
              localStorage.setItem('username', this.loginForm.username)
              localStorage.setItem('token', data)
              this.$router.push({name: 'index'})
              // this.$router.push('/user_info')// 跳转页面
            } else if (status === 202) {
              this.$message({
                type: 'error',
                message: '登录失败，用户名或密码错误！'
              })
            } else {
              this.$message({
                type: 'error',
                message: '发生错误，登录失败！'
              })
            }
          })
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm (formName) {
      // this.$refs[formName].resetFields()
      this.$router.push({name: 'register'})
    }
  },
  components: {login}
}
</script>

<style scoped>
#loginPage {
  width: 500px;
  height: 500px;
}
</style>
