package hello.proxy.pureproxy.decorate;

import hello.proxy.pureproxy.decorate.code.Component;
import hello.proxy.pureproxy.decorate.code.DecoratePatternClient;
import hello.proxy.pureproxy.decorate.code.decorator.MessageDecorator;
import hello.proxy.pureproxy.decorate.code.RealComponent;
import hello.proxy.pureproxy.decorate.code.decorator.TimeDecorator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class DecoratorPatternTest {

  @Test
  void noDecorator() {
    Component component = new RealComponent();
    DecoratePatternClient client = new DecoratePatternClient(component);
    client.execute();
  }

  @Test
  void decorator1() {
    Component realComponent = new RealComponent();
    MessageDecorator messageDecorator = new MessageDecorator(realComponent);
    DecoratePatternClient client = new DecoratePatternClient(messageDecorator);
    client.execute();
  }

  @Test
  void decorator2() {
    Component realComponent = new RealComponent();
    MessageDecorator messageDecorator = new MessageDecorator(realComponent);
    TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
    DecoratePatternClient client = new DecoratePatternClient(timeDecorator);
    client.execute();
  }
}
