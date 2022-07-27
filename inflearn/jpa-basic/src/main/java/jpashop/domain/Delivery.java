package jpashop.domain;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Embedded;
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

  @Embedded
  private Address address;

  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; // 주문상태 [ORDER, CANCEL]

  @OneToOne(mappedBy = "delivery", fetch = LAZY)
  private Order order;
}
