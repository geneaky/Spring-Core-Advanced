package hello.advanced.trace.callback;

@FunctionalInterface
public interface TraceCallback<T> {
  T call();
}
