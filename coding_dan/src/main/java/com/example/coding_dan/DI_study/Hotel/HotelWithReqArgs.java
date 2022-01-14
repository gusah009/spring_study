package com.example.coding_dan.DI_study.Hotel;

import com.example.coding_dan.DI_study.Chef;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@ToString
@Getter
// RequiredArgsConstructor가 @NonNull로 표기된 모든 인스턴스 변수를 가져오는 생성자를 만들어 준다.
// 생성자를 만듦과 동시에 Autowired가 묵시적 주입이 되면서 깔끔하게 제작된다.
@RequiredArgsConstructor
public class HotelWithReqArgs {

  // Null이 아닌 생성자 생성에 꼭 필요한 변수임을 나타낸다.
  @NonNull
  private final Chef chef;
}
