package com.example.jdbc.service;

import com.example.jdbc.domain.Member;
import com.example.jdbc.repository.MemberRepositoryV1;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberServiceV1 {

  private final MemberRepositoryV1 memberRepository;

  public void accountTransfer(String fromId, String toId, int money) throws SQLException {
    Member fromMember = memberRepository.findById(fromId);
    Member toMember = memberRepository.findById(toId);

    memberRepository.update(fromId, fromMember.getMoney() - money);
    validation(toMember);
    memberRepository.update(toId, toMember.getMoney() + money);
  }

  private static void validation(Member toMember) {
    if (toMember.getMemberId().equals("ex")) {
      throw new IllegalStateException("이체 중 예외 발생");
    }
  }

}
