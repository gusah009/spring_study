package jpashop.domain;

import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@EqualsAndHashCode
public class Address {

  private String city;
  private String street;
  private String zipcode;
}
