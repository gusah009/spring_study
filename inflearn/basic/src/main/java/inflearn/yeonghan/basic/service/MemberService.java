package inflearn.yeonghan.basic.service;

import inflearn.yeonghan.basic.domain.Member;
import inflearn.yeonghan.basic.repository.MemberRepository;
import inflearn.yeonghan.basic.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  /**
   * 회원 가입
   */
  public Long join(Member member) {
    // 같은 이름이 있는 중복 회원 X
    validateDuplicateMember(member);

    this.memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  /**
   * 전체 회원 조회
   */
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  /**
   * 특정 회원 조회
   */
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
}
