package hello.proxy.pureproxy.decorate.code.decorator;

import hello.proxy.pureproxy.decorate.code.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator extends Decorator {

  public TimeDecorator(Component component) {
    super(component);
  }

  @Override
  public String operation() {
    log.info("TimeDecorator 실행");
    long startTime = System.currentTimeMillis();
    String result = component.operation();
    long endTime = System.currentTimeMillis();
    log.info("TimeDecorator 종료 resultTime = {}ms", endTime - startTime);
    return result;
  }
}
