package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV3 {

  public void save(String itemId) {
    if (itemId.equals("ex")) {
      throw new IllegalStateException("예외 발생!");
    }

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
