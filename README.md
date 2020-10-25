# framework (springcloud2.x版)

	author: 			wkk
	-------------------------应用---------------------------
	jdk 				1.8
	SringBoot 			2.1.12.RELEASE
	SpringCloud 			GreenWich.SR6
	eureka-server			2.1.5.RELEASE
	config-server			2.1.8.RELEASE
	oauth-server			2.3.6.RELEASE
	zuul				2.1.5.RELEASE
	admin				2.1.4
	dataway				4.2.1
	
	说明:
	1.eureka-server	为微服务应用提供服务注册
	2.config-server	为应用提供配置中心服务
	3.oauth-server	提供基于oauth2协议的认证功能(不做授权)
	4.zuul		提供微服务路由,统一网关入口做鉴权,认证,限流等
	5.admin		提供对微服务应用的健康检查,jvm监控,日志,调用统计等
	6.dataway:	利用dataway可以极快的为前端提供数据接口,而舍去java架构的controller,service,dao,mapper等(需部署在网关层后做鉴权处理)
	
	------------------------plugin-------------------------
	jib-maven-plugin 			2.6.0
	dockercompose-maven-plugin 		1.0.0-SNAPSHOT
	
	说明:
	1.jib-maven-plugin:		利用google提供jib-maven-plugin的插件,绑定maven install执行,增量构建docker镜像,更快速的CI,本地无需安装docker后台程序(随处执行构建).
					mvn clean install -Djib.skip=false -DsendCredentialsOverHttp=true -f pom.xml
	2.dockercompose-maven-plugin:	利用自定义的dockercompose-maven-plugin插件,绑定maven install执行,为当前应用自动生成swarm yaml编排文件,结合jenkins实现更智能便捷的CD.


	**初代版本,用于生产部分功能需自己完善**
