<template>
  <div>
    <my-header></my-header>
    <h2>列表内容</h2>
    <el-table
      :data="list"
      style="width: 100%"
      max-height="250" >
      <el-table-column
        fixed
        prop="java"
        label="Java"
        width="150"
      >
      </el-table-column>
      <el-table-column
        prop="python"
        label="Python"
        width="150">
      </el-table-column>
      <el-table-column
        prop="c"
        label="C"
        width="150">
      </el-table-column>
      <el-table-column
        fixed="right"
        label="操作"
        width="120">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="deleteRow(scope.$index, list)"
            type="text"
            size="small">
            移除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'list',
  data () {
    return {
      list: [{
        java: 'newInstace()',
        python: 'py',
        c: 'c'
      }
      ]
    }
  },
  mounted () {
    this.liebiao()
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    liebiao () {
      this.axios.get('/list').then(res => {
        const {status, data} = res
        if (status === 200) {
          console.log(data)
          // this.list = data
        }
      }).catch({})
    }
  }
}
</script>

<style scoped>

</style>
