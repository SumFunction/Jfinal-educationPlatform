<template>
  <div class="main">
    <div class="title">
      <a href="/login">登录</a>
      <span>·</span>
      <a class="active" href="/register">注册</a>
    </div>
    <div class="sign-up-container">
      <el-form
        :model="ruleForm"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="昵称" prop="nickname">
          <el-input v-model="ruleForm.nickname"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="ruleForm.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="mobile">
          <el-input v-model="ruleForm.mobile"></el-input>
        </el-form-item>
        <el-form-item label="用户签名" prop="sign">
          <el-input type="textarea" v-model="ruleForm.sign"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex">
            <el-radio :label="1">男</el-radio>
            <el-radio :label="2">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-upload
          class="upload-demo"
          :action="this.baseURL + '/eduoss/upload'"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :on-success="successUpload"
          multiple
          :limit="1"
          :file-list="fileList"
        >
          <el-button size="small" type="primary">点击上传</el-button>
          <div slot="tip" class="el-upload__tip" style="display: inline-block">
            只能上传jpg/png文件，且不超过500kb
          </div>
        </el-upload>
        <el-form-item style="margin: 30px auto; text-align: center">
          <el-button type="primary" @click="submitForm">立即创建</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import regist from '@/api/regist'
export default {
  layout: 'sign',
  data() {
    return {
      fileList: [],
      baseURL: process.env.BASE_URL,
      imagecropperShow: false,
      imagecropperKey: 1,
      radio: 1,
      ruleForm: {
        nickname: '',
        password: '',
        mobile: '',
        age: '',
        sign: '',
        sex: 1,
        avatar: '',
      },
      sending: true,      //是否发送验证码
      second: 60,        //倒计时间
      codeTest: '获取验证码'
    }
  },
  methods: {
    successUpload(response) {//头像成功上传回调
      this.ruleForm.avatar = response.data.url
    },
    handlePreview() {
    },
    handleRemove() {
    },
    beforeRemove() {
    },
    submitForm() {
      regist.registUser(this.ruleForm).then(response => {
        this.$message({
          type: 'success',
          message: "注册成功"
        })
        this.$router.push({ path: '/login' })
      })
    },
    cropSuccess(data) {
      this.imagecropperShow = false
      this.teacher.avatar = data.url
      // 上传成功后，重新打开上传组件时初始化组件，否则显示上一次的上传结果
      this.imagecropperKey = this.imagecropperKey + 1
    },
    close() {
      this.imagecropperShow = false
    }
  }
}
</script>
