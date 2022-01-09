package hello.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

  @Test
  public void begin_end() throws Exception{
    //given
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
    trace.end(status2);
    trace.end(status1);

    //when

    //then
  }

  @Test
  public void begin_exception() throws Exception{
    //given
    HelloTraceV2 trace = new HelloTraceV2();
    TraceStatus status1 = trace.begin("hello1");
    TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
    trace.exception(status2, new IllegalStateException());
    trace.exception(status1, new IllegalStateException());

    //when

    //then
  }
}