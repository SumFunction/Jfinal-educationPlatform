<template>
  <div class="app-container">
    <el-form label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="user.username" />
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="user.password" />
      </el-form-item>
      <el-form-item
        label="人员权限"
        v-if="user.auto != 0 || this.$route.params.id == null"
      >
        <el-select v-model="user.auto" clearable placeholder="请选择">
          <!--
数据类型一定要和取出的json中的一致，否则没法回填
因此，这里value使用动态绑定的值，保证其数据类型是number
-->
          <el-option :value="1" label="讲师管理员" />
          <el-option :value="2" label="分类管理员" />
          <el-option :value="3" label="课程管理员" />
        </el-select>
      </el-form-item>

      <!-- 讲师头像 -->
      <el-form-item label="管理员头像" method="post" name="file">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="user.avatar" />
        <!-- 文件上传按钮 -->
        <el-button
          type="primary"
          icon="el-icon-upload"
          @click="imagecropperShow = true"
          >更换头像
        </el-button>
        <!--
      v-show：是否显示上传组件
      :key：类似于id，如果一个页面多个图片上传控件，可以做区分
      :url：后台上传的url地址
      @close：关闭上传组件
      @crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API + '/eduoss/upload'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import authrity from '@/api/edu/authrity'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
  components: { ImageCropper, PanThumb },
  data() {
    return {
      user: {},
      BASE_API: process.env.BASE_API,
      imagecropperShow: false
    }
  },
  created() {
    if (this.$route.params.id != null) {
      authrity.getMemberInfoById(this.$route.params.id).then(response => {
        this.user = response.data.item
      })
    }
  },
  methods: {
    cropSuccess(data) {
      this.imagecropperShow = false
      this.user.avatar = data.url
      // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1
    },
    close() {
      this.imagecropperShow = false
    },
    saveOrUpdate() {
      if (this.$route.params.id != null) {
        authrity.updateMember(this.user).then(response => {
          this.$message({
            message: '修改成功！',
            type: 'success'
          });
          this.$router.push("/alc/list")
        })
      } else {
        authrity.addMember(this.user).then(response => {
          this.$message({
            message: '添加成功！',
            type: 'success'
          });
          this.$router.push("/alc/list")
        })
      }
    }
  }


}
</script>
