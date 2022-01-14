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

  @Test
  public void typeExactMatch() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();

  }


  //type match가 super type에 대한 match도 가능함
  @Test
  public void typeExacSuperType() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  //부모타입에 있는 메서드만 자시 타입에서 매칭됨 여기서는 자식타입에만 있는 internal 메서드를 지정했기 때문에 false
  @Test
  public void typeExactInternal() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
    Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
  }

  @Test
  public void typeExactNoSuperTypeMethodFalse() throws Exception{
    pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
    Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
    assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
  }

  //String 타입의 파라미터 허용
  // (String)
  @Test
  public void argsMatch() throws Exception{
    pointcut.setExpression("execution(* *(String))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  //파라미터가 없어야함
  // ()
  @Test
  public void argsMatchNoArgs() throws Exception{
    pointcut.setExpression("execution(* *())");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
  }

  //정확히 하나의 파라미터 허용, 모든 타입 허용
  // ()
  @Test
  public void argsMatchStart() throws Exception{
    pointcut.setExpression("execution(* *(*))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  // 숫자와 무관하게 모든 파라미터, 모든 타입 허용
  //(), (Xxx), (Xxx, Xxx)
  @Test
  public void argsMatchAll() throws Exception{
    pointcut.setExpression("execution(* *(..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }

  //String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
  //(String), (String,Xxx), (String,Xxx, Xxx)
  @Test
  public void argsMatchComplext() throws Exception{
    pointcut.setExpression("execution(* *(String, ..))");
    assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
  }



}
