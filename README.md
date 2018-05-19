# 资产管理系统

## 准备工作


- JDK8
- maven3(源码编译环境)
- mysql(数据库)


    项目以Spring组件为核心，集成了Swagger、JPA，数据库采用PostgreSQL。


## 开发指南

### 技能要求

- ***Spring Boot***
- ***Spring Framework***
- ***Spring Fox***
- ***Hibernate Validator***
- ***JPA***
- ***Logback***
- ***Maven***

### 层次结构

    本系统是一个标准的Java Web项目，却又不太一样。

#### 过滤器层

    同传统的过滤器(Filter)一样，每个过滤器需要实现javax.servlet.Filter。所有的过滤器都存放在
    com.phy.license.filter包中。由于Spring Boot内部实现了Servlets容器，它对Web服务的定义
    与传统不同，它没有web.xml配置文件，而是通过Java类来配置，这也是Spring Boot的特征之一。
    而这个类在本系统中是com.phy.license.core.FilterConfiguration。
    
    定义过滤器的关键是生成FilterRegistrationBean，并通过@Bean注解来完成最后一道工序。其中，
    方法名是什么没有任何联系，重要的是方法体内对过滤器的属性设置。

#### 监听器层

    略，同过滤器，不同之处在于所注册的Bean类别为ServletListenerRegeistrationBean。

#### 控制器层

    com.htjn.assetManagement.controller 包中用于定义控制器类。
    
    控制器类的编写主要在于以下几点：

        1. 声明控制器，标注@RestController注解等；
        1. 定义资源，标注@RequestMapping注解等；
        2. 描述资源，标注@Api注解等。。
    
#### 服务层

    com.htjn.assetManagement.service： 定义服务类，标注@Service注解，继承AbstractService类。
    
    必要时，可以通过@Transactional注解处理事务。
    
    必要时，可以通过调用AbstractService的validate()方法进行参数验证。

#### 数据访问层

    com.htjn.assetManagement.repository： 定义数据访问接口。
    
    由于是使用JPA，所以只要声明接口就行了，所有数据访问接口需要继承JpaRepository。
    JpaRepository接口有常用的增删改查接口，若想声明自定义接口，可先网上搜索方法，
    后续会补上。
    
    无须实现此层接口。
    
#### 实体层

    com.htjn.assetManagement.entity： 定义业务实体模型。
    
    此层也与JPA有关，为了实现ORM，每个实体类对应一张数据表，根据E-R图得出的关系模型，
    对实体类的各个字段与列映射上，并加上对应定义与约束注解。
    
#### 其他

    com.htjn.assetManagement.util： 定义工具类或辅助类
    com.htjn.assetManagement.validate： 自定义验证器和相关类
    
    关于 @SLF4J 注解的用法：
        
        首先， IntelliJ Idea 下要安装插件 IntelliJ Lombok plugin；
        其次， 在即将要用的类上进行标注。
        这样， 我们可以不用自己声明日志器而可以直接使用 log。
        例如： log.info("Hello world");
        
        其内部机理是Maven在编译期前会插入一条语句在此类内部。
                
            private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(${classname})
        
        而Idea的插件可以避免在开发期间所带来的声明异常而引起困扰。
    
#### 配置

    核心配置文件： application.properties
    日志配置文件： logback-spring.xml
#### swagger
    访问api接口文档： localhost:8888/swagger-ui.html
    需登录： 用户名:admin 密码：abcd1234    

 **_暂且就这些， 未完待续。_**