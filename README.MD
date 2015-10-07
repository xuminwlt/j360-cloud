j360-cloud
==============

-* 基于spring cloud应用在分布式系统中的运用
    - config
    - eureka
    - monitor
    - servicediscover

##Spring Cloud Netflix##
-   应用程序+Netflix 组件构建大型分布式系统。包括
    -   服务发现(Eureka)
    -   断路器(Hystrix) 
    -   智能路由(Zuul)
    -   客户端负载均衡(Ribbon)

###服务发现(Eureka)###
-* Eureka Client
-   注册
    - @EnableEurekaClient
    - application.yml
    
    ```
        eureka:
          client:
            serviceUrl:
              defaultZone: http://localhost:8761/eureka/
    ```
-* Eureka Server
-   @EnableEurekaServer
- 【注】服务器有一个主页界面，HTTP API Eureka功能端点 /eureka/*。
####Standalone Mode####
-   application.yml (Standalone Eureka Server)
    -   serviceUrl指向同一个本地实例的host
    
```
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

####High Availability, Zones and Regions####
- ...

###断路器(Hystrix) ###
- Hystrix Client
- Hystrix DashBoard

###智能路由(Zuul) ###
- Netflix uses Zuul for the following:
    - Authentication
    - Insights
    - Stress Testing
    - Canary Testing
    - Dynamic Routing
    - Service Migration
    - Load Shedding
    - Security
    - Static Response handling
    - Active/Active traffic management


###客户端负载均衡(Ribbon) ###
- Ribbon提供客户端的负载均衡，可以为http和tcp客户端提供控制功能，Feign 同样使用Ribbon
- 定制Ribbon客户端
- Eureka中使用Ribbon

```

```
- 直接使用RibbonAPI

```
public class MyClass {
    @Autowired
    private LoadBalancerClient loadBalancer;

    public void doStuff() {
        ServiceInstance instance = loadBalancer.choose("stores");
        URI storesUri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
        // ... do something with the URI
    }
}
```

##声明式 REST Client: Feign##
- Spring Cloud整合Ribbon和Eureka提供负载均衡的http client时使用Feign.

- 实例案例

```
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
- 调用(StoreClient.java)

```
@FeignClient("stores")
public interface StoreClient {
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathParameter("storeId") Long storeId, Store store);
}
```

###继承支持###

```
UserService.java
public interface UserService {

    @RequestMapping(method = RequestMethod.GET, value ="/users/{id}")
    User getUser(@PathVariable("id") long id);
}
UserResource.java
@RestController
public class UserResource implements UserService {

}
UserClient.java
@FeignClient("users")
public interface UserClient extends UserService {

}
```

##Spring Cloud Bus##
- Spring Cloud Bus通过轻量级消息代理连接分布式系统的每个节点
- 添加依赖spring-cloud-starter-bus-amqp
- Rabbit config

```
spring:
  rabbitmq:
    host: mybroker.com
    port: 5672
    username: user
    password: secret
```
- http端点
    - /bus/*
    - /bus/env 发送key/values键值对更新spring环境变量
    - /bus/refresh 更新所有的环境变量
    