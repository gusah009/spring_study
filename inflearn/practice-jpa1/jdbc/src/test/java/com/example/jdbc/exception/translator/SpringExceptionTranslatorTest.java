package com.example.jdbc.exception.translator;

import static com.example.jdbc.connection.ConnectionConst.PASSWORD;
import static com.example.jdbc.connection.ConnectionConst.URL;
import static com.example.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.jdbc.connection.ConnectionConst;
import com.example.jdbc.exception.translator.ExTranslatorV1Test.Repository;
import com.example.jdbc.exception.translator.ExTranslatorV1Test.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

public class SpringExceptionTranslatorTest {

  DataSource dataSource;

  @BeforeEach
  void init() {
    dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
  }

  @Test
  void sqlExceptionErrorCode() {
    String sql = "select bad grammer";

    try {
      Connection con = dataSource.getConnection();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.executeQuery();
    } catch (SQLException e) {
      assertThat(e.getErrorCode()).isEqualTo(42122);
    }
  }

  @Test
  void exceptionTranslator() {
    String sql = "select bad grammer";

    try {
      Connection con = dataSource.getConnection();
      PreparedStatement stmt = con.prepareStatement(sql);
      stmt.executeQuery();
    } catch (SQLException e) {
      assertThat(e.getErrorCode()).isEqualTo(42122);

      SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(
          dataSource);
      DataAccessException resultEx = exTranslator.translate("select", sql, e);
      assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
    }
  }
}
