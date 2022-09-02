package com.example.jdbc.repository;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.ex.MyDbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * 예외 누수 문제 해결 체크 예외를 런타입 예외로 변경 MemberRepository 인터페이스 이용 throws SQLException 제거
 */
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV4_1 implements MemberRepository {

  private final DataSource dataSource;

  public Member save(Member member) {
    String sql = "insert into member(member_id, money) values (?, ?)";

    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      con = getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, member.getMemberId());
      pstmt.setInt(2, member.getMoney());
      pstmt.executeUpdate();
      return member;
    } catch (SQLException e) {
      log.error("db error", e);
      throw new MyDbException(e);
    } finally {
      close(con, pstmt, null);
    }
  }

  public Member findById(String memberId) {
    String sql = "select * from member where member_id = ?";

    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      con = getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, memberId);

      rs = pstmt.executeQuery();
      if (rs.next()) {
        Member member = new Member();
        member.setMemberId(rs.getString("member_id"));
        member.setMoney(rs.getInt("money"));
        return member;
      } else {
        throw new NoSuchElementException("member not found memberId=" + memberId);
      }
    } catch (SQLException e) {
      log.error("db error", e);
      throw new MyDbException(e);
    } finally {
      close(con, pstmt, rs);
    }
  }

  public void update(String memberId, int money) {
    String sql = "update member set money=? where member_id=?";

    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      con = getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, money);
      pstmt.setString(2, memberId);
      int resultSize = pstmt.executeUpdate();
      log.info("resultSize={}", resultSize);
    } catch (SQLException e) {
      log.error("db error", e);
      throw new MyDbException(e);
    } finally {
      close(con, pstmt, null);
    }
  }

  public void delete(String memberId) {
    String sql = "delete from member where member_id=?";

    Connection con = null;
    PreparedStatement pstmt = null;

    try {
      con = getConnection();
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, memberId);
      int resultSize = pstmt.executeUpdate();
      log.info("resultSize={}", resultSize);
    } catch (SQLException e) {
      log.error("db error", e);
      throw new MyDbException(e);
    } finally {
      close(con, pstmt, null);
    }
  }

  private void close(Connection con, Statement stmt, ResultSet rs) {
//    JdbcUtils.closeConnection(con);
    JdbcUtils.closeStatement(stmt);
    JdbcUtils.closeResultSet(rs);
    // 주의! 트랜잭션 동기화를 사용하려면 DataSourceUtils를 사용해야 한다.
    DataSourceUtils.releaseConnection(con, dataSource);
  }

  private Connection getConnection() {
    Connection con = DataSourceUtils.getConnection(dataSource);
    log.info("con = {}", con);
    return con;
  }
}
