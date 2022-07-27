package jpql;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamJPQL {

  @Id
  @GeneratedValue
  private Integer id;
  private String name;

  @OneToMany
  private List<MemberJPQL> memberList = new ArrayList<>();

}
