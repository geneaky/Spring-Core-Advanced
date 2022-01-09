package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  /**
   * 전략 패턴 적용
   */
  @Test
  public void strategyV1() throws Exception{
    //given
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(() -> log.info("hi"));
    contextV2.execute(() -> log.info("bi"));

    //when

    //then
  }
}
