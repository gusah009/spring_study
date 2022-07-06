package associationMapping;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class AssoicationTeam {

  @Id
  @GeneratedValue
  @Column(name = "TEAM_ID")
  private Long id;
  private String name;

  @OneToMany(mappedBy = "team")
  private List<AssoicationMember> members = new ArrayList<>();
}
