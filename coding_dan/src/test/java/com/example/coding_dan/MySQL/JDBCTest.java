package com.example.coding_dan.MySQL;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Log4j2
public class JDBCTest {

  static {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("JDBC 연결 테스트")
  public void testConnection() {
    try (Connection con =
        DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/rs_Test?serverTimezone=UTC&characterEncoding=UTF-8",
            "book_ex",
            "book_ex")) {
      log.info(con);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
