<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.name" placeholder="讲师名" />
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="courseQuery.status"
          clearable
          placeholder="课程状态"
        >
          <el-option value="Normal" label="已发布" />
          <el-option value="Draft" label="未发布" />
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
      <el-table-column label="序号" width="100" align="center">
        <template slot-scope="scope">
          {{ (current - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column label="课程信息" width="515" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img
                :src="scope.row.cover"
                :alt="scope.row.title"
                width="150px"
              />
            </div>
            <div class="title">
              <a href="">{{ scope.row.title }}</a>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="价格" width="150" align="center">
        <template slot-scope="scope">
          {{
            Number(scope.row.price) === 0
              ? "免费"
              : "¥" + scope.row.price.toFixed(2)
          }}
        </template>
      </el-table-column>

      <el-table-column
        prop="buyCount"
        label="付费学员"
        width="100"
        align="center"
      >
        <template slot-scope="scope"> {{ scope.row.buyCount }}人 </template>
      </el-table-column>
      <el-table-column
        prop="teacherId"
        label="讲师姓名"
        width="150"
        align="center"
      >
        <template slot-scope="scope"> {{ scope.row.teacherId }}</template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="课程状态"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.status == "Normal" ? "已发布" : "未发布" }}</template
        >
      </el-table-column>

      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/editInformation/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit"
              >编辑课程信息</el-button
            >
          </router-link>
          <router-link :to="'/course/Chapter/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit"
              >编辑课程大纲</el-button
            >
          </router-link>
          <el-button
            type="text"
            size="mini"
            icon="el-icon-delete"
            @click="removeCourseById(scope.row.id)"
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
import course from '@/api/edu/course'
export default {
  data() {
    return {
      list: null,//查询到的讲师数据
      limit: 3,//每页限制大小
      current: 1,//当前页
      total: 1,//总记录数
      courseQuery: {}//查询条件
    }
  },
  created() {
    this.getCourseList()
  },
  methods: {
    getCourseList() {
      course.findCourseByPages(this.current, this.limit, this.courseQuery).then(response => {
        this.list = response.data.items
        this.total = response.data.total
      })
    },
    changePage(current = 1) {
      this.current = current
      this.getCourseList()
    },
    queryList() {//按条件查询课程
      this.getCourseList()
    },
    resetData() {//清空讲师
      this.courseQuery = {}
      this.current = 1
      this.getCourseList()
    },
    removeCourseById(id) {
      this.$confirm('此操作将永久删除该, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        course.removeCourseById(id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.current = 1
          this.getCourseList()
        })
        this.changePage();//刷新回到第一页
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