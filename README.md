# framework (springcloud版)
author: wkk</br>
=======================================================</br>
相关组件依赖及版本说明: </br>
<h8>SpringCloud GreenWich.SR6</h8> </br>
<h4>SringBoot 2.1.12.RELEASE</h4></br>
<h4>jdk 1.8</h4></br>
<h4>jib-maven-plugin</h4> </br>
<font>替换docker官方的docker-maven-plugin插件,与maven命令执行绑定,自动构建docker镜像并推送到docker私服,优点:1.本地无需docker daemon后台程序 2.增量打包(仅构建改动部分,更快速的CI)
</font></br>
<h4>自定义dockercompose-maven-plugin插件<h4> 与maven的执行绑定,自动生成需要的容器编排yaml文件,便于更加快速智能的CI</br>

=======================================================</br>

          初代版本,用于生产部分功能需自己完善.
