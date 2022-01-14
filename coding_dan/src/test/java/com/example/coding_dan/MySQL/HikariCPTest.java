package com.example.coding_dan.MySQL;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// 짠 사실 Spring Framework 사용, ComponentScan 사용 등등 모두 다 여기 안에 들어있었습니다~
// 이것만 사용하면 편안하긴 한데 안에 뭐가 들었는진 알고 쓰는게 좋음!
@SpringBootTest(
    properties = "spring.datasource.type=com.zaxxer.hikari.HikariDataSource"
)
@Log4j2
public class HikariCPTest {

  @Setter(onMethod_ = @Autowired)
  private DataSource dataSource;

  @Test
  @DisplayName("Spring Boot에 HikariCP가 들어가 있는 지 테스트")
  public void testConnection() {
    try (Connection con = dataSource.getConnection()) {
      log.info(con);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
