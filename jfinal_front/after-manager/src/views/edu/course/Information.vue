<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="1"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input
          v-model="courseInfo.title"
          placeholder=" 示例：机器学习项目课：从基础到搭建项目视"
        />
      </el-form-item>
      <!-- 所属分类 TODO -->
      <el-form-item label="课程类别">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类"
          @change="select"
        >
          <el-option
            v-for="subject in subjectNestedList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.value"
            :label="subject.title"
            :value="subject.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程讲师 TODO -->
      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"
          />
        </el-select>
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>
      <!-- 课程封面 TODO -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :action="BASE_API + '/eduoss/upload'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover" />
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number
          :min="0"
          v-model="courseInfo.price"
          controls-position="right"
          placeholder=""
        />
      </el-form-item>

      <el-form label-width="120px">
        <el-form-item>
          <el-button type="primary" @click="next">保存并下一步</el-button>
        </el-form-item>
      </el-form>
    </el-form>
  </div>
</template>
<script>
import teacher from '@/api/edu/teacher'
import subject from '@/api/edu/subject'
import course from '@/api/edu/course'
import Tinymce from '@/components/Tinymce'
const defaultForm = {
  title: '',
  subjectId: '',
  teacherId: '',
  description: '',
  cover: 'https://guli-subjecttemplete.oss-cn-beijing.aliyuncs.com/32%25%29F%7D7%28PB_4LLJ2%60%5BBYL1X.png',
  price: 0
}
export default {
  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfo: defaultForm,
      teacherList: [],
      subjectNestedList: [],
      subSubjectList: [],//二级分类
      BASE_API: process.env.BASE_API
    }
  },
  created() {
    this.courseInfo = { ...defaultForm }
    teacher.getAllTeacher().then(response => {
      this.teacherList = response.data.items
    })
    subject.getSubjectList().then(response => {//得到所有 类别信息
      this.subjectNestedList = response.data.items
      if (this.$route.params.id != null) {
        course.findCourseById(this.$route.params.id).then(response => {//如果是更新课程 则进行回显
          this.courseInfo = response.data.item
          this.bindSubId(this.courseInfo.subjectParentId)
        })
      }
    })//查询所有类别的信息
  },
  methods: {
    update() {
      course.updateCourseInformation(this.courseInfo).then(response => {
        this.$message({
          type: "success",
          message: "修改成功"
        })
        this.$router.push({ path: '/course/Chapter/' + this.courseInfo.id })
      })
    },
    add() {
      course.addCourseInformation(this.courseInfo).then(response => {
        this.$message({
          type: "success",
          message: "添加成功"
        })
        this.$router.push({ path: '/course/Chapter/' + response.data.id })
      })
    }
    ,
    next() {
      console.log(this.$route.params.id)
      if (this.$route.params.id != null)
        this.update()
      else this.add()
    },
    select(id) {
      this.courseInfo.subjectId = ''//避免一级分类出现切换 数据不清空
      this.bindSubId(id)
    },
    bindSubId(id) {//绑定二级分类的id数据
      for (var i = 0; i < this.subjectNestedList.length; i++) {
        if (this.subjectNestedList[i].id === id) {
          this.subSubjectList = this.subjectNestedList[i].childrens
          break
        }
      }
    },
    handleAvatarSuccess(data) {
      this.courseInfo.cover = data.data.url
    },

  }
}
</script>