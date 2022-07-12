package inheritanceMapping;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InheritanceBook extends InheritanceItem {

  private String author;
  private String isbn;
  private int page;
}
