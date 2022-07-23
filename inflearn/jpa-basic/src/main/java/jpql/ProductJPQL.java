package jpql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductJPQL {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private int price;
  private int sockAmount;
}
