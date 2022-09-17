package hello.proxy.pureproxy.decorate.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratePatternClient {

  private Component component;

  public DecoratePatternClient(Component component) {
    this.component = component;
  }

  public void execute() {
    String result = component.operation();
    log.info("result = {}", result);
  }
}
