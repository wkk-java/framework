# framework (springcloud版)
author: wkk
==================================================================
	SpringCloud 				GreenWich.SR6
	SringBoot 				2.1.12.RELEASE
	jdk 					1.8
	jib-maven-plugin 			2.6.0
	dockercompose-maven-plugin 		1.0.0-SNAPSHOT
	


1.利用google提供jib-maven-plugin的插件,绑定maven install执行,增量构建docker镜像,更快速的CI,构建机器环境无需安装docker后台程序.
2.利用自定义的dockercompose-maven-plugin插件,绑定maven install执行,为当前应用自动生成swarm yaml编排文件,便于更智能便捷的CD.
3.
==================================================================</br>

          初代版本,用于生产部分功能需自己完善.
