package com.example.coding_dan.mybatis;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class TimeMapperTest {

  @Setter(onMethod_ = @Autowired)
  private TimeMapper timeMapper;

  @Test
  @DisplayName("@SELECT Annotation 테스트")
  public void testGetTimeWithAnnotation() {
//    // 분명히 TimeMapper는 인터페이스로 만들어 줬는데 알아서 적당한 객체가 들어가고 있음을 확인할 수 있습니다.
//    log.info(timeMapper.getClass().getName());
//    log.info(timeMapper.getTimeWithSelectAnnotation());

    // log4jdbc-log4j2 덕분에 이제 log.info()를 따로 찍어주지 않아도 알아서 log를 남깁니다.
    timeMapper.getTimeWithSelectAnnotation();
  }

  @Test
  @DisplayName("xml mapper 테스트")
  public void testGetTimeWithXML() {

//    // 분명히 TimeMapper는 인터페이스로 만들어 줬는데 알아서 적당한 객체가 들어가고 있음을 확인할 수 있습니다.
//    log.info(timeMapper.getClass().getName());
//    log.info(timeMapper.getTimeWithNoAnnotation());

    // log4jdbc-log4j2 덕분에 이제 log.info()를 따로 찍어주지 않아도 알아서 log를 남깁니다.
    timeMapper.getTimeWithNoAnnotation();
  }
}