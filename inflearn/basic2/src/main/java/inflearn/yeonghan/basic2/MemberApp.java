package inflearn.yeonghan.basic2;

import inflearn.yeonghan.basic2.config.AppConfig;
import inflearn.yeonghan.basic2.member.Grade;
import inflearn.yeonghan.basic2.member.Member;
import inflearn.yeonghan.basic2.member.MemberService;
import inflearn.yeonghan.basic2.member.MemberServiceImpl;
import inflearn.yeonghan.basic2.order.OrderService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

  public static void main(String[] args) {
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = new MemberServiceImpl();
//    MemberService memberService = appConfig.memberService();

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
        AppConfig.class);
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(1L);
    System.out.println("new Member = " + member.getName());
    System.out.println("find Member = " + findMember.getName());
  }
}
