<template>
  <div id="aCoursesList" class="bg-fa of">
    <!-- /课程列表 开始 -->
    <section class="container">
      <header class="comm-title">
        <h2 class="fl tac">
          <span class="c-333">全部课程</span>
        </h2>
      </header>
      <section class="c-sort-box">
        <section class="c-s-dl">
          <dl>
            <dt>
              <span class="c-999 fsize14">课程类别</span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li
                  :class="{
                    'current bg-orange': oneIndex == -1,
                  }"
                >
                  <a title="全部" href="#" @click="selectAll">全部</a>
                </li>
                <li v-for="(subject, index) in subjectList" :key="subject.id">
                  <a
                    :title="subject.title"
                    href="#"
                    @click="selectOneSubject(subject.id, index)"
                    :class="{
                      'current bg-orange': oneIndex == index,
                    }"
                    >{{ subject.title }}</a
                  >
                </li>
              </ul>
            </dd>
          </dl>
          <dl>
            <dt>
              <span class="c-999 fsize14"></span>
            </dt>
            <dd class="c-s-dl-li">
              <ul class="clearfix">
                <li
                  v-for="(subject, index) in subSubjectList"
                  :key="subject.id"
                  :class="{
                    'current bg-orange': twoIndex == index,
                  }"
                >
                  <a
                    :title="subject.title"
                    href="#"
                    @click="selectTwoSubject(subject.id, index)"
                    >{{ subject.title }}</a
                  >
                </li>
              </ul>
            </dd>
          </dl>
          <div class="clear"></div>
        </section>
        <div class="js-wrap">
          <section class="fr">
            <span class="c-ccc">
              <i class="c-master f-fM">1</i>/
              <i class="c-666 f-fM">1</i>
            </span>
          </section>
          <section class="fl">
            <ol class="js-tap clearfix">
              <li>
                <a
                  title="默认"
                  href="#"
                  @click="listOrder(-1)"
                  :class="{
                    'current bg-orange':
                      queryCourse.buyCountSort == '' &&
                      queryCourse.priceSort == '',
                  }"
                  >默认</a
                >
              </li>
              <li>
                <a
                  title="购买量"
                  href="#"
                  @click="listOrder(0)"
                  :class="{
                    'current bg-orange': queryCourse.buyCountSort != '',
                  }"
                  >购买量&nbsp; <span>👇</span></a
                >
              </li>
              <li
                :class="{ 'current bg-orange': queryCourse.priceSort != '' }"
                @click="listOrder(1)"
              >
                <a title="价格" href="#"
                  >价格&nbsp;
                  <span>👆</span>
                </a>
              </li>
            </ol>
          </section>
        </div>
        <div class="mt40">
          <!-- /无数据提示 开始-->
          <section class="no-data-wrap" v-if="total == 0">
            <em class="icon30 no-data-ico">&nbsp;</em>
            <span class="c-666 fsize14 ml10 vam"
              >没有相关数据，小编正在努力整理中...</span
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
                      style="height: 10vw"
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
      current: 1,
      limit: 8,
      total: 0,
      items: {},
      queryCourse: {
        subjectId: "",
        subjectParentId: "",
        buyCountSort: "",
        priceSort: ""
      },
      subjectList: [],
      subSubjectList: [],
      oneIndex: -1,
      twoIndex: -1,
    }
  },
  created() {
    this.getCourseList()
    this.getSubjectList()
  },
  methods: {
    changePage(current = 1) {
      this.current = current
      this.getCourseList()
    },
    getCourseList() {
      course.getCourseList(this.current, this.limit, this.queryCourse).then(response => {
        this.total = response.data.total
        this.items = response.data.items
      })
    },
    getSubjectList() {
      course.getSubjectList().then(response => {
        this.subjectList = response.data.items
      })
    },
    selectOneSubject(id, index) {//选中一级标题触发的事件 //1.发送请求更新页面  2.显示二级标题
      this.queryCourse.subjectId = ""
      this.queryCourse.subjectParentId = id
      this.oneIndex = index
      this.twoIndex = -1
      for (var i = 0; i < this.subjectList.length; i++) {
        if (this.subjectList[i].id == id) {
          this.subSubjectList = this.subjectList[i].childrens
        }
      }

      this.queryCourse.subjectParentId = id
      this.current = 1
      this.getCourseList()
    },
    selectTwoSubject(id, index) {//选中二级标题触发的事件 
      this.queryCourse.subjectId = id
      this.twoIndex = index
      this.current = 1
      this.getCourseList()
    },
    selectAll() {//选择全部
      this.queryCourse.subjectParentId = ""
      this.queryCourse.subjectId = ""
      this.oneIndex = -1
      this.twoIndex = -1
      this.subSubjectList = []
      this.current = 1
      this.getCourseList()
    },
    listOrder(index) {
      if (index == -1) {
        this.queryCourse.buyCountSort = ""
        this.queryCourse.priceSort = ""
      }
      if (index == 0)
        this.queryCourse.buyCountSort = "0"
      if (index == 1)
        this.queryCourse.priceSort = "1"
      this.current = 1
      this.getCourseList()
    }
  }
};
</script>
