package hello.aop.proxyvs;

import static org.junit.jupiter.api.Assertions.*;

import hello.aop.member.MemberSErviceImplChild;
import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class ProxyCastingTest {

  @Test
  public void jdkProxy() throws Exception{

    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(false); //jdk 동적 프록시

    //프록시를 인터페이스로 캐스팅 성공
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    // JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
    assertThrows(ClassCastException.class, () ->{
      MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    });


  }

  @Test
  public void cglibProxy() throws Exception{

    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true); //jdk 동적 프록시

    //프록시를 인터페이스로 캐스팅 성공
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    log.info("proxy class={}", memberServiceProxy.getClass());

    // CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
    MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;

  }

  @Test
  public void cglibProxyExtendsChildTest() throws Exception{

    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true); //jdk 동적 프록시

    //프록시를 인터페이스로 캐스팅 성공
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    log.info("proxy class={}", memberServiceProxy.getClass());

    // CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
    MemberServiceImpl castingMemberService = (MemberServiceImpl) memberServiceProxy;
    assertThrows(ClassCastException.class, () ->{
      MemberSErviceImplChild castingMemberServiceChild = (MemberSErviceImplChild) memberServiceProxy;
    });
  }

}
