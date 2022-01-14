package com.example.coding_dan.DI_study.Hotel;

import com.example.coding_dan.DI_study.Chef;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
public class Hotel {

  private final Chef chef;

  // Autowired 어노테이션이 없어도 알아서 주입이 들어가고 있다.
  // 실제로 테스트해보면 에러가 나지 않는다.
  public Hotel(Chef chef) {
    this.chef = chef;
  }
}
