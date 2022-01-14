package com.example.coding_dan.DI_study;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.coding_dan.CodingDanApplication;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// Spring Framework을 실행시켜서 의존성 주입을 하기 위함.
@ExtendWith(SpringExtension.class)
// Component Scan을 하기 위함.
@ContextConfiguration(classes = CodingDanApplication.class)
// DataSource를 사용하지 않는데 들어가기 때문에 제외해 주기 위함.
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@Log4j2
public class DI_RestaurantTest {

  @Setter(onMethod_ = @Autowired)
  private Restaurant restaurant;

  @Test
  @DisplayName("Restaurant Autowired 테스트")
  public void testExist() {
    assertNotNull(restaurant);
    log.info(restaurant);
    log.info("---------------------------");
    log.info(restaurant.getChef());
  }
}
