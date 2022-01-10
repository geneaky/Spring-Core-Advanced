package hello.proxy.config.v1_proxy;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryConcreteProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceConcreteProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteClassProxyConfig {

  @Bean
  public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
    OrderRepositoryV2 repositoryImpl = new OrderRepositoryV2();
    return new OrderRepositoryConcreteProxy(repositoryImpl,logTrace);
  }

  @Bean
  public OrderServiceV2 orderService(LogTrace logTrace) {
    OrderServiceV2 serviceImpl = new OrderServiceV2(orderRepository(logTrace));
    return new OrderServiceConcreteProxy(serviceImpl, logTrace);
  }

  @Bean
  public OrderControllerV2 orderController(LogTrace logTrace) {
    OrderControllerV2 controllerImpl = new OrderControllerV2(orderService(logTrace));
    return new OrderControllerConcreteProxy(controllerImpl,logTrace);
  }

}
