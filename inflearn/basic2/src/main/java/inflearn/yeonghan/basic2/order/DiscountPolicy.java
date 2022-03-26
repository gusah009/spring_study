package inflearn.yeonghan.basic2.order;

import inflearn.yeonghan.basic2.member.Member;

public interface DiscountPolicy {

  /**
   * @return 할인 대상 금액
   */
  int discount(Member member, int price);
}
