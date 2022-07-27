package valueType;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
// @Setter // 불변 객체로 만들기
public class ValueTypeAddress {

  private String city;
  private String street;
  private String zipcode;
}
