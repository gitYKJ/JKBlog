import axios from 'axios'
<template>
    <div>
        <a class="ab" @click="add()">添加</a>
        <form>
            // v-bind绑定属性变量 v-model绑定表单数据 v-if @click='可以用表达式' <transition></transition>动画过渡的使用
            <input v-model="name">
        </form>
        <tr v-for="(i,v) in list" :key="i">
            // prevent作用取消a标签的默认行为
            <td><a @click.prevent="deleteData(v.id)">删除</a></td>
        </tr>
    </div>
</template>

<script>
    //导出该组件中的内容
    export default {
        //动态路由数据共享 标识相同参数不同时用 /:id表示路由格式
        //1、A组件把id通过点击传到动态路由中，例：path: /edit/:id
        //2、在B组件中使用this.$route.params.id的js代码获取到A组件传的id参数
        //所有组件都有一个this.$route(路由配置对象)和this.$router(路由对象)对象
        //编程式导航this.$router.push({path:'',name:'',params{id:ID}})
        //修改数据在请求方法中传入this.formData
        //全局导入axios import axios from 'axios' Vue.prototype.axios=axios 设置axios.defaults.baseRUL=''
        name: "testvue",


        //组件中的data是带有返回参数的函数
        data(){
            return{
                name:'',
                list:[]
            }
        },
        //页面加载完自动调用该函数相当于window.onload
        mounted(){
            this.getData()
        },
        //
        methods: {
            add(){
                //js代码修改标识用到编程式导航或者router-link标签
                //在路由配置中找到add名字的路由
              this.$router.push({name:'add'})
            },

            deleteData(ID) {
                if(confirm('Sure?')) {
                    axios.delete(''+ID)
                    //调用成功后进行then处理
                        .then((res)=> {
                            this.getData()
                        })
            }
        },
            getData(){
                axios.get()
                    //调用成功后进行then处理
                    .then((res)=> {
                        const {status,data}=res
                        if(status==200){
                            this.list=data
                        }
                    }
                )
            }
        },
        computed:{},
        watch:{}
    }
</script>

<style scoped>

</style>