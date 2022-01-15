package hello.aop.internalcall;

import static org.junit.jupiter.api.Assertions.*;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(CallLogAspect.class)
@Slf4j
@SpringBootTest
class CallServiceV0Test {

  @Autowired
  CallServiceV0 callServiceV0;

  @Test
  public void external() throws Exception{
    //given
    callServiceV0.external();
    //when

    //then
  }

  @Test
  public void internal() throws Exception{
    //given
    callServiceV0.internal();

    //when

    //then
  }

}