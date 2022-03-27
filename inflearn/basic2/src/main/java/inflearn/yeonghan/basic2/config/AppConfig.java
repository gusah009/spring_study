package inflearn.yeonghan.basic2.config;

import inflearn.yeonghan.basic2.discount.DiscountPolicy;
import inflearn.yeonghan.basic2.discount.RateDiscountPolicy;
import inflearn.yeonghan.basic2.member.MemberRepository;
import inflearn.yeonghan.basic2.member.MemberService;
import inflearn.yeonghan.basic2.member.MemberServiceImpl;
import inflearn.yeonghan.basic2.member.MemoryMemberRepository;
import inflearn.yeonghan.basic2.order.OrderService;
import inflearn.yeonghan.basic2.order.OrderServiceImpl;

public class AppConfig {

  public MemberService memberService() {
    return new MemberServiceImpl(memberRepository());
  }

  private MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

  public OrderService orderService() {
    return new OrderServiceImpl(
        memberRepository(),
        discountPolicy());
  }

  private DiscountPolicy discountPolicy() {
    return new RateDiscountPolicy();
  }
}
