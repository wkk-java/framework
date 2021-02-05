# framework (springcloud2.x版)

	author: 			wkk

##	应用

|       模块        |      版本      | 说明                                                         |
| :---------------: | :------------: | ------------------------------------------------------------ |
|     SringBoot     | 2.1.12.RELEASE |                                                              |
|    SpringCloud    | GreenWich.SR6  |                                                              |
| ~~eureka-server~~ | 2.1.5.RELEASE  | ~~为微服务应用提供服务注册~~,由于后续开源版本停更,已弃用,改为nacos替代 |
| ~~config-server~~ | 2.1.8.RELEASE  | 为应用提供配置中心服务,改为nacos替代                         |
|   oauth-server    | 2.3.6.RELEASE  | 提供基于oauth2协议的认证功能(不做授权)                       |
|       zuul        | 2.1.5.RELEASE  | 提供微服务路由,统一网关入口做认证,鉴权,认证,全局入口级限流等 |
|   admin-server    |     2.1.4      | 提供对微服务应用的健康检查,jvm监控,日志查看,调用统计等       |
|      dataway      |     4.2.1      | 利用dataway可以极快的为前端提供数据接口<br>而舍去java架构的controller,service,dao,mapper等<br>需部署在网关层后做鉴权处理 |
|                   |                |                                                              |
|                   |                |                                                              |
|                   |                |                                                              |



## plugin

| 模块                       | 版本           | 说明                                                         |
| -------------------------- | -------------- | ------------------------------------------------------------ |
| jib-maven-plugin           | 2.6.0          | 利用google提供jib-maven-plugin的插件,绑定maven install执行<br>执行:mvn clean install -Djib.skip=false -DsendCredentialsOverHttp=true -f pom.xml<br>优点:<br/>		1.增量构建docker镜像,更快速的CI.<br/>		2.本地无需安装docker后台程序(随处执行docker构建及推送). |
| dockercompose-maven-plugin | 1.0.0-SNAPSHOT | 利用自定义的dockercompose-maven-plugin插件,绑定maven install执行<br/>优点:<br/>1.为当前应用自动生成swarm yaml编排文件,结合jenkins实现更智能便捷的CD. |
| ~~docker-maven-plugin~~    | 1.2.2          | 构建docker镜像的插件,绑定在 install执行周期,执行构建与推送   |
| versions-maven-plugin      |                | 工程版本管理插件                                             |
| maven-release-plugin       |                | 工程版本管理插件                                             |
|                            |                |                                                              |
|                            |                |                                                              |
|                            |                |                                                              |
|                            |                |                                                              |


																																																					**路过就给个🌟star吧**

