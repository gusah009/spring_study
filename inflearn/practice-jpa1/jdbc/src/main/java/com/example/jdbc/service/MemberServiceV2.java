package com.example.jdbc.service;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.MemberRepositoryV2;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 트랜잭션 - 파라미터 연동, 풀을 고려한 종료
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

  private final DataSource dataSource;
  private final MemberRepositoryV2 memberRepository;

  public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    Connection con = dataSource.getConnection();
    try {
      con.setAutoCommit(false);
      bizLogic(con, fromId, toId, money);
    } catch (Exception e) {
      con.rollback();
      throw new IllegalStateException(e);
    } finally {
      if (con != null) {
        try {
          con.commit();
          release(con);
        } catch (Exception e) {
          log.info("error", e);
        }
      }
    }
  }

  private void bizLogic(Connection con, String fromId, String toId, int money)
      throws SQLException {
    Member fromMember = memberRepository.findById(fromId);
    Member toMember = memberRepository.findById(toId);

    memberRepository.update(con, fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepository.update(con, toId, toMember.getMoney() + money);
  }

  private static void release(Connection con) throws SQLException {
    con.setAutoCommit(true); // true로 돌려줘야 커넥션 풀을 고려해주는 것임
    con.close();
  }

  private static void validation(Member toMember) {
    if (toMember.getMemberId().equals("ex")) {
      throw new IllegalStateException("이체 중 예외 발생");
    }
  }

}
