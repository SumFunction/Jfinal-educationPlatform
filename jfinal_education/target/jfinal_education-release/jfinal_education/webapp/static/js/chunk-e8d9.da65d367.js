(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-e8d9"],{qp15:function(t,e,l){},w4pA:function(t,e,l){"use strict";var s=l("qp15");l.n(s).a},"yx/x":function(t,e,l){"use strict";l.r(e);var s={data:function(){return{BASE_API:"http://101.43.205.252:80",OSS_PATH:"https://guli-subjecttemplete.oss-cn-beijing.aliyuncs.com/%E5%88%86%E7%B1%BB%E6%B5%8B%E8%AF%95.xlsx",fileUploadBtnText:"上传到服务器",importBtnDisabled:!1,loading:!1}},methods:{submitUpload:function(){this.$refs.upload.submit()},fileUploadSuccess:function(){this.$message("添加成功！"),this.$router.push("/subject/list")},fileUploadError:function(){this.$message("添加失败！")}}},a=(l("w4pA"),l("KHd+")),i=Object(a.a)(s,function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("div",{staticClass:"app-container"},[l("el-form",{attrs:{"label-width":"120px"}},[l("el-form-item",{attrs:{label:"信息描述"}},[l("el-tag",{attrs:{type:"info"}},[t._v("excel模版说明")]),t._v(" "),l("el-tag",[l("i",{staticClass:"el-icon-download"}),t._v(" "),l("a",{attrs:{href:t.OSS_PATH}},[t._v("点击下载模版")])])],1),t._v(" "),l("el-form-item",{attrs:{label:"选择Excel"}},[l("el-upload",{ref:"upload",attrs:{"auto-upload":!1,"on-success":t.fileUploadSuccess,"on-error":t.fileUploadError,disabled:t.importBtnDisabled,limit:1,action:t.BASE_API+"/eduservice/after/subject/addSubject",name:"file",accept:"application/vnd.ms-excel"}},[l("el-button",{attrs:{slot:"trigger",size:"small",type:"primary"},slot:"trigger"},[t._v("选取文件")]),t._v(" "),l("el-button",{staticStyle:{"margin-left":"10px"},attrs:{loading:t.loading,size:"small",type:"success"},on:{click:t.submitUpload}},[t._v(t._s(t.fileUploadBtnText))])],1)],1)],1)],1)},[],!1,null,null,null);i.options.__file="add.vue";e.default=i.exports}}]);