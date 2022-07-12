package inheritanceMapping;

import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class InheritanceAlbum extends InheritanceItem {

  private String artist;
  private int page;
}
