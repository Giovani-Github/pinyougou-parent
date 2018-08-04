## 品优购商城项目

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
 *  待补充

### 项目结构

> **`pinyougou-parent`** ：父工程，管理依赖，定义项目所需依赖（未使用），各模块再根据实际情况进行实际使用，打包方式：pom
>
> > **`pinyougou-pojo`**：通用实体类模块，打包方式：jar
>
> 
>
> > **`pinyougou-dao`**：通用数据访问模块，打包方式：jar
> >
> > 依赖：`mybatis` `pinyougou-pojo` `mysql-connector-java ` `druid`
>
> 
>
> > **`pinyougou-coommon`** ：通用工具类模块，打包方式：jar
>
> 
>
> > **`pinyougou-sellergoods-interface`**：商家商品服务接口模块 ，打包方式：jar
> >
> > 依赖：`pinyougou-pojo`
>
> 
>
> > **`pinyougou-sellergoods-service`**：商家商品服务模块，打包方式：war，tomcat端口：`9001`
> >
> > 依赖：`spirng` `dubbox` `pinyougou-dao` `pinyougou-coommon` `pinyougou-sellergoods-interface`
>
> 
>
> > **`pinyougou-manager-web`**：运营商管理后台，打包方式：war，tomcat端口：`9101`
> >
> > 依赖：`spring`  `springmvc`  `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface`
>
> 
>
> > **`pinyougou-shop-web`**：商家管理后台，打包方式：war，tomcat端口：`9102`
> >
> > 依赖：`spring` `springmvc` `dubbox` `pinyougou-common` `pinyougou-sellergoods-interface`

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