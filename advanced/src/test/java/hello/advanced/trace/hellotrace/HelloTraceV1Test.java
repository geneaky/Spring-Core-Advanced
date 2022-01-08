package hello.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {

  @Test
  public void begin_end() throws Exception{
    //given
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");
    trace.end(status);

    //when

    //then
  }

  @Test
  public void begin_exception() throws Exception{
    //given
    HelloTraceV1 trace = new HelloTraceV1();
    TraceStatus status = trace.begin("hello");
    trace.exception(status, new IllegalStateException());

    //when

    //then
  }
}