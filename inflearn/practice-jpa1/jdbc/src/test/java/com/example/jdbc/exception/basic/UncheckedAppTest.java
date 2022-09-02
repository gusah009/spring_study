package com.example.jdbc.exception.basic;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.ConnectException;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedAppTest {

  @Test
  void checked() {
    Controller controller = new Controller();
    assertThatThrownBy(controller::request)
        .isInstanceOf(Exception.class);
  }

  static class Controller {

    Service service = new Service();

    public void request() {
      service.logic();
    }
  }

  static class Service {

    Repository repository = new Repository();
    NetworkClient networkClient = new NetworkClient();

    public void logic() {
      repository.call();
      networkClient.call();
    }
  }

  static class RuntimeConnectException extends RuntimeException {

    public RuntimeConnectException(String message) {
      super(message);
    }
  }

  static class RuntimeSQLException extends RuntimeException {

    public RuntimeSQLException(String message) {
      super(message);
    }
  }

  static class NetworkClient {

    public void call() {
      throw new RuntimeConnectException("ex");
    }
  }

  static class Repository {

    public void call() {
      throw new RuntimeSQLException("ex");
    }
  }
}
