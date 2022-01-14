package com.example.coding_dan.DI_study.Hotel;

import com.example.coding_dan.DI_study.Chef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@Component
@ToString
@Getter
// AllArgsConstructor가 모든 인스턴스 변수를 가져오는 생성자를 만들어 준다.
// 생성자를 만듦과 동시에 Autowired가 묵시적 주입이 되면서 깔끔하게 제작된다.
@AllArgsConstructor
public class HotelWithAllArgs {

  private final Chef chef;
}
