package com.whyzaa.vspringblog.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Description:
 * date: 2020/7/23 19:25
 *
 * @author whyzaa<br />
 */
public class CodeGenerator {


    /**
     * 代码生成器的配置常量
     */
    private static final String outPutDir = "/src/main/java";
    private static final String dataName = "root";
    private static final String dataPwd = "root";
    private static final String dataUrl = "jdbc:mysql://localhost:3306/vspringblog?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String parentPackage = "com.whyzaa.vspringblog";
    private static final String mapperName = "mapper";
    private static final String serviceName = "service";
    private static final String implName = "service.impl";
    private static final String pojoName = "entity";
    private static final String controllerName = "controller";
    private static final String xmlName = "mapper";
    // 当前工程路径   配合outPutDir使用，例如多模块开发 Demo/test1，Demo/test2
    // projectPath拿到的是Demo路径，把outPutDir设置成/test1即可
    private static final String projectPath = System.getProperty("user.dir");

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = getGlobalConfig();
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = getDataSourceConfig();
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = getPackageConfig();
        mpg.setPackageInfo(pc);

        // 去掉默认的mybatis的xml生成
        // 不设置的话会生成两份xml
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 自定义xml生成路径
        InjectionConfig cfg = getInjectionConfig();
        mpg.setCfg(cfg);

        // 策略配置
        StrategyConfig strategy = getStrategyConfig();
        mpg.setStrategy(strategy);
        mpg.execute();
    }


    /**
     * 全局配置
     *
     * @return
     */
    public static GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(projectPath + outPutDir)
                .setDateType(DateType.ONLY_DATE)
                .setAuthor("whyzaa")
                .setOpen(false)
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                // 覆盖生成的文件
                .setFileOverride(true)
                .setServiceName("%sService");
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setUrl(dataUrl)
                .setDriverName(driverName)
                .setUsername(dataName)
                .setPassword(dataPwd);
    }

    /**
     * 包配置
     *
     * @return
     */
    public static PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent(parentPackage)
                .setMapper(mapperName)
                .setEntity(pojoName)
                .setService(serviceName)
                .setController(controllerName)
                .setServiceImpl(implName);
    }

    /**
     * 策略配置
     *
     * @return
     */
    public static StrategyConfig getStrategyConfig() {
        return new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityTableFieldAnnotationEnable(true)
                .setEntityLombokModel(true)
                .setInclude(scanner("sys_notice").split(","))
                //默认生成全部
                //.setExclude(null)
                .setTablePrefix("sys_")
                .setControllerMappingHyphenStyle(true);
    }

    /**
     * 自定义xml文件生成路径
     * 这里注意会生成两个xml，一个是在你指定的下面，一个是在mapper包下的xml
     * 因为源码中的判断，判断的是tableInfo和pathInfo的xml属性是否为null，这两个类都是默认生成属性的
     * 且对if (null != injectionConfig)自定义生成的判断在默认的前面，所以会生成两遍。
     * 具体可见AbstractTemplateEngine batchOutput()的方法
     *
     * @return
     */
    public static InjectionConfig getInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() { }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 读取控制台内容
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}