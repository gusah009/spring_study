package com.example.coding_dan.DI_study;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.coding_dan.CodingDanApplication;
import com.example.coding_dan.DI_study.Hotel.Hotel;
import com.example.coding_dan.DI_study.Hotel.HotelWithAllArgs;
import com.example.coding_dan.DI_study.Hotel.HotelWithReqArgs;
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
public class DI_HotelTest {

  @Setter(onMethod_ = @Autowired)
  private Hotel hotel;

  @Setter(onMethod_ = @Autowired)
  private HotelWithAllArgs hotelWithAllArgs;

  @Setter(onMethod_ = @Autowired)
  private HotelWithReqArgs hotelWithReqArgs;

  @Test
  @DisplayName("Autowired 묵시적 주입 테스트")
  public void testExist() {
    assertNotNull(hotel);

    log.info(hotel);
    log.info("---------------------------");
    log.info(hotel.getChef());
  }

  @Test
  @DisplayName("AllArgsConstructor 테스트")
  public void testAllArgs() {
    assertNotNull(hotelWithAllArgs);

    log.info(hotelWithAllArgs);
    log.info("---------------------------");
    log.info(hotelWithAllArgs.getChef());
  }

  @Test
  @DisplayName("RequiredArgsConstructor 테스트")
  public void testReqArgs() {
    assertNotNull(hotelWithReqArgs);

    log.info(hotelWithReqArgs);
    log.info("---------------------------");
    log.info(hotelWithReqArgs.getChef());
  }
}
