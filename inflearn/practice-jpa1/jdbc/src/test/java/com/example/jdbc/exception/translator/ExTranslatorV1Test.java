package com.example.jdbc.exception.translator;

import static com.example.jdbc.connection.ConnectionConst.PASSWORD;
import static com.example.jdbc.connection.ConnectionConst.URL;
import static com.example.jdbc.connection.ConnectionConst.USERNAME;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.ex.MyDbException;
import com.example.jdbc.repository.ex.MyDuplicateKeyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

public class ExTranslatorV1Test {

  Service service;
  Repository repository;

  @BeforeEach
  void init() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    repository = new Repository(dataSource);
    service = new Service(repository);
  }

  @Test
  void duplicateKeySave() {
    service.create("myId");
    service.create("myId"); // 같은 아이디 저장 시도
  }

  @Slf4j
  @RequiredArgsConstructor
  static class Service {

    private final Repository repository;

    public void create(String memberId) {
      try {
        repository.save(new Member(memberId, 0));
        log.info("saveId={}", memberId);
      } catch (MyDuplicateKeyException e) {
        String retryId = generateNewId(memberId);
        repository.save(new Member(retryId, 0));
        log.info("키 중복, 복구 시도 : {}", retryId);
      } catch (MyDbException e) {
        log.info("ex", e);
        throw e;
      }
    }

    private static String generateNewId(String memberId) {
      return memberId + new Random().nextInt(10000);
    }
  }


  @RequiredArgsConstructor
  static class Repository {

    private final DataSource datasource;

    public Member save(Member member) {
      String sql = "insert into member(member_id, money) values(?,?)";
      Connection con = null;
      PreparedStatement pstmt = null;

      try {
        con = datasource.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setString(1, member.getMemberId());
        pstmt.setInt(2, member.getMoney());
        pstmt.executeUpdate();
        return member;
      } catch (SQLException e) {
        // h2 db
        if (e.getErrorCode() == 23505) {
          throw new MyDuplicateKeyException(e);
        }
        throw new MyDbException(e);
      } finally {
        JdbcUtils.closeStatement(pstmt);
        JdbcUtils.closeConnection(con);
      }
    }
  }

}
