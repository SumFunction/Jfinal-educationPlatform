<template>
  <div class="main">
    <div class="title">
      <a class="active" href="/login">登录</a>
      <span>·</span>
      <a href="/regist">注册</a>
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
        <el-form-item style="margin: 30px auto; text-align: center">
          <el-button type="primary" @click="submitForm">立即登录</el-button>
        </el-form-item>
      </el-form>
      <!-- 更多注册方式 -->
      <div class="more-sign">
        <h6>社交帐号直接登录</h6>
        <ul>
          <li>
            <a
              id="weixin"
              class="weixin"
              target="_blank"
              href="http://huaan.free.idcfengye.com/api/ucenter/wx/login"
              ><i class="iconfont icon-weixin"
            /></a>
          </li>
          <li>
            <a id="qq" class="qq" target="_blank" href="#"
              ><i class="iconfont icon-qq"
            /></a>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import '~/assets/css/sign.css'
import '~/assets/css/iconfont.css'
import Cookies from 'js-cookie'
import login from '@/api/login'
export default {
  layout: 'sign',
  data() {
    return {
      ruleForm: {
        nickname: '',
        password: ''
      }
    }
  },
  methods: {
    submitForm() {
      login.loginUser(this.ruleForm).then(response => {
        Cookies.set('user_token', response.data.token)
        login.getUserInfo().then(response => {
          this.loginInfo = JSON.stringify(response.data.user)

          //将用户信息记录cookie
          Cookies.set('ucenter', this.loginInfo)
          //跳转页面
          window.location.href = "/";
        })
      })


    }
  }
}
</script>

<style>
.sign .main {
  margin-top: 100px;
}
</style>