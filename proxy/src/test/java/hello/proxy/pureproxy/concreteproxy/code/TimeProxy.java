package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{

  private ConcreteLogic concreteLogic;

  public TimeProxy(ConcreteLogic concreteLogic) {
    this.concreteLogic = concreteLogic;
  }

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    String result = concreteLogic.operation();
    log.info("resultTime={} result={}", resultTime, result);

    return result;
  }
}
