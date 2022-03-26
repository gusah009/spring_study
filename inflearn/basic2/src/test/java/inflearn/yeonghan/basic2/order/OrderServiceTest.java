package inflearn.yeonghan.basic2.order;

import static org.junit.jupiter.api.Assertions.*;

import inflearn.yeonghan.basic2.member.Grade;
import inflearn.yeonghan.basic2.member.Member;
import inflearn.yeonghan.basic2.member.MemberService;
import inflearn.yeonghan.basic2.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

  MemberService memberService = new MemberServiceImpl();
  OrderService orderService = new OrderServiceImpl();

  @Test
  void createOrder() {
    long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);
    Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
  }
}