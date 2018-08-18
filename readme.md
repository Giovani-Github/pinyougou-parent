##  

品优购商城项目

### 项目简介

**来源**：

2017.7黑马web49期

**项目使用的架构是**：

* 分布式
* SOA

**所用技术**：

 *  SSM框架
 *  zookeeper
 *  dobbox
 *  AngularJs
 *  Redis
*  Spring Data Redis
 *  spring security
 *  fastDFS
 *  Solr
 *  spring-data-solr
 *  freemarker（网页静态化）
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
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-search-interface`
>
> 
>
> > **`pinyougou-page-service`**：网页静态化生成服务模块，打包方式：`war`，tomcat端口：	 `9005`，dubbo协议在哪个端口暴露服务: `20885`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-page-interface` `freemarker`
>
> 
>
> > **`pinyougou-manager-web`**：运营商管理后台，打包方式：`war`，tomcat端口：`9101`
> >
> > 依赖：`spring`  `springmvc`  `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface` `SpringSecurity ` `pinyougou-search-interface` `pinyougou-content-interface` `pinyougou-page-interface`
>
> 
>
> > **`pinyougou-shop-web`**：商家管理后台，打包方式：`war`，tomcat端口：`9102`
> >
> > 依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface` `SpringSecurity ` 
>
> 
>
> >**`pinyougou-portal-web`**：商城，打包方式：`war`，tomcat端口：`9103`
> >
> >依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-content-interface`
>
> 
>
> > **`pinyougou-search-web`**：搜索web项目，打包方式：`war`，tomcat端口：`9104`
> >
> > 依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-search-interface`
>
> 
>
> > **`pinyougou-page-web`**：商品详情页web项目，打包方式：`war`，tomcat端口：`9105`
> >
> > 依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-page-interface`



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

    ​	跑这个异常说明注入内容有问题，遇到类似异常，检查注入内容是否正确！！ `controller`与`service`之间的注入有问题。检查文件导入是否有问题

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

    

    > 原因1：
    >
    > 服务原本是这样写的：`com.pinyougou.search.service.impl.ItemSearchServiceImpl`
    >
    > ```
    > @Service(timeout = 5000)
    > public class ItemSearchServiceImpl implements ItemSearchService {
    > ```
    >
    > 配置文件：`applicationContext-service.xml`
    >
    > ```
    > <dubbo:protocol name="dubbo" port="20884"></dubbo:protocol>
    > ```
    >
    > 更改了一下；
    >
    > ```
    > @Service(timeout = 3000)
    > public class ItemSearchServiceImpl implements ItemSearchService {
    > ```
    >
    > ```
    > <dubbo:protocol name="dubbo" port="20887"></dubbo:protocol>
    > ```
    >
    > 问题就解决了，重新改回，问题就没发生了。
    >
    > 费解，费解.....
    >
    > 原因2：`service`没加`com.alibaba.dubbo.config.annotation.Service`注解

13. 