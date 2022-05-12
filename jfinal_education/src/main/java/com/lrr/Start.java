package com.lrr;

import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.lrr.eduService.bean._MappingKit;
import com.lrr.eduService.interceptor.CrossInterceptor;
import com.lrr.eduService.routes.AdminRoute;
import com.lrr.eduService.routes.FrontRoute;
import com.lrr.eduService.validator.after.AfterValidator;
import com.lrr.eduService.validator.after.AlcValitor;
import com.lrr.eduService.validator.front.FrontLoginValidator;

public class Start extends JFinalConfig{
    Routes routes;
    public static void main(String[] args) {
        UndertowServer.start(Start.class, 8001, true);
    }
    public void configConstant(Constants me) {
        // 配置开发模式，true 值为开发模式
        me.setDevMode(true);

        // 配置 encoding，默认为 UTF8
        me.setEncoding("UTF8");
    }
    public void configRoute(Routes me) {
        this.routes = me;
        //配置后台路由
        /**
         * 扫描后台路由
         */
        me.add(new AdminRoute());
        me.add(new FrontRoute());//前后端路由配置
        me.scan("com.lrr.eduOss.controller");//这两个包为前后端公共服务，全局扫描
        me.scan("com.lrr.eduVod.controller");

    }

    public void configEngine(Engine me) {

    }
    public void configPlugin(Plugins me) {
        String jdbcUrlString="jdbc:mysql://127.0.0.1:3306/jfinal?serverTimezone=UTC";// 数据库连接
        DruidPlugin druidPlugin=new DruidPlugin(jdbcUrlString, "root", "123456");  //本地数据库连接
        //DruidPlugin druidPlugin=new DruidPlugin(jdbcUrlString, "jfinal", "2hHxHfsbXinxW4Bz");//服务器数据库连接
        ActiveRecordPlugin activeRecordPlugin=new ActiveRecordPlugin(druidPlugin);//模型插件
        activeRecordPlugin.setDevMode(true);
        activeRecordPlugin.setShowSql(false);
        activeRecordPlugin.addSqlTemplate("all.sql");
        //添加插件
        _MappingKit.mapping(activeRecordPlugin);
        me.add(druidPlugin);
        me.add(activeRecordPlugin);

        //添加缓存插件
        me.add(new EhCachePlugin());

    }
    public void configInterceptor(Interceptors me) {
        me.add(new CrossInterceptor());//全局跨域 以及 options处理
//        me.add(new FrontLoginValidator()); //验证前台 用户是否登录
//        me.add(new AfterValidator());//验证后台 用户是否登录
        //验证后台 管理员是否登录以及权限放行
    }
    public void configHandler(Handlers me) {}

}
