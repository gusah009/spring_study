package hello.proxy.pureproxy.decorate.code.decorator;

import hello.proxy.pureproxy.decorate.code.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator extends Decorator {

  public MessageDecorator(Component component) {
    super(component);
  }

  @Override
  public String operation() {
    log.info("MessageDecorator 실행");

    String result = component.operation();
    String decoResult = "*****" + result + "*****";
    log.info("MessageDecorator 꾸미기 적용 전={} 꾸미기 적용 후={}", result, decoResult);
    return decoResult;
  }
}
