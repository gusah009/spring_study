package com.example.jdbc.service;

import static com.example.jdbc.connection.ConnectionConst.PASSWORD;
import static com.example.jdbc.connection.ConnectionConst.URL;
import static com.example.jdbc.connection.ConnectionConst.USERNAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.MemberRepositoryV1;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 기본 동작, 트랜잭션이 없어서 문제가 생길 예정
 */
class MemberServiceV1Test {

  public static final String MEMBER_A = "memberA";
  public static final String MEMBER_B = "memberB";
  public static final String MEMBER_EX = "ex";

  private MemberRepositoryV1 memberRepository;
  private MemberServiceV1 memberService;

  @BeforeEach
  void before() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
    memberRepository = new MemberRepositoryV1(dataSource);
    memberService = new MemberServiceV1(memberRepository);
  }

  @Test
  @DisplayName("정상 이체")
  void accountTransfer() throws SQLException {
    //given
    Member memberA = new Member(MEMBER_A, 10000);
    Member memberB = new Member(MEMBER_B, 10000);
    memberRepository.save(memberA);
    memberRepository.save(memberB);

    //when
    memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

    //then
    Member findMemberA = memberRepository.findById(memberA.getMemberId());
    Member findMemberB = memberRepository.findById(memberB.getMemberId());
    assertThat(findMemberA.getMoney()).isEqualTo(8000);
    assertThat(findMemberB.getMoney()).isEqualTo(12000);
  }

  @Test
  @DisplayName("이체 중 예외 발생")
  void accountTransferEx() throws SQLException {
    //given
    Member memberA = new Member(MEMBER_A, 10000);
    Member memberEx = new Member(MEMBER_EX, 10000);
    memberRepository.save(memberA);
    memberRepository.save(memberEx);

    //when
    assertThatThrownBy(
        () -> memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(), 2000))
        .isInstanceOf(IllegalStateException.class);

    //then
    Member findMemberA = memberRepository.findById(memberA.getMemberId());
    Member findMemberEx = memberRepository.findById(memberEx.getMemberId());
    assertThat(findMemberA.getMoney()).isEqualTo(8000);
    assertThat(findMemberEx.getMoney()).isEqualTo(10000);
  }

  @AfterEach
  void after() throws SQLException {
    memberRepository.delete(MEMBER_A);
    memberRepository.delete(MEMBER_B);
    memberRepository.delete(MEMBER_EX);
  }

}