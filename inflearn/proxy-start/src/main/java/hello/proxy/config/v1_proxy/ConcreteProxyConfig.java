package hello.proxy.config.v1_proxy;

import hello.proxy.app.v2.OrderControllerV2;
import hello.proxy.app.v2.OrderRepositoryV2;
import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderControllerProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderRepositoryProxy;
import hello.proxy.config.v1_proxy.concrete_proxy.OrderServiceProxy;
import hello.proxy.trace.logtrace.LogTrace;
import hello.proxy.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConcreteProxyConfig {

  @Bean
  public LogTrace logTrace() {
    return new ThreadLocalLogTrace();
  }

  @Bean
  public OrderControllerV2 orderController(LogTrace logTrace) {
    OrderControllerV2 controller = new OrderControllerV2(orderService(logTrace));
    return new OrderControllerProxy(controller, logTrace);
  }

  @Bean
  public OrderServiceV2 orderService(LogTrace logTrace) {
    OrderServiceV2 service = new OrderServiceV2(orderRepository(logTrace));
    return new OrderServiceProxy(service, logTrace);
  }

  @Bean
  public OrderRepositoryV2 orderRepository(LogTrace logTrace) {
    OrderRepositoryV2 repository = new OrderRepositoryV2();
    return new OrderRepositoryProxy(repository, logTrace);
  }

}
