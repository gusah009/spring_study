package inflearn.yeonghan.basic2;

import inflearn.yeonghan.basic2.config.AppConfig;
import inflearn.yeonghan.basic2.member.Grade;
import inflearn.yeonghan.basic2.member.Member;
import inflearn.yeonghan.basic2.member.MemberService;
import inflearn.yeonghan.basic2.order.Order;
import inflearn.yeonghan.basic2.order.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class OrderApp {

  public static void main(String[] args) {
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService();
//    OrderService orderService = appConfig.orderService();

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        AppConfig.class);

    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

    long memberId = 1L;

    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 20000);

    System.out.println("order = " + order);
  }
}
