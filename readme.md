# 项目说明

本项目为一个jfinal课程的课设项目，里面的内容均本着学习的目的，不做商业用途，有啥疑问欢迎联系邮箱572232253@qq.com

**<a href="https://gitee.com/link_L_rong/jfinal_education.git">gitee项目地址</a>**

# 项目技术点

## 后端

* jfinal框架
* 数据库：mysql

## 前端

* vue-admin-template：一个以vue为依托后端管理模板
* nuxt：以vue为基础的前端异步渲染开发框架
* element-ui：UI组件库
* axios：ajax的异步发送工具
* 一些其它杂七杂八的东西

# 项目涉及的功能

## 前台系统部分

阿里云在线播放视频整合

课程购买：支付宝支付功能（该项目为沙箱测试版本，如果后期想真实上线，请去支付宝开发平台申请权限）

> 另外说明，支付的账号密码为本人的沙箱账号密码，若要使用支付功能请邮箱联系，或者你也可以改后端代码改为自己的沙箱账号支付）

课程收藏

课程查询

## 后台系统部分

讲师的增删改查（oss云存储图像技术）

课程的增删改查（云存储视频技术）

课程分类的上传添加（easyexcel读取excel技术点）

人员权限分配及其管理

统计可视化（Echarts）

# 开发依赖安装

## 前端部分

安装下面的依赖前需要提前安装node.js环境（node.js的环境安装建议百度）

<a href="https://blog.csdn.net/weixin_39477597/article/details/87784418?spm=1001.2101.3001.6650.4&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-4-87784418-blog-101292386.pc_relevant_default&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-4-87784418-blog-101292386.pc_relevant_default&utm_relevant_index=7"> node.js详细安装教程</a>

到对应目录下执行命令：npm install（前后台界面均执行此命令）

依赖安装完毕后，将会看到如下目录环境：

**前台界面目录结构：**

<img src="https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220512105858.png" alt="image-20220512105536620" style="zoom:50%;" />

**后台界面目录结构：**

<img src="https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220512113904.png" alt="image-20220512110012839" style="zoom:50%;" />

## 后端部分

**后端系统依赖**：主要基于Maven配置，导入到IDEA即可完成自动安装 记得提前下载Maven

依赖安装完毕后，记得去Start启动类中修改对应的数据库连接

# 项目结构说明

jfinal_education：后端系统代码

- common：一些工具类以及通用方法
- eduOss：里面定义了阿里云图片上传的相关接口及其方法
- eduVod：里面定义了阿里云视频上传以及视频获取等相关接口及其方法
- eduService：项目主要的业务逻辑
  * validator：验证器，主要用来验证是否登录以及注册成功还有后台的权限验证等等
  * interceptor：拦截器，拦截options请求（会报错），以及处理跨域问题
  * routes：前后端路由分离配置
  * bean：对应数据库表的一些类以及自定义用于数据交互类
  * controller：定义数据接口
  * service：定义业务对应的逻辑方法
  * utils：一些工具方法

> 说明，本项目分为前台业务接口处理（front） 以及后台接口处理（after）

final_front：前端系统代码

​	front-manager：前台系统页面代码（nuxt框架）

​    after-manager：后台系统页面代码（vue-element-ui框架）

# 服务器部署篇

## 前台页面系统部署

前台系统采用的是nuxt，故需要使用nuxt自带的服务器进行部署，并利用nginx完成反向代理

**nuxt内网部署**

* 执行到front-manager目录下执行`npm build `命令
* 将<img src="https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220512115628.png" alt="image-20220424190653200" style="zoom:50%;" />这几个文件，拷到服务器的某个文件夹下
* 服务器执行`npm install --production`命令，完成依赖安装 （服务器需要提前安装好npm）
* 执行`npm start`命令，当出现这个->![image-20220424190822942](https://lian-tuchuang.oss-cn-beijing.aliyuncs.com/img/20220512115630.png)说明部署成功啦！

* pm2进程守护

> 说明，上面npm start的方式为前台启动方式，也就是说你小黑框关闭了，服务也就停止了，我们就不能访问了，使用pm2的方式能后台开启进程一直提供服务

> 说明下如何使用pm2进行后台运行项目
>
> **全局安装pm2** npm install -g pm2
>
> **查看pm2的安装路径(就是node将它放到了哪里)**
>
> **将上述的安装路径进行软连接 否则pm2会提示找不到** ln -sf /usr/local/node/bin/pm2 /usr/local/bin/ 
>
> (我的服务器是装在了/usr/local/node/bin/pm2 一定要看清楚装到了哪 否则软连接实际没连接到 会一直提示找不到命令)
>
> 到对应的项目目录下 执行如下命令
>
> pm2 start npm -- start 完成项目启动
>
> **ps：**有可能你启动成功了，然后页面访问502，可能原因为pm2版本过老，需要pm2 upgrade下

**nginx反向代理**

上述部署只能在本地访问，其本质为服务器端的3000端口开了一个服务，不能被外网访问，需要利用nginx完成反向代理，实现公网访问

* 安装nginx（不多说，如果不会装可以自行百度）
* 修改nginx的conf配置文件，修改的内容如下

```conf
	upstream nuxtserver1 {
		server 0.0.0.0:3000; #nuxt的启动端口 
	}
	server
	{
		listen 80; #监听端口
		server_name 127.0.0.1; 
		location / {
			proxy_pass   http://nuxtserver1; #一定要加这个 完成代理配置
			index  index.html index.htm;
		}
	}
```

* 启动nginx，访问公网ip，就能完成前台页面部署

在/usr/local/nginx/sbin 目录下执行

## 后台系统+后端服务器部署

后台系统页面可以通过npm build方式打包成为静态页面，并放入后端服务器的webapp下进行访问，故这两个部署是放在一起的（你也可以单独将这个静态页面利用nginx部署到单独一个服务器）

* 到front-manager目录下执行`npm build`命令，将产生的dist文件夹下的文件全部拷贝到后端系统 webapp目录下
* 剩下的参考jfinal官网undertow的<a href="https://jfinal.com/doc/1-3">打包部署教程</a>
* 将对应压缩包等传到服务器，再利用nginx反向代理一波即可
