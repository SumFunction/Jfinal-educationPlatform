<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">搜索结果</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关课程哟，小编正在努力整理中...</span
            >
          </section>
          <!-- /无数据提示 结束-->
          <article class="comm-course-list" v-if="total > 0">
            <ul class="of" id="bna">
              <li v-for="course in items" :key="course.id">
                <div class="cc-l-wrap">
                  <section class="course-img">
                    <img
                      :src="course.cover"
                      class="img-responsive"
                      :alt="course.title"
                    />
                    <div class="cc-mask">
                      <a
                        :href="'/course/' + course.id"
                        title="开始学习"
                        class="comm-btn c-btn-1"
                        >开始学习</a
                      >
                    </div>
                  </section>
                  <h3 class="hLh30 txtOf mt10">
                    <a
                      :href="'/course/' + course.id"
                      :title="course.title"
                      class="course-title fsize18 c-333"
                      >{{ course.title }}</a
                    >
                  </h3>
                  <section class="mt10 hLh20 of">
                    <span class="fr jgTag bg-green" v-if="course.price <= 0">
                      <i class="c-fff fsize12 f-fA">免费</i>
                    </span>
                    <span class="fr jgTag bg-red" v-if="course.price > 0">
                      <i class="c-fff fsize12 f-fA">收费</i>
                    </span>
                    <span class="fl jgAttr c-ccc f-fA" v-if="course.price > 0">
                      <i class="c-999 f-fA">{{ course.buyCount }}人购买</i>
                    </span>
                  </section>
                </div>
              </li>
            </ul>
            <div class="clear"></div>
          </article>
        </div>
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
      </section>
    </section>
    <!-- /课程列表 结束 -->
  </div>
</template>
<script>
import course from '@/api/course'
export default {
  data() {
    return {
      items: [],
      total: 0,
      limit: 8,
      current: 1
    }
  },
  created() {
    this.searchByCourseName()
  },
  methods: {
    searchByCourseName() {
      course.searchByCourseName(this.current, this.limit, this.$route.params.name).then(response => {
        this.items = response.data.items,
          this.total = response.data.total
      })
    },
    changePage(current = 1) {
      this.current = current
      this.searchByCourseName()
    },
  }
};
</script>
