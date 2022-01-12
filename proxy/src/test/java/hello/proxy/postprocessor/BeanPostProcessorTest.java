package hello.proxy.postprocessor;

import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanPostProcessorTest {

  @Test
  public void basicConfig() throws Exception{
    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BasicConfig.class);

    //빈 a이름으로 b객체가 빈으로 등록된다.
    B beanB = applicationContext.getBean("beanA",B.class);
    beanB.helloB();

    //A는 빈으로 등록되지 않음
    assertThrows(NoSuchBeanDefinitionException.class, () ->{
      applicationContext.getBean(A.class);
    });
  }

  @Slf4j
  @Configuration
  static class BasicConfig {
    @Bean(name = "beanA")
    public A a() {
      return new A();
    }

    @Bean
    public AToBPostProcessor helloPostProcessor() {
      return new AToBPostProcessor();
    }
  }

  @Slf4j
  static class A {
    public void helloA() {
      log.info("Hello A");
    }
  }

  @Slf4j
  static class B {
    public void helloB() {
      log.info("Hello B");
    }
  }

  @Slf4j
  static class AToBPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
      log.info("beanName={} bean={}", beanName, bean);
      if (bean instanceof A) {
        return new B();
      }

      return bean;
    }
  }
}
