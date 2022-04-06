package inflearn.yeonghan.basic2.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import inflearn.yeonghan.basic2.config.AutoAppConfig;
import inflearn.yeonghan.basic2.discount.DiscountPolicy;
import inflearn.yeonghan.basic2.member.Grade;
import inflearn.yeonghan.basic2.member.Member;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {

  @Test
  void findAllBean() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
        AutoAppConfig.class, DiscountService.class);

    DiscountService discountService = ac.getBean(DiscountService.class);
    Member member = new Member(1L, "userA", Grade.VIP);
    int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

    assertThat(discountService).isInstanceOf(DiscountService.class);
    assertThat(discountPrice).isEqualTo(1000L);

    int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
    assertThat(rateDiscountPrice).isEqualTo(2000L);

  }

  static class DiscountService {

    private final Map<String, DiscountPolicy> policyMap;
    private final List<DiscountPolicy> policyList;

    public DiscountService(
        Map<String, DiscountPolicy> policyMap,
        List<DiscountPolicy> policyList) {
      this.policyMap = policyMap;
      this.policyList = policyList;
      System.out.println("policyMap = " + policyMap);
      System.out.println("policyList = " + policyList);
    }

    public int discount(Member member, int price, String discountCode) {
      DiscountPolicy discountPolicy = policyMap.get(discountCode);
      return discountPolicy.discount(member, price);
    }
  }


}
