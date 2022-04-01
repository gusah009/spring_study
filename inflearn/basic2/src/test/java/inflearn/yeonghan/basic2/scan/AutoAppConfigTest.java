package inflearn.yeonghan.basic2.scan;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import inflearn.yeonghan.basic2.config.AutoAppConfig;
import inflearn.yeonghan.basic2.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

  @Test
  void basicScan() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    MemberService memberService = ac.getBean(MemberService.class);
    assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
