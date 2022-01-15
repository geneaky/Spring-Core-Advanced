package hello.aop.member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberSErviceImplChild extends MemberServiceImpl{

  public void hi() {
    log.info("hi");
  }

}
