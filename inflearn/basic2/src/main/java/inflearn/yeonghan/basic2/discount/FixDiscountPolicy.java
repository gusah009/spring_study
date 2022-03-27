package inflearn.yeonghan.basic2.discount;

import inflearn.yeonghan.basic2.member.Grade;
import inflearn.yeonghan.basic2.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

  private final int discountFixAmount = 1000;

  @Override
  public int discount(Member member, int price) {
    if (member.getGrade() == Grade.VIP) {
      return discountFixAmount;
    } else {
      return 0;
    }
  }
}
