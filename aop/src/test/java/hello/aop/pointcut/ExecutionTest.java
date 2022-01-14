package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.*;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {

  AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
  Method helloMethod;

  @BeforeEach
  public void init() throws NoSuchMethodException {
    helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
  }

  @Test
  public void printMethod() throws Exception{
    //given
    //public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
    log.info("helloMethod = {}", helloMethod);

    //when

    //then
  }

  @Test
  public void exactMatch() throws Exception{
    //given
    pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

    //when

    //then
  }

  @Test
  public void allMatch() throws Exception{
    //given
    pointcut.setExpression("execution(* *(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    //when

    //then
  }

  @Test
  public void nameMatch() throws Exception{
    //given
    pointcut.setExpression("execution(* hello(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    //when


    //when

    //then
  }

  @Test
  public void nameMatchStar1() throws Exception{
    //given
    pointcut.setExpression("execution(* hel*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    //when

    //then
  }

  @Test
  public void nameMatchStar2() throws Exception{
    //given
    pointcut.setExpression("execution(* *el*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    //when

    //then
  }

  @Test
  public void nameMatchFalse() throws Exception{
    pointcut.setExpression("execution(* nono(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();

  }

  @Test
  public void packageExactMatch1() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }

  @Test
  public void packageExactMatch2() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }

  @Test
  public void packageExactFalse() throws Exception{
    pointcut.setExpression("execution(* hello.aop.*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();

  }

  @Test
  public void packageMatchSubPackage1() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member..*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }

  // * -> wildcard
  // .. -> 해당 패키지와 그 하위 패키지 전부

  @Test
  public void packageMatchSubPackage2() throws Exception{
    pointcut.setExpression("execution(* hello.aop..*.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }
}
