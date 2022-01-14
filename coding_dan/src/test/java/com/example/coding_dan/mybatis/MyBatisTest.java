package com.example.coding_dan.mybatis;

import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// MyBatis 설정도 Spring Boot에 기본적으로 들어가 있습니다.
// 그 외 설정은 application.properties에 넣어 놨습니다.
@SpringBootTest
@Log4j2
public class MyBatisTest {

  @Setter(onMethod_ = @Autowired)
  private DataSource dataSource;

  @Setter(onMethod_ = @Autowired)
  private SqlSessionFactory sqlSessionFactory;

  @Test
  public void testMyBatis() {
    try (SqlSession session = sqlSessionFactory.openSession();
        Connection con = session.getConnection();
    ) {
      log.info(session);
      log.info(con);
    } catch (Exception e) {
      fail(e.getMessage());
    }
  }
}
