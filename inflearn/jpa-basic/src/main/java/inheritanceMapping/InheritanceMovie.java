package inheritanceMapping;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InheritanceMovie extends InheritanceItem {

  private String director;
  private String actor;

}
