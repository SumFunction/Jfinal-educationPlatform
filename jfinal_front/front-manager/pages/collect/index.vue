<template>
  <div id="aCoursesList" class="bg-fa of">
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">我的收藏</span>
        </h2>
      </header>
      <section class="c-sort-box unBr">
        <div>
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="total <= 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam">暂无数据哟...</span>
          </section>
          <div v-if="total > 0">
            <el-table :data="tableData" style="width: 100%">
              <el-table-column label="课程信息" width="230" align="center">
                <template slot-scope="scope">
                  <img
                    style="margin-left: 10px; width: 100%; height: 100%"
                    :src="scope.row.cover"
                    class="cover"
                    @click="gotoCourse(scope.row.id)"
                  />
                  <p>{{ scope.row.title }}</p>
                </template>
              </el-table-column>
              <el-table-column label="讲师姓名" width="230" align="center">
                <template slot-scope="scope">
                  <div slot="reference" class="name-wrapper">
                    <el-tag size="medium">{{ scope.row.teacherName }}</el-tag>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="课程类别" width="230" align="center">
                <template slot-scope="scope">
                  <p>
                    {{ scope.row.subjectLevelOne }} \
                    {{ scope.row.subjectLevelTwo }}
                  </p>
                </template>
              </el-table-column>
              <el-table-column label="价格" align="center" width="230">
                <template slot-scope="scope">
                  <b class="c-yellow" style="font-size: 24px"
                    >￥{{ scope.row.price }}</b
                  >
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center" width="230">
                <template slot-scope="scope">
                  <el-button
                    size="mini"
                    @click.stop="cancelCollect(scope.row.id)"
                    >取消收藏</el-button
                  >
                </template>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </section>
    </section>
    <!-- 公共分页 开始 -->
    <div>
      <div class="paging">
        <el-pagination
          :current-page="current"
          :page-size="limit"
          :total="total"
          style="padding: 30px 0; text-align: center"
          layout="total, prev, pager, next, jumper"
          @current-change="changePage"
        />
      </div>
    </div>
    <!-- 公共分页 结束 -->
  </div>
</template>

<script>
import collect from '@/api/collect'
export default {
  data() {
    return {
      current: 1,
      limit: 3,
      total: 20,
      tableData: []
    }
  }
  ,
  methods: {
    changePage(current) {
      this.current = current
      this.getCollectList()
    },
    getCollectList() {
      collect.getCollectList(this.current, this.limit).then(response => {
        this.tableData = response.data.items
        this.total = response.data.total
      })
    },
    cancelCollect(courseId) {
      collect.cancelCollect(courseId).then(response => {
        this.$message({
          type: "success",
          message: "取消成功！"
        })
        this.getCollectList()
      })
    },
    gotoCourse(courseId) {
      this.$router.push({ path: '/course/' + courseId })
    }
  }, created() {
    this.getCollectList()
  }

}
</script>

<style>
.el-table__row {
  cursor: pointer;
}
.el-table__row img:hover {
  transform: scale(1.05);
  transition-duration: 0.5s;
}
</style>