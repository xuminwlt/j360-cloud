j360-cloud 基于Spring Cloud的微服务架构设计
==============
- *基于spring cloud应用在分布式系统中的运用*
    - config
    - eureka
    - dashboard
    - serviceDiscover
    - Declarative REST Client: Feign
    
#构建#  
- Java 1.7+(官方建议1.8)
- Maven 3.2+
- Spring 4.1.3
- Spring-boot 1.2.4.RELEASE
- Spring-cloud 1.0.3

#模块#
- j360-cloud-config 分布式配置服务器，可选本地文件或远程git服务作为资源库
- j360-cloud-eureka Service Discovery服务器，用于service注册等管理服务
- j360-cloud-dashboard eureka的监控平台
- j360-cloud-service 自定义业务service
- j360-cloud-clientui 自定义业务client，用于展示和调用service


##j360-cloud-config ##
- 设置注解@EnableConfigServer

##j360-cloud-eureka##
- 设置注解@EnableEurekaServer
- application.yml(Standalone Mode)

##j360-cloud-dashboard##

##j360-cloud-service##

##j360-cloud-clientui##
