(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-112e"],{QsIs:function(e,t,r){"use strict";r.r(t);var a=r("X/S6"),s=r("iWu5"),u=r("PLwA"),o={components:{ImageCropper:s.a,PanThumb:u.a},data:function(){return{user:{},BASE_API:"http://101.43.205.252:80",imagecropperShow:!1}},created:function(){var e=this;null!=this.$route.params.id&&a.a.getMemberInfoById(this.$route.params.id).then(function(t){e.user=t.data.item})},methods:{cropSuccess:function(e){this.imagecropperShow=!1,this.user.avatar=e.url,this.imagecropperKey=this.imagecropperKey+1},close:function(){this.imagecropperShow=!1},saveOrUpdate:function(){var e=this;null!=this.$route.params.id?a.a.updateMember(this.user).then(function(t){e.$message({message:"修改成功！",type:"success"}),e.$router.push("/alc/list")}):a.a.addMember(this.user).then(function(t){e.$message({message:"添加成功！",type:"success"}),e.$router.push("/alc/list")})}}},i=r("KHd+"),n=Object(i.a)(o,function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-form",{attrs:{"label-width":"120px"}},[r("el-form-item",{attrs:{label:"用户名"}},[r("el-input",{model:{value:e.user.username,callback:function(t){e.$set(e.user,"username",t)},expression:"user.username"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"密码"}},[r("el-input",{model:{value:e.user.password,callback:function(t){e.$set(e.user,"password",t)},expression:"user.password"}})],1),e._v(" "),0!=e.user.auto||null==this.$route.params.id?r("el-form-item",{attrs:{label:"人员权限"}},[r("el-select",{attrs:{clearable:"",placeholder:"请选择"},model:{value:e.user.auto,callback:function(t){e.$set(e.user,"auto",t)},expression:"user.auto"}},[r("el-option",{attrs:{value:1,label:"讲师管理员"}}),e._v(" "),r("el-option",{attrs:{value:2,label:"分类管理员"}}),e._v(" "),r("el-option",{attrs:{value:3,label:"课程管理员"}})],1)],1):e._e(),e._v(" "),r("el-form-item",{attrs:{label:"管理员头像",method:"post",name:"file"}},[r("pan-thumb",{attrs:{image:e.user.avatar}}),e._v(" "),r("el-button",{attrs:{type:"primary",icon:"el-icon-upload"},on:{click:function(t){e.imagecropperShow=!0}}},[e._v("更换头像\n        ")]),e._v(" "),r("image-cropper",{directives:[{name:"show",rawName:"v-show",value:e.imagecropperShow,expression:"imagecropperShow"}],key:e.imagecropperKey,attrs:{width:300,height:300,url:e.BASE_API+"/eduoss/upload",field:"file"},on:{close:e.close,"crop-upload-success":e.cropSuccess}})],1),e._v(" "),r("el-form-item",[r("el-button",{attrs:{type:"primary"},on:{click:e.saveOrUpdate}},[e._v("保存")])],1)],1)],1)},[],!1,null,null,null);n.options.__file="add.vue";t.default=n.exports},"X/S6":function(e,t,r){"use strict";var a=r("t3Un");t.a={findByPages:function(e,t,r){return Object(a.a)({url:"/eduservice/after/authrity/findByPages?current="+e+"&limit="+t+"&aut="+r,method:"get"})},getAllMember:function(){return Object(a.a)({url:"/eduservice/after/authrity/getAllMember",method:"get"})},deleteMemberById:function(e){return Object(a.a)({url:"/eduservice/after/authrity/deleteMemberById?id="+e,method:"get"})},updateMember:function(e){return Object(a.a)({url:"/eduservice/after/authrity/updateMember",method:"post",data:e})},getMemberInfoById:function(e){return Object(a.a)({url:"/eduservice/after/authrity/getMemberInfoById?id="+e,method:"get"})},addMember:function(e){return Object(a.a)({url:"/eduservice/after/authrity/addMember",method:"post",data:e})}}}}]);