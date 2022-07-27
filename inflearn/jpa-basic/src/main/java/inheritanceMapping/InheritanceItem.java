package inheritanceMapping;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Getter
@Setter
public abstract class InheritanceItem extends InheritanceBaseEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private int price;

}
