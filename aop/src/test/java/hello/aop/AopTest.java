package hello.aop;

import static org.assertj.core.api.Assertions.*;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV1;
import hello.aop.order.aop.AspectV2;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
//@Import(AspectV1.class)
@Import(AspectV2.class)
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  public void aopInfo() throws Exception{
    log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
    log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
  }

  @Test
  public void success() throws Exception{
    //given
    orderService.orderItem("itemA");

    //when

    //then
  }

  @Test
  public void exception() throws Exception{
    //given

    assertThatThrownBy((() -> orderService.orderItem("ex")))
        .isInstanceOf(IllegalStateException.class);
    //when

    //then
  }


}
