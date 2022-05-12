<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名" />
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="teacherQuery.level"
          clearable
          placeholder="讲师头衔"
        >
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-button type="primary" icon="el-icon-search" @click="queryList"
        >查 询</el-button
      >
      <el-button type="default" @click="resetData">清空</el-button>
    </el-form>
    <el-table
      :data="list"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名称" width="80"> </el-table-column>
      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === "1" ? "高级讲师" : "首席讲师" }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="个人介绍"> </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="changePage"
    />
  </div>
</template>

<script>
import teacher from '@/api/edu/teacher'
export default {
  data() {
    return {
      list: null,//查询到的讲师数据
      limit: 3,//每页限制大小
      current: 1,//当前页
      total: 1,//总记录数
      teacherQuery: {}//查询条件
    }
  },
  created() {
    this.getTeacherList()
  },
  methods: {
    getTeacherList() {
      teacher.getTeacherList(this.current, this.limit, this.teacherQuery).then(response => {
        console.log(response.data)
        this.list = response.data.items
        this.total = response.data.total
      })
    },
    changePage(current = 1) {
      this.current = current
      this.getTeacherList()
    },
    queryList() {//按条件查询讲师
      this.getTeacherList()
    },
    resetData() {//清空讲师
      this.teacherQuery = {}
      this.current = 1
      this.getTeacherList()
    },
    removeDataById(id) {
      this.$confirm('此操作将永久删除该, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        teacher.removeDataById(id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.current = 1
          this.getTeacherList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
}
</script>

<style>
</style>