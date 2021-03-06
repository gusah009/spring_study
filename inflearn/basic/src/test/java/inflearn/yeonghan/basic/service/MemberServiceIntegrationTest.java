package inflearn.yeonghan.basic.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import inflearn.yeonghan.basic.domain.Member;
import inflearn.yeonghan.basic.repository.MemberRepository;
import inflearn.yeonghan.basic.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberRepository memberRepository;

  @Test
  void 회원가입() {
    // given
    Member member = new Member();
    member.setName("hello");

    // when
    Long saveId = memberService.join(member);

    // then
    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  void 중복_회원_예외() {
    // given
    Member member1 = new Member();
    member1.setName("duplicate_spring");

    Member member2 = new Member();
    member2.setName("duplicate_spring");

    // when
    memberService.join(member1);

    // then
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
  }

  @Test
  void 전체회원조회() {
  }

  @Test
  void 특정회원조회() {
  }
}