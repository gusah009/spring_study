package jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;
  private String name;
  private String city;
  private String street;
  private String zipcode;

  @OneToMany(mappedBy = "member")
  List<Order> orders = new ArrayList<>();

  public void addOrders(Order order) {
    orders.add(order);
    order.setMember(this);
  }
}
