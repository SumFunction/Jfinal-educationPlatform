<template>
  <div class="app-container">
    <h2 style="text-align: center">发布新课程</h2>
    <el-steps
      :active="2"
      process-status="wait"
      align-center
      style="margin-bottom: 40px"
    >
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <div class="app-container">
      <el-button size="mini" @click="dialogChapterFormVisible = true">
        添加章节
      </el-button>
      <el-tree
        :data="data"
        :props="defaultProps"
        :filter-node-method="filterNode"
        class="filter-tree"
        :expand-on-click-node="false"
        ><span class="custom-tree-node" slot-scope="{ node, data }">
          <span>{{ node.label }}</span>
          <el-button
            type="text"
            size="mini"
            v-if="data.children"
            @click="showChapter(data)"
          >
            修改章节
          </el-button>

          <el-button
            type="text"
            size="mini"
            v-if="data.children"
            slot="reference"
            @click="deleteChapter(data)"
          >
            删除章节
          </el-button>
          <el-button
            type="text"
            size="mini"
            v-if="data.children"
            @click="showDialogVideo(data)"
          >
            添加小节
          </el-button>
          <el-button
            type="text"
            size="mini"
            v-if="!data.children"
            @click="showVideo(data.id)"
          >
            编辑小节
          </el-button>
          <el-button
            type="text"
            size="mini"
            v-if="!data.children"
            @click="deleteVideo(data.id)"
          >
            删除小节
          </el-button>
        </span>
      </el-tree>
    </div>

    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateChapter">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title" />
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.isFree">
            <el-radio :label="0">免费</el-radio>
            <el-radio :label="1">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API + '/eduvod/uploadVideo'"
            :limit="1"
            class="upload-demo"
          >
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">
                最大支持1G，<br />
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br />
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br />
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br />
                SWF、TS、VOB、WMV、WEBM 等视频格式上传
              </div>
              <i class="el-icon-question" />
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

    <el-form label-width="120px">
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="previous"
          >返回上一步</el-button
        >
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next"
          >保存并下一步</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import chapter from '@/api/edu/chapter'
import video from '@/api/edu/video'
export default {
  data() {
    return {
      saveBtnDisabled: false, // 保存按钮是否禁用
      data: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      chapter: {},
      video: {},
      fileList: [],
      dialogChapterFormVisible: false,
      dialogVideoFormVisible: false,
      BASE_API: process.env.BASE_API
    }
  },
  created() {
    this.init()
  },
  methods: {
    init() {
      chapter.findChapter(this.$route.params.id).then(response => {
        this.data = response.data.items
      })
    },
    next() {//保存跳转到发布页面
      this.$router.push({ path: '/course/publish/' + this.$route.params.id })
    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.indexOf(value) !== -1
    },
    previous() {
      this.$router.push({ path: '/course/editInformation/' + this.$route.params.id })
    },
    saveOrUpdateChapter() {
      if (this.chapter.id != null) {//说明是修改
        this.updateChapter()
      }
      else this.addChapter()
    },
    addChapter() {
      this.chapter.courseId = this.$route.params.id
      chapter.addChapter(this.chapter).then(response => {
        this.$message({
          type: "success",
          message: "添加成功！"
        })
        this.dialogChapterFormVisible = false
        this.init()
      })
    },
    updateChapter() {
      chapter.updateChapter(this.chapter).then(response => {
        this.$message({
          type: "success",
          message: "修改成功！"
        })
        this.dialogChapterFormVisible = false
        this.init()
      })
    },
    showChapter(data) {
      this.dialogChapterFormVisible = true
      chapter.getChapterInfo(data.id).then(response => {
        this.chapter = response.data.item
      })
    },
    deleteChapter(data) {
      this.$confirm('此操作将永久删除该章节,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        chapter.deleteChapter(data.id).then(response => {
          this.$message({
            type: "success",
            message: "删除成功！"
          })
          this.init()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    //==============================视频部分=============================//
    saveOrUpdateVideo() {
      if (this.video.id != null)
        this.updateVideo()
      else
        this.addVideo()
    },
    addVideo() {//添加视频信息
      video.addVideo(this.video).then(response => {
        this.$message({
          type: "success",
          message: "添加成功！"
        })
        this.init()
        this.dialogVideoFormVisible = false
      })


    },
    showDialogVideo(data) {//添加视频弹框操作
      this.fileList = []
      this.video = {}
      this.video.courseId = this.$route.params.id
      this.dialogVideoFormVisible = true
      this.video.chapterId = data.id
    },
    showVideo(id) {//回显视频信息
      this.dialogVideoFormVisible = true
      video.findVideoById(id).then(response => {
        this.video = response.data.item
      })
    },
    updateVideo() {//更新视频信息
      video.updateVideo(this.video).then(response => {
        this.$message({
          type: "success",
          message: "修改成功"
        }),
          this.init()
        this.dialogVideoFormVisible = false
      })
    },
    deleteVideo(id) {//删除视频
      this.$confirm('此操作将永久删除该小节,是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        video.deleteVideoById(id).then(response => {
          this.$message({
            type: 'success',
            message: '成功删除'
          })
          this.init()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },//成功上传视频回显
    handleVodUploadSuccess(response, file, fileList) {
      this.video.videoOriginalName = file.name
      this.video.videoSourceId = response.data.videoId
    },
    //视图上传多于一个视频
    handleUploadExceed(files, fileList) {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
    //点击删除视频的弹框
    handleVodRemove() {
      video.deleteVodById(this.video.videoSourceId).then(response => {
        this.$message({
          type: 'success',
          message: "删除成功!"
        })
        this.fileList = []
        this.video.videoSourceId = ''
        this.video.videoOriginalName = ''
      })
    },
    //点击删除视频之前的回调
    beforeVodRemove(file) {
      return this.$confirm(`确定移除 ${file.name}？`)
    }
  }
}
</script>