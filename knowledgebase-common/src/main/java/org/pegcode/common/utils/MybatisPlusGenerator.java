package org.pegcode.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        //需要构建一个 代码自动生成器 对象
        AutoGenerator mpg = new AutoGenerator();

        //配置策略
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String propertyPath = System.getProperty("user.dir");//获取当前系统目录
        globalConfig.setOutputDir(propertyPath+"/src/main/java");//输出到哪个目录
        globalConfig.setAuthor("dreampig123");
        globalConfig.setOpen(false);//生成完不打开window文件夹
        globalConfig.setFileOverride(true);//是否覆盖原来生成的
        globalConfig.setServiceName("%sService");//去掉Service 默认的I前缀
        globalConfig.setIdType(IdType.ID_WORKER);//Id生成策略
        globalConfig.setDateType(DateType.ONLY_DATE);//日期类型
        globalConfig.setSwagger2(true);//自动配置swagger文档
        mpg.setGlobalConfig(globalConfig);//把配置好的全局配置放到 代码自动生成器里

        //2、设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/knowledgebase?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //3、包的配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("generator");//在 com.kuang 下生成的新包名hui
        pc.setParent("org.pegcode.common");//生成到这个包下
        pc.setEntity("entity");//在hui下实体类的包名
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("tb_online_document");//设置要映射的数据库表名，此处虽只写了一个参数"user"，但是要知道它可以同时写多个参数

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//下划线驼峰映射
        strategy.setEntityLombokModel(true);//自动lombok
        strategy.setLogicDeleteFieldName("deleted");//逻辑删除字段
        //自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("create_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        //乐观锁
        strategy.setVersionFieldName("version");
        strategy.setRestControllerStyle(true);//驼峰命名
        mpg.setStrategy(strategy);

        mpg.execute();//执行
    }

}
