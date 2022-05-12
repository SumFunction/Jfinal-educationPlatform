# 后端系统结构说明

service

​	eduOss：阿里云云存储相关实现

​	eduVod：阿里云视频相关实现

​	eduService：后端主要项目代码

​		bean：相关实体类封装

​		controller：后端项目controller存放位置

​			after：后台系统controller存放位置

​			front：前台系统controller存放位置

​		service：与上面对应

​		interceptor：拦截器，主要用来解决全局跨域以及options请求问题

​		validator：

​				after：后端验证器，主要解决验证是否登录以及权限管理

​				front：前端验证器，主要验证用户注册逻辑是否正确以及是否登录

resource:

​	all.sql:用来存放一些很长，并且经常变动的sql语句模板