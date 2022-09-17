package hello.proxy.pureproxy.decorate.code.decorator;

import hello.proxy.pureproxy.decorate.code.Component;

public abstract class Decorator implements Component {

  protected final Component component;

  public Decorator(Component component) {
    this.component = component;
  }

  @Override
  abstract public String operation();
}
