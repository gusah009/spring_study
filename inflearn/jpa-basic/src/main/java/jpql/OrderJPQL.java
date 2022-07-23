package jpql;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderJPQL {

  @Id
  @GeneratedValue
  private Integer id;
  private int orderAmount;
  @Embedded
  private AddressJPQL address;

  @ManyToOne
  @JoinColumn
  private ProductJPQL productJPQL;
}
