package inflearn.yeonghan.basic2.config;

import inflearn.yeonghan.basic2.discount.DiscountPolicy;
import inflearn.yeonghan.basic2.discount.RateDiscountPolicy;
import inflearn.yeonghan.basic2.member.MemberRepository;
import inflearn.yeonghan.basic2.member.MemberService;
import inflearn.yeonghan.basic2.member.MemberServiceImpl;
import inflearn.yeonghan.basic2.member.MemoryMemberRepository;
import inflearn.yeonghan.basic2.order.OrderService;
import inflearn.yeonghan.basic2.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  @Bean
  public MemberService memberService() {
    System.out.println("call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("call AppConfig.orderService");
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }
}
