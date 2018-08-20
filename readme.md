## 项目总结

品优购商城项目

### 项目简介

**来源**：

2017.7黑马web49期

**项目使用的架构是**：

* 分布式
* SOA

**所用技术**：

 *  SSM框架
 *  zookeeper（服务注册中心）
 *  dobbox（远程调用服务）
 *  AngularJs（js封装框架）
 *  Redis（缓存）
*  Spring Data Redis（spring对调用redis的封装）
 *  spring security（安全框架）
 *  fastDFS（图片服务器）
 *  Solr（搜索）
 *  spring-data-solr（spring对调用solr的封装）
 *  freemarker（网页静态化）
 *  ActiveMQ（消息中间件，“JMS”）
 *  SpringJms（spring对调用jms的封装）
 *  Spring Boot
 *  阿里大于（短信服务）
 *  待补充

### 项目结构

> **`pinyougou-parent`** ：父工程，管理依赖，定义项目所需依赖（未使用），各模块再根据实际情况进行实际使用，打包方式：`pom`
>
> > **`pinyougou-pojo`**：通用实体类模块，打包方式：`jar`
> >
> > 依赖：`spring-data-solr`
>
>
>
> > **`pinyougou-dao`**：通用数据访问模块，打包方式：`jar`
> >
> > 依赖：`mybatis` `pinyougou-pojo` `mysql-connector-java ` `druid`
>
>
>
> > **`pinyougou-coommon`** ：通用工具类模块，打包方式：`jar`
> >
> > 依赖：`fileupload` `fastDFS` `Redis`
>
>
>
> > **`pinyougou-solr-util`** ：品优购-批量数据导入solr，打包方式：`jar`
> >
> > 依赖：`pinyougou-dao` `spring`
>
>
>
> > **`pinyougou-sellergoods-interface`**：商家商品服务接口模块 ，打包方式：`jar`
> >
> > 依赖：`pinyougou-pojo`
>
>
>
> > **`pinyougou-content-interface`**：广告服务接口模块，打包方式：`jar`
> >
> > 依赖：`pinyougou-pojo`
>
>
>
> > **`pinyougou-search-interface`**：搜索服务接口模块，打包方式：`jar`
> >
> > 依赖：`pinyougou-pojo`
>
>
>
> > **`pinyougou-page-interface`**：网页静态化生成服务接口模块，打包方式：`jar`
> >
> > 依赖：`pinyougou-pojo`
>
>
>
> > **`pinyougou-user-interface`**：用户服务接口模块，打包方式：`jar`
> >
> > 依赖：`pinyougou-pojo`
>
>
>
> > **`pinyougou-sellergoods-service`**：商家商品服务模块，打包方式：`war`，tomcat端口：`9001`，dubbo协议在哪个端口暴露服务: `20881`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-sellergoods-interface`
>
>
>
> > **`pinyougou-content-service`**：广告服务模块，打包方式：`war`，tomcat端口：	 `9002`，dubbo协议在哪个端口暴露服务: `20882`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-content-interface`
>
>
>
> > **`pinyougou-search-service`**：搜索服务模块，打包方式：`war`，tomcat端口：	 `9004`，dubbo协议在哪个端口暴露服务: `20884`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-search-interface` `activemq-client` `solr`
>
>
>
> > **`pinyougou-page-service`**：网页静态化生成服务模块，打包方式：`war`，tomcat端口：	 `9005`，dubbo协议在哪个端口暴露服务: `20885`
> >
> > 依赖：`spirng` ~~dubbox~~ `pinyougou-dao` `pinyougou-coommon` `pinyougou-page-interface` `freemarker` `activemq-client`
> >
> > 不用dubbox，使用activeMQ，与其他模块进行交互
>
>
>
> > **`pinyougou-user-service`**：用户服务接口模块，打包方式：`war`，tomcat端口：`9006`，dubbo协议在哪个端口暴露服务: `20886`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-user-interface`  `activemq-client`
>
>
>
> > **`pinyougou-manager-web`**：运营商管理后台，打包方式：`war`，tomcat端口：`9101`
> >
> > 依赖：`spring`  `springmvc`  `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface` `SpringSecurity `    ~~pinyougou-search-interface~~   `pinyougou-content-interface`   ~~pinyougou-page-interface~~ `activemq-client`
> >
> > 使用消息中间件，进行管理：`pinyougou-search-interface` `pinyougou-page-interface`
>
>
>
> > **`pinyougou-shop-web`**：商家管理后台，打包方式：`war`，tomcat端口：`9102`
> >
> > 依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface` `SpringSecurity ` 
>
>
>
> >**`pinyougou-portal-web`**：商城首页，打包方式：`war`，tomcat端口：`9103`
> >
> >依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-content-interface`
>
>
>
> > **`pinyougou-search-web`**：搜索web项目，打包方式：`war`，tomcat端口：`9104`
> >
> > 依赖：`spring` `springmvc`   `dubbox` `pinyougou-common` `pinyougou-search-interface ` 
>
>
>
> > **`pinyougou-page-web`**：商品详情页web项目，打包方式：`war`，tomcat端口：`9105`
> >
> > 依赖：`spring` `springmvc`  `dubbox` `pinyougou-common` `pinyougou-page-interface` 
>
>
>
> > **`pinyougou-user-web`**：用户中心web项目，打包方式：`war`，tomcat端口：`9108`
> >
> > 依赖：`spring` `springmvc`  `dubbox` `pinyougou-common` `pinyougou-user-interface` 
>



> **`giovani-sms-service`**：短信发送微服务，使用springboot搭建，打包方式：`jar`
>
> 依赖：`activeMQ` `阿里大于` `spring-boot-starter-web`
>
> 监听的消息发布者：`sms`

### 错误总结：

1. **在注册中心找不到对应的服务**

   ```
   Caused by: org.springframework.beans.factory.BeanInitializationException: Failed to init remote service reference at filed brandService in class com.pinyougou.manager.controller.BrandController; nested exception is java.lang.IllegalStateException: Failed to check the status of the service com.pinyougou.sellergoods.service.BrandService. No provider available for the service com.pinyougou.sellergoods.service.BrandService from the url zookeeper://192.168.25.130:2181/com.alibaba.dubbo.registry.RegistryService?application=pinyougou-manager-web&dubbo=2.8.4&interface=com.pinyougou.sellergoods.service.BrandService&methods=findAll&pid=6248&revision=1.0-SNAPSHOT&side=consumer&timestamp=1533375502674 to the consumer 192.168.234.1 use dubbo version 2.8.4
   	at com.alibaba.dubbo.config.spring.AnnotationBean.postProcessBeforeInitialization(AnnotationBean.java:253)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.applyBeanPostProcessorsBeforeInitialization(AbstractAutowireCapableBeanFactory.java:408)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1570)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545)
   	... 32 more
   ```

   > 这种错误是服务层代码没有成功注册到注册中心导致，请检查一下你的服务层代码是否添加了`@service`注解，并且该注解的包一定是`com.alibaba.dubbo.config.annotation`包，不是`org.springframework.stereotype.Service`，这个地方极容易出错。另外还有一个原因就是你的服务层工程由于某些原因没有正常启动，也无法注册到注册中心里。

2.  **服务无法注册到注册中心的错误原因1**

   ```
   17:35:58,115  WARN XmlWebApplicationContext:546 - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tbAddressMapper' defined in URL [jar:file:/D:/Repositories/MavenRepository/com/pinyougou/pinyougou-dao/1.0-SNAPSHOT/pinyougou-dao-1.0-SNAPSHOT.jar!/com/pinyougou/mapper/TbAddressMapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. Cause: org.apache.ibatis.builder.BuilderException: Wrong namespace. Expected 'com.pinyougou.mapper.TbAddressMapper' but found 'com.pinyougou.com.mapper.TbAddressMapper'.
   17:35:58,118 ERROR ContextLoader:353 - Context initialization failed
   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tbAddressMapper' defined in URL [jar:file:/D:/Repositories/MavenRepository/com/pinyougou/pinyougou-dao/1.0-SNAPSHOT/pinyougou-dao-1.0-SNAPSHOT.jar!/com/pinyougou/mapper/TbAddressMapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. Cause: org.apache.ibatis.builder.BuilderException: Wrong namespace. Expected 'com.pinyougou.mapper.TbAddressMapper' but found 'com.pinyougou.com.mapper.TbAddressMapper'.
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1578)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482)
   	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306)
   	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
   	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302)
   	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197)
   	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:753)
   	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:839)
   	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:538)
   	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:446)
   	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:328)
   	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:107)
   	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4939)
   	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5434)
   	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
   	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
   	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
   	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
   	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
   	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
   	at java.lang.Thread.run(Thread.java:748)
   ```

   > ```
   > Wrong namespace. Expected 'com.pinyougou.mapper.TbAddressMapper' but found 'com.pinyougou.com.mapper.TbAddressMapper'.
   > ```
   >
   > 主要是因为idea复制逆向工程生成的代码是，复制的路径出了问题，检查mapper.xml文件
   >
   > `<mapper namespace="com.pinyougou.com.mapper.TbAddressMapper">` 
   >
   > 名称空间是否正确。以上是错误的，改为
   >
   > `<mapper namespace="com.pinyougou.mapper.TbAddressMapper">`

3. **服务无法注册到注册中心的错误原因2**

   ```
   17:48:18,593  WARN XmlWebApplicationContext:546 - Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tbAddressMapper' defined in URL [jar:file:/D:/Repositories/MavenRepository/com/pinyougou/pinyougou-dao/1.0-SNAPSHOT/pinyougou-dao-1.0-SNAPSHOT.jar!/com/pinyougou/mapper/TbAddressMapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. Cause: java.lang.IllegalArgumentException: XML fragments parsed from previous mappers already contains value for com.pinyougou.mapper.TbAddressMapper.Example_Where_Clause
   17:48:18,596 ERROR ContextLoader:353 - Context initialization failed
   org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'tbAddressMapper' defined in URL [jar:file:/D:/Repositories/MavenRepository/com/pinyougou/pinyougou-dao/1.0-SNAPSHOT/pinyougou-dao-1.0-SNAPSHOT.jar!/com/pinyougou/mapper/TbAddressMapper.class]: Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: org.apache.ibatis.builder.BuilderException: Error parsing Mapper XML. Cause: java.lang.IllegalArgumentException: XML fragments parsed from previous mappers already contains value for com.pinyougou.mapper.TbAddressMapper.Example_Where_Clause
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.initializeBean(AbstractAutowireCapableBeanFactory.java:1578)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:545)
   	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:482)
   	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:306)
   	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:230)
   	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:302)
   	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:197)
   	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:753)
   	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:839)
   	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:538)
   	at org.springframework.web.context.ContextLoader.configureAndRefreshWebApplicationContext(ContextLoader.java:446)
   	at org.springframework.web.context.ContextLoader.initWebApplicationContext(ContextLoader.java:328)
   	at org.springframework.web.context.ContextLoaderListener.contextInitialized(ContextLoaderListener.java:107)
   	at org.apache.catalina.core.StandardContext.listenerStart(StandardContext.java:4939)
   	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5434)
   	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
   	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1559)
   	at org.apache.catalina.core.ContainerBase$StartChild.call(ContainerBase.java:1549)
   	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
   	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
   	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
   	at java.lang.Thread.run(Thread.java:748)
   ```

   > 引用: [mybatis(错误一) 项目启动时报“Result Maps collection already contains value forxxx”的解决方案]([https://blog.csdn.net/zengdeqing2012/article/details/46340357]())
   >
   > 原因：下载了个可以直接运行的eclipse 项目，再与之前的项目对比，好像也没什么不同。最后使绝招：代码对比工具，发现生成的PetMapper.xml多了几百行！原来是我在运行时没有把之前已经生成的PetMapper.xml删除掉，再次生成代码时，又附加了上去！所以在运行代码生成之前，要把以前已经生成的xml文件清掉，以防出错。

4. 注意，`angularJs`中`ng-model`属性不可定义空值

5. 注意，写完`Contrller`记得加注解`@requestMapping()`

6. 注意，虚拟机中的`liunx`可能ip地址会自动更改，导致dobbox服务注册不上

7. 注意，页面功能不跟预期一致，可能是浏览器缓存导致

8. `select2`下拉列表插件，出现`loding`加载动画，导致选择异常问题。查看对应的数据库表中是否有`null`值

9. 注意，不要在`service`层处理异常信息，即`catch`异常。异常处理放在`controller`层

10. 注意，要先启动服务层项目，避免控制层项目找不到服务层相关代码

11. `angularJS1` 异常`Error: [$injector:unpr]` 和 `Error: [ng:areq]`：

     跑这个异常说明注入内容有问题，遇到类似异常，检查注入内容是否正确！！ `controller`与`service`之间的注入有问题。检查文件导入是否有问题

12. `dobbox服务提供者` 注册不上，但有没有报错误信息

    > 加入log4j，查看控制台输出信息
    >
    > 如没有一下相关信息输出，表明没有服务没有成功注册上。打开`doboox-admin服务管理`，发现没有注册上

    ```
    18:13:09,209  INFO ZkEventThread:64 - Starting ZkClient event thread.
    18:13:18,238  INFO ZooKeeper:100 - Client environment:zookeeper.version=3.4.7-1713338, built on 11/09/2015 04:32 GMT
    18:13:18,239  INFO ZooKeeper:100 - Client environment:host.name=Giovani-Home
    18:13:18,240  INFO ZooKeeper:100 - Client environment:java.version=1.8.0_161
    18:13:18,240  INFO ZooKeeper:100 - Client environment:java.vendor=Oracle Corporation
    18:13:18,241  INFO ZooKeeper:100 - Client environment:java.home=F:\giovani-applicaion\jdk1.8.0_161\jre
    18:13:18,241  INFO ZooKeeper:100 - Client environment:java.class.path=F:\giovani-applicaion\apache-maven-3.3.9\boot\plexus-classworlds-2.5.2.jar;F:\giovani-applicaion\IntelliJ IDEA 2018.1\lib\idea_rt.jar;C:\Users\Administrator\.IntelliJIdea2018.1\system\captureAgent\debugger-agent.jar
    18:13:18,242  INFO ZooKeeper:100 - Client environment:java.library.path=F:\giovani-applicaion\jdk1.8.0_161\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;F:\giovani-applicaion\MySQL\MySQL Server 5.5\bin;F:\giovani-applicaion\jdk1.8.0_161\bin;C:\WINDOWS\System32\OpenSSH\;F:\giovani-applicaion\apache-maven-3.3.9\bin;C:\Users\Administrator\AppData\Local\Microsoft\WindowsApps;;.
    18:13:18,243  INFO ZooKeeper:100 - Client environment:java.io.tmpdir=C:\Users\ADMINI~1\AppData\Local\Temp\
    18:13:18,243  INFO ZooKeeper:100 - Client environment:java.compiler=<NA>
    18:13:18,244  INFO ZooKeeper:100 - Client environment:os.name=Windows 10
    18:13:18,244  INFO ZooKeeper:100 - Client environment:os.arch=amd64
    18:13:18,245  INFO ZooKeeper:100 - Client environment:os.version=10.0
    18:13:18,245  INFO ZooKeeper:100 - Client environment:user.name=Giovani
    18:13:18,246  INFO ZooKeeper:100 - Client environment:user.home=C:\Users\Administrator
    18:13:18,246  INFO ZooKeeper:100 - Client environment:user.dir=E:\IdeaProjects\pinyougou-parent\pinyougou-search-service
    ```



    > - 原因1：
    >
    >   服务原本是这样写的：`com.pinyougou.search.service.impl.ItemSearchServiceImpl`
    >
    >   ```
    >   @Service(timeout = 5000)
    >   public class ItemSearchServiceImpl implements ItemSearchService {
    >   ```
    >
    >   配置文件：`applicationContext-service.xml`	
    >
    >   ```
    >   <dubbo:protocol name="dubbo" port="20884"></dubbo:protocol>
    >   ```
    >
    >   更改了一下；
    >
    >   ```
    >   @Service(timeout = 3000)
    >   public class ItemSearchServiceImpl implements ItemSearchService {
    >   ```
    >
    >   ```
    >   <dubbo:protocol name="dubbo" port="20887"></dubbo:protocol>
    >   ```
    >
    >   问题就解决了，重新改回，问题就没发生了。
    >
    >   费解，费解.....
    >
    >
    > - 原因2：`service`没加`com.alibaba.dubbo.config.annotation.Service`注解

13. `IDEA` 中`spring`配置`bean`之后，属性注入的时候，`ref`红色字段问题

    ![](https://raw.githubusercontent.com/Giovani-Github/Giovani-resource/master/markdown-resource/Snipaste_2018-08-19_18-15-32.png)

    > - 原因1：在当前文件中，`idea`没有找到所引入的`bean`。
    >
    >   解决方法：在当前文件中创建该`bean`即可`
    >
    > - 原因2：该红色字段`bean`不是定义在相同文件中，而是分文件定义的。
    >
    >   解决方法如下图：
    >
    >   ![](https://raw.githubusercontent.com/Giovani-Github/Giovani-resource/master/markdown-resource/Snipaste_2018-08-19_18-59-44.png)
    >
    >   ![](https://raw.githubusercontent.com/Giovani-Github/Giovani-resource/master/markdown-resource/Snipaste_2018-08-19_19-04-10.png)
    >
    >   ![](https://raw.githubusercontent.com/Giovani-Github/Giovani-resource/master/markdown-resource/Snipaste_2018-08-19_19-07-34.png)
    >
    > - 原因3：通过上诉配置后仍不能解决。该红色字段`bean`不是配置在spring配置文件中，而是通过注解配置
    >
    >   解决方法：在该红色字段`bean`的配置文件中，添加包扫描
    >
    >   ```
    >   <context:component-scan base-package="com.pinyougou.page.service"/>
    >   ```
    >
    > - 原因4：包扫描也不是配置项相同文件夹中，且通过原因2配置后，仍不能解决。是因为在`src`没有创建该`beana`
    >
    > - 原因5：经过以上仍无法解决。即你用的是`dobbox`，在`dobbx`配置文件中，已经设置了包扫描。但是字段还是红色的，没关系，不影响程序运行
    >
    >   ```
    >   <?xml version="1.0" encoding="UTF-8"?>
    >   <beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    >          xmlns:p="http://www.springframework.org/schema/p" xmlns:context="http://www.springframework.org/schema/context"
    >          xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
    >          xmlns:mvc="http://www.springframework.org/schema/mvc" xmlns="http://www.springframework.org/schema/beans"
    >          xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
    >           http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
    >           http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd
    >           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    >   
    >       <!-- 用dubbo协议在20882端口暴露服务 -->
    >       <dubbo:protocol name="dubbo" port="20884"></dubbo:protocol>
    >   
    >       <dubbo:application name="pinyougou-search-service"/>
    >       <dubbo:registry address="zookeeper://192.168.25.137:2181"/>
    >       <!-- 包扫描 -->
    >       <dubbo:annotation package="com.pinyougou.search.service.impl"/>
    >   
    >   </beans>
    >   ```

14. 通过`tb_goods` 表中的字段`goods_id`查不到`tb_item`表中的信息

    > - 原因：初始的数据库，`tb_item`表中的`goods_id`字段全部都是`1`，和`tb_goods`表中的字段`goods_id`是对应不上的，所以查询不到
    >
    >   解决方法：自己手动新增新的商品，拿我们新增的商品信息做测试即可

15. `activeMQ`通过广播和订阅模式发布消息，订阅方收不到广播方的消息

    > - 原因：订阅方配置文件中，所订阅的广播者与广播者配置文件中，设置的广播名称不一致
    >
    >   ```
    >   <!--这个是订阅模式  这个是广播者-->
    >   <bean id="topicPageDestination" class="org.apache.activemq.command.ActiveMQTopic">
    >   	<!-- 广播者的名称 -->
    >       <constructor-arg value="pinyougou_topic_page"/>
    >   </bean>
    >   ```
    >
    >   ```
    >   <!--订阅模式  这是订阅者  -->
    >   <bean id="topicPageDestination" class="org.apache.activemq.command.ActiveMQTopic">
    >       <!-- 要订阅哪一个消息广播者。与上方定义的广播者的名称一致 -->
    >       <constructor-arg value="pinyougou_topic_page"/>
    >   </bean>
    >   ```

16. `zookeeper`无法启动

    启动信息如下：

    ```
    [root@localhost bin]# ./zkServer.sh start
    ZooKeeper JMX enabled by default
    Using config: /root/zookeeper-3.4.13/bin/../conf/zoo.cfg
    Starting zookeeper ... already running as process 2365.
    ```

    ```
    [root@localhost bin]# ./zkServer.sh status
    ZooKeeper JMX enabled by default
    Using config: /root/zookeeper-3.4.13/bin/../conf/zoo.cfg
    Error contacting service. It is probably not running.
    ```

    > - 原因：`zookeeper/conf/zoo.cfg`文件，`dataDir`属性未正确设置
    >
    >   解决方法：参照讲义第一天，设置`zookeeper`

17. `dobbox`成功调用服务层，但是还报`timout`错误，就在服务层类上，加注解`@Serivce(timeout = 5000 )`

