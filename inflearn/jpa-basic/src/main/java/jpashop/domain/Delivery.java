package jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class Delivery extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String city;
  private String street;
  private String zipcode;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // 주문상태 [ORDER, CANCEL]

  @OneToOne(mappedBy = "delivery")
  private Order order;
}
