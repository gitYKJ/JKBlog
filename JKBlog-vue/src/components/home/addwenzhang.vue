<template>
  <div>
    <my-header></my-header>
    <editor v-model="contents.content"
            :init="init"
            :disabled="disabled"
            @onClick="onClick">
    </editor>
    <button style="width: 200px; height: 50px" @click="publish()">发表</button>
  </div>
</template>
<script>
import tinymce from 'tinymce/tinymce'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/themes/silver'
//  插件
import 'tinymce/plugins/image' // 上传图片插件
import 'tinymce/plugins/media' // 视频插件
import 'tinymce/plugins/table' // 表格插件
import 'tinymce/plugins/lists' // 列表插件
import 'tinymce/plugins/wordcount' // 字数统计插件
export default {
  components: {
    Editor
  },
  props: {
    value: {
      type: String,
      default: ''
    },
    disabled: {
      type: Boolean,
      default: false
    },
    plugins: {
      type: [String, Array],
      default: 'lists image media table wordcount'
    },
    toolbar: {
      type: [String, Array],
      default: 'undo redo |  formatselect | bold italic forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | lists image media table | removeformat'
    }
  },
  data () {
    return {
      a: '',
      init: {
        language_url: '/static/tinymce/langs/zh_CN.js', // 中文包路径
        language: 'zh_CN', // 中文包名称
        skin_url: '/static/tinymce/skins/ui/oxide', // 默认白色
        // skin_url: '/tinymce/skins/ui/oxide-dark', // 暗色
        height: 600, // 设置高度
        // width: 150, // 设置宽度
        plugins: this.plugins,
        toolbar: this.toolbar,
        branding: false,
        menubar: false,
        // 此处为图片上传处理函数，用了base64的图片上传格式，具体可以参考文档
        images_upload_handler: (blobInfo, success, failure) => {
          const img = 'data:image/jpeg;base64,' + blobInfo.base64()
          success(img)
        }
      },
      contents: {
        content: '',
        type: 'java',
        userId: '2'
      },
      userinfo: {
        user: localStorage.getItem('username'),
        lable: 'python'
        // userId: '2'
      }
    }
  },
  mounted () {
    tinymce.init({})
  },
  methods: {
    // 添加相关的事件
    onClick (e) {
      this.$emit('onClick', e, tinymce)
    },
    publish () {
      this.axios.post('/add', this.contents).then(res => {
        const {status, data} = res
        if (status === 200) {
          alert(data)
        }
      })
      alert('hello world!')
    },
    // 可以添加一些自己的自定义事件，如清空内容
    clearContent () {
      this.content = ''
    }
  }
}
</script>
<style scoped>
</style>
