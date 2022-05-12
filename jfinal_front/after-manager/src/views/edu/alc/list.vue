<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-select clearable v-model="aut" placeholder="权限查询">
          <el-option :value="1" label="讲师管理员" />
          <el-option :value="2" label="课程管理员" />
          <el-option :value="3" label="类别管理员" />
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
      <el-table-column prop="username" label="名称" width="80">
      </el-table-column>
      <el-table-column prop="password" label="密码"> </el-table-column>
      <el-table-column label="管理权限" width="100">
        <template slot-scope="scope">
          <span v-if="scope.row.auto === 0">全局管理员</span>
          <span v-if="scope.row.auto === 1">讲师管理员</span>
          <span v-if="scope.row.auto === 2">分类管理员</span>
          <span v-if="scope.row.auto === 3">课程管理员</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/alc/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeDataById(scope.row.id, scope.row)"
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
import authrity from '@/api/edu/authrity'
export default {
  data() {
    return {
      list: null,//查询到的讲师数据
      limit: 8,//每页限制大小
      current: 1,//当前页
      total: 1,//总记录数
      aut: "",//-1代表正常分页
      list: []
    }
  },
  created() {
    this.findByPages()
  },
  methods: {
    findByPages() {
      authrity.findByPages(this.current, this.limit, this.aut).then(response => {
        this.list = response.data.items
      })
    },
    changePage() {

    },
    queryList() {
      this.findByPages()
    },
    resetData() {
      this.aut = ""
      this.current = 1
      this.findByPages()
    },
    removeDataById(id, user) {
      if (user.auto === 0) {
        this.$message({
          type: 'error',
          message: '这可是你自己，删除不了呢!'
        });
        return
      }
      this.$confirm('此操作将永久删除该, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        authrity.deleteMemberById(id).then(response => {
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
          this.current = 1
          this.findByPages()
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

