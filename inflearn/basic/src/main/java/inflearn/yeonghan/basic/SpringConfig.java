package inflearn.yeonghan.basic;

import inflearn.yeonghan.basic.repository.MemberRepository;
import inflearn.yeonghan.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }


  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }

  /**
   * 현재 가상의 시나리오로 DB가 선정되지 않았다고 가정했는데, 이 때 구현체인 MemoryMemberRepository가 변경되는 경우, 간단하게
   * DBMemberRepository같은 구현체로 바꿀 수 있기 때문에, Component Scan보다는 java config 설정을 추천한다!!
   */
//  @Bean
//  public MemberRepository memberRepository() {
//    return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }

}
