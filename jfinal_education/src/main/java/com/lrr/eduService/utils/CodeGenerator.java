package com.lrr.eduService.utils;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.druid.DruidPlugin;

import javax.sql.DataSource;

public class CodeGenerator {
    public static void main(String[] args) {

        //设置 base model的包名
        String baseModelPackageName = "com.lrr.eduService.bean.base";
        //设置 base model的物理路径
        String baseModelOutputDir = PathKit.getWebRootPath().replace("webapp","") + "java/com/lrr/eduService/bean/base";

        //设置model的包名
        String modelPackageName = "com.lrr.eduService.bean";
        //设置 model的物理路径
        String modelOutputDir = PathKit.getWebRootPath().replace("webapp","") + "java/com/lrr/eduService/bean";

        Generator gen = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);

        // 设置数据库方言
        gen.setDialect(new MysqlDialect());

        // 在 getter、setter 方法上生成字段备注内容
        gen.setGenerateRemarks(true);

        // 开始生成代码
        gen.generate();
    }
    //获取数据源插件并启动
    public static DataSource getDataSource(){
        DruidPlugin dp = new DruidPlugin("jdbc:mysql://127.0.0.1:3306/jfinal?serverTimezone=UTC","root", "123456");
        dp.start();
        return dp.getDataSource();
    }

}
